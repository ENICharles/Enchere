package fr.eni.enchere.dao.enchere;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dao.DAOException;

@TestMethodOrder(OrderAnnotation.class)
class EnchereDAOImplTest 
{
	EnchereDAOImpl enchereDAO = null;
	
	@BeforeEach
	void newEnchere()
	{	
		enchereDAO = new EnchereDAOImpl();
	}	
	
	@AfterEach
	void clearEnchere()
	{
		enchereDAO = null;
	}
	
	/**
	 * Vérification de la création d'une nouvelle instance
	 */
	@Test 
	@Order(1)
	void testEnchereDAOImpl() 
	{
		System.out.println("Test 1");
		assertFalse(true);
	}

	/**
	 * Vérification de la création d'un Enchere en base
	 */
	@Test 
	@Order(2)
	void testCreateEnchere() 
	{
		System.out.println("Test 2");
		assertFalse(true);
	}

	@Test 
	@Order(3)
	void testUpdateEnchere() 
	{
		System.out.println("Test 4");
		assertFalse(true);
	}

	@Test 
	@Order(4)
	void testDeleteEnchere() 
	{
		System.out.println("Test 5");
		assertFalse(true);
	}

	@Test 
	@Order(5)
	void testGetCategorie() 
	{
		System.out.println("Test 5");
		try 
		{
			List<Categorie> lst = enchereDAO.getCategories();
			
			assertEquals("Ameublement", lst.get(0).getLibelle());
		} 
		catch (DAOException e1) 
		{
			/* echec de la creation */
			assertFalse(true);
		}
	}
	
	
}
