package fr.eni.enchere.dao;

public class DaoFactory
{
	public static EnchereDAOImpl getManager()
	{
		return new EnchereDAOImpl();
	}
}
