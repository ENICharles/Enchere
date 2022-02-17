package fr.eni.enchere.dao.enchere;

public class EnchereFactory
{
	public static EnchereDAOImpl getDao()
	{
		return new EnchereDAOImpl();
	}
}
