package fr.eni.enchere.dao;

import java.util.List;

import fr.eni.enchere.bo.Enchere;

public interface EnchereManagerDAO
{
	List<Enchere> getEnchereUser(int idUser) throws DAOException;
	void 	createEnchereUser(int idUser,Enchere enchere) throws DAOException;
}
