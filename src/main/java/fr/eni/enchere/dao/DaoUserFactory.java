package fr.eni.enchere.dao;

public class DaoUserFactory
{
	public static UtilisateurDAOImpl getDao()
	{
		return new UtilisateurDAOImpl();
	}
}
