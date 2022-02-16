package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;

public interface BllManager
{
	public Utilisateur getUtilisateur(String identifiant,String password) throws BllException;
}
