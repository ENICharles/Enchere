package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
import fr.eni.enchere.bo.EtatVente;

import fr.eni.enchere.serveur.SchedulerTask;

/**
 * Servlet implementation class listeEncheres
 */

//@WebServlet(urlPatterns = {"/accueil"})
@WebServlet(urlPatterns = {"","/accueil"})
public class listeEncheres extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		/* lancement du scheduleur */
		@SuppressWarnings("unused")
		SchedulerTask sc = SchedulerTask.getSchedulerTask();
	}
	
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
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EnchereManager 		enchereMng 		= EnchereFactory.getManager();
		List<Categorie> 	lstCategories 	= null;
		List<ArticleVendu> 	lstArticles   	= null;
		
		try
		{
			Integer idCategorie = 0 ; 
			String  rechercher  = "";
			
			/* configuration du filtre de recherche */
			if(request.getParameter("categorie") != null)
			{
				idCategorie = Integer.parseInt(request.getParameter("categorie"));
			}			
			if(request.getParameter("articleToFind") != null)
			{
				rechercher = (String)request.getParameter("articleToFind");
			}
						
			lstCategories 	= enchereMng.getCategories();
			lstArticles 	= enchereMng.getArticleVendus(0,"EN_COURS",idCategorie,rechercher);
			
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
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/test.jsp");
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
