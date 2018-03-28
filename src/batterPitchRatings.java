
public class batterPitchRatings
{
	private double goodSwingRating, badTakeRating, contactValue;
	private double atBats, hits;
	private double powerRating, groundballPercent;
	public batterPitchRatings(double goodSwingRating, double badTakeRating, double contactValue, double powerValue, double groundballPercent)
	{
		this.goodSwingRating = goodSwingRating;
		this.badTakeRating = badTakeRating;
		this.contactValue = contactValue;
		atBats = 0;
		hits = 0;
	}
	public double getGroundballPercent()
	{
		return groundballPercent;
	}
	public double getPowerRating()
	{
		return powerRating;
	}
	public void addAtBat(boolean hit)
	{
		if(hit)hits++;
		atBats++;
	}
	public double getAverage()
	{
		return hits/atBats;
	}
	public double getGoodSwingRating()
	{
		return goodSwingRating;
	}
	public double getBadTakeRating()
	{
		return badTakeRating;
	}
	public double getContactValue()
	{
		return contactValue;
	}
}
