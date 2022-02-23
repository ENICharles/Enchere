package fr.eni.enchere.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategorieTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCategorie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testCategorieIntString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetNoCategorie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetNoCategorie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetLibelle() {

		/* création d'une catégorie avec un libelle="test" */
		Categorie cat = new Categorie(1,"coucou");
		
		System.out.println(cat.getLibelle());
		
		assertEquals("coucou", cat.getLibelle());
		
		
	}

	@Test
	void testSetLibelle() {
		fail("Not yet implemented"); // TODO
	}

}
