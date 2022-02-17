package fr.eni.enchere.bll.enchere;

import java.util.List;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.EnchereManager;
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
	
	@Override
	public List<Enchere> getEncheres(int idUser) throws BllException
	{
		try
		{
			List<Enchere> lst = enchereDAO.getEnchereUser(1);
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture des encheres");
		}
		return null;
	}

	@Override
	public void createEnchereUser(Enchere enchere) throws BllException
	{
		
	}

//	@Override
//	public List<Categorie> getCategories(int idUser) throws BllException
//	{
//		try
//		{
//			List<Categorie> lst = enchereDAO.getCategories();
//		}
//		catch (DAOException e)
//		{
//			throw new BllException("Pb sur la lecture des catégories");
//		}
//		return null;
//	}
}
