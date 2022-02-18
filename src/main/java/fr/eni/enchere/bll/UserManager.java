package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;

public interface UserManager
{
	public Utilisateur getUtilisateur(String identifiant,String password) throws BllException;
	public void createUtilisateur(Utilisateur utilisateur) throws BllException;
	public void updateUtilisateur(Utilisateur utilisateur) throws BllException;
}
