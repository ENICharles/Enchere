/**
 * 
 */
package fr.eni.enchere.bo;

import java.time.LocalDateTime;

/**
 * @author Chris
 *
 */
public class ArticleVendu {
	
	private int noArticle					= 0;
	private String nomArticle				= "";
	private String description				= "";
	private LocalDateTime dateDebutEnchere	= LocalDateTime.now();
	private LocalDateTime dateFinEnchere	= LocalDateTime.now();
	private float miseAPrix					= 0.00f;
	private float prixVente					= 0.00f;
	private EtatVente etatVente				= null;
	
	
	/**
	 * Constructeur ArticleVendu par default
	 */
	public ArticleVendu() {
		
	}
	
	/**
	 * Constructeur Retrait prenant en compte les champs des variables
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEnchere
	 * @param dateFinEnchere
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, float miseAPrix, float prixVente, EtatVente etatVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}

	/**
	 * GETTER noArticle
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
	}

	/**
	 * SETTER noArticle
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 * GETTER nomArticle
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * SETTER nomArticle
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * GETTER description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * SETTER description 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * GETTER dateDebutEnchere
	 * @return the dateDebutEnchere
	 */
	public LocalDateTime getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	/**
	 * SETTER dateDebutEnchere
	 * @param dateDebutEnchere the dateDebutEnchere to set
	 */
	public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	/**
	 * GETTER dateFinEnchere
	 * @return the dateFinEnchere
	 */
	public LocalDateTime getDateFinEnchere() {
		return dateFinEnchere;
	}

	/**
	 * SETTER dateFinEnchere
	 * @param dateFinEnchere the dateFinEnchere to set
	 */
	public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	/**
	 * GETTER miseAPrix
	 * @return the miseAPrix
	 */
	public float getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * SETTER miseAPrix
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(float miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * GETTER prixVente
	 * @return the prixVente
	 */
	public float getPrixVente() {
		return prixVente;
	}

	/**
	 * SETTER prixVente
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}

		/**
	 * GETTER etatVente
	 * @return the etatVente
	 */
	public EtatVente getEtatVente() {
		return etatVente;
	}

	/**
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(EtatVente etatVente) {
		this.etatVente = etatVente;
	}
		
}
