import java.util.HashMap;

public class adultPitcher extends adultPlayer implements pitcher
{
	private HashMap<pitchType, pitcherPitchRatings> pitches;
	private double staminaRating, staminaRemaining;
	private int outs, runsAllowed, earnedRunsAllowed;
	public adultPitcher(HashMap<pitchType, pitcherPitchRatings> pitches, String name,HashMap<pitchType, batterPitchRatings> battingRatings, double speedRating, double fieldingRating, double throwPower, double throwAccuracy, double staminaRating, int pos)
	{
		super(name,battingRatings, speedRating, fieldingRating, throwPower, throwAccuracy, staminaRating, pos, null);
		this.pitches = pitches;
		staminaRemaining = 100;
		this.staminaRating = staminaRating;
	}
	public adultPitcher()
	{
		super(0);
		pitches = new HashMap<pitchType, pitcherPitchRatings>();
		pitches.put(pitchType.FOUR_SEAM_FASTBALL, new pitcherPitchRatings(90, 50, 50, 50));
		staminaRemaining = 100;
		this.staminaRating = 50;
		
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
	public void addOut()
	{
		outs++;
	}
	public void addRun(boolean earned)
	{
		addRun(1, earned);
	}
	public void addRun(int i, boolean earned)
	{
		runsAllowed += i;
		if(earned) earnedRunsAllowed += i;
	}
	public String getERA()
	{
		String retVal = "";
		retVal = "" + 27.0/outs*earnedRunsAllowed;
		return retVal;
	}
}
