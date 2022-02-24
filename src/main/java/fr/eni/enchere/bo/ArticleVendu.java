/**
 * 
 */
package fr.eni.enchere.bo;

import java.time.LocalDate;

/**
 * @author Chris
 *
 */
public class ArticleVendu 
{
	private int 		noArticle			= 0;
	private String 		nomArticle			= "";
	private String 		description			= "";
	private LocalDate 	dateDebutEnchere	= LocalDate.now();
	private LocalDate 	dateFinEnchere		= LocalDate.now();
	private int 		miseAPrix			= 0;
	private int 		prixVente			= 0;
	private EtatVente 	etatVente			= null;
	private Categorie	categorie			= new Categorie();
	private Retrait 	retrait				= null;
	private int			idPossesseur		= 0;
	
	/**
	 * Constructeur ArticleVendu par default
	 */
	public ArticleVendu() {
		
	}
	
	/**
	 * Constructeur ArticleVendu prenant en compte les champs des variables
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEnchere
	 * @param dateFinEnchere
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 */
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere, int miseAPrix, int prixVente, EtatVente etatVente,Categorie categorie,Retrait retrait) {
		super();
		this.nomArticle 		= nomArticle;
		this.description 		= description;
		this.dateDebutEnchere 	= dateDebutEnchere;
		this.dateFinEnchere 	= dateFinEnchere;
		this.miseAPrix 			= miseAPrix;
		this.prixVente 			= prixVente;
		this.etatVente 			= etatVente;
		this.categorie 			= categorie;
		this.retrait 			= retrait;
	}
	
	/**
	 * Constructeur ArticleVendu prenant en compte les champs des variables
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEnchere
	 * @param dateFinEnchere
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere, int miseAPrix, int prixVente, EtatVente etatVente,Categorie categorie,Retrait retrait) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.retrait = retrait;
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
	public LocalDate getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	/**
	 * SETTER dateDebutEnchere
	 * @param dateDebutEnchere the dateDebutEnchere to set
	 */
	public void setDateDebutEnchere(LocalDate dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	/**
	 * GETTER dateFinEnchere
	 * @return the dateFinEnchere
	 */
	public LocalDate getDateFinEnchere() {
		return dateFinEnchere;
	}

	/**
	 * SETTER dateFinEnchere
	 * @param dateFinEnchere the dateFinEnchere to set
	 */
	public void setDateFinEnchere(LocalDate dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	/**
	 * GETTER miseAPrix
	 * @return the miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * SETTER miseAPrix
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * GETTER prixVente
	 * @return the prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * SETTER prixVente
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(int prixVente) {
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
	public void setEtatVente(String etatVente) {
		switch(etatVente)
		{
			case "CREE": 	 this.etatVente = EtatVente.CREE; break;
			case "EN_COURS": this.etatVente = EtatVente.EN_COURS; break;
			case "TERMINEE": this.etatVente = EtatVente.TERMINEE; break;
		}
	}

	public Categorie getCategorie()
	{
		return categorie;
	}

	public void setCategorie(int numCategorie,String libCategorie)
	{
		this.categorie.setNoCategorie(numCategorie);
		this.categorie.setLibelle(libCategorie);
	}

	public Retrait getRetrait()
	{
		return retrait;
	}

	public void setRetrait(Retrait retrait)
	{
		this.retrait = retrait;
	}

	public int getIdPossesseur()
	{
		return idPossesseur;
	}

	public void setIdPossesseur(int idPossesseur)
	{
		this.idPossesseur = idPossesseur;
	}
}
