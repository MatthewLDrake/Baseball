import java.util.HashMap;

public interface pitcher extends player
{
	public HashMap<pitchType, pitcherPitchRatings> getPitches();

	public pitcherPitchRatings getPitchRatings(pitchType selectedPitch);
	public double getStaminaRating();
	public double getStaminaRemaining();

	public double getPitchOverall(pitchType pitch);

	public void addRun(boolean earned);
	public void addRun(int i, boolean earned);
	public void addOut(int i);
	public String getERA();
	
	public Inning getInnings();
	public int getEarnedRuns();
	public int getRunsAllowed();

	public int getWalksGiven();

	public int getStrikeOutsPitched();
	public void addStrikeOut();
	public void addWalk();
	public double getERAAsDouble();
}
