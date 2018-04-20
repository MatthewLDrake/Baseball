import java.util.HashMap;

public interface pitcher
{
	public HashMap<pitchType, pitcherPitchRatings> getPitches();

	public pitcherPitchRatings getPitchRatings(pitchType selectedPitch);
	public double getStaminaRating();
	public double getStaminaRemaining();

	public double getPitchOverall(pitchType pitch);

	public void addRun(boolean earned);
	public void addRun(int i, boolean earned);
	public void addOut();
	public String getERA();
	
	
}
