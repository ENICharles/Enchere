package fr.eni.enchere.bll.enchere;

import java.util.Timer;

import fr.eni.enchere.bll.CheckEncheres;

public class SchedulerTask
{
	private static SchedulerTask sc = null;
	
	private SchedulerTask()
	{
	    Timer timer;
	    timer = new Timer();
	    timer.schedule(new CheckEncheres(), 1000, 5000);
	}
	
	public static SchedulerTask getSchedulerTask()
	{
		if(SchedulerTask.sc == null)
		{
			System.out.println("nouveau timer");
			SchedulerTask.sc = new SchedulerTask();
		}
		
		return SchedulerTask.sc;		
	}
}
