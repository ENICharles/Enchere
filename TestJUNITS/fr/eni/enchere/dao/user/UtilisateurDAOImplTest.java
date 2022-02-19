package fr.eni.enchere.dao.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.eni.enchere.dao.enchere.EnchereDAOImpl;

class UtilisateurDAOImplTest 
{
	UtilisateurDAOImpl utilisateurDAO = null;
	
	@Test
	void testUtilisateurDAOImpl() 
	{
		assertNotNull((utilisateurDAO = new UtilisateurDAOImpl()));
	}

	@Test
	void testGetUserConnection() 
	{
		//fail("Not yet implemented"); // TODO
	}

	@Test
	void testCreateUser() 
	{
		//fail("Not yet implemented"); // TODO
	}

	@Test
	void testUpdateUser() 
	{
		//fail("Not yet implemented"); // TODO
	}

}
