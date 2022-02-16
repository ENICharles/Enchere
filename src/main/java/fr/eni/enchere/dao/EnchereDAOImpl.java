package fr.eni.enchere.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.util.Settings;

public class EnchereDAOImpl implements DAOManager
{
	private static final String SELECT_USER_ADRS	= "SELECT no_utilisateur,pseudo,nom,prenom,credit,administrateur FROM UTILISATEURS WHERE (email = ?) AND (mot_de_passe like ?);";
	private static final String SELECT_USER_PSEUDO	= "SELECT no_utilisateur,pseudo,nom,prenom,credit,administrateur FROM UTILISATEURS WHERE (pseudo = ?) AND (mot_de_passe like ?);";
	
    private Connection  con     = null;
    private ResultSet   myRez   = null;
    
	private String 		cible   = Settings.getPropriete("urlDB");;//10.12.200.24:1433";
	private String 		usr     = Settings.getPropriete("userDB");//"sa";
	private String 		psw  	= Settings.getPropriete("pswDB");;//"Pa$$w0rd";
	private String 		base 	= Settings.getPropriete("baseDB");;//"videoLocs";
    
	public EnchereDAOImpl() 
	{
		super();
	}
	
    /**
     * Connexion à la base de donnée
     * TODO : passer par des information déporté pour les identifiants de connexion
     * @throws DAOException 
     */
    private void loadDb() throws DAOException
    {
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
            con = (Connection) DriverManager.getConnection(cible + "/" + base + "?",usr,psw);  
        }catch(Exception e) 
        {
        	throw new DAOException("Echec de la connexion à la base.\nBase, utilisateur ou mot de passe faux.");
        }         
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
    	
        try
        {     
        	loadDb();       

        	rqt = con.clientPrepareStatement(SELECT_USER_PSEUDO);
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
            unLoadDb(); 
        }
        
    	return ret;
    }
    
    /**
     * Fermeture de la connexion
     * @throws DAOException 
     */
    private void unLoadDb() throws DAOException
    {
        if(con != null) 
        {
            try
            {
                con.close();
            }catch(SQLException e) 
            {
            	throw new DAOException("Echec de la fermeture de la base " + e.getMessage());
            }   
        }     
    }
}
