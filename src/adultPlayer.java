import java.util.ArrayList;
import java.util.HashMap;

public class adultPlayer implements player
{
	private HashMap<pitchType, batterPitchRatings> pitchStats;
	// 0 is speed, 1 is fielding, 2 is throw power, 3 is throw accuracy, 4 is stamina
	private double[] ratings;
	// 0 is atBats, 1 is hits, 2 is doubles, 3 is triples, 4 is Home Runs, 5 is RBI's, 6 is Strikeouts, 7 is walks
	private int[] stats;
	private int pos;
	private ArrayList<Integer> secondaryPositions;
	private double overall;
	private String name;
	private double staminaRemaining;
	public adultPlayer(String name,HashMap<pitchType, batterPitchRatings> pitchStats, double speedRating, double fieldingRating, double throwPower, double throwAccuracy, double staminaRating, int pos, ArrayList<Integer> secondaryPositions)
	{
		this.pitchStats = pitchStats;
		this.name = name;
		ratings = new double[5];
		ratings[0] = speedRating;
		ratings[1] = fieldingRating;
		ratings[2] = throwPower;
		ratings[3] = throwAccuracy;
		ratings[4] = staminaRating;
				
		staminaRemaining = 100;
		
		stats = new int[8];
		for(int i = 0; i < stats.length; i ++)
		{
			stats[i] = 0;
		}
		this.pos = pos;
		this.secondaryPositions = secondaryPositions;
		overall = 50;
	}
	public String toString()
	{
		return name;
	}
	// random constructor, for testing
	public adultPlayer(int pos)
	{
		pitchStats = new HashMap<pitchType, batterPitchRatings>();
		pitchStats.put(pitchType.FOUR_SEAM_FASTBALL, new batterPitchRatings(50, 50, 50, 50, 50));
		ratings = new double[5];
		stats = new int[8];
		for(int i = 0; i < stats.length; i ++)
		{
			stats[i] = 0;
		}
		ratings[0] = 50;
		ratings[1] = 50;
		ratings[2] = 50;
		ratings[3] = 50;
		ratings[4] = 50;
		
		staminaRemaining = 100;
		this.pos = pos;
		secondaryPositions = new ArrayList<Integer>();
		overall = 50;
	}
	public double getStaminaRating()
	{
		return ratings[4];
	}
	public double getStaminaRemaining()
	{
		return staminaRemaining;
	}
	public ArrayList<Integer> getSecondaryPositions()
	{
		return secondaryPositions;
	}
	public void resetGameStats()
	{
		return;
	}
	public batterPitchRatings getRatingsVsPitch(pitchType type)
	{
		return pitchStats.get(type);
	}
	@Override
	public double getBattingAverageVs(pitchType key)
	{
		
		return pitchStats.get(key).getAverage();
	}
	public batterPitchRatings getBatterRatingsVs(pitchType selectedPitch)
	{
		return pitchStats.get(selectedPitch);
	}
	public void addAtBat(atBatResult type, pitchType lastPitch, int rbis)
	{
		stats[5] += rbis;
		if(type == atBatResult.WALK)
		{
			stats[7]++;
			return;
		}
		else if(type == atBatResult.SACRIFICE)return;
		stats[0]++;
		if(type == atBatResult.STRIKEOUT)
		{
			stats[6]++;
			pitchStats.get(lastPitch).addAtBat(false);
			return;
		}
		else if(type == atBatResult.OUT || type == atBatResult.ERROR)
		{
			pitchStats.get(lastPitch).addAtBat(false);
			return;
		}
		else 
		{
			pitchStats.get(lastPitch).addAtBat(true);
			stats[1]++;
			switch(type)	
			{
			case DOUBLE:
				stats[2]++;
				break;
			case TRIPLE:
				stats[3]++;
				break;
			case HOME_RUN:
				stats[4]++;
				break;
			default:
				break;
			}
		}
	}
	@Override
	public double getBattingAverage()
	{
		return ((double)stats[1])/((double)stats[0]);
	}

	@Override
	public int getTotalAtBats()
	{
		return stats[0];
	}

	@Override
	public int getTotalHits()
	{
		return stats[1];
	}

	@Override
	public int getHomeRuns()
	{
		return stats[4];
	}

	@Override
	public double getSpeedRating()
	{
		return ratings[0];
	}

	@Override
	public double getThrowPower()
	{
		return ratings[2];
	}

	@Override
	public double getThrowAccuracy()
	{
		return ratings[3];
	}

	@Override
	public double getFielding()
	{
		return ratings[1];
	}
	@Override
	public int getDoubles()
	{
		return stats[2];
	}

	@Override
	public int getTriples()
	{
		return stats[3];
	}

	@Override
	public int getStrikeOuts()
	{
		return stats[6];
	}

	@Override
	public int getWalks()
	{
		return stats[7];
	}

	@Override
	public double getOnBasePercentage()
	{
		return ((double)stats[1]+stats[7])/((double)stats[0]+stats[7]);
	}

	@Override
	public int getRBIs()
	{
		return stats[5];
	}
	public int getPosition()
	{
		return pos;
	}
	// TODO: Fix this
	public int getOverall(int position)
	{
		return (int) Math.round(overall);
	}
	public void setPositionToOrderBy(int pos)
	{
		posToSortBy = pos;
	}
	private int posToSortBy;
	@Override
	public int compareTo(player o)
	{
		return this.getOverall(posToSortBy) - o.getOverall(posToSortBy);
	}
}
