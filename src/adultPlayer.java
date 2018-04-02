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
	private int intendedLeague;
	public void setIntendedLeague(int i)
	{
		intendedLeague = i;
	}
	public int getIntendedLeague()
	{
		return intendedLeague;
	}
	public String printRatings()
	{
		String str = "";
		for(int i =0; i < ratings.length; i++)
		{
			str += ratings[i] + " ";
		}
		for(pitchType rating : pitchType.values())
		{
			str += " " + rating.toString() + " " + pitchStats.get(rating).toString();
		}
		return str;
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
		overall = 0;
		if(position == -1)
		{
			for(int i = 0; i < 11; i++)
			{
				overall = Math.max(overall, getOverall(i));
			}
		}
		else
		{
			double averageContactRating = 0;
			double averagePowerRating = 0;
			double averageSwingRating = 0;
			double averageTakeRating = 0;
			int count = 0;
			for(pitchType type : pitchType.values())
			{
				averageContactRating += pitchStats.get(type).getContactValue();
				averagePowerRating += pitchStats.get(type).getPowerRating();
				averageSwingRating += pitchStats.get(type).getGoodSwingRating();
				averageTakeRating += pitchStats.get(type).getBadTakeRating();
				count++;
				
			}
			averageContactRating = averageContactRating/count;
			averagePowerRating = averagePowerRating/count;
			averageSwingRating = averageSwingRating/count;
			averageTakeRating = averageTakeRating/count;
			
			double contactModifier = 0, powerModifier = 0, swingModifier = 0, takeModifier = 0, fieldingModifier = 0, throwPowerModifier = 0, throwAccuracyModifier = 0, staminaModifier = 0, speedModifier = 0;
			double positionModifier = .75;
			
			if(position == pos)
			{
				positionModifier = 1;
			}
			else if(secondaryPositions != null)
			{
				for(int i = 0; i < secondaryPositions.size(); i++)
				{
					if(position == secondaryPositions.get(i))
					{
						positionModifier = .95;
					}
				}
			}
			
			switch(position)
			{
			// First base
			case 1:
				contactModifier = .25; 
				powerModifier = .15;
				swingModifier = .05; 
				takeModifier = .05; 
				fieldingModifier = .3;
				throwPowerModifier = .025;
				throwAccuracyModifier = .025;
				staminaModifier = .15;
				speedModifier = .025;				
					break;
			// Second Base
			case 2:
				contactModifier = .2; 
				powerModifier = .1;
				swingModifier = .05; 
				takeModifier = .05; 
				fieldingModifier = .3;
				throwPowerModifier = .05;
				throwAccuracyModifier = .2;
				staminaModifier = .15;
				speedModifier = .025;	
				break;
			// Third Base
			case 3:
				contactModifier = .25; 
				powerModifier = .15;
				swingModifier = .05; 
				takeModifier = .05; 
				fieldingModifier = .25;
				throwPowerModifier = .15;
				throwAccuracyModifier = .15;
				staminaModifier = .15;
				speedModifier = .025;	
				break;
			// Short Stop
			case 4:
				contactModifier = .25; 
				powerModifier = .15;
				swingModifier = .05; 
				takeModifier = .05; 
				fieldingModifier = .25;
				throwPowerModifier = .125;
				throwAccuracyModifier = .175;
				staminaModifier = .15;
				speedModifier = .025;	
				break;
			// Left Field
			case 5:
				contactModifier = .225; 
				powerModifier = .175;
				swingModifier = .05; 
				takeModifier = .05; 
				fieldingModifier = .2;
				throwPowerModifier = .2;
				throwAccuracyModifier = .15;
				staminaModifier = .15;
				speedModifier = .025;	
				break;
			// Right Field
			case 6:
				contactModifier = .25; 
				powerModifier = .2;
				swingModifier = .075; 
				takeModifier = .075; 
				fieldingModifier = .1;
				throwPowerModifier = .2;
				throwAccuracyModifier = .15;
				staminaModifier = .15;
				speedModifier = .025;	
				break;
			// Center Field
			case 7:
				contactModifier = .25; 
				powerModifier = .15;
				swingModifier = .05; 
				takeModifier = .05; 
				fieldingModifier = .2;
				throwPowerModifier = .175;
				throwAccuracyModifier = .15;
				staminaModifier = .15;
				speedModifier = .05;	
				break;
			// Catcher
			case 8:
				contactModifier = .25; 
				powerModifier = .15;
				swingModifier = .05; 
				takeModifier = .05; 
				fieldingModifier = .1;
				throwPowerModifier = .225;
				throwAccuracyModifier = .225;
				staminaModifier = .15;
				speedModifier = .025;	
				break;
			// Starting Pitcher
			case 9:
				if(!(this instanceof pitcher))return 0;
				contactModifier = .025; 
				powerModifier = .025;
				swingModifier = .025; 
				takeModifier = .025; 
				fieldingModifier = .05;
				throwPowerModifier = .075;
				throwAccuracyModifier = .075;
				staminaModifier = .1;
				speedModifier = .025;	
				// .8 for pitching
				pitcher startingPitcher = (pitcher) this;
				double pitchRatings = 0;
				
				HashMap<pitchType, pitcherPitchRatings> pitches = startingPitcher.getPitches();
				
				count = 0;
				for(pitchType pitch : pitches.keySet())
				{
					pitchRatings += startingPitcher.getPitchOverall(pitch);
					count++;
				}
				
				overall = startingPitcher.getStaminaRating() * .3 + pitchRatings * .5;
				break;
			// Relief Pitcher
			case 10:
				if(!(this instanceof pitcher))return 0;

				fieldingModifier = .05;
				throwPowerModifier = .075;
				throwAccuracyModifier = .075;
				staminaModifier = .1;
				speedModifier = .025;	
				// .9 for pitching
				pitcher relief = (pitcher) this;
				double reliefPitchRatings = 0;
				
				HashMap<pitchType, pitcherPitchRatings> reliefPitches = relief.getPitches();
				
				count = 0;
				for(pitchType pitch : reliefPitches.keySet())
				{
					reliefPitchRatings += relief.getPitchOverall(pitch);
					count++;
				}
				reliefPitchRatings = reliefPitchRatings/count;
				
				overall = relief.getStaminaRating() * .2 + reliefPitchRatings * .7;
			}
			overall += ratings[0] * speedModifier + ratings[1] * fieldingModifier + ratings[2] * throwPowerModifier + ratings[3] * throwAccuracyModifier + ratings[4] * staminaModifier + averageContactRating * contactModifier + averagePowerRating * powerModifier + takeModifier * averageTakeRating + swingModifier * averageSwingRating;
			overall = overall*positionModifier;
		}
		return (int) Math.round(overall);
	}
	public String getPositionAsString()
	{
		switch(pos)
		{
		case 1:
			return "1B";
		case 2:
			return "2B";
		case 3:
			return "3B";
		case 4:
			return "SS";
		case 5:
			return "LF";
		case 6:
			return "RF";
		case 7:
			return "CF";
		case 8:
			return "C";
		case 9:
			return "SP";
		case 10:
			return "RP";
			
		}
		return "";
	}
	public void setPositionToOrderBy(int pos)
	{
		posToSortBy = pos;
	}
	private int posToSortBy;
	@Override
	public int compareTo(player o)
	{
		return o.getOverall(posToSortBy) - this.getOverall(posToSortBy);
	}
}
