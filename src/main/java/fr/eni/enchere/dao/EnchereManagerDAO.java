package fr.eni.enchere.dao;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Liste des fonctions exploitable par la BLL 
 * throws DAOException
 */
public interface EnchereManagerDAO
{
	Utilisateur 		getUtilisateurId(int idUtilisateur) throws DAOException;
	ArticleVendu 		getArticle(int idarticle) throws DAOException;
	List<ArticleVendu> 	getArticleVendus(int idUser,String etatVente,int categorie,String article) throws DAOException;
	List<Enchere> 		getEncheres(int idUser) throws DAOException;
	Enchere 			getEncheresByIdArticle(int idArticle) throws DAOException;
	List<Categorie> 	getCategories() throws DAOException;
	
	void 				createArticle(int idUser,ArticleVendu article) throws DAOException;
	void 				createEnchere(Enchere enchere) throws DAOException;
	
	void 				updateUserCredit(int idUser1,int minusCredit) throws DAOException;
	int					updateEnchere(Enchere enchere) throws DAOException;
	void 				updateCategorie(Categorie categorie) throws DAOException;
	void 				updateEnchereEtatVente(ArticleVendu article) throws DAOException;
	void				updateArticle(ArticleVendu article) throws DAOException;
}
