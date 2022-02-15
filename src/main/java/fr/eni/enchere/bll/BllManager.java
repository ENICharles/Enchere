package fr.eni.enchere.bll;

import java.util.List;
import java.util.ArrayList;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.EnchereDAO;

public class BllManager
{
	private EnchereDAO enchereDAO = null;

	public BllManager()
	{
		super();
		
		enchereDAO = new EnchereDAO();
	}
	
	public Utilisateur getUtilisateur(String identifiant,String password)
	{
		Utilisateur  utilisateur = null;
		List<String> infoUtilisateur = null;
		
		infoUtilisateur = enchereDAO.getUserConnection(identifiant,password);
		
		return utilisateur;
	}
}
