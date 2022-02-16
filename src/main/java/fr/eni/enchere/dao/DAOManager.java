package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Utilisateur;

public interface DAOManager
{
	Utilisateur getUserConnection(String id,String paswd) throws DAOException;
}
