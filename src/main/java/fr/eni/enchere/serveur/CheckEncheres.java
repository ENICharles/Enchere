package fr.eni.enchere.serveur;

import java.time.LocalDate;
import java.util.List;
import java.util.TimerTask;

import fr.eni.enchere.bll.BllException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.enchere.EnchereFactory;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.EtatVente;

public class CheckEncheres extends TimerTask
{
	EnchereManager 		enchereMng 	= null;
	List<ArticleVendu>  liste 		= null;
	Boolean				evolution	= true;
		
	@Override
	public void run()
	{
		enchereMng 	= EnchereFactory.getManager();
		
		try
		{
			liste = enchereMng.getArticleVendus(0, "Toutes", 0, "");
				
			for(ArticleVendu article :liste)
			{
				if(LocalDate.now().isAfter(article.getDateDebutEnchere()))
				{
					if(LocalDate.now().isBefore(article.getDateFinEnchere())==false)
					{
						if(article.getEtatVente() != EtatVente.TERMINEE)
						{
							article.setEtatVente(EtatVente.TERMINEE.name());
							evolution = true;
						}
						else
						{
							//TODO : envoie d'un mail au vainqueur
							//modification du possesseur
						}
					}
					else
					{
						if(article.getEtatVente() != EtatVente.EN_COURS)
						{	
							article.setEtatVente(EtatVente.EN_COURS.name());
							evolution = true;
						}
					}
				}
				else
				{
					if(article.getEtatVente() != EtatVente.CREE)
					{
						article.setEtatVente(EtatVente.CREE.name());
						evolution = true;
					}
				}
				
				if(evolution == true)
				{
					enchereMng.updateEnchereEtatVente(article);
					evolution = false;
				}
			}
		}
		catch (BllException e)
		{
			e.getStackTrace();
			System.out.println("pb sur scheduleur " + e.getMessage());
		}
	}
}
