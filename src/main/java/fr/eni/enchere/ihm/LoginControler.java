package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.user.UserFactory;
import fr.eni.enchere.bll.enchere.EnchereFactory;
import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.UserManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class Controler
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControler() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getSession().removeAttribute("utilisateur");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		String 			identifiant = "";
		String 		 	password 	= "";
		Utilisateur		utilisateur	= null;
		UserManager  	manager		= (UserManager) UserFactory.getManager();
		
		try
		{
			controleInformation((String)request.getParameter("identifiant"),(String)request.getParameter("password"));
			
			identifiant = (String)request.getParameter("identifiant");
			password 	= (String)request.getParameter("password");
			
			/* interogation de la DAL pour savoir si l'utilisateur est connu */
			try
			{
				utilisateur = manager.getUtilisateur(identifiant,password);
			}
			catch (BllException e)
			{
				throw new IhmExeception(e.getMessage());
			} 
			
			/* redirection vers un controleur d'une autre VU ou directement sur une nouvelle JSP si connu, sinon */
			if(utilisateur == null)
			{
				/* retour sur la JSP pour afficher le message d'erreur que l'utilisateur est inconnu ou 
				 * le couple mdp etlogin sont erron� */
				request.setAttribute("erreur","Login et/ou mot de passe erron�s");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
				rd.forward(request, response);
			}
			else
			{
				/* création d'une session */
				request.getSession().setAttribute("utilisateur", utilisateur);
				
				EnchereManager  mng 	= EnchereFactory.getManager();
				try
				{
					List<Categorie> 	lstCategories 	= mng.getCategories();
					List<ArticleVendu> 	lstArticles 	= mng.getArticleVendus(utilisateur.getNoUtilisateur(),"Toutes",0,"");
					
					request.setAttribute("lstCategories",lstCategories);
					request.setAttribute("lstArticles",lstArticles);					
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/MesEncheresVue.jsp");
					rd.forward(request, response);
				}
				catch (BllException e)
				{
					/* retour sur la JSP pour afficher le message d'erreur */
					request.setAttribute("erreur : ",e.getMessage());
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
					rd.forward(request, response);
				}
			}
		}
		catch (IhmExeception e)
		{
			/* retour sur la JSP pour afficher le message d'erreur */
			request.setAttribute("erreur",e.getMessage());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
			rd.forward(request, response);
		}
	}
	
	/**
	 * V�rifie que les information sont conformes
	 * @param id
	 * @return
	 * @throws IhmExeception 
	 * TODO : ajouter des v�rificxations compl�mentaires
	 */
	private void controleInformation(String id,String pswd) throws IhmExeception
	{
		String message 	= "";
		
		/* v�rification de l'absence de chaine vide */
		if(id.equals("") == true)
		{
			message = "Identifiant/adresse mail vide interdite (" + id + ")  ";
		}
		
		/* v�rification de l'absence de chaine vide */
		if(pswd.equals("") == true)
		{
			message += "Mot de passe vide interdit(" + pswd + ")";
		}
		
		/* v�rifiaction de l'absence de message d'erreur */
		if(message.equals("") != true)
		{
			throw new IhmExeception(message);
		}
	}
}
