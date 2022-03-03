package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Utilisateur;

public interface UserManagerDAO
{
	Utilisateur getUserConnection	(String id,String paswd) throws DAOException;
	Utilisateur getUserConnectionID	(int idUser) throws DAOException;
	
	void 		createUser			(Utilisateur user) throws DAOException;
	
	void 		deleteUser			(Utilisateur user) throws DAOException;
	
	void 		updateUser			(Utilisateur user) throws DAOException;
}
