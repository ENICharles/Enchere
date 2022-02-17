package fr.eni.enchere.dao.user;

public class UserFactory
{
	public static UtilisateurDAOImpl getDao()
	{
		return new UtilisateurDAOImpl();
	}
}
