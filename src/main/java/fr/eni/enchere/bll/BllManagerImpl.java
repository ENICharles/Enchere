package fr.eni.enchere.bll;


import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.DAOManager;
import fr.eni.enchere.dao.DaoFactory;

public class BllManagerImpl implements BllManager
{
	private DAOManager enchereDAO = null;

	public BllManagerImpl()
	{
		super();
		
		enchereDAO = DaoFactory.getManager();
	}
	
	/**
	 * Sélection de l'utilisateur en fonction de son identifiant et de son mot de passe
	 * @param identifiant
	 * @param password
	 * @return
	 * @throws BllException 
	 */
	@Override
	public Utilisateur getUtilisateur(String identifiant,String password) throws BllException
	{
		Utilisateur infoUtilisateur = null;
		
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
