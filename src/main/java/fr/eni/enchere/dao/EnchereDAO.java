package fr.eni.enchere.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class EnchereDAO
{
    private Connection  con     = null;
    private Statement   myState = null;
    private ResultSet   myRez   = null;
    
	public EnchereDAO() 
	{
		super();
	}
	
    /* connection à la database */
    private void loadDb()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }        
        catch(ClassNotFoundException e)        
        {
            e.printStackTrace();
        } 
        
        try
        {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/TEST_CONNECT_DB?","root","root85");        
            myState = (Statement) con.createStatement();
        }catch(Exception e) 
        {
        	System.out.println("oups");
        }         
    } 
    
    public List<String> getUserConnection(String id,String paswd)
    {
    	List<String> 	ret 	= new ArrayList<String>();
    	String 			request	= "";
    	
    	/* connexion à la base */
    	loadDb();
    	
        try
        {            
        	request = "select nom from clients";
            myRez = myState.executeQuery(request);
            
            while(myRez.next())
            {
            	ret.add(myRez.getString("nom"));       
            }
        }
        catch(Exception e)        
        {
            System.out.println("echec de requete");
        }finally
        {
            unLoadDb(); 
        }
        
    	return ret;
    }
    
    /**
     * Fermeture de la connexion
     */
    private void unLoadDb()
    {
        if(con != null) 
        {
            try
            {
                con.close();
            }catch(SQLException e) 
            {
                System.out.println("Erreur de fermeture de la connexion");
            }   
        }     
    }
}
