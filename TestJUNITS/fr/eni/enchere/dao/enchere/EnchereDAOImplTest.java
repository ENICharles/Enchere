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
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.user.UtilisateurDAOImpl;

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
	 * V�rification de la cr�ation d'une nouvelle instance
	 */
	@Test 
	@Order(1)
	void testEnchereDAOImpl() 
	{
		System.out.println("Test 1");
		
		EnchereDAOImpl enchereDAOLocal = null;
		assertNotNull((enchereDAOLocal = new EnchereDAOImpl()));
	}

	/**
	 * Vérification de la cr�ation d'un Enchere en base
	 */
	@Test 
	@Order(2)
	void testCreateEnchere() 
	{
		System.out.println("Test 2");
		//assertFalse(true);
	}

	/**
	 * Vérification de la cr�ation d'un Enchere en base
	 */
	@Test 
	@Order(3)
	void testGetEnchere() 
	{
		System.out.println("Test 2");
		try
		{
			assertEquals("Orinateur",enchereDAO.getArticleVendus(1,"Toutes", 0,"*").get(0).getNomArticle());
			assertEquals("EN_COURS",enchereDAO.getArticleVendus(1,"Toutes", 0,"*").get(0).getEtatVente().toString());
		}
		catch (DAOException e)
		{
			/* echec de la vérification de la lecture des encheres */
			assertFalse(true);
		}
	}

	@Test 
	@Order(4)
	void testUpdateEnchere() 
	{
		System.out.println("Test 4");
		assertFalse(true);
	}

	@Test 
	@Order(5)
	void testDeleteEnchere() 
	{
		System.out.println("Test 5");
		//assertFalse(true);
	}

	@Test 
	@Order(6)
	void testGetCategorie() 
	{
		System.out.println("Test 5");
		try 
		{
			List<Categorie> lst = enchereDAO.getCategories();
			
			assertEquals("Informatique", lst.get(0).getLibelle());
		} 
		catch (DAOException e1) 
		{
			/* echec de la lecture des catégoories */
			assertFalse(true);
		}
	}	
}
