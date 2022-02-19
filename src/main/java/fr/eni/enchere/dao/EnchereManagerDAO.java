package fr.eni.enchere.dao;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;

/**
 * Liste des fonctions exploitable par la BLL 
 * throws DAOException
 */
public interface EnchereManagerDAO
{
	List<Enchere> 		getEnchereUser(int idUser) throws DAOException;
	void 				createEnchereUser(int idUser,Enchere enchere) throws DAOException;
	List<Categorie> 	getCategories() throws DAOException;
}
