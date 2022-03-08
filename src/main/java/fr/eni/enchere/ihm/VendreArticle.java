package fr.eni.enchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.enchere.EnchereFactory;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class VendreArticle
 */
@WebServlet(urlPatterns = { "/VendreArticle" })
public class VendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String IMAGES_FOLDER = "D:\\Projects\\Java\\GIT\\Enchere\\src\\main\\webapp\\images";
	public String uploadPath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendreArticle() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("get vendre article ");
		EnchereManager mng = EnchereFactory.getManager();

		Utilisateur user = (Utilisateur)request.getSession().getAttribute("utilisateur");
		
		List<Categorie> lstCategories = null;
		try 
		{
			lstCategories = mng.getCategories();
			
		} 
		catch (BllException e) 
		{
			e.printStackTrace();
		}

		request.setAttribute("dateD", LocalDate.now());
		request.setAttribute("dateF", LocalDate.now().plusDays(1));
		
		request.setAttribute("lstCategories", lstCategories);		
		request.setAttribute("utilisateur", user);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/VendreArticleVue.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EnchereManager 	mng = EnchereFactory.getManager();
		List<Categorie> lstCategories = null;
		Utilisateur 	user = (Utilisateur)request.getSession().getAttribute("utilisateur");
		try 
		{
			lstCategories = mng.getCategories();
			
		} 
		catch (BllException e) 
		{
			request.setAttribute("erreur : ", e.getMessage());
		}
				
		if(((request.getParameter("nom") != null) && (request.getParameter("categorie") != null) && (request.getParameter("description") != null) && (request.getParameter("miseaprix") != null) && (request.getParameter("rue") != null) && (request.getParameter("codepostal") != null) && (request.getParameter("ville")) != null))
		{			
			ArticleVendu art = new ArticleVendu((String)request.getParameter("nom"),
												(String)request.getParameter("description"),
												LocalDate.parse(request.getParameter("dateDebut")),
												LocalDate.parse(request.getParameter("dateFin")),
												Integer.valueOf(request.getParameter("miseaprix")),
												0,
												EtatVente.CREE,
												lstCategories.get(Integer.valueOf(request.getParameter("categorie"))),
																new Retrait(
																	(String)request.getParameter("rue"),
																	(String)request.getParameter("codepostal"),
																	(String)request.getParameter("ville")));
			
			try 
			{
				mng.createArticle(user.getNoUtilisateur(), art);
			} 
			catch (BllException e) 
			{
				request.setAttribute("erreur : ", e.getMessage());				
			}
		}
		
		doGet(request,response);
	}
}
