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
 * Servlet implementation class listeEncheres
 */
//@WebServlet("/accueil")
@WebServlet(urlPatterns = {"","/accueil"})
public class listeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listeEncheres() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EnchereManager 	enchereMng 	= EnchereFactory.getManager();
		UserManager 	userMng 	= UserFactory.getManager();		
		Utilisateur 	user		= null;
				
		int idArticle	= -1;
		
		if(request.getParameter("idArticle") != null)
		{
			System.out.println("article " +(String)request.getParameter("idArticle"));
			
			idArticle = Integer.parseInt(request.getParameter("idArticle"));
			try
			{	
				user		= userMng.getUtilisateur("Dodo2","1232");
				
				System.out.println("---------------------------------------");
				System.out.println("-  " + user.getNom() +" ralise une enchère");
				System.out.println("---------------------------------------");
				
				enchereMng.createEnchere(user.getNoUtilisateur(), idArticle, 100);
			}
			catch (BllException e)
			{
				request.setAttribute("erreur","erreur sur la création de l'enchère (" + user.getNom() + ")" + e.getMessage());
			}
		}
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EnchereManager 	enchereMng = EnchereFactory.getManager();
		
		List<Categorie> 	lstCategories = null;
		List<ArticleVendu> 	lstArticles   = null;
		
		
		if(request.getAttribute("utilisateur") != null)
		{
			request.setAttribute("utilisateur",(String)request.getAttribute("utilisateur"));
		}
		
		try
		{
			Integer idCategorie = 0 ; 
			String  rechercher  = "";
			
			if(request.getParameter("categorie") != null)
			{
				System.out.println("cat " + request.getParameter("categorie"));
				idCategorie = Integer.parseInt(request.getParameter("categorie"));
			}			
			if(request.getParameter("articleToFind") != null)
			{
				System.out.println("article " + request.getParameter("articleToFind"));
				rechercher = (String)request.getParameter("articleToFind");
			}
						
			lstCategories 	= enchereMng.getCategories();
			lstArticles 	= enchereMng.getArticleVendus(0,"Toutes",idCategorie,rechercher);
			
			request.setAttribute("lstCategories",lstCategories);
			request.setAttribute("lstArticles",lstArticles);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/ListeDesEncheres.jsp");
			rd.forward(request, response);
		}
		catch (BllException e)
		{
			try
			{
				lstCategories 	= enchereMng.getCategories();
				lstArticles 	= enchereMng.getArticleVendus(0,"Toutes",0,"");
			
				request.setAttribute("erreur",e.getMessage());
				request.setAttribute("lstCategories",lstCategories);
				request.setAttribute("lstArticles",lstArticles);
				
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/ListeDesEncheres.jsp");
				rd.forward(request, response);
			}
			catch (BllException e1)
			{
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/ListeDesEncheres.jsp");
				rd.forward(request, response);
			}
		}
	}

}
