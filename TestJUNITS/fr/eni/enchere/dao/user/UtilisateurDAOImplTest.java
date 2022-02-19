package fr.eni.enchere.dao.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DAOException;

@TestMethodOrder(OrderAnnotation.class)
class UtilisateurDAOImplTest 
{
	UtilisateurDAOImpl utilisateurDAO = null;
	
	@BeforeEach
	void newEnchere()
	{	
		utilisateurDAO = new UtilisateurDAOImpl();
	}	
	
	@AfterEach
	void clearEnchere()
	{
		utilisateurDAO = null;
	}
	
	/**
	 * Vérification de la création d'une nouvelle instance
	 */
	@Test 
	@Order(1)
	void testUtilisateurDAOImpl() 
	{
		System.out.println("Test 1");
		
		UtilisateurDAOImpl utilisateurDAOLocal = null;
		assertNotNull((utilisateurDAOLocal = new UtilisateurDAOImpl()));
	}

	/**
	 * Vérification de la création d'un utilisateur en base
	 */
	@Test 
	@Order(2)
	void testCreateUser() 
	{
		System.out.println("Test 2");
		
		/* création de l'utilisateur */
		Utilisateur utilisateur = new Utilisateur("pseudo","nom","prenom","email@exemple","telephone","rue","codepostal","ville","motdepasse",123,1);
		
		/* insertion en base */
		try 
		{
			utilisateurDAO.createUser(utilisateur);
		} 
		catch (DAOException e1) 
		{
			/* echec de la creation */
			assertFalse(true);
		}
	}

	/**
	 * Vérification de la lecture d'un uilisateur en base
	 */
	@Test 
	@Order(3)
	void testGetUserConnection() 
	{
		System.out.println("Test 3");
		
		/* création de l'utilisateur */
		Utilisateur utilisateur = new Utilisateur("pseudo","","","email@exemple","","","","","motdepasse",0,0);
		
		/* lecture des info de la base */
		try 
		{
			utilisateur = utilisateurDAO.getUserConnection(utilisateur.getPseudo(), utilisateur.getMotDePasse());
			
			assertNotNull(utilisateur);
			assertEquals("nom",utilisateur.getNom());
		} 
		catch (DAOException e) 
		{
			/* echec de la lecture */
			assertFalse(true);
		}
	}

	@Test 
	@Order(4)
	void testUpdateUser() 
	{
		System.out.println("Test 4");
		
		/* création de l'utilisateur */
		Utilisateur utilisateur = new Utilisateur("pseudo","","","email@exemple","","","","","motdepasse",0,0);
		
		/* récupération des info */
		try 
		{
			//System.out.println(utilisateur.getEmail());
			utilisateur = utilisateurDAO.getUserConnection(utilisateur.getPseudo(), utilisateur.getMotDePasse());
		} 
		catch (DAOException e) 
		{
			/* echec de la lecture */
			System.out.println("oups1");
			assertFalse(true);
		}
		
		//System.out.println(utilisateur.getEmail());
		/* modification de l'utilisateur */
		utilisateur.setPrenom("bob");
		
		/* mise à jour de la base */
		try 
		{
			//System.out.println(utilisateur.getNoUtilisateur());
			utilisateurDAO.updateUser(utilisateur);
		} 
		catch (DAOException e) 
		{
			/* echec de la lecture */
			System.out.println("oups2");
			assertFalse(true);
		}
		
		/* relecture de la base */
		try 
		{
			utilisateur = utilisateurDAO.getUserConnection(utilisateur.getPseudo(), utilisateur.getMotDePasse());
			assertEquals("bob",utilisateur.getPrenom());
		} 
		catch (DAOException e) 
		{
			/* echec de la lecture */
			System.out.println("oups3");
			assertFalse(true);
		}	
	}

	@Test 
	@Order(5)
	void testDeleteUser() 
	{
		System.out.println("Test 5");
		/* création de l'utilisateur */
		Utilisateur utilisateur = new Utilisateur("pseudo","","","email@exemple","","","","","motdepasse",0,0);
		
		/* lecture des info en base */
		try 
		{
			utilisateur = utilisateurDAO.getUserConnection(utilisateur.getPseudo(), utilisateur.getMotDePasse());
		} 
		catch (DAOException e) 
		{
			/* echec de la lecture */
			assertFalse(true);
		}
		
		/* supression de l'utilisateur */
		try 
		{
			utilisateurDAO.deleteUser(utilisateur);
		} 
		catch (DAOException e) 
		{
			/* echec de la lecture */
			assertFalse(true);
		}	
	}
}
