import java.util.ArrayList;

public interface player extends Comparable<player>
{

	public void resetGameStats();

	public double getBattingAverageVs(pitchType key);
	public double getBattingAverage();
	public int getTotalAtBats();
	public int getTotalHits();
	public int getHomeRuns();
	public int getDoubles();
	public int getTriples();
	public int getStrikeOuts();
	public int getWalks();
	public double getOnBasePercentage();
	public int getRBIs();
	public batterPitchRatings getBatterRatingsVs(pitchType selectedPitch);
	
	public double getSpeedRating();
	public double getThrowPower();
	public double getThrowAccuracy();
	public double getFielding();
	public void addAtBat(atBatResult type, pitchType lastPitch, int rbis);

	public int getPosition();
	public ArrayList<Integer> getSecondaryPositions();
	public int getOverall(int pos);
	public void setPositionToOrderBy(int pos);
	public double getStaminaRating();
	public double getStaminaRemaining();
}
