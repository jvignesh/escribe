
public class TimeAlert extends Thread {
	public static long ms;
	
	public TimeAlert(long ms)
	{
		TimeAlert.ms = ms;
		this.start();
	}
	
	public void run()
	{
		try
		{
			while(ms-->0)
				sleep(1);
		}
		catch (Exception e)
		{
			System.out.println("Error at time alert: ");
			e.printStackTrace();
		}
	}
	
	public long getMS()
	{
		return TimeAlert.ms;
	}
}
