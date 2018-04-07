import java.util.Random;

public class BallInPlay
{
	public player firstBase, secondBase, thirdBase;
	public int runsScored;
	public atBatResult result;
	private pitchResult pitch;
	private currentTeam fielders;
	public int outs;
	private Random r;
	private player currentBatter;
	private pitchType type;
	public BallInPlay(pitchResult pitch, player currentBatter, player firstBase, player secondBase, player thirdBase,
			currentTeam fieldingTeam, int outs, pitchType type)
	{
		this.type = type;
		this.currentBatter = currentBatter;
		if(currentBatter == null)
		{
			System.err.println("fuck");
			System.exit(1);
		}
		this.firstBase = firstBase;
		this.secondBase = secondBase;
		this.thirdBase = thirdBase;
		r = new Random();
		this.pitch = pitch;
		this.fielders = fieldingTeam;

		start();

	}
	private void start()
	{
		switch (pitch)
		{
		case POPUP:
			popup();
			break;
		case FLYBALL:
			flyball(false);
			break;
		case STRONG_FLYBALL:
			flyball(true);
			break;
		case GROUNDBALL:
			groundball(false);
			break;
		case STRONG_GROUNDBALL:
			groundball(true);
			break;
		default:
			break;
		}
	}
	public void popup()
	{
		// infield fly rule
		if(firstBase != null && outs < 2)
		{
			result = atBatResult.OUT;
			outs++;
			return;
		}
		player fieldingPlayer = null;
		int num = r.nextInt(5);
		if(num == 0)
		{
			fieldingPlayer = fielders.getCatcher();
		}
		else if(num == 1)
		{
			fieldingPlayer = fielders.getFirstBase();
		}
		else if(num == 2)
		{
			fieldingPlayer = fielders.getSecondBase();
		}
		else if(num == 3)
		{
			fieldingPlayer = fielders.getShortStop();
		}
		else if(num == 4)
		{
			fieldingPlayer = fielders.getThirdBase();
		}
		else if(num == 5)
		{
			fieldingPlayer = fielders.getPitcherAsPlayer();
		}

		double timeToMeetingPoint = Math.abs(r.nextGaussian() * 2)+2;
		double distanceToBall = Math.abs(r.nextGaussian() * 10);

		double speed = fieldingPlayer.getSpeedRating();

		double timeLeft = timeToMeetingPoint - findTime(speed, distanceToBall);
		double runner = findTime(currentBatter.getSpeedRating(), 90) - (timeLeft+timeToMeetingPoint);
		double runnerFromFirst = -1, runnerFromSecond = -1, runnerFromThird = -1;

		if(outs == 2)
		{
			if(firstBase != null)
			{
				runnerFromFirst = findTime(firstBase.getSpeedRating(), 90) - (timeLeft+timeToMeetingPoint);
			}
			if(secondBase != null)
			{
				runnerFromSecond = findTime(secondBase.getSpeedRating(), 90) - (timeLeft+timeToMeetingPoint);
			}
			if(thirdBase != null)
			{
				runnerFromThird = findTime(thirdBase.getSpeedRating(), 90) - (timeLeft+timeToMeetingPoint);
			}
		}

		if(timeLeft >= 0)
		{
			if(tryToCatch(fieldingPlayer))
			{
				result = atBatResult.OUT;
			}
			else
			{
				if(firstBase != null)
				{
					runnerFromFirst = findTime(firstBase.getSpeedRating(), 90);
				}
				if(secondBase != null)
				{
					runnerFromSecond = findTime(secondBase.getSpeedRating(), 90);
				}
				if(thirdBase != null)
				{
					runnerFromThird = findTime(thirdBase.getSpeedRating(), 90);
				}
				if(runner > 0)runner -= .2;
				if(runnerFromFirst > 0)runnerFromFirst -= .2;
				if(runnerFromSecond > 0)runnerFromSecond -= .2;
				if(runnerFromThird > 0)runnerFromThird -= .2;
				fieldInfieldBall(fieldingPlayer, runner, runnerFromFirst, runnerFromSecond, runnerFromThird, true, 0);
			}
		}
		else
		{
			fieldInfieldBall(fieldingPlayer, runner, runnerFromFirst, runnerFromSecond, runnerFromThird, false, 0);
		}
	}
	private void fieldInfieldBall(player fieldingPlayer, double runner, double runnerFromFirst, double runnerFromSecond, double runnerFromThird, boolean potentialError, int fieldBallDifficulty)
	{
		while(!fieldBall(fieldingPlayer, fieldBallDifficulty))
		{
			potentialError = true;
			if(runner > 0)runner -= .2;
			if(runnerFromFirst > 0)runnerFromFirst -= .2;
			if(runnerFromSecond > 0)runnerFromSecond -= .2;
			if(runnerFromThird > 0)runnerFromThird -= .2;
		}
		if(runner > 0)runner -= .2;
		if(runnerFromFirst > 0)runnerFromFirst -= .2;
		if(runnerFromSecond > 0)runnerFromSecond -= .2;
		if(runnerFromThird > 0)runnerFromThird -= .2;

		double max = Math.max(Math.max(runner, runnerFromFirst), Math.max(runnerFromSecond, runnerFromThird));
		if(max > .2)
		{
			if(max == runner)
			{
				if(throwBall(fieldingPlayer, fielders.getFirstBase(),true))
				{
					outs++;
					result = atBatResult.OUT;
				}
				else
				{
					result = atBatResult.ERROR;
					if(thirdBase != null)
					{
						runsScored++;
						thirdBase = null;
					}
					if(secondBase != null)
					{
						thirdBase = secondBase;
						secondBase = null;
					}
					if(firstBase != null)
					{
						secondBase = firstBase;
						firstBase = null;
					}
					firstBase = currentBatter;
				}
			}
			else if(max == runnerFromFirst)
			{
				if(fieldingPlayer.equals(fielders.getSecondBase()))
				{
					if(throwBall(fieldingPlayer, fielders.getShortStop(), true))
					{
						outs++;
						result = atBatResult.OUT;
					}
					else
					{
						result = atBatResult.ERROR;
						if(thirdBase != null)
						{
							runsScored++;
							thirdBase = null;
						}
						if(secondBase != null)
						{
							thirdBase = secondBase;
							secondBase = null;
						}
						if(firstBase != null)
						{
							secondBase = firstBase;
							firstBase = null;
						}
						firstBase = currentBatter;
					}
				}
				else 
				{
					if(throwBall(fieldingPlayer, fielders.getSecondBase(), true))
					{
						outs++;
						result = atBatResult.OUT;
					}
					else
					{
						result = atBatResult.ERROR;
						if(thirdBase != null)
						{
							runsScored++;
							thirdBase = null;
						}
						if(secondBase != null)
						{
							thirdBase = secondBase;
							secondBase = null;
						}
						if(firstBase != null)
						{
							secondBase = firstBase;
							firstBase = null;
						}
						firstBase = currentBatter;
					}
				}
			}
			else if(max == runnerFromSecond)
			{
				if(throwBall(fieldingPlayer, fielders.getThirdBase(), true))
				{
					outs++;
					result = atBatResult.OUT;

				}
				else
				{
					result = atBatResult.ERROR;
					if(thirdBase != null)
					{
						runsScored++;
						thirdBase = null;
					}
					if(secondBase != null)
					{
						thirdBase = secondBase;
						secondBase = null;
					}
					if(firstBase != null)
					{
						secondBase = firstBase;
						firstBase = null;
					}
					firstBase = currentBatter;
				}
			}
			else
			{
				if(throwBall(fieldingPlayer, fielders.getThirdBase(), true))
				{
					outs++;
					result = atBatResult.OUT;
					if(outs != 3)
					{
						secondBase = null;
						if(thirdBase != null)runsScored++;
						if(firstBase != null)secondBase = firstBase;
						firstBase = currentBatter;


					}
				}
				else
				{
					result = atBatResult.ERROR;
					if(thirdBase != null)
					{
						runsScored++;
						thirdBase = null;
					}
					if(secondBase != null)
					{
						thirdBase = secondBase;
						secondBase = null;
					}
					if(firstBase != null)
					{
						secondBase = firstBase;
						firstBase = null;
					}
					firstBase = currentBatter;
				}
			}
		}
		else 
		{
			if(potentialError)result = atBatResult.ERROR;
			else result = atBatResult.SINGLE;

			if(thirdBase != null)
			{
				runsScored++;
				thirdBase = null;
			}
			if(secondBase != null)
			{
				thirdBase = secondBase;
				secondBase = null;
			}
			if(firstBase != null)
			{
				secondBase = firstBase;
				firstBase = null;
			}
			firstBase = currentBatter;
		}
	}
	private boolean throwBall(player throwingPlayer, player catchingPlayer, boolean infieldThrow)
	{
		if(throwingPlayer.equals(catchingPlayer))return true;

		double throwCatchRating = throwingPlayer.getThrowAccuracy() * 4 + catchingPlayer.getFielding();
		throwCatchRating /= 5;

		if(infieldThrow)
		{
			throwCatchRating += 25;
			return r.nextInt(100) < throwCatchRating;
		}

		return false;
	}
	private boolean fieldBall(player fieldingPlayer, int i)
	{
		// easy
		if(i == 0)
		{
			return r.nextInt(100) > 95;
		}
		return false;
	}
	private boolean tryToCatch(player fieldingPlayer)
	{
		double a = -.0082;
		double b = 1.51;
		double c = 30;

		double result = a * Math.pow(fieldingPlayer.getFielding(), 2) + b*fieldingPlayer.getFielding() + c;

		if(r.nextInt(100) > result)return false;
		return true;
	}
	private double findTime(double speed, double distanceToBall)
	{
		double feetPerSecond = (3/50) * speed + 24 + r.nextDouble()-.5;
		double result = distanceToBall / feetPerSecond;
		return result;
	}
	private void flyball(boolean strong)
	{
		double power = currentBatter.getBatterRatingsVs(type).getPowerRating();
		double distanceTravelled = findDistance(power, strong);
		if(distanceTravelled > 350)
		{
			result = atBatResult.HOME_RUN;
			runsScored = 1;
			if(firstBase != null)runsScored++;
			if(secondBase != null)runsScored++;
			if(thirdBase != null)runsScored++;
			
			firstBase=  null;
			secondBase = null;
			thirdBase = null;
			return;
		}
		Pair locationOfBall = new Pair(Math.max(-300, Math.min(300, r.nextGaussian() * 100 + 50)) ,distanceTravelled);
		

		player fieldingPlayer = null;
		Pair locationOfPlayer = null;
		if(locationOfBall.x < -75)
		{
			fieldingPlayer = fielders.getRightField();
			locationOfPlayer = new Pair(-150, 225);
		}
		else if(locationOfBall.x < 75)
		{
			fieldingPlayer = fielders.getCenterField();
			locationOfPlayer = new Pair(0, 225);
		}
		else 
		{
			fieldingPlayer = fielders.getLeftField();
			locationOfPlayer = new Pair(150, 225);
		}

		double timeToMeetingPoint = Math.abs(r.nextGaussian() * 2)+5.5;
		double distanceToBall = distanceFormula(locationOfBall, locationOfPlayer);

		double speed = fieldingPlayer.getSpeedRating();
		double timeFielderRuns = findTime(speed, distanceToBall);
		double timePassed = Math.min(timeToMeetingPoint, timeFielderRuns);
		double timeLeft = timeToMeetingPoint - timeFielderRuns;
		double runner = findTime(currentBatter.getSpeedRating(), 90) - (timePassed);
		double runnerFromFirst = -1, runnerFromSecond = -1, runnerFromThird = -1;
		player playerFromFirst = firstBase, playerFromSecond = secondBase, playerFromThird = thirdBase;
		if(outs == 2)
		{
			if(thirdBase != null)
			{
				runnerFromThird = findTime(thirdBase.getSpeedRating(), 90) - timePassed;
				if(runnerFromThird < 0)
				{
					runsScored++;
					thirdBase = null;
				}
			}
			if(secondBase != null)
			{
				runnerFromSecond = findTime(secondBase.getSpeedRating(), 90) - timePassed;
				if(runnerFromSecond < 0)
				{
					runnerFromSecond = findTime(secondBase.getSpeedRating(),90) + runnerFromSecond;
					thirdBase = playerFromSecond;
					secondBase = null;
				}
			}
			if(firstBase != null)
			{
				runnerFromFirst = findTime(firstBase.getSpeedRating(), 90) - timePassed;
				if(runnerFromFirst < 0)
				{
					runnerFromFirst = findTime(firstBase.getSpeedRating(),90) + runnerFromFirst;
					secondBase = playerFromSecond;
					firstBase = null;
				}
			}
			
			
		}
		if(timeLeft > 0)
		{
			if(tryToCatch(fieldingPlayer))
			{
				result = atBatResult.OUT;
			}
			else
			{
				result = atBatResult.OUT;
				// TODO: 
				boolean errorChance = true;
			}
		}
		else
		{
			double timeSpent = 0;
			if(timeLeft > .2 && outs != 2)
			{
				
				if(thirdBase != null)
				{
					runnerFromThird = findTime(thirdBase.getSpeedRating(), 90) - timePassed;
					if(runnerFromThird < 0)
					{
						runsScored++;
						thirdBase = null;
					}
				}
				if(secondBase != null)
				{
					runnerFromSecond = findTime(secondBase.getSpeedRating(), 90) - timePassed;
					if(runnerFromSecond < 0)
					{
						runnerFromSecond = findTime(secondBase.getSpeedRating(),90) + runnerFromSecond;
						thirdBase = playerFromSecond;
						secondBase = null;
					}
				}
				if(firstBase != null)
				{
					runnerFromFirst = findTime(firstBase.getSpeedRating(), 90) - timePassed;
					if(runnerFromFirst < 0)
					{
						runnerFromFirst = findTime(firstBase.getSpeedRating(),90) + runnerFromFirst;
						secondBase = playerFromSecond;
						firstBase = null;
					}
				}
				
				
			}
			else if (outs != 2)
			{
				while(fieldBall(fieldingPlayer, 1))
				{
					timeSpent += .2;
				}
				timeSpent += .2;
				runner -= timeSpent;
				
				if(firstBase != null)
				{
					runnerFromFirst = findTime(firstBase.getSpeedRating(), 90) - timeSpent;
				}
				if(secondBase != null)
				{
					runnerFromSecond = findTime(secondBase.getSpeedRating(), 90) - timeSpent;
				}
				if(thirdBase != null)
				{
					runnerFromThird = findTime(thirdBase.getSpeedRating(), 90) - timeSpent;
				}
				
			}
			if(runner < 0)
			{
				
				firstBase = currentBatter;
			}
			result = atBatResult.ERROR;
			//TODO: Didn't reach
		}
		
	}
	private double distanceFormula(Pair pairOne, Pair pairTwo)
	{
		return Math.sqrt(Math.pow(pairOne.x - pairOne.y, 2) + Math.pow(pairOne.y - pairTwo.y, 2));	
	}
	private double findDistance(double power, boolean strong)
	{
		if(strong)
		{
			double gaussNum = r.nextGaussian();

			if(gaussNum > 0)
				return (gaussNum * (power) + 225 + power-10);
			else
				return (gaussNum * (110-power) + 225 + power-10);
		}
		else
		{
			double gaussNum = r.nextGaussian();

			if(gaussNum > 0)
				return (gaussNum * (power/2) + 225 + power/2);
			else
				return (gaussNum * (100-power/2) + 225 + power/2);
		}

	}
	private void groundball(boolean strong)
	{
		int num = r.nextInt(18);

		player fieldingPlayer = null;

		if(num < 3)
		{
			fieldingPlayer = fielders.getThirdBase();
		}
		else if(num < 8)
		{
			fieldingPlayer = fielders.getSecondBase();
		}
		else if(num < 13)
		{
			fieldingPlayer = fielders.getShortStop();
		}
		else if(num == 13)
		{
			fieldingPlayer = fielders.getPitcherAsPlayer();
		}
		else
		{
			fieldingPlayer = fielders.getFirstBase();
		}

		double timeToMeetingPoint = 0.0;

	}

}
class Pair
{
	public double x, y;
	Pair(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
}
