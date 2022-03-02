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
import fr.eni.enchere.bll.UserManager;
import fr.eni.enchere.bll.enchere.EnchereFactory;
import fr.eni.enchere.bll.user.UserFactory;
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
		EnchereManager enchereMng = EnchereFactory.getManager();
		UserManager userMng = UserFactory.getManager();
		Utilisateur user = null;
		int numUser = 0;

		if (request.getParameter("numUser") != null)
		{
			numUser = Integer.parseInt((String) request.getParameter("numUser"));
			try
			{
				user = userMng.getUtilisateurID(numUser);
			}
			catch (BllException e)
			{
				request.setAttribute("erreur",
						"erreur sur la récupération de l'utilisateur (" + numUser + ")" + e.getMessage());
			}

			request.setAttribute("numUser", numUser);
		}

		int idArticle = -1;

		if (request.getParameter("idArticle") != null)
		{
			System.out.println("article " + (String) request.getParameter("idArticle"));

			idArticle = Integer.parseInt(request.getParameter("idArticle"));
			try
			{
				enchereMng.createEnchere(user.getNoUtilisateur(), idArticle, 100);
			}
			catch (BllException e)
			{
				request.setAttribute("erreur",
						"erreur sur la création de l'enchère (" + user.getNom() + ")" + e.getMessage());
			}
		}

		request.setAttribute("utilisateur", user);

		EnchereManager mng = EnchereFactory.getManager();
		try
		{
			List<Categorie> lstCategories = mng.getCategories();
			List<ArticleVendu> lstArticles = mng.getArticleVendus(user.getNoUtilisateur(), "Toutes", 0, "");

			request.setAttribute("lstCategories", lstCategories);
			request.setAttribute("lstArticles", lstArticles);

			request.setAttribute("utilisateur", user);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/MesEncheresVue.jsp");
		}
		catch (BllException e)
		{
			/* retour sur la JSP pour afficher le message d'erreur */
			request.setAttribute("erreur : ", e.getMessage());

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
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
		//UserManager 	userMng 	= UserFactory.getManager();
		//Utilisateur 	user 		= (Utilisateur) request.getSession().getAttribute("utilisateur");

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

		//TODO : vérifier que l'utilisateur existe en session (s'il n'existe pas -> page de connexion)
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
