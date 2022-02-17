package fr.eni.enchere.bll;


import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.DAOUser;
import fr.eni.enchere.dao.DaoUserFactory;

public class BllManagerImpl implements BllManager
{
	private DAOUser userDAO = null;

	public BllManagerImpl()
	{
		super();
		
		userDAO = DaoUserFactory.getDao();
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
			infoUtilisateur = userDAO.getUserConnection(identifiant,password);
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
				
		return infoUtilisateur;
	}

	@Override
	/**
	 * Vérification de l'unicité du nouvel utilisateur TODO : AJOUTER  CE CAS
	 * Création d'un nouvel utilisateur 
	 */
	public void createUtilisateur(Utilisateur utilisateur) throws BllException
	{
		try
		{
			userDAO.createUser(utilisateur);
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
	}
}
