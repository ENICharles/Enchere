package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;

/**
 * Liste des fonctions exploitable par l'IHM
 * throws BllException
 */
public interface EnchereManager
{
	public List<Enchere> 	getEncheres(int idUser) throws BllException;
	public List<Categorie> 	getCategories() throws BllException;
	//public void 			createEnchereUser(Enchere enchere) throws BllException;
}
