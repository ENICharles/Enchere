package fr.eni.enchere.ihm;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.UserManager;
import fr.eni.enchere.bll.user.UserFactory;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println(request.getSession().getAttribute("utilisateur"));
		
		Utilisateur user = (Utilisateur)request.getSession().getAttribute("utilisateur");
		
		System.out.println(user.getNom());
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/UpdateProfile.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utilisateur user = null;
		
		UserManager	mng =  UserFactory.getManager();
		
		if(request.getSession().getAttribute("utilisateur") != null)
		{
			user = (Utilisateur)request.getSession().getAttribute("utilisateur");
			
			user.setPseudo((String)request.getParameter("pseudo"));
			user.setNom((String)request.getParameter("prenom"));
			user.setPrenom((String)request.getParameter("nom"));
			user.setVille((String)request.getParameter("ville"));
			user.setRue((String)request.getParameter("rue"));
			
			try
			{
				mng.updateUtilisateur(user);
			}
			catch (BllException e)
			{
				request.setAttribute("erreur","Problème sur la mise à jour de l'utilisateu" + e.getMessage());
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/UpdateProfile.jsp");
		rd.forward(request, response);
	}
}
