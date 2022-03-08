package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dao.DAOException;

/**
 * Liste des fonctions exploitable par l'IHM
 * throws BllException
 */
public interface EnchereManager
{
	public List<ArticleVendu>	getArticleVendus(int idUser,String etatVente,int categorie,String article) throws BllException;
	public ArticleVendu			getArticleVendus(int idArticle) throws BllException;
	
	public List<Enchere>		getEncheres(int idUser) throws BllException;
	public Enchere 				getEncheresByIdArticle(int idArticle) throws BllException;
	public List<Categorie> 		getCategories() throws BllException;
	
	public void 				createArticle(int idUser,ArticleVendu article) throws BllException;
	public void					createEnchere(int idUser,int idArticle,int montant) throws BllException;
	
	public void					updateEnchereEtatVente(ArticleVendu article) throws BllException;
	public void					updateArticle(ArticleVendu article) throws BllException;
	
	public void					putPictureToBase(String pathPicture,int idArticle) throws BllException;
	public String 				getPictureToBase(int idArticle) throws BllException;
}
