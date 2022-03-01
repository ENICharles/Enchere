package fr.eni.enchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.UserManager;
import fr.eni.enchere.bll.enchere.EnchereFactory;
import fr.eni.enchere.bll.user.UserFactory;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServeltTest
 */

//@WebServlet(urlPatterns = {""})
public class ServeltTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeltTest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utilisateur user = null;
		Utilisateur user2 = null;
		Utilisateur user3 = null;
		
		System.out.println("Get ServeLetTest");
		UserManager 	userMng = UserFactory.getManager();
		List<Categorie> listCategorie = null;
				
		EnchereManager 	enchereMng = EnchereFactory.getManager();
		
		/************* afficher la liste des catégories *************/
		try
		{
			listCategorie = enchereMng.getCategories();
			
			System.out.println("---------------------------------------");
			System.out.println("-     Liste des catégories        -----");
			System.out.println("---------------------------------------");
			for(Categorie cat : listCategorie)
			{
				System.out.println(cat.getNoCategorie() + " " + cat.getLibelle());
				
			}
		}
		catch (BllException e)
		{
			System.out.println("Erreur lecture des catégories");
		}
		
		
		/************* afficher la liste des articles vendus *************/
		try
		{
			List<ArticleVendu> listArticleVendu = enchereMng.getArticleVendus(0, "Toutes", 0,"");
			
			System.out.println("---------------------------------------");
			System.out.println("-   Liste des articles mis en ventes  -");
			System.out.println("---------------------------------------");
			
			if(listArticleVendu != null)
			{
				for(ArticleVendu art : listArticleVendu)
				{
					System.out.println(art.getNoArticle() + " " + art.getNomArticle() + " " + art.getDescription() + " " + art.getDateDebutEnchere() + " " + art.getEtatVente() + " " + art.getCategorie() + " " + art.getIdPossesseur());
				}
			}
		}
		catch (BllException e)
		{
			System.out.println("Erreur lecture des articles vendus " + e.getMessage());
		}
		

		
		/************* l'utilisateur met en vente un article *************/
		try
		{
			user = userMng.getUtilisateur("Bob","123456");//utilisateur id=2
			ArticleVendu art = new ArticleVendu("Sac","Gros sac bleu",LocalDate.of(2022,3,5),LocalDate.of(2022,3,30),55,0,EtatVente.CREE,listCategorie.get(1),new Retrait());
			
			System.out.println("---------------------------------------");
			System.out.println("-   L'utilisateur "+ user.getNom() + " met en vente un article");
			
			
			enchereMng.createArticle(user.getNoUtilisateur(), art);
		
			System.out.println("Article mis en vente : " + art.getNomArticle() + " " + art.getDescription() + ", ID : " + art.getNoArticle());
			
			System.out.println();
			System.out.println("---------------------------------------");
		}
		catch (BllException e1)
		{
			System.out.println(e1.getMessage());
		}
		
		
		/************* l'utilisateur fait une enchère *************/
		try
		{
			user = userMng.getUtilisateur("Foufou","123456");
			
			System.out.println("---------------------------------------");
			System.out.println("-  " + user.getNom() +" ralise une enchère");
			System.out.println("---------------------------------------");
			
			enchereMng.createEnchere(user.getNoUtilisateur(), 3, 100);
		}
		catch (BllException e)
		{
			System.out.println("erreur sur la création de l'enchère (" + user.getNom() + ")" + e.getMessage());
		}
		try
		{
			user = userMng.getUtilisateur("Foufou","123456");
			
			System.out.println("---------------------------------------");
			System.out.println("-  " + user.getNom() +" ralise une enchère");
			System.out.println("---------------------------------------");
			
			enchereMng.createEnchere(user.getNoUtilisateur(), 4, 5);
		}
		catch (BllException e)
		{
			System.out.println("erreur sur la création de l'enchère (" + user.getNom() + ")" + e.getMessage());
		}
		
		
		
		
		try
		{
			user2 = userMng.getUtilisateur("Bob","123456");
			
			System.out.println("---------------------------------------");
			System.out.println("-  " + user2.getNom() +" réalise une enchère");
			System.out.println("---------------------------------------");
			
			enchereMng.createEnchere(user2.getNoUtilisateur(), 1, 100);
		}
		catch (BllException e)
		{
			System.out.println("erreur sur la création de l'enchère (" + user2.getNom() + ")" + e.getMessage());
		}
		
		
		
		try
		{
			user3 = userMng.getUtilisateur("Oups","123456");
			
			System.out.println("---------------------------------------");
			System.out.println("-  " + user3.getNom() +" réalise une enchère");
			System.out.println("---------------------------------------");
			
			enchereMng.createEnchere(user3.getNoUtilisateur(), 2, 100);
		}
		catch (BllException e)
		{
			System.out.println("erreur sur la création de l'enchère (" + user3.getNom() + ")" + e.getMessage());
		}
		try
		{
			user3 = userMng.getUtilisateur("Oups","123456");
			
			System.out.println("---------------------------------------");
			System.out.println("-  " + user3.getNom() +" réalise une enchère");
			System.out.println("---------------------------------------");
			
			enchereMng.createEnchere(user3.getNoUtilisateur(), 3, 150);
		}
		catch (BllException e)
		{
			System.out.println("erreur sur la création de l'enchère (" + user3.getNom() + ")" + e.getMessage());
		}
		

		
		
		/************* afficher la liste des enchere d'un utilisateur *************/
		try
		{
			System.out.println("---------------------------------------");
			System.out.println("-   Liste des enchères de "+ user.getNom());
			System.out.println("---------------------------------------");
			
			List<Enchere> listEncheres = enchereMng.getEncheres(user.getNoUtilisateur());
			
			if(listEncheres != null)
			{
				for(Enchere enchere : listEncheres)
				{
					System.out.println(enchere.getArticleVendu().getNomArticle() + " " + enchere.getMontant_enchere() + " " + enchere.getDateEnchere());
				}
			}
		}
		catch (BllException e)
		{
			System.out.println("Erreur lecture des Encheres");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
