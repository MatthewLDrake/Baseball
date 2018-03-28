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
	public BallInPlay(pitchResult pitch, player currentBatter, player firstBase, player secondBase, player thirdBase,
			currentTeam fieldingTeam, int outs)
	{
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

		double timeToMeetingPoint = r.nextDouble() * 3.2;
		double distanceToBall = Math.abs(r.nextGaussian() * 10);

		double speed = fieldingPlayer.getSpeedRating();

		double timeLeft = findTime(speed, distanceToBall) - timeToMeetingPoint;
		double runner = findTime(currentBatter.getSpeedRating(), 90);
		double firstToSecondTime = -1, secondToThirdTime = -1, thirdToHomeTime = -1;

		if(outs == 2)
		{
			if(firstBase != null)
			{
				firstToSecondTime = findTime(firstBase.getSpeedRating(), 90);
			}
			if(secondBase != null)
			{
				secondToThirdTime = findTime(secondBase.getSpeedRating(), 90);
			}
			if(thirdBase != null)
			{
				thirdToHomeTime = findTime(thirdBase.getSpeedRating(), 90);
			}
		}

		if(timeLeft <= 0)
		{
			if(tryToCatch(fieldingPlayer))
			{
				result = atBatResult.OUT;
			}
			else
			{
				// dropped catch, bad news for anyone on first
				//TODO: replace
				result = atBatResult.SINGLE;
			}
		}
		else
		{
			boolean potentialError = false;
			while(!fieldBall(fieldingPlayer, 0))
			{
				potentialError = true;
				if(runner > 0)runner -= .2;
				if(firstToSecondTime > 0)firstToSecondTime -= .2;
				if(secondToThirdTime > 0)secondToThirdTime -= .2;
				if(thirdToHomeTime > 0)thirdToHomeTime -= .2;
			}
			if(runner > 0)runner -= .2;
			if(firstToSecondTime > 0)firstToSecondTime -= .2;
			if(secondToThirdTime > 0)secondToThirdTime -= .2;
			if(thirdToHomeTime > 0)thirdToHomeTime -= .2;

			double max = Math.max(Math.max(runner, firstToSecondTime), Math.max(secondToThirdTime, thirdToHomeTime));
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
				else if(max == firstToSecondTime)
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
				else if(max == secondToThirdTime)
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
		int num = r.nextInt(10);

		player fieldingPlayer = null;

		if(num < 2)
		{
			fieldingPlayer = fielders.getRightField();
		}
		else if(num < 7)
		{
			fieldingPlayer = fielders.getCenterField();
		}
		else 
		{
			fieldingPlayer = fielders.getLeftField();
		}

		double timeToMeetingPoint = 0.0;

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
