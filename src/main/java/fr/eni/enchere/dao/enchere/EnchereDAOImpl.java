package fr.eni.enchere.dao.enchere;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.EnchereManagerDAO;
import fr.eni.enchere.dao.util.Settings;

public class EnchereDAOImpl implements EnchereManagerDAO
{
	private static final String GET_CATEGORIES			= "select no_categorie,libelle from CATEGORIES;";
	private static final String GET_ENCHERE_OPEN		= "SELECT ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.etat_vente, UTILISATEURS.pseudo, CATEGORIES.libelle FROM  ARTICLES_VENDUS JOIN  CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur;";
	//private static final String GET_USER_ENCHERE_OPEN	= "SELECT ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.etat_vente, UTILISATEURS.pseudo, CATEGORIES.libelle FROM  ARTICLES_VENDUS JOIN  CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur;";
	//private static final String GET_USER_ENCHERE_ALL	= "SELECT ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.etat_vente, UTILISATEURS.pseudo, CATEGORIES.libelle FROM  ARTICLES_VENDUS JOIN  CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur WHERE ENCHERES.no_utilisateur = ?;";
	
	public EnchereDAOImpl()
	{
		super();
	}
	
    /**
     * Connexion à la base de donnée
     * @throws DAOException 
     */
    private Connection loadDb() throws DAOException
    {
    	Connection  con     = null;
    	
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }        
        catch(ClassNotFoundException e)        
        {
            throw new DAOException("Driver de base non trouvé " + e.getMessage());
        } 
        
        try
        {
            con = (Connection) DriverManager.getConnection(Settings.getPropriete("urlDB") + "/" + Settings.getPropriete("baseDB") + "?",Settings.getPropriete("userDB"),Settings.getPropriete("pswDB"));  
        }catch(Exception e) 
        {
        	throw new DAOException("Echec de la connexion à la base.\nBase, utilisateur ou mot de passe faux.");
        } 
        
        return con;
    } 

	/**
	 * Récupère toutes les encheres en fonction des filtres: si idUser=0 alors toutes les encheres visibles(les siens ou pas)
	 */
    @Override
	public List<Enchere> getEnchereUser(int idUser) throws DAOException
	{
		List<Enchere> 		ret 	= null;
		Enchere		  		enchere	= null;
    	
    	PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	ResultSet   		myRez   = null;
    	
        try
        {     
        	cnx = loadDb();       

        	rqt = cnx.clientPrepareStatement(GET_ENCHERE_OPEN);
						
        	myRez = rqt.executeQuery();
        	
            while(myRez.next())
            {
            	System.out.println(myRez.getString("nom_article"));
            	System.out.println(myRez.getString("description"));
            	//System.out.println(myRez.getDate("date_fin_enchere"));
            	System.out.println(myRez.getString("prix_initial"));
            	System.out.println(myRez.getString("pseudo"));
            	
            	enchere = new Enchere();
            	//TODO : Ajout des setter de class
            	
            	if(ret == null)
            	{
            		ret = new ArrayList<Enchere>();
            	}
            	ret.add(enchere);
            }
        }
        catch(DAOException e)              
        {
        	throw new DAOException(e.getMessage());
        }
		catch (SQLException e)
		{
			throw new DAOException("Echec de la lecture de la base " + e.getMessage());
		}
        finally
        {
            if(cnx != null) 
            {
                try
                {
                	cnx.close();
                }catch(SQLException e) 
                {
                	throw new DAOException("Echec de la fermeture de la connexion " + e.getMessage());
                }   
            } 
        }
        
    	return ret;
	}

	@Override
	public void createEnchereUser(int idUser, Enchere enchere) throws DAOException
	{
		/* fonction à créer */
	}

	/**
	 * Récupération de la liste des catégorie dans la base
	 */
	@Override
	public List<Categorie> getCategories() throws DAOException 
	{
		List<Categorie> 	ret		 	= null;
		Categorie			categorie	= null;
    	
    	PreparedStatement 	rqt 		= null;
    	Connection  		cnx 		= null;
    	ResultSet   		myRez   	= null;
    	
        try
        {     
        	cnx = loadDb();       

        	rqt = cnx.clientPrepareStatement(GET_CATEGORIES);
						
        	myRez = rqt.executeQuery();
        	
            while(myRez.next())
            {           	       
            	categorie = new Categorie();
            	categorie.setNoCategorie(myRez.getInt("no_categorie"));
            	categorie.setLibelle(myRez.getString("libelle"));
            	
            	if(ret == null)
            	{
            		ret	= new ArrayList<Categorie>();
            	}
            	ret.add(categorie);
            }
        }
        catch(DAOException e)              
        {
        	throw new DAOException(e.getMessage());
        }
		catch (SQLException e)
		{
			throw new DAOException("Echec de la lecture de la base " + e.getMessage());
		}
        finally
        {
            if(cnx != null) 
            {
                try
                {
                	cnx.close();
                }catch(SQLException e) 
                {
                	throw new DAOException("Echec de la fermeture de la connexion " + e.getMessage());
                }   
            } 
        }
        
    	return ret;
	}
}
