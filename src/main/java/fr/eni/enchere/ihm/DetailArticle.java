package fr.eni.enchere.ihm;

import java.io.IOException;

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

/**
 * Servlet implementation class DetailArticle
 */
@WebServlet("/DetailArticle")
public class DetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailArticle() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("détail get");
		RequestDispatcher 	rd 			= null;
		EnchereManager 		enchereMng 	= EnchereFactory.getManager();
		ArticleVendu		article		= null;
		
		if((request.getSession().getAttribute("utilisateur") != null) && (request.getParameter("idArticle") != null))
		{
			try
			{
				article = enchereMng.getArticleVendus(Integer.valueOf((String)request.getParameter("idArticle")));
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
				request.setAttribute("erreur", "Pb detail " + e.getMessage());
			}
			catch (BllException e)
			{
				e.printStackTrace();
				request.setAttribute("erreur", "Pb detail " + e.getMessage());
			}
			
			//request.setAttribute("article", article);
			request.getSession().setAttribute("article", article);
			
			
			rd = request.getRequestDispatcher("/WEB-INF/vues/DetailArticleVue.jsp");
		}
		else
		{
			rd = request.getRequestDispatcher("login");
		}
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("détail post");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/DetailArticleVue.jsp");
		rd.forward(request, response);
	}

}
