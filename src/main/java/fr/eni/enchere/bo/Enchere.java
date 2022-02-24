package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Enchere
{
	private LocalDate 		dateEnchere		= LocalDate.now();
	private int			 	montant_enchere	= 0;
	private ArticleVendu 	articleVendu	= null;
	private int			 	idEnchereur		= 0;
	
	
	public Enchere() 
	{
			
	}
	/**
	 *  Constructeur Enchere par default
	 **/
	public Enchere(ArticleVendu articleVendu) 
	{
		this.articleVendu = articleVendu;	
	}
		
	/**
	 * Constructeur Enchere prenant en compte les champs des variables
	 * @param dateEnchere
	 * @param montant_enchere
	 */
	public Enchere(LocalDate dateEnchere, int montant_enchere,int enchereur) 
	{
		super();
		
		this.dateEnchere 	 = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.idEnchereur 		 = enchereur;
	}

	/**
	 * GETTER dateEnchere
	 * @return the dateEnchere
	 */
	public LocalDate getDateEnchere() 
	{
		return dateEnchere;
	}

	/**
	 * SETTER dateEnchere
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDate dateEnchere) 
	{
		this.dateEnchere = dateEnchere;
	}

	/**
	 * GETTER montant_enchere
	 * @return the montant_enchere
	 */
	public int getMontant_enchere() 
	{
		return montant_enchere;
	}

	/**
	 * SETTER montant_enchere
	 * @param montant_enchere the montant_enchere to set
	 */
	public void setMontant_enchere(int montant_enchere) 
	{
		this.montant_enchere = montant_enchere;
	}

	public ArticleVendu getArticleVendu()
	{
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu)
	{
		this.articleVendu = articleVendu;
	}
	public int getEnchereur()
	{
		return idEnchereur;
	}
	public void setEnchereur(int enchereur)
	{
		this.idEnchereur = enchereur;
	}
}
