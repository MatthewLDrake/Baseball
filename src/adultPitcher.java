import java.util.HashMap;

public class adultPitcher extends adultPlayer implements pitcher
{
    private HashMap<pitchType, pitcherPitchRatings> pitches;
    private double staminaRating, staminaRemaining;
    private int[] stats, careerStats;
    public adultPitcher(HashMap<pitchType, pitcherPitchRatings> pitches, String name,HashMap<pitchType, batterPitchRatings> battingRatings, double speedRating, double fieldingRating, double throwPower, double throwAccuracy, double staminaRating, int pos)
    {
	super(name,battingRatings, speedRating, fieldingRating, throwPower, throwAccuracy, staminaRating, pos, null);
	this.pitches = pitches;
	staminaRemaining = 100;
	this.staminaRating = staminaRating;
	
	stats = new int[5];
	careerStats = new int[5];
	
	for(int i = 0; i < stats.length; i++)
	{
	    stats[i] = 0;
	    careerStats[i] = 0;
	}
    }

    public double getStaminaRating()
    {
	return staminaRating;
    }
    public double getStaminaRemaining()
    {
	return staminaRemaining;
    }
    public HashMap<pitchType, pitcherPitchRatings> getPitches()
    {
	return pitches;
    }
    @Override
    public pitcherPitchRatings getPitchRatings(pitchType selectedPitch)
    {
	return pitches.get(selectedPitch);
    }
    @Override
    public double getPitchOverall(pitchType pitch)
    {
	pitcherPitchRatings temp = getPitchRatings(pitch);

	return (temp.getControl() + temp.getMovement())/2;
    }
    public void addOut(int amount)
    {
	stats[0] += amount;
    }
    public void addStrikeOut()
    {
	stats[1]++;
	stats[0]++;
    }
    public void addWalk()
    {
	stats[2]++;
    }
    public void addRun(boolean earned)
    {
	addRun(1, earned);
    }
    public void addRun(int i, boolean earned)
    {
	stats[3] += i;
	if(earned) stats[4] += i;
    }
    public String getERA()
    {
	String retVal = "";
	retVal = "" + 27.0/stats[0]*stats[4];
	return retVal;
    }
    public double getERAAsDouble()
    {
	if(stats[0] == 0)return 0;
	return 27.0/stats[0]*stats[4];
    }
    public Inning getInnings()
    {
	return new Inning(stats[0]/3, stats[0]%3);
    }
    public int getEarnedRuns()
    {
	return stats[4];
    }
    public int getRunsAllowed()
    {
	return stats[3];
    }
    @Override
    public int getWalksGiven()
    {
	return stats[2];
    }
    @Override
    public int getStrikeOutsPitched()
    {
	return stats[1];
    }
    @Override
    public void pitcherOffseason()
    {
	for(int i = 0; i < stats.length; i++)
	{
	    careerStats[i] += stats[i];
	    stats[i] = 0;
	    
	}	
    }
}
