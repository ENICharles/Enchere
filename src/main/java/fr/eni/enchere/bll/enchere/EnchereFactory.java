package fr.eni.enchere.bll.enchere;

public class EnchereFactory
{
	public static EnchereManagerImpl getManager()
	{
		return new EnchereManagerImpl();
	}
}
