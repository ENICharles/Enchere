package fr.eni.enchere.bll;

public class BllFactory
{
	public static BllManagerImpl getManager()
	{
		return new BllManagerImpl();
	}
}
