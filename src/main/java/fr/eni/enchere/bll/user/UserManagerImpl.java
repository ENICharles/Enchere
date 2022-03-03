package fr.eni.enchere.bll.user;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.UserManager;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.UserManagerDAO;
import fr.eni.enchere.dao.user.UserFactory;

public class UserManagerImpl implements UserManager
{
	private UserManagerDAO userDAO = null;

	public UserManagerImpl()
	{
		super();
		
		userDAO = (UserManagerDAO) UserFactory.getDao();
	}
	
	/**
	 * S�lection de l'utilisateur en fonction de son identifiant et de son mot de passe
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
	
	/**
	 * S�lection de l'utilisateur en fonction de son identifiant et de son mot de passe
	 * @param identifiant
	 * @param password
	 * @return
	 * @throws BllException 
	 */
	@Override
	public Utilisateur getUtilisateur(Utilisateur utilisateur) throws BllException
	{
		Utilisateur infoUtilisateur = null;
		
		try
		{
			infoUtilisateur = userDAO.getUserConnection(utilisateur.getPseudo(),utilisateur.getMotDePasse());
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
				
		return infoUtilisateur;
	}
	
	/**
	 * S�lection de l'utilisateur en fonction de son identifiant et de son mot de passe
	 * @param identifiant
	 * @param password
	 * @return
	 * @throws BllException 
	 */
	@Override
	public Utilisateur getUtilisateurID(int idUser) throws BllException
	{
		Utilisateur infoUtilisateur = null;
		
		try
		{
			infoUtilisateur = userDAO.getUserConnectionID(idUser);
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
				
		return infoUtilisateur;
	}

	/**
	 * Cr�ation d'un nouvel utilisateur 
	 */
	@Override
	public void createUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,String rue, String codePostal, String ville, String motDePasse, int credit, int administrateur) throws BllException
	{
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone,rue, codePostal, ville, motDePasse, credit, administrateur);
		
		try
		{
			userDAO.createUser(utilisateur);
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
	}

	/**
	 * Cr�ation d'un nouvel utilisateur 
	 */
	@Override
	public void createUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,String rue, String codePostal, String ville, String motDePasse, String confirmMotDePasse) throws BllException
	{
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone,rue, codePostal, ville, motDePasse);
		
		try
		{
			userDAO.createUser(utilisateur);
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
	}

	/**
	 * Suppression d'un nouvel utilisateur 
	 */
	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) throws BllException
	{
		try
		{
			userDAO.deleteUser(utilisateur);
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
	}

	/**
	 * Mise � jour des donn�es de l'utilisateur
	 */
	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws BllException
	{
		try
		{
			userDAO.updateUser(utilisateur);
		}
		catch (DAOException e)
		{
			throw new BllException(e.getMessage());
		}
	}
}
