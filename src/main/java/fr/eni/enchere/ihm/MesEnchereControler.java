package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.enchere.EnchereFactory;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class MesEnchereControler
 */
@WebServlet("/MesEncheres")
public class MesEnchereControler extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MesEnchereControler()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher 	rd 				= null;
		EnchereManager 		enchereMng 		= EnchereFactory.getManager();
		List<Categorie> 	lstCategories 	= null; 
		List<ArticleVendu> 	lstArticles		= null;
		Utilisateur 		user 			= null;
		int 				idArticle 		= -1;
		
		if(request.getSession().getAttribute("article")!=null)
		{
			request.getSession().removeAttribute("article");
		}
		
		if(request.getSession().getAttribute("utilisateur")!=null)
		{
			user = (Utilisateur)request.getSession().getAttribute("utilisateur");
		}

		if (request.getParameter("idArticle") != null)
		{
			idArticle = Integer.parseInt(request.getParameter("idArticle"));
			try
			{
				enchereMng.createEnchere(user.getNoUtilisateur(), idArticle, 100);
			}
			catch (BllException e)
			{
				request.setAttribute("erreur",
						"erreur sur la cr�ation de l'ench�re (" + user.getNom() + ")" + e.getMessage());
			}
		}

		try
		{
			lstCategories 	= enchereMng.getCategories();
			lstArticles 	= enchereMng.getArticleVendus(user.getNoUtilisateur(), "Toutes", 0, "");

			request.setAttribute("lstCategories", lstCategories);
			request.setAttribute("lstArticles", lstArticles);

			request.setAttribute("utilisateur", user);

			rd = request.getRequestDispatcher("/WEB-INF/vues/MesEncheresVue.jsp");
			rd.forward(request, response);
		}
		catch (BllException e)
		{
			/* retour sur la JSP pour afficher le message d'erreur */
			request.setAttribute("erreur : ", e.getMessage());

			rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		EnchereManager 	enchereMng 	= EnchereFactory.getManager();

		int 	idCategorie = 0;
		int 	userFiltre 	= 0;
		String 	rechercher 	= "";

		if (request.getParameter("userFiltre") != null)
		{
			userFiltre = Integer.parseInt(request.getParameter("userFiltre"));
		}
		if (request.getParameter("categorie") != null)
		{
			idCategorie = Integer.parseInt(request.getParameter("categorie"));
		}
		if (request.getParameter("articleToFind") != null)
		{
			rechercher = (String) request.getParameter("articleToFind");
		}

		//TODO : v�rifier que l'utilisateur existe en session (s'il n'existe pas -> page de connexion)
		try
		{
			List<Categorie> 	lstCategories 	= enchereMng.getCategories();
			List<ArticleVendu> 	lstArticles 	= enchereMng.getArticleVendus(userFiltre, "Toutes", idCategorie,rechercher);

			request.setAttribute("lstCategories", lstCategories);
			request.setAttribute("lstArticles", lstArticles);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/MesEncheresVue.jsp");
			rd.forward(request, response);
		}
		catch (BllException e)
		{
			/* retour sur la JSP pour afficher le message d'erreur */
			request.setAttribute("erreur : ", e.getMessage());

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
			rd.forward(request, response);
		}
	}
}
