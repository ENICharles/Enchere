package fr.eni.enchere.ihm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.enchere.EnchereFactory;
import fr.eni.enchere.bo.ArticleVendu;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
@MultipartConfig( fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5 )
public class UploadImage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public static final String IMAGES_FOLDER = "D:\\Projects\\Java\\GIT\\Enchere\\src\\main\\webapp\\images";
	public String uploadPath;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("getUpload");
		EnchereManager 	enchereMng 		= EnchereFactory.getManager();
		
		/* récupération et copie de l'image dans le dossier cible */
		Part 	filePart = request.getPart("fic");
	    String 	fileName = filePart.getSubmittedFileName();
	    
	    for (Part part : request.getParts()) 
	    {
	    	System.out.println("fichier " + fileName);
	    	part.write(IMAGES_FOLDER+"\\" + fileName);
	    }
	    
	    System.out.println("fichier à convertire " + IMAGES_FOLDER+"\\" + fileName );
	    
        File file = new File(IMAGES_FOLDER+"\\" + fileName);
	    String encodedString = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
	    
	    /* envoie de l'image en base */
		try
		{
			enchereMng.putPictureToBase(encodedString,((ArticleVendu)request.getSession().getAttribute("article")).getNoArticle());
		}
		catch (BllException e)
		{
			System.out.println("Erreur lecture des cat�gories");
		}
		
		
//		/* récupération de l'image */
//		String decodingString = "";
//		try
//		{
//			decodingString = enchereMng.getPictureToBase(1);
//		}
//		catch (BllException e)
//		{
//			System.out.println("Erreur lecture des cat�gories");
//		}
//		
//		/* comparaison des chaines */
//		if(encodedString.equals(decodingString))
//		{
//			System.out.println("ce sont les meme images");
//		}
//		else
//		{
//			System.out.println("images différentes");
//			byte[] enc = encodedString.getBytes();
//			byte[] dec = decodingString.getBytes();
//			
//			
//			for(int iloop=0;iloop<enc.length;iloop++)
//			{
//				
//				if(dec[iloop] != enc[iloop])
//				{
//					System.out.println("different byte : " + dec[iloop] + " au lieu de : " + enc[iloop]);
//				}
//			}
//		}
		
		RequestDispatcher rd = request.getRequestDispatcher("VendreArticle");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("postUpload");
		EnchereManager 	enchereMng 		= EnchereFactory.getManager();
		
		/* récupération et copie de l'image dans le dossier cible */
		Part 	filePart = request.getPart("fic");
	    String 	fileName = filePart.getSubmittedFileName();
	    
	    for (Part part : request.getParts()) 
	    {
	    	System.out.println("fichier " + fileName);
	    	part.write(IMAGES_FOLDER+"\\" + fileName);
	    }
	    
	    System.out.println("fichier à convertire " + IMAGES_FOLDER+"\\" + fileName );
	    
        File file = new File(IMAGES_FOLDER+"\\" + fileName);
	    String encodedString = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
	    
	    /* envoie de l'image en base */
		try
		{
			//enchereMng.putPictureToBase(encodedString,1);
			enchereMng.putPictureToBase(encodedString,((ArticleVendu)request.getSession().getAttribute("article")).getNoArticle());
		}
		catch (BllException e)
		{
			System.out.println("Erreur lecture des cat�gories");
		}
		
		
		/* récupération de l'image */
		String decodingString = "";
		try
		{
			decodingString = enchereMng.getPictureToBase(((ArticleVendu)request.getSession().getAttribute("article")).getNoArticle());
		}
		catch (BllException e)
		{
			System.out.println("Erreur lecture des cat�gories");
		}
		
		/* comparaison des chaines */
		if(encodedString.equals(decodingString))
		{
			System.out.println("ce sont les meme images");
		}
		else
		{
			System.out.println("images différentes");
			byte[] enc = encodedString.getBytes();
			byte[] dec = decodingString.getBytes();
			
			
			for(int iloop=0;iloop<enc.length;iloop++)
			{
				
				if(dec[iloop] != enc[iloop])
				{
					System.out.println("different byte : " + dec[iloop] + " au lieu de : " + enc[iloop]);
				}
			}
		}
		
		request.setAttribute("image1", encodedString);
		request.setAttribute("image2", decodingString);
		
		RequestDispatcher rd = request.getRequestDispatcher("VendreArticle");
		rd.forward(request, response);
	}
}
