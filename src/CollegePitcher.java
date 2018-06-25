import java.util.HashMap;

public class CollegePitcher extends CollegePlayer implements pitcher
{
	private HashMap<pitchType, pitcherPitchRatings> pitches;
    private double staminaRating, staminaRemaining;
    private int[] stats, careerStats;
    private int runsInCurrentGame;
    private boolean pitched;
    public CollegePitcher(HashMap<pitchType, pitcherPitchRatings> pitches, String name,HashMap<pitchType, batterPitchRatings> battingRatings, double speedRating, double fieldingRating, double throwPower, double throwAccuracy, double staminaRating, int pos, int age, int year, boolean redShirt, CollegeTeam college)
    {
	super(name,battingRatings, speedRating, fieldingRating, throwPower, throwAccuracy, staminaRating, pos, null, age, year, redShirt, college);
	this.pitches = pitches;
	staminaRemaining = 100;
	this.staminaRating = staminaRating;
	
	stats = new int[9];
	careerStats = new int[9];
	
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
    	runsInCurrentGame += i;
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
    public void addWin()
    {
    	stats[5]++;
    }
    public void addLoss()
    {
    	stats[6]++;
    }
    public int getWins()
    {
    	return stats[5];
    }
    public int getLosses()
    {
    	return stats[6];
    }
    public void pitchHappened()
    {
    	// TODO: this
    }
    public int getRunsAllowedInCurrentGame()
    {
    	return runsInCurrentGame;
    }
    public void endGame()
    {
    	super.endGame();
    	pitched = false;
    	runsInCurrentGame = 0;
    }

	@Override
	public void successfulSave()
	{
		stats[8]++;
		
	}

	@Override
	public void addSaveOpportunity()
	{
		stats[7]++;
		
	}
	public int getSaves()
	{
		return stats[8];
	}
	public int getBlownSaves()
	{
		return stats[7] - stats[8];
	}
	public void startedPitching()
	{
		pitched = true;
	}
	public boolean hasPitched()
	{
		return pitched;
	}
}
