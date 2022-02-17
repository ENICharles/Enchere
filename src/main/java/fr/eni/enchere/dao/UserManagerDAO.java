package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Utilisateur;

public interface UserManagerDAO
{
	Utilisateur getUserConnection(String id,String paswd) throws DAOException;
	void 		createUser(Utilisateur user) throws DAOException;
}
