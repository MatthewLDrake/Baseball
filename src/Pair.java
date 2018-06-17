public class Pair
{
	public int x, y;
	public double dx, dy;
	public boolean conferenceGame;
	public Pair(int x, int y)
	{
		this.x = x;
		this.y = y;
		dx = x;
		dy = y;
		conferenceGame = false;
	}
	public Pair(double x, double y)
	{
		dx = x;
		dy = y;
		conferenceGame = false;
		
	}
	public Pair(int x, int y, boolean conference)
	{
		this.x = x;
		this.y = y;
		dx = x;
		dy = y;
		conferenceGame = conference;
	}
	
}