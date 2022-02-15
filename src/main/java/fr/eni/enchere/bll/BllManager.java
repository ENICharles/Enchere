package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;

public class BllManager
{
	//private EnchereDAO enchereDAO;

	public BllManager()
	{
		super();
	}
	
	public Utilisateur getUtilisateur(String identifiant,String password)
	{
		Utilisateur utilisateur = new Utilisateur();
		
		return utilisateur;
	}
}
