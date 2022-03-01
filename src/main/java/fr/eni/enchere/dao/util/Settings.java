package fr.eni.enchere.dao.util;

import java.io.IOException;
import java.util.Properties;

public class Settings
{
	private static Properties proprietes;

	static
	{
		proprietes = new Properties();

		try
		{
			//proprietes.load( Settings.class.getResourceAsStream("configBDD.properties"));
			proprietes.loadFromXML(Settings.class.getResourceAsStream("config.xml"));
		}
		catch (IOException e)
		{
			System.out.println("Impossible de lire le fichier : configBDD.properties");
			e.printStackTrace();
		}
	}

	/**
	 * Retourne la valeur de la propriete passé en parametre
	 * @param String
	 */
	public static String getPropriete(String clef)
	{
		String ret = "";

		ret = proprietes.getProperty(clef);

		return ret;
	}
}
