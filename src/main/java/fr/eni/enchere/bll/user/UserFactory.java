package fr.eni.enchere.bll.user;

public class UserFactory
{
	public static UserManagerImpl getManager()
	{
		return new UserManagerImpl();
	}
}
