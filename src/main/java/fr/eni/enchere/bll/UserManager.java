package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;

public interface UserManager
{
	public Utilisateur 	getUtilisateur	 (String identifiant,String password) 	throws BllException;
	public Utilisateur  getUtilisateur	 (Utilisateur utilisateur) throws BllException;
	public Utilisateur 	getUtilisateurID (int id) throws BllException;
	
	public void 		createUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,String rue, String codePostal, String ville, String motDePasse, int credit, int administrateur)	throws BllException;
	public void 		createUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,String rue, String codePostal, String ville, String motDePasse, String confirmMotDePasse)	throws BllException;
	
	public void 		deleteUtilisateur(Utilisateur utilisateur) throws BllException;
	public void 		updateUtilisateur(Utilisateur utilisateur) throws BllException;
	
}
