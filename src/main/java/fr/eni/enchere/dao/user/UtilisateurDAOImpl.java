package fr.eni.enchere.dao.user;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.UserManagerDAO;
import fr.eni.enchere.dao.util.Settings;

public class UtilisateurDAOImpl implements UserManagerDAO
{
	private static final String SELECT_USER			= "SELECT count(*) FROM UTILISATEURS WHERE (email LIKE ?) OR (pseudo LIKE ?);";
	private static final String SELECT_USER_PSEUDO	= "SELECT no_utilisateur,pseudo,nom,prenom,credit,administrateur FROM UTILISATEURS WHERE (pseudo = ?) AND (mot_de_passe like ?);";
	private static final String CREATE_USER			= "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUE (?,?,?,?,?,?,?,?,?,100,1);";
        
	public UtilisateurDAOImpl() 
	{
		super();
	}
	
    /**
     * Connexion à la base de donnée
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
     * Vérifie que l'Email est unique 
     * @param user
     * @return
     * @throws DAOException
     */
    private Boolean checkExistUser(Utilisateur user) throws DAOException
    {
    	Boolean ret = false;
    	
    	PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	ResultSet   		myRez   = null;
    	int					nb 		= 0;
    	
        try
        {     
        	cnx = loadDb();       

        	rqt = cnx.clientPrepareStatement(SELECT_USER);
			rqt.setString(2, user.getPseudo());
			rqt.setString(1, user.getEmail());
			
        	myRez = rqt.executeQuery();
        	
            while(myRez.next())
            {
            	nb++;
            	break;
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
        
        if(nb >0)
        {
        	ret = true;
        }
        else
        {
        	ret= false;
        }
    	return ret;
    }
    
    /**
     * Selectionne l'utilisateur avec le bon identifiant et le bon mot de passe
     * @param id
     * @param paswd
     * @return
     * @throws DAOException
     */
    @Override
    public Utilisateur getUserConnection(String id,String paswd) throws DAOException
    {
    	PreparedStatement 	rqt 	= null;
    	Utilisateur 		ret 	= null;
    	Connection  		cnx 	= null;
    	ResultSet   		myRez   = null;
    	
        try
        {     
        	cnx = loadDb();       

        	rqt = cnx.clientPrepareStatement(SELECT_USER_PSEUDO);
			rqt.setString(1, id);
			rqt.setString(2, paswd);
			
        	myRez = rqt.executeQuery();
        	
            while(myRez.next())
            {
            	if(ret == null)
            	{
            		ret = new Utilisateur();
            	}
            	
            	ret.setNoUtilisateur(myRez.getInt("no_utilisateur"));
            	ret.setPseudo(myRez.getString("pseudo"));
            	ret.setNom(myRez.getString("nom"));
            	ret.setPrenom(myRez.getString("prenom"));            	
            	ret.setCredit(myRez.getInt("credit"));
            	ret.setAdministrateur(myRez.getString("administrateur"));
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
	 * Créé un nouvel utilisateur si il n'est pas déja en base 
     * @param user
     * @throws DAOException
	 */
    @Override
	public void createUser(Utilisateur user) throws DAOException
	{
    	PreparedStatement 	rqt 	= null;
    	Connection  		cnx 	= null;
    	ResultSet   		myRez   = null;
    	
    	if(checkExistUser(user) == false)
    	{
	        try
	        {     
	        	cnx = loadDb();       
	        	
	        	rqt = cnx.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
				rqt.setString(1, user.getPseudo());
				rqt.setString(2, user.getNom());
				rqt.setString(3, user.getPrenom());
				rqt.setString(4, user.getEmail());
				rqt.setString(5, user.getTelephone());
				rqt.setString(6, user.getRue());
				rqt.setString(7, user.getCodePostal());
				rqt.setString(8, user.getVille());
				rqt.setString(9, user.getMotDePasse());
				
				System.out.println(rqt);
				
	        	int nb = rqt.executeUpdate();
	        	
	        	if(nb == 1)
	        	{
		        	myRez = rqt.getGeneratedKeys();
		            if(myRez.next() == true)
		            {        
		            	System.out.println(myRez.getInt(1));
		            	user.setNoUtilisateur(myRez.getInt(1));
		            }
	        	}
	        	else
	        	{
	        		throw new DAOException("Echec de l'insertion du nouvel utilisateur (" + user.toString() + ")");
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
    	}
    	else
    	{
    		throw new DAOException("Echec de l'insertion du nouvel utilisateur (" + user.toString() + ")\nPseudo/Email déjà existant");
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
