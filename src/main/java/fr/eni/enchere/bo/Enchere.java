package fr.eni.enchere.bo;

import java.time.LocalDateTime;

public class Enchere
{
	private LocalDateTime dateEnchere	= LocalDateTime.now();
	private float montant_enchere		= 0.00f;
	
	
	/**
	 *  Constructeur Enchere par default
	 **/
	public Enchere() {
	
	}
		
	/**
	 * Constructeur Enchere prenant en compte les champs des variables
	 * @param dateEnchere
	 * @param montant_enchere
	 */
	public Enchere(LocalDateTime dateEnchere, float montant_enchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	/**
	 * GETTER dateEnchere
	 * @return the dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * SETTER dateEnchere
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * GETTER montant_enchere
	 * @return the montant_enchere
	 */
	public float getMontant_enchere() {
		return montant_enchere;
	}

	/**
	 * SETTER montant_enchere
	 * @param montant_enchere the montant_enchere to set
	 */
	public void setMontant_enchere(float montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	
}
