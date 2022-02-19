package fr.eni.enchere.dao.enchere;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.enchere.dao.DAOException;

class EnchereDAOImplTest 
{	
	EnchereDAOImpl enchereDAO = null;	
	
	@BeforeEach
	void newEnchere()
	{	
		System.out.println("new");
		enchereDAO = new EnchereDAOImpl();
	}	
	
	@AfterEach
	void clearEnchere()
	{
		System.out.println("clear");
		enchereDAO = null;
	}

	
	@Test
	void testEnchereDAOImpl() 
	{
		assertNotNull(enchereDAO);
	}

	@Test
	void testGetEnchereUser() 
	{
		fail("Not yet implemented"); // TODO
//		try 
//		{
//			float montantEnchere = enchereDAO.getEnchereUser(0).get(0).getMontant_enchere();
//			
//			assertEquals(12.34, montantEnchere);
//		} 
//		catch (DAOException e) 
//		{
//			/* pb sur la base */
//			assertFalse(true);
//		}
	}

	@Test
	void testCreateEnchereUser() 
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetCategories() 
	{
		try 
		{
			String nomLibelle = enchereDAO.getCategories().get(0).getLibelle();
			
			assertEquals("Ameublement", nomLibelle);
		} 
		catch (DAOException e) 
		{
			/* pb sur la base */
			assertFalse(true);
		}
	}

}
