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
 * Servlet implementation class CreaCompteControler
 */
@WebServlet(urlPatterns = {"/creation"})
public class CreaCompteControler extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaCompteControler() 
    {
        super();
    }

  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/MonProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			
		if(request.getParameter("btn") != null)
		{
			System.out.println((String)request.getParameter("btn"));
		}
		
		
		try
		{
			Utilisateur utilisateur = controleInformation(
									(String)request.getParameter("pseudo"),(String)request.getParameter("nom"),
									(String)request.getParameter("prenom"),(String)request.getParameter("email"),
									(String)request.getParameter("telephone"),(String)request.getParameter("rue"),
									(String)request.getParameter("codePostal"),(String)request.getParameter("ville"),
									(String)request.getParameter("password"),(String)request.getParameter("confirmMdp"));
			 
			UserManager	mng =  UserFactory.getManager();
	
			utilisateur.setNoUtilisateur(4);
			mng.createUtilisateur(
					(String)request.getParameter("pseudo"),(String)request.getParameter("nom"),
					(String)request.getParameter("prenom"),(String)request.getParameter("email"),
					(String)request.getParameter("telephone"),(String)request.getParameter("rue"),
					(String)request.getParameter("codePostal"),(String)request.getParameter("ville"),
					(String)request.getParameter("password"),(String)request.getParameter("confirmMdp"));
			
			response.sendRedirect("login");
		}
		catch (IhmExeception e) 
		{
			// Afficher message d'erreur
			request.setAttribute("erreur",e.getMessage());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/MonProfil.jsp");
			rd.forward(request, response);
		}
		catch (BllException e)
		{
			// Afficher message d'erreur
			request.setAttribute("erreur",e.getMessage());
			
			RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/vues/MonProfil.jsp");
			rd2.forward(request, response);
		}
	}



	//V?rification des infos utilisateur => Fonctions de controle
	/**
	 * V?rifie que les information sont conformes
	 * @param id
	 * @return
	 * @throws IhmExeception 
	 * TODO : ajouter des v?rificxations compl?mentaires
	 */
	private Utilisateur controleInformation(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String password, String confirmMdp) throws IhmExeception
	{
		
		Utilisateur utilisateur = new Utilisateur();
		String 		message 	= "";
		
		/* v?rification de l'absence de chaine vide Pseudo */
		if(pseudo.equals("") == true)
		{
			message = "Veuillez renseigner un Pseudo";
		}
		else
		{
			utilisateur.setPseudo(pseudo);
		}
		
		/* v?rification de l'absence de chaine vide Nom */
		if(nom.equals("") == true)
		{
			message = "Veuillez renseigner votre Nom";
		}else {
			utilisateur.setNom(nom);
		}
		
		/* v?rifiaction de l'absence de chaine vide Pr?nom */
		if(prenom.equals("") == true)
		{
			message = "Veuillez renseigner votre Pr?nom";
		}else {
			utilisateur.setPrenom(prenom);
		}
		
		/* v?rification de l'absence de chaine vide Email */
		if(email.equals("") == true)
		{
			message = "Veuillez renseigner votre Email";
		}else {
			utilisateur.setEmail(email);
		}
		
		/* v?rification de l'absence de chaine vide T?l?phone */
		if(telephone.equals("") == true)
		{
			message = "Veuillez renseigner votre num?ro de T?l?phone";
		}else {
			utilisateur.setTelephone(telephone);
		}
		
		/* v?rifiaction de l'absence de chaine vide Rue */
		if(rue.equals("") == true)
		{
			message = "Veuillez renseigner la rue de votre adresse postale";
		}else {
			utilisateur.setRue(rue);
		}
		
		
		/* v?rification de l'absence de chaine vide Code Postal */
		if(codePostal.equals("") == true)
		{
			message = "Veuillez renseigner votre Code Postal";
		}else {
			utilisateur.setCodePostal(codePostal);
		}
		
		/* v?rification de l'absence de chaine vide Ville */
		if(ville.equals("") == true)
		{
			message = "Veuillez renseigner votre Ville";
		}else {
			utilisateur.setVille(ville);
		}
				
		/* v?rification de l'absence de chaine vide Mot de passe */
		if(password.equals(confirmMdp) == false)
		{
			message += "Mot de passe vide interdit(" + password + ")";
		}else {
			utilisateur.setMotDePasse(password);
		}
		
		/* v?rifiaction de l'absence de message d'erreur */
		if(message.equals("") != true)
		{
			throw new IhmExeception(message);
		}
		
		// Envoi du nouvel utilisateur ?? la BLL => Instanciation
		return utilisateur;
	}

}
