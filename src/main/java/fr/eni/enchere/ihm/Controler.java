package fr.eni.enchere.ihm;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BllManager;
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
        // TODO Auto-generated constructor stub
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
		
		String 		identifiant = "";
		String 		password 	= "";
		Utilisateur	utilisateur	= null;
		BllManager  manager		= new BllManager();
		
		try
		{
			controleInformation((String)request.getAttribute("identifiant"),(String)request.getAttribute("password"));
			
			identifiant = (String)request.getAttribute("identifiant");
			password 	= (String)request.getAttribute("password");
			
			utilisateur = manager.getUtilisateur(identifiant,password); 
		}
		catch (IhmExeception e)
		{
			//retour sur la JSP pour afficher le message d'erreur
			request.setAttribute("erreur",e.getMessage());
		}
	}
	
	/**
	 * Vérifie que les information sont conformes
	 * @param id
	 * @return
	 * @throws IhmExeception 
	 */
	private void controleInformation(String id,String pswd) throws IhmExeception
	{
		String message 	= "";
		
		if(id == null)
		{
			message = "Identifiant/adresse mail invalide " + id;
		}
		
		if(pswd == null)
		{
			message += " Mot de passe invalide " + pswd;
		}
		
		if(message.equals("") == true)
		{
			System.out.println("oups pb");
			throw new IhmExeception(message);
		}
	}
}
