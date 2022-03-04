package fr.eni.enchere.ihm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

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
//	public static final String IMAGES_FOLDER = "D:\\Projects\\Java\\GIT\\Enchere\\src\\main\\webapp\\images";
//	public String uploadPath;
	
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("postUpload");
			
		
//		Part filePart = request.getPart("file");
//	    String fileName = filePart.getSubmittedFileName();
//	    for (Part part : request.getParts()) 
//	    {
//	    	System.out.println("fichier " + fileName);
//	      part.write(IMAGES_FOLDER+"\\" + fileName);
//	    }
		
		
//		String imageEncoded= new String(Base64.encodeBase64(bytes), "UTF-8");
//		
//		byte[] fileContent = FileUtils.readFileToByteArray(new File("D:\\Projects\\Java\\GIT\\Enchere\\src\\main\\webapp\\images\\Variables.PNG"));
//		String encodedString = Base64.getEncoder().encodeToString(fileContent);
	}
	
//	  private String getFileName( Part part ) 
//	  {
//	        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) 
//	        {
//	            if ( content.trim().startsWith( "filename" ) )
//	                return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
//	        }
//	        return "Default.file";
//	  }
//	  
//	  private static String encodeFileToBase64Binary(File file)
//	  {
//          String encodedfile = null;
//          try 
//          {
//              FileInputStream fileInputStreamReader = new FileInputStream(file);
//              byte[] bytes = new byte[(int)file.length()];
//              fileInputStreamReader.read(bytes);
//              encodedfile = Base64.encodeBase64(bytes).toString();
//              
//          } 
//          catch (FileNotFoundException e) 
//          {
//              // TODO Auto-generated catch block
//              e.printStackTrace();
//          } catch (IOException e) 
//          {
//              // TODO Auto-generated catch block
//              e.printStackTrace();
//          }
//
//          return encodedfile;
//      }
}
