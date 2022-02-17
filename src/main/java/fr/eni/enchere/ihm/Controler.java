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
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class Controler
 */
@WebServlet(urlPatterns = {"", "/Controler"})
public class Controler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controler() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("post");
		
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
				 * le couple mdp etlogin sont erroné */
				request.setAttribute("erreur","Login et/ou mot de passe erronés");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
				rd.forward(request, response);
			}
			else
			{
				System.out.println("Bienvenue "+ utilisateur.getNom() + " " + utilisateur.getPrenom());
				/* TODO : redirection vers une autre VU (controler ou jsp) */
				//UserManager  	manager	= (UserManager) UserFactory.getManager();
				EnchereManager  mng 	= EnchereFactory.getManager();
				try
				{
					List<Enchere> lst = mng.getEncheres(0);
					request.setAttribute("erreur","cool");
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
					rd.forward(request, response);
				}
				catch (BllException e)
				{
					/* retour sur la JSP pour afficher le message d'erreur */
					request.setAttribute("erreur",e.getMessage());
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueLogin.jsp");
					rd.forward(request, response);
				}
				//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/vueUtilisateurSommaire.jsp");
				//rd.forward(request, response);
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
	 * Vérifie que les information sont conformes
	 * @param id
	 * @return
	 * @throws IhmExeception 
	 * TODO : ajouter des vérificxations complémentaires
	 */
	private void controleInformation(String id,String pswd) throws IhmExeception
	{
		String message 	= "";
		
		/* vérification de l'absence de chaine vide */
		if(id.equals("") == true)
		{
			message = "Identifiant/adresse mail vide interdite (" + id + ")  ";
		}
		
		/* vérification de l'absence de chaine vide */
		if(pswd.equals("") == true)
		{
			message += "Mot de passe vide interdit(" + pswd + ")";
		}
		
		/* vérifiaction de l'absence de message d'erreur */
		if(message.equals("") != true)
		{
			throw new IhmExeception(message);
		}
	}
}
