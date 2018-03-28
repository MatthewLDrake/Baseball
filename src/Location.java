import java.util.Random;

public class Location
{
	public String city, state;
	private boolean isState;
	public Location(String city, String state)
	{
		this.city = city;
		this.state = state;
	}
	public boolean isState()
	{
		return isState;
	}
	public String getLocation()
	{
		Random r = new Random();
		if(r.nextInt(10) == 5)isState = true;
		else isState = false;
		
		if(isState)return state;
		else return city;
	}
	
}
