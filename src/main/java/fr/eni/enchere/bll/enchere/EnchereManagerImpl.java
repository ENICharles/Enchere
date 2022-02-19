package fr.eni.enchere.bll.enchere;

import java.util.List;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.EnchereManagerDAO;
import fr.eni.enchere.dao.enchere.EnchereFactory;

public class EnchereManagerImpl implements EnchereManager
{
	private EnchereManagerDAO enchereDAO = null;

	public EnchereManagerImpl()
	{
		super();
		
		enchereDAO = (EnchereManagerDAO) EnchereFactory.getDao();
	}
	
	/**
	 * R�cup�re la liste des encheres : si idUser=0 alors r�cup�rer toutes les encheres sinon seulement les ench�re de l'utilisateur
	 *  throws BllException
	 */
	@Override
	public List<Enchere> getEncheres(int idUser) throws BllException
	{
		List<Enchere> lst = null;
		
		try
		{
			lst = enchereDAO.getEnchereUser(idUser);
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture des encheres");
		}
		return lst;
	}

	
	/**
	 * R�cup�re la liste des cat�gories
	 *  throws BllException
	 */
	@Override
	public List<Categorie> getCategories() throws BllException 
	{
		List<Categorie> lst = null;
		
		try
		{
			lst = enchereDAO.getCategories();
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture des cat�gories " + e.getMessage());
		}
		
		return lst;
	}
}
