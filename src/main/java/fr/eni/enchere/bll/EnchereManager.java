package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bo.Enchere;

public interface EnchereManager
{
	public List<Enchere> getEncheres(int idUser) throws BllException;
	//public List<Categories> getCategories() throws BllException;
	public void createEnchereUser(Enchere enchere) throws BllException;
}
