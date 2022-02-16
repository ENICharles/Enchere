package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.EnchereDAO;

public class BllManager
{
	private EnchereDAO enchereDAO = null;

	public BllManager()
	{
		super();
		
		enchereDAO = new EnchereDAO();
	}
	
	/**
	 * Sélection de l'utilisateur en fonction de son identifiant et de son mot de passe
	 * @param identifiant
	 * @param password
	 * @return
	 * @throws BllException 
	 */
	public List<String> getUtilisateur(String identifiant,String password) throws BllException
	{
		List<String> infoUtilisateur = null;
		
		try
		{
			infoUtilisateur = enchereDAO.getUserConnection(identifiant,password);
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
		
		return infoUtilisateur;
	}
}
