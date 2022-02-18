/**
 * 
 */
package fr.eni.enchere.bo;

/**
 * @author Chris
 *
 */
public class Categorie {
	
	private int noCategorie	= 0;
	private String libelle	= "";

	/**
	 *  Constructeur Categorie par default
	 **/
	public Categorie() {
	
	}

	/**
	 * Constructeur Categorie prenant en compte les champs des variables noCategorie et libelle
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	/**
	 * GETTER noCategorie
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}

	/**
	 * SETTER noCategorie
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * GETTER libelle
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * SETTER libelle
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
}
