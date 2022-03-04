package fr.eni.enchere.dao.enchere;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.EnchereManagerDAO;
import fr.eni.enchere.dao.util.Settings;

public class EnchereDAOImpl implements EnchereManagerDAO
{
	private static final int 	ALL						= 0;
	
	private static final String GET_UTILISATEUR			= "SELECT no_utilisateur,pseudo,nom,prenom,credit FROM UTILISATEURS WHERE no_utilisateur=?;";
	
	private static final String GET_CATEGORIES			= "SELECT no_categorie,libelle FROM CATEGORIES;";
	private static final String UPDATE_CATEGORIE		= "UPDATE CATEGORIES SET libelle=? WHERE no_categorie=?;";
	
	private static final String UPDATE_ETAT_VENTE		= "UPDATE ARTICLES_VENDUS SET etat_vente=? WHERE no_Article=?;";
	
	private static final String GET_ENCHERES			= "SELECT ENCHERES.date_enchere, ENCHERES.montant_enchere, ENCHERES.no_article, ENCHERES.no_utilisateur, ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_debut_encheres, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.no_article, ARTICLES_VENDUS.etat_vente FROM ENCHERES JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article=ENCHERES.no_article WHERE ENCHERES.no_utilisateur=?;";
	private static final String GET_ENCHERES_ARTICLE	= "SELECT ENCHERES.date_enchere, ENCHERES.montant_enchere, ENCHERES.no_article, ENCHERES.no_utilisateur, ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_debut_encheres, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.no_article, ARTICLES_VENDUS.etat_vente FROM ENCHERES JOIN ARTICLES_VENDUS ON ARTICLES_VENDUS.no_article=ENCHERES.no_article WHERE ENCHERES.no_article=?;";
	private static final String CREATE_ENCHERE			= "INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) VALUES(?,?,?,?);";
	private static final String UPDATE_ENCHERE			= "UPDATE ENCHERES set date_enchere=?,montant_enchere=?, no_utilisateur=? WHERE  no_article=?;";
	
	private static final String UPDATE_ARTICLE			= "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,no_categorie=? WHERE no_Article=?;";
	
	private static final String UPDATE_USER_CREDIT		= "UPDATE UTILISATEURS SET credit=? WHERE no_utilisateur=?";
	
	private static final String GET_ARTICLE_BY_ID		= "SELECT ARTICLES_VENDUS.no_Article, ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_debut_encheres, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.etat_vente,ARTICLES_VENDUS.no_utilisateur, ARTICLES_VENDUS.prix_vente, UTILISATEURS.pseudo, CATEGORIES.no_categorie, CATEGORIES.libelle FROM  ARTICLES_VENDUS JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur WHERE ARTICLES_VENDUS.no_Article = ?";
	
	private static final String GET_ARTICLES			= "SELECT ARTICLES_VENDUS.no_Article, ARTICLES_VENDUS.nom_article, ARTICLES_VENDUS.description, ARTICLES_VENDUS.date_debut_encheres, ARTICLES_VENDUS.date_fin_encheres, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.etat_vente, ARTICLES_VENDUS.prix_vente,ARTICLES_VENDUS.no_utilisateur, UTILISATEURS.pseudo, CATEGORIES.no_categorie, CATEGORIES.libelle FROM  ARTICLES_VENDUS JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur";
	private static final String FILTRE_USER				= " ARTICLES_VENDUS.no_utilisateur = ?";
	private static final String FILTRE_CATEGORIE		= " CATEGORIES.no_categorie = ?";
	private static final String FILTRE_ETAT				= " ARTICLES_VENDUS.etat_vente = ?";
	private static final String FILTRE_ARTICLE			= " ARTICLES_VENDUS.nom_article LIKE ?";
	
	private static final String CREATE_ARTICLE			= "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente) VALUES ( ?,?,?,?,?,?,?,?,'CREE');";
	
	public EnchereDAOImpl()
	{
		super();
	}
	
    /**
     * Connexion �la base de donn�e
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
            throw new DAOException("Driver de base non trouv� " + e.getMessage());
        } 
        
        try
        {
            con = (Connection) DriverManager.getConnection(Settings.getPropriete("urlDB") + "/" + Settings.getPropriete("baseDB") + "?",Settings.getPropriete("userDB"),Settings.getPropriete("pswDB"));  
        }catch(Exception e) 
        {
        	throw new DAOException("Echec de la connexion � la base.\nBase, utilisateur ou mot de passe faux.");
        } 
        
        return con;
    } 

	/**
	 * R�cup�re toutes les encheres en fonction des filtres: 
	 * 	si idUser=0 alors toutes les encheres visibles(les siens ou pas)
	 *  si filtre=Toutes alors toutes les encheres visibles(sans aucun filtre)
	 */
    @Override
	public Enchere getEncheresByIdArticle(int idArticle) throws DAOException
	{
    	Enchere				enchere = null;
    	ArticleVendu 		article = null;
    	
    	PreparedStatement 	rqt 		 = null;
    	Connection  		cnx 		 = null;
    	ResultSet   		myRez   	 = null;
    	
        try
        {  
        	cnx = loadDb(); 
        	
        	rqt	= cnx.clientPrepareStatement(GET_ENCHERES_ARTICLE);
        	rqt.setInt(1, idArticle);
        	
        	//System.out.println("Reqttte : " + rqt.toString());
        	myRez = rqt.executeQuery();
        	
            while(myRez.next())
            {            	  
            	enchere = new Enchere();
            	article = new ArticleVendu();
            	
            	enchere.setDateEnchere(myRez.getDate("date_enchere").toLocalDate());
            	enchere.setMontant_enchere(myRez.getInt("montant_enchere"));
            	enchere.setEnchereur(myRez.getInt("no_utilisateur"));           	
            	article.setNomArticle(myRez.getString("nom_article"));
        		article.setDescription(myRez.getString("description"));
        		article.setDateDebutEnchere(myRez.getDate("date_debut_encheres").toLocalDate());
        		article.setDateFinEnchere(myRez.getDate("date_fin_encheres").toLocalDate());
        		article.setMiseAPrix(myRez.getInt("prix_initial"));
        		article.setEtatVente(myRez.getString("etat_vente"));
        		article.setNoArticle(myRez.getInt("no_article"));
        		
            	enchere.setArticleVendu(article);
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
        
    	return enchere;
	}

	/**
	 * R�cup�re toutes les encheres en fonction des filtres: 
	 * 	si idUser=0 alors toutes les encheres visibles(les siens ou pas)
	 *  si filtre=Toutes alors toutes les encheres visibles(sans aucun filtre)
	 */
    @Override
	public List<Enchere> getEncheres(int idUser) throws DAOException
	{
    	List<Enchere> 		ret 	= null;
    	Enchere				enchere = null;
    	ArticleVendu 		article = null;
    	
    	PreparedStatement 	rqt 		 = null;
    	Connection  		cnx 		 = null;
    	ResultSet   		myRez   	 = null;
    	
        try
        {  
        	cnx = loadDb(); 
        	
        	rqt	= cnx.clientPrepareStatement(GET_ENCHERES);
        	rqt.setInt(1, idUser);
        	
        	//System.out.println("Reqttte : " + rqt.toString());
        	myRez = rqt.executeQuery();
        	
            while(myRez.next())
            {            	  
            	enchere = new Enchere();
            	article = new ArticleVendu();
            	
            	enchere.setDateEnchere(myRez.getDate("date_enchere").toLocalDate());
            	enchere.setMontant_enchere(myRez.getInt("montant_enchere"));
            	            	
            	article.setNomArticle(myRez.getString("nom_article"));
        		article.setDescription(myRez.getString("description"));
        		article.setDateDebutEnchere(myRez.getDate("date_debut_encheres").toLocalDate());
        		article.setDateFinEnchere(myRez.getDate("date_fin_encheres").toLocalDate());
        		article.setMiseAPrix(myRez.getInt("prix_initial"));
        		article.setEtatVente(myRez.getString("etat_vente"));
        		article.setNoArticle(myRez.getInt("no_article"));
        		
            	enchere.setArticleVendu(article);

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

	/**
	 * R�cup�re toutes les encheres en fonction des filtres: 
	 * 	si idUser=0 alors toutes les encheres visibles(les siens ou pas)
	 *  si filtre=Toutes alors toutes les encheres visibles(sans aucun filtre)
	 */
    @Override
	public List<ArticleVendu> getArticleVendus(int idUser,String etatVente,int categorie,String article) throws DAOException
	{
		List<ArticleVendu>	ret 		 = null;
		ArticleVendu  		articleVendu = null;
		String 				requet		 = "";
		String 				filtre		 = "";
		
    	PreparedStatement 	rqt 		 = null;
    	Connection  		cnx 		 = null;
    	ResultSet   		myRez   	 = null;
    	
    	int 				nbParam	 	 = 0;
    	
        try
        {     
        	cnx = loadDb();       

        	requet 	= GET_ARTICLES;
        	
        	if(idUser != ALL)
        	{
        		filtre 	= FILTRE_USER;
        		nbParam = 1;
        	}
        	
        	if(categorie != ALL)
        	{
        		filtre = filtre.equals("")? "":(filtre + " AND ");
        		filtre += FILTRE_CATEGORIE;
        		nbParam += 4;
        	}  
        	
        	if(!etatVente.equals("Toutes"))
        	{
        		filtre = filtre.equals("")? "":(filtre + " AND ");
        		filtre += FILTRE_ETAT;
        		nbParam += 2;
        	}
        	
        	if(article.equals("")==false)
        	{
        		filtre = filtre.equals("")? "":(filtre + " AND ");
        		filtre += FILTRE_ARTICLE;
        		nbParam += 8;
        	}
        	
        	if(filtre.equals(""))
        	{
        		rqt	= cnx.clientPrepareStatement(requet);
        	}
        	else
        	{
        		//System.out.println("val Param : " + nbParam);
        		rqt	= cnx.clientPrepareStatement(requet + " WHERE " + filtre);
            	
            	switch(nbParam)
            	{
            		case 1:
            			rqt.setInt(1, idUser);
            		break;
            		case 2:
            			rqt.setString(1, etatVente);
            		break;
            		case 3:
            			rqt.setInt(1, idUser);
            			rqt.setString(2, etatVente);
            		break;
            		case 4:
            			rqt.setInt(1, categorie);
            		break;
            		case 5:
            			rqt.setInt(1, idUser);
            			rqt.setInt(2, categorie);
            		break;
            		case 6:
            			rqt.setString(1, etatVente);
            			rqt.setInt(2, categorie);
            		break;
            		case 7:
            			rqt.setInt(1, idUser);
            			rqt.setString(2, etatVente);
            			rqt.setInt(3, categorie);
            		break;
            		case 8:
            			rqt.setString(1, "%"+article+"%");
            		break;
            		case 9:
            			rqt.setInt(1, idUser);
            			rqt.setString(2, "%"+article+"%");
            		break;
            		case 10:
            			rqt.setString(1, etatVente);
            			rqt.setString(2, "%"+article+"%");
            		break;
            		case 11:
            			rqt.setInt(1, idUser);
            			rqt.setString(2, etatVente);
            			rqt.setString(3, "%"+article+"%");
            		break;
            		case 12:
            			rqt.setInt(1, categorie);
            			rqt.setString(2, "%"+article);
            		break;
            		
            		case 13:
            			rqt.setInt(1, idUser);
            			rqt.setInt(2, categorie);
            			rqt.setString(3, "%"+article+"%");
            		break;
            		case 14:
            			rqt.setString(1, etatVente);
            			rqt.setInt(2, categorie);
            			rqt.setString(3, "%"+article+"%");
            		break;
            		case 15:
            			rqt.setInt(1, idUser);
            			rqt.setString(2, etatVente);
            			rqt.setInt(3, categorie);
            			rqt.setString(4, "%"+article+"%");
            		break;
            	}
        	}
   	
			//System.out.println("Reqttte : " + rqt.toString());
        	myRez = rqt.executeQuery();
        	
            while(myRez.next())
            {            	  
            	articleVendu = new ArticleVendu();
            	
            	articleVendu.setNoArticle(myRez.getInt("no_article"));
            	articleVendu.setNomArticle(myRez.getString("nom_article"));
            	articleVendu.setDescription(myRez.getString("description"));
            	articleVendu.setDateDebutEnchere(myRez.getDate("date_debut_encheres").toLocalDate());
            	articleVendu.setDateFinEnchere(myRez.getDate("date_fin_encheres").toLocalDate());
            	articleVendu.setMiseAPrix(myRez.getInt("prix_initial"));
            	articleVendu.setEtatVente(myRez.getString("etat_vente"));
            	articleVendu.setPrixVente(myRez.getInt("prix_vente"));
            	articleVendu.setCategorie(myRez.getInt("no_categorie"),myRez.getString("libelle"));
            	articleVendu.setIdPossesseur(myRez.getInt("no_utilisateur"));
            	
            	if(ret == null)
            	{
            		ret = new ArrayList<ArticleVendu>();
            	}
            	
            	ret.add(articleVendu);
            }
        }
        catch(DAOException e)              
        {
        	throw new DAOException("pas d'acc�s � la base " + e.getMessage());
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
    public Utilisateur getUtilisateurId(int idUtilisateur) throws DAOException
    {
    	Utilisateur 		user 	= null;
    	
    	PreparedStatement 	rqt 		 = null;
    	Connection  		cnx 		 = null;
    	ResultSet   		myRez   	 = null;
    	
        try
        {  
        	cnx = loadDb(); 
        	
        	rqt	= cnx.clientPrepareStatement(GET_UTILISATEUR);
        	rqt.setInt(1, idUtilisateur);
        	
        	myRez = rqt.executeQuery();
       
            while(myRez.next())
            {            	  
            	user 	= new Utilisateur();
            	
            	user.setNoUtilisateur(myRez.getInt("no_utilisateur"));
            	user.setPseudo(myRez.getString("pseudo"));
            	user.setNom(myRez.getString("nom"));
            	user.setCredit(myRez.getInt("credit"));
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
    	
    	return user;
    }
    
    /**
     * Mise � jour du cr�dit de l'enchereur pr�c�dent et du nouvel enchereur
     * @param idUser1 id du nouvel enchereur
     * @param minusCredit montant de l'ench�re
     * @param idUser2 id de l'ancien enchereur
     * @param upperCredit montant de la restitution
     * @throws DAOException 
     */
    @Override
    public void updateUserCredit(int idUser1,int minusCredit) throws DAOException
    {
    	PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	
        try
        {     
        	cnx = loadDb();       
        	
        	rqt = cnx.prepareStatement(UPDATE_USER_CREDIT);
      		
      		rqt.setInt(1, minusCredit);
      		rqt.setInt(2, idUser1);
			
			//System.out.println(rqt);
        	int nb = rqt.executeUpdate();
        	
        	if(nb != 1)
        	{
        		throw new DAOException("Echec de la mise � jour de l'enchere ");
        	}
        }
        catch(DAOException e)              
        {
        	throw new DAOException(e.getMessage());
        }
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("Echec de la lecture de la base " + e.getMessage());
		}

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
    
	@Override
	public void createEnchere(Enchere enchere) throws DAOException
	{
		PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	
        try
        {     
        	cnx = loadDb();       
        	
        	rqt = cnx.prepareStatement(CREATE_ENCHERE);
        	
			rqt.setDate(1, Date.valueOf(enchere.getDateEnchere()));
      		rqt.setInt(2, enchere.getMontant_enchere());
  			rqt.setInt(3, enchere.getArticleVendu().getNoArticle());
  			rqt.setInt(4, enchere.getEnchereur());
			
			////System.out.println(rqt);
        	int nb = rqt.executeUpdate();
        	
        	if(nb != 1)
        	{
        		throw new DAOException("Echec de l'insertion de l'enchere (" + enchere.getArticleVendu().getNomArticle() + ")");
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

	@Override
	public int updateEnchere(Enchere enchere) throws DAOException
	{
		PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	
    	int ret = 0;
    	
        try
        {     
        	cnx = loadDb();       
        	
        	rqt = cnx.prepareStatement(UPDATE_ENCHERE);
        	
			rqt.setDate(1, Date.valueOf(enchere.getDateEnchere()));
      		rqt.setInt(2, enchere.getMontant_enchere());
      		rqt.setInt(3, enchere.getEnchereur());
  			rqt.setInt(4, enchere.getArticleVendu().getNoArticle());
			
			//System.out.println(rqt);
        	ret = rqt.executeUpdate();
        }
        catch(DAOException e)              
        {
        	throw new DAOException(e.getMessage());
        }
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("Echec de la lecture de la base " + e.getMessage());
		}

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
        
        return ret;
	}

	@Override
	public void updateArticle(ArticleVendu article) throws DAOException
	{
		System.out.println("Mise à jour état vente en base ");
		PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	
        try
        {     
        	cnx = loadDb();       
        	
        	rqt = cnx.prepareStatement(UPDATE_ARTICLE);
        	        			
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, Date.valueOf(article.getDateDebutEnchere()));
			rqt.setDate(4, Date.valueOf(article.getDateFinEnchere()));
			rqt.setInt(5, article.getMiseAPrix());
			rqt.setInt(6, article.getCategorie().getNoCategorie());
			
			System.out.println("modification de l'article " + rqt);
        	int nb = rqt.executeUpdate();
        	
        	if(nb != 1)
        	{
        		throw new DAOException("Echec de la modification de l'article (" + article.getNomArticle() + ")");
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

	/**
	 * R�cup�ration de la liste des cat�gorie dans la base
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

	/**
	 * Modifie l'utilisateur par son ID(une  v�rification de la disponibilit� des adresse et psuedo est faite)
	 * @throws DAOException
	 */
	@Override
	public void updateCategorie(Categorie categorie) throws DAOException
	{
		PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	
        try
        {     
        	cnx = loadDb();       
        	
        	rqt = cnx.prepareStatement(UPDATE_CATEGORIE);
        	
			rqt.setString(1, categorie.getLibelle());
			rqt.setInt(2, categorie.getNoCategorie());
			
			////System.out.println(rqt);
        	int nb = rqt.executeUpdate();
        	
        	if(nb != 1)
        	{
        		throw new DAOException("Echec de la modification de la cat�gorie (" + categorie.getLibelle() + ")");
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
	
	/**
	 * Cr�ation d'un article en base
	 */
	public void createArticle(int idUser,ArticleVendu article)throws DAOException
	{
		PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	ResultSet   		myRez   = null;
    	
        try
        {     
        	cnx = loadDb();       
        	
        	rqt = cnx.prepareStatement(CREATE_ARTICLE,PreparedStatement.RETURN_GENERATED_KEYS);
        			
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, Date.valueOf(article.getDateDebutEnchere()));
			rqt.setDate(4, Date.valueOf(article.getDateFinEnchere()));
			rqt.setInt(5, article.getMiseAPrix());
			rqt.setInt(6, 0);
			rqt.setInt(7, idUser);
			rqt.setInt(8, 1);
			
			//System.out.println(rqt);
        	int nb = rqt.executeUpdate();
        	
        	if(nb != 1)
        	{
        		throw new DAOException("Echec de l'insertion de larticle (" + article.getNomArticle() + ")");
        	}
        	else
        	{
	        	myRez = rqt.getGeneratedKeys();
	            if(myRez.next() == true)
	            {        
	            	article.setNoArticle(myRez.getInt(1));
	            }
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
	
	public ArticleVendu getArticle(int idArticle)throws DAOException
	{
		ArticleVendu 		articleVendu 		= null;
    	
    	PreparedStatement 	rqt 		= null;
    	Connection  		cnx 		= null;
    	ResultSet   		myRez   	= null;
    	
        try
        {     
        	cnx = loadDb();       

        	rqt = cnx.clientPrepareStatement(GET_ARTICLE_BY_ID);
        	rqt.setInt(1, idArticle);
						
        	myRez = rqt.executeQuery();
        	
            while(myRez.next())
            {           	       
            	articleVendu = new ArticleVendu();
            	
     			articleVendu.setNoArticle(myRez.getInt("no_Article"));
            	articleVendu.setNomArticle(myRez.getString("nom_article"));
            	
            	articleVendu.setCategorie(myRez.getInt("no_categorie"),myRez.getString("libelle"));
            	
            	articleVendu.setDescription(myRez.getString("description"));
            	articleVendu.setDateDebutEnchere(myRez.getDate("date_debut_encheres").toLocalDate());
            	articleVendu.setDateFinEnchere(myRez.getDate("date_fin_encheres").toLocalDate());
            	articleVendu.setMiseAPrix(myRez.getInt("prix_initial"));
            	articleVendu.setIdPossesseur(myRez.getInt("no_utilisateur"));
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
        
    	return articleVendu;
	}
	
	public void updateEnchereEtatVente(ArticleVendu article)throws DAOException
	{
		System.out.println("Mise à jour état vente en base ");
		PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	
        try
        {     
        	cnx = loadDb();       
        	
        	rqt = cnx.prepareStatement(UPDATE_ETAT_VENTE);
        	
			rqt.setString(1,article.getEtatVente().name());
			rqt.setInt(2, article.getNoArticle());
			
			////System.out.println(rqt);
        	int nb = rqt.executeUpdate();
        	
        	if(nb != 1)
        	{
        		throw new DAOException("Echec de la modification de la cat�gorie (" + article.getNomArticle() + ")");
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
}
