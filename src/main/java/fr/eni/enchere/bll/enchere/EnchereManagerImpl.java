package fr.eni.enchere.bll.enchere;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DAOException;
import fr.eni.enchere.dao.EnchereManagerDAO;
import fr.eni.enchere.dao.enchere.EnchereFactory;

public class EnchereManagerImpl implements EnchereManager
{
	private EnchereManagerDAO enchereDAO = null;

	public EnchereManagerImpl()
	{
		super();
		
		enchereDAO = (EnchereManagerDAO) EnchereFactory.getDao();
	}
	
	/**
	 * R�cup�re la liste des article : si idUser=0 alors r�cup�rer toutes les encheres sinon seulement les ench�re de l'utilisateur
	 * retourne null si pas d'article
	 *  throws BllException
	 * @throws BllException 
	 */
	@Override
	public Enchere getEncheresByIdArticle(int idArticle) throws BllException
	{
		Enchere 	enchere = null;
		
		try
		{
			enchere = enchereDAO.getEncheresByIdArticle(idArticle);
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture de l'enchere de l'article (" + idArticle + ")");
		}
		
		return enchere;
	}
	
	/**
	 * R�cup�re la liste des article : si idUser=0 alors r�cup�rer toutes les encheres sinon seulement les ench�re de l'utilisateur
	 * retourne null si pas d'article
	 *  throws BllException
	 */
	@Override
	public List<Enchere> getEncheres(int idUser) throws BllException
	{
		List<Enchere> lst = null;
		
		try
		{
			lst = enchereDAO.getEncheres(idUser);
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture des encheres");
		}
		return lst;
	}
	
	/**
	 * R�cup�re la liste des article : si idUser=0 alors r�cup�rer toutes les encheres sinon seulement les ench�re de l'utilisateur
	 * retourne null si pas d'article
	 *  throws BllException
	 */
	@Override
	public List<ArticleVendu> getArticleVendus(int idUser,String filtre,int categorie,String article) throws BllException
	{
		List<ArticleVendu> lst = null;
		
		try
		{
			lst = enchereDAO.getArticleVendus(idUser,filtre,categorie,article);
			
			if(lst != null)
			{
				for(ArticleVendu art : lst)
				{
					//System.out.println(LocalDate.now());
					//System.out.println(art.getDateDebutEnchere());
					long debut 	= ChronoUnit.DAYS.between(LocalDate.now(), art.getDateDebutEnchere());
					long fin 	= ChronoUnit.DAYS.between(LocalDate.now(), art.getDateFinEnchere());
					
					/* date de d�but pass� et date de fin pas pass� => encoure */
					
					/* date de fin pass� => termin� */
					
					/* date de fin pass� => termin� */
					
					if(fin<=0)
					{
						//System.out.println("termin�");
						art.setEtatVente(EtatVente.TERMINEE.toString());					
					}
					else
					{
						if(debut<=0)
						{
							//System.out.println("encours");
							art.setEtatVente(EtatVente.EN_COURS.toString());
						}
						else
						{
							//System.out.println("cree");
							art.setEtatVente(EtatVente.CREE.toString());
						}
					}
				}
			}
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture des articles vendus");
		}
		return lst;
	}

	
	/**
	 * R�cup�re la liste des cat�gories
	 *  throws BllException
	 */
	@Override
	public List<Categorie> getCategories() throws BllException 
	{
		List<Categorie> lst = null;
		
		try
		{
			lst = enchereDAO.getCategories();
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture des cat�gories " + e.getMessage());
		}
		
		return lst;
	}
	
	/**
	 * Ajout d'un article a vendre
	 *  throws BllException
	 */
	@Override
	public void createArticle(int idUser,ArticleVendu article)throws BllException
	{
		try
		{
			enchereDAO.createArticle(idUser,article);
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture des cat�gories " + e.getMessage());
		}
	}
	
	/**
	 * Cr�ation d'une enchere
	 *  TODO : ajouter le/les contr�les (ex:article null?)
	 *  throws BllException
	 */
	@Override
	public void createEnchere(int idNewEnchereur,int idArticle,int montant)throws BllException
	{
		Enchere enchere = null;
		
		try
		{
			/* r�cup�ration de l'article point� par l'id */
			ArticleVendu article = enchereDAO.getArticle(idArticle);

			/* r�cup�re les ench�res de larticle */
			Enchere enchereArticle = this.getEncheresByIdArticle(idArticle);

			/* r�cup�ration de l'utilisateur */
			Utilisateur enchereur = enchereDAO.getUtilisateurId(idNewEnchereur);
			
			/* d�bit de l'enchereur si possible */
			if(enchereur.getCredit()>=montant)
			{
				/* cr�ation de l'ench�re sur l'article il n'y en � pas daj� de existante */
				if(enchereArticle == null)
				{
					/* cr�ation de l'enchere */
					enchere = new Enchere(article);
					enchere.setMontant_enchere(montant);
					enchere.setEnchereur(idNewEnchereur);
					enchere.setDateEnchere(LocalDate.now());
					
					enchereDAO.createEnchere(enchere);
					
					enchereDAO.updateUserCredit(idNewEnchereur,enchereur.getCredit()-montant);
				}
				else
				{
					/* ne permet que un nouveau enchereur */
					if(idNewEnchereur != enchereArticle.getEnchereur())
					{
						/* recredit du pr�c�dent */
						Utilisateur enchereurOld = enchereDAO.getUtilisateurId(enchereArticle.getEnchereur());
						enchereDAO.updateUserCredit(enchereurOld.getNoUtilisateur(),enchereurOld.getCredit()+enchereArticle.getMontant_enchere());
						
						/* d�bit du nouveau */
						enchereDAO.updateUserCredit(idNewEnchereur,enchereur.getCredit()-montant);
						enchereArticle.setEnchereur(idNewEnchereur);
	
						enchereArticle.setMontant_enchere(montant);
						enchereArticle.setEnchereur(idNewEnchereur);
						enchereArticle.setDateEnchere(LocalDate.now());
						
						/* mise � jour de l'ench�re dans la base */
						enchereDAO.updateEnchere(enchereArticle);
					}
					else
					{
						throw new BllException("Vous avez propos� l'offre la plus haute, vous ne pouvez pas refaire cette ench�re");
					}
				}
			}
			else
			{
				throw new BllException("Cr�dit inssufisant (" + enchereur.getCredit() + ")");
			}
		}
		catch (DAOException e)
		{
			throw new BllException("Pb sur la lecture des cat�gories " + e.getMessage());
		}
	}
}
