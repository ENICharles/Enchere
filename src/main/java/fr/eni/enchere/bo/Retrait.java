/**
 * 
 */
package fr.eni.enchere.bo;

/**
 * @author Chris
 *
 */
public class Retrait {
	
	private String rue 			= "";
	private String code_postal	= "";
	private String ville		= "";
	
	
	
	/**
	 ** Constructeur Retrait par default
	 */
	public Retrait() {
		
	}
	
	/**
	 * Constructeur Retrait prenant en compte les champs des variables 
	 * @param rue
	 * @param code_postal
	 * @param ville
	 */
	public Retrait(String rue, String code_postal, String ville) {
		super();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	/**
	 * GETTER rue
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * SETTER rue
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * GETTER code_postal
	 * @return the code_postal
	 */
	public String getCode_postal() {
		return code_postal;
	}

	/**
	 * SETTER code_postal
	 * @param code_postal the code_postal to set
	 */
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	/**
	 * GETTER ville
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * SETTER ville
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

}
