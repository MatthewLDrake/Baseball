import java.util.ArrayList;
import java.util.Random;

public class BallInPlay
{
    public PlayerOnBase firstBase, secondBase, thirdBase;
    public ArrayList<PlayerOnBase> runsScored;
    public atBatResult result;
    private pitchResult pitch;
    private currentTeam fielders;
    public int outs;
    private Random r;
    private PlayerOnBase currentBatter;
    private pitchType type;
    public static int popupHits = 0, popupAtBats = 0, groundBallHits = 0, groundBallAtBats = 0, flyballHits = 0, flyballAtBats = 0;
    public BallInPlay(pitchResult pitch, PlayerOnBase currentBatter, PlayerOnBase firstBase, PlayerOnBase secondBase, PlayerOnBase thirdBase,
	    currentTeam fieldingTeam, int outs, pitchType type)
    {
	this.type = type;
	this.currentBatter = currentBatter;
	this.firstBase = firstBase;
	this.secondBase = secondBase;
	this.thirdBase = thirdBase;
	r = new Random();
	this.pitch = pitch;
	this.fielders = fieldingTeam;
	this.outs = outs;
	runsScored = new ArrayList<PlayerOnBase>();
	start();

    }
    public BallInPlay()
    {

    }
    private void start()
    {
	switch (pitch)
	{
	case POPUP:
	    result = popup();
	    popupAtBats++;
	    if(!result.equals(atBatResult.ERROR) && !result.equals(atBatResult.OUT))
	    {
		popupHits++;
	    }
	    break;
	case FLYBALL:
	    result = flyball(false);
	    /*flyballAtBats++;
			if(!result.equals(atBatResult.ERROR) && !result.equals(atBatResult.OUT))
			{
			    flyballHits++;
			}*/
	    break;
	case STRONG_FLYBALL:
	    result = flyball(true);
	    flyballAtBats++;
	    if(!result.equals(atBatResult.ERROR) && !result.equals(atBatResult.OUT))
	    {
		flyballHits++;
	    }
	    break;
	case GROUNDBALL:
	    result = groundball(false);
	    /*groundBallAtBats++;
			if(!result.equals(atBatResult.ERROR) && !result.equals(atBatResult.OUT))
			{
			    groundBallHits++;
			}*/
	    break;
	case STRONG_GROUNDBALL:
	    result = groundball(true);
	    groundBallAtBats++;
	    if(!result.equals(atBatResult.ERROR) && !result.equals(atBatResult.OUT))
	    {
		groundBallHits++;
	    }
	    break;
	default:
	    System.err.println("Invalid type of hit");
	    System.exit(1);
	    break;
	}
    }
    public atBatResult popup()
    {
	atBatResult result;
	// infield fly rule
	if(firstBase != null && outs < 2)
	{
	    result = atBatResult.OUT;
	    outs++;
	    return result;
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
	double runner = findTime(currentBatter.getPlayer().getSpeedRating(), 90) - (timeLeft+timeToMeetingPoint);
	double runnerFromFirst = -1, runnerFromSecond = -1, runnerFromThird = -1;

	if(outs == 2)
	{
	    if(firstBase != null)
	    {
		runnerFromFirst = findTime(firstBase.getPlayer().getSpeedRating(), 90) - (timeLeft+timeToMeetingPoint);
	    }
	    if(secondBase != null)
	    {
		runnerFromSecond = findTime(secondBase.getPlayer().getSpeedRating(), 90) - (timeLeft+timeToMeetingPoint);
	    }
	    if(thirdBase != null)
	    {
		runnerFromThird = findTime(thirdBase.getPlayer().getSpeedRating(), 90) - (timeLeft+timeToMeetingPoint);
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
		    runnerFromFirst = findTime(firstBase.getPlayer().getSpeedRating(), 90);
		}
		if(secondBase != null)
		{
		    runnerFromSecond = findTime(secondBase.getPlayer().getSpeedRating(), 90);
		}
		if(thirdBase != null)
		{
		    runnerFromThird = findTime(thirdBase.getPlayer().getSpeedRating(), 90);
		}
		if(runner > 0)runner -= .2;
		if(runnerFromFirst > 0)runnerFromFirst -= .2;
		if(runnerFromSecond > 0)runnerFromSecond -= .2;
		if(runnerFromThird > 0)runnerFromThird -= .2;
		result = fieldInfieldBall(fieldingPlayer, runner, runnerFromFirst, runnerFromSecond, runnerFromThird, true, 0);
	    }
	}
	else
	{
	    result = fieldInfieldBall(fieldingPlayer, runner, runnerFromFirst, runnerFromSecond, runnerFromThird, false, 0);
	}
	return result;
    }
    private atBatResult fieldInfieldBall(player fieldingPlayer, double runner, double runnerFromFirst, double runnerFromSecond, double runnerFromThird, boolean potentialError, int fieldBallDifficulty)
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
			runsScored.add(thirdBase);
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
			    runsScored.add(thirdBase);
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
			    runsScored.add(thirdBase);
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
			runsScored.add(thirdBase);
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
			if(thirdBase != null)runsScored.add(thirdBase);
			if(firstBase != null)secondBase = firstBase;
			firstBase = currentBatter;


		    }
		}
		else
		{
		    result = atBatResult.ERROR;
		    if(thirdBase != null)
		    {
			runsScored.add(thirdBase);
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
		runsScored.add(thirdBase);
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
	return result;
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
	else
	{
	    return r.nextInt(100) < throwCatchRating;
	}
    }
    private boolean fieldBall(player fieldingPlayer, int i)
    {
	// easy
	if(i == 0)
	{
	    return r.nextInt(100) < 97;
	}
	if(i == 1)
	{
	    return r.nextInt(100) < 90;
	}
	else
	{
	    return r.nextInt(100) < 80;
	}

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
    private atBatResult flyball(boolean strong)
    {
	atBatResult result;
	double power = currentBatter.getPlayer().getBatterRatingsVs(type).getPowerRating();
	double distanceTravelled = findDistance(power, strong);
	if(distanceTravelled > 380)
	{
	    result = atBatResult.HOME_RUN;
	    runsScored.add(currentBatter);
	    if(firstBase != null)runsScored.add(firstBase);
	    if(secondBase != null)runsScored.add(secondBase);
	    if(thirdBase != null)runsScored.add(thirdBase);

	    firstBase=  null;
	    secondBase = null;
	    thirdBase = null;
	    return result;
	}
	Pair locationOfBall = new Pair(Math.max(-300, Math.min(300, r.nextGaussian() * 100 + 50)) ,distanceTravelled);


	player fieldingPlayer = null;
	Pair locationOfPlayer = null;
	if(locationOfBall.dx < -75)
	{
	    fieldingPlayer = fielders.getRightField();
	    locationOfPlayer = new Pair(-150, 225);
	}
	else if(locationOfBall.dx < 75)
	{
	    fieldingPlayer = fielders.getCenterField();
	    locationOfPlayer = new Pair(0, 225);
	}
	else 
	{
	    fieldingPlayer = fielders.getLeftField();
	    locationOfPlayer = new Pair(150, 225);
	}

	double timeToMeetingPoint = Math.abs(r.nextGaussian() * 3)+5.5;
	double distanceToBall = distanceFormula(locationOfBall, locationOfPlayer);

	double speed = fieldingPlayer.getSpeedRating();
	double timeFielderRuns = findTime(speed, distanceToBall) - 4;
	double timePassed = Math.min(timeToMeetingPoint, timeFielderRuns);
	double timeLeft = timeToMeetingPoint - timeFielderRuns;

	playerHelper[] players = new playerHelper[4]; 
	players[0] = new playerHelper(currentBatter, 0);
	if(firstBase != null)players[1] = new playerHelper(firstBase, 1);
	if(secondBase != null)players[2] = new playerHelper(secondBase,2); 
	if(thirdBase != null)players[3] = new playerHelper(thirdBase,3);
	boolean error = false;
	if(timeLeft > 0)
	{
	    if(tryToCatch(fieldingPlayer))
	    {
		outs++;
		if(outs == 3)
		    return atBatResult.OUT;
		else
		{
		    boolean sac = false;
		    if(strong)
		    {
			if(thirdBase != null)
			{
			    runsScored.add(thirdBase);
			    sac = true;
			    thirdBase = null;
			}
			if(secondBase != null)
			{
			    thirdBase = secondBase;
			    secondBase = null;
			    sac = true;
			}
			return sac ? atBatResult.SACRIFICE : atBatResult.OUT;
		    }
		    else
		    {
			result = atBatResult.OUT;

			boolean ifThrow = false;
			boolean playerTwoHold = false, playerThreeHold = false;
			if(players[2] != null)
			{
			    if(players[2].hold(true))
			    {
				ifThrow = true;
				playerTwoHold = true;
			    }

			}
			if(players[3] != null)
			{
			    if(players[3].hold(true))
			    {
				ifThrow = true;
				playerThreeHold = true;
			    }
			    else if(ifThrow)
			    {
				playerTwoHold = false;
			    }
			}
			if(ifThrow)
			{
			    int num = r.nextInt(100);
			    double threshHold = 0.0;
			    if(playerThreeHold)
			    {
				threshHold = players[3].getPlayer().getPlayer().getSpeedRating()*.6;
				if(threshHold < num)
				{
				    players[3].setOut();
				    outs++;
				    if(playerTwoHold && outs != 3)
				    {
					players[2].advancedSafely();
				    }
				}
				else
				{
				    result = atBatResult.SACRIFICE;
				    players[3].advancedSafely();
				    runsScored.add(thirdBase);
				    if(playerTwoHold)
				    {
					players[2].advancedSafely();
				    }
				}
			    }
			    else
			    {
				threshHold = players[2].getPlayer().getPlayer().getSpeedRating()*.6;
				if(threshHold < num)
				{
				    players[2].setOut();
				    outs++;

				}
				else
				{
				    players[2].advancedSafely();
				    result = atBatResult.SACRIFICE;
				}
			    }

			}
			return result;
		    }

		}
	    }
	    else error = true;
	}
	if(timeLeft > -.2 && timePassed < 4)
	{
	    players[0].advancedSafely();
	    result = atBatResult.SINGLE;
	    for(int i = 1; i < players.length; i++)
	    {
		if(players[i] != null)players[i].advancedSafely();
	    }

	}
	else if(timeLeft > -.2)
	{
	    result = atBatResult.DOUBLE;
	    players[0].advancedSafely();
	    players[0].advancedSafely();
	    for(int i = 1; i < players.length; i++)
	    {
		if(players[i] != null)
		{
		    players[i].advancedSafely();
		    players[i].advancedSafely();
		}
	    }
	}
	else if(timePassed - timeLeft < 10)
	{
	    result = atBatResult.DOUBLE;
	    players[0].advancedSafely();
	    players[0].advancedSafely();
	    boolean thrownFromOutfield = false;
	    for(int i = 3; i >= 0; i--)
	    {
		if(players[i] != null)
		{
		    players[i].advancedSafely();
		    players[i].advancedSafely();

		    if(players[i].hold(false) || thrownFromOutfield)
		    {

			if(thrownFromOutfield)
			{
			    players[i].advancedSafely();
			    if(i == 0)
			    {
				result = atBatResult.TRIPLE;
			    }
			}
			else
			{
			    thrownFromOutfield = true;
			    double acc = fieldingPlayer.getThrowAccuracy();
			    double runnerSpeed = players[i].getPlayer().getPlayer().getSpeedRating();

			    if(finishFromOutfield(acc, runnerSpeed, i))
			    {
				players[i].advancedSafely();
			    }
			    else
			    {
				players[i].setOut();
				if(error)result = atBatResult.OUT;
				outs++;
			    }

			}

		    }

		}
	    }
	}
	else
	{
	    result = atBatResult.DOUBLE;
	    players[0].advancedSafely();
	    players[0].advancedSafely();
	    for(int i = 1; i < players.length; i++)
	    {
		if(players[i] != null)
		{
		    players[i].advancedSafely();
		    players[i].advancedSafely();
		    players[i].advancedSafely();
		}
	    }
	}
	for(int i = 0; i < players.length; i++)
	{
	    if(players[i] != null)
	    {
		if(players[i].hasScored())runsScored.add(players[i].getPlayer());
		else if(players[i].isOut())continue;
		else if(players[i].getFinalBase() == 3)thirdBase = players[i].getPlayer();
		else if(players[i].getFinalBase() == 2)secondBase = players[i].getPlayer();
		else if(players[i].getFinalBase() == 1)firstBase = players[i].getPlayer();
		else System.err.println("Error, player not on valid base");


	    }
	}
	return error ? atBatResult.ERROR : result;
    }
    private boolean finishFromOutfield(double acc, double runnerSpeed, int i)
    {
	return (runnerSpeed * .75) - (acc * .5) + r.nextInt(40) - 20 > 0;
    }
    private double distanceFormula(Pair pairOne, Pair pairTwo)
    {
	return Math.sqrt(Math.pow(pairOne.x - pairTwo.x, 2) + Math.pow(pairOne.y - pairTwo.y, 2));	
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
    private atBatResult groundball(boolean strong)
    {
	String errorProbable = " ";
	atBatResult result;
	int num = r.nextInt(18);

	player fieldingPlayer = null;

	if(num < 3)
	{
	    errorProbable += "1";
	    fieldingPlayer = fielders.getThirdBase();
	}
	else if(num < 8)
	{
	    errorProbable += "2";
	    fieldingPlayer = fielders.getSecondBase();
	}
	else if(num < 13)
	{
	    errorProbable += "3";
	    fieldingPlayer = fielders.getShortStop();
	}
	else if(num == 13)
	{
	    errorProbable += "4";
	    fieldingPlayer = fielders.getPitcherAsPlayer();
	}
	else
	{
	    errorProbable += "5";
	    fieldingPlayer = fielders.getFirstBase();
	}
	int randNum = r.nextInt(100);
	boolean metWithBall = randNum >= 23;

	playerHelper[] players = new playerHelper[4]; 
	players[0] = new playerHelper(currentBatter, 0);
	if(firstBase != null)players[1] = new playerHelper(firstBase, 1);
	if(secondBase != null)players[2] = new playerHelper(secondBase,2); 
	if(thirdBase != null)players[3] = new playerHelper(thirdBase,3);

	if(fieldingPlayer == null)
	{
	    System.err.println("wat" + errorProbable);
	    System.exit(1);
	}

	if(metWithBall)
	{
	    errorProbable += "1";
	    if(fieldBall(fieldingPlayer, strong ? 2 : 1))
	    {
		errorProbable += "1";
		if(outs == 2)
		{
		    errorProbable += "1";
		    if(fieldingPlayer.equals(fielders.getFirstBase()))
		    {
			errorProbable += "1";
			result = atBatResult.OUT;
			players[0].setOut();
			outs++;
		    }
		    else
		    {
			errorProbable += "2";
			if(throwBall(fieldingPlayer, fielders.getFirstBase(),true))
			{
			    errorProbable += "1";
			    result = atBatResult.OUT;
			    players[0].setOut();
			    outs++;
			}
			else
			{
			    errorProbable += "2";
			    result = atBatResult.ERROR;
			    for(int i = 0; i < players.length; i++)
			    {
				if(players[i] != null)players[i].advancedSafely();
			    }
			}
		    }
		}
		else
		{
		    errorProbable += "2";
		    if(firstBase == null || !(strong && r.nextBoolean()))
		    {
			errorProbable += "1";
			if(fieldingPlayer.equals(fielders.getFirstBase()))
			{
			    errorProbable += "1";
			    result = atBatResult.OUT;
			    players[0].setOut();
			    outs++;
			}
			else
			{
			    errorProbable += "2";
			    if(throwBall(fieldingPlayer, fielders.getFirstBase(),true))
			    {
				errorProbable += "1";
				result = atBatResult.OUT;
				players[0].setOut();
				outs++;
			    }
			    else
			    {
				errorProbable += "2";
				result = atBatResult.ERROR;
				for(int i = 0; i < players.length; i++)
				{
				    if(players[i] != null)players[i].advancedSafely();
				}
			    }
			}
		    }
		    else
		    {
			errorProbable += "2";
			if(fieldingPlayer.equals(fielders.getSecondBase()) || fieldingPlayer.equals(fielders.getShortStop()))
			{
			    errorProbable += "1";
			    result = atBatResult.OUT;
			    players[1].setOut();
			    outs++;
			    if(throwBall(fieldingPlayer, fielders.getFirstBase(),true))
			    {
				errorProbable += "1";
				players[0].setOut();
				outs++;
			    }
			    else
			    {
				errorProbable += "2";
				for(int i = 0; i < players.length; i++)
				{
				    if(players[i] != null)players[i].advancedSafely();
				}
			    }
			}
			else
			{
			    player catcher = r.nextBoolean() ? fielders.getShortStop() : fielders.getSecondBase();
			    errorProbable += "2";
			    if(throwBall(fieldingPlayer, catcher,true))
			    {
				errorProbable += "1";
				players[1].setOut();
				outs++;
				result = atBatResult.OUT;

			    }
			    else
			    {
				players[1].advancedSafely();
				result = atBatResult.ERROR;
			    }
			    if(throwBall(catcher, fielders.getFirstBase(), true))
			    {
				errorProbable += "2";
				players[0].setOut();
				outs++;
				result = atBatResult.OUT;
			    }
			    else
			    {
				players[0].advancedSafely();
			    }



			}
		    }
		}
	    }
	    else
	    {
		errorProbable += "2";
		result = (strong || r.nextInt(20) == 2) ? atBatResult.SINGLE : atBatResult.ERROR;
		for(int i = 0; i < players.length; i++)
		{
		    if(players[i] != null)players[i].advancedSafely();
		    else break;
		}
	    }
	}
	else
	{
	    errorProbable += "2";
	    result = atBatResult.SINGLE;
	    boolean ballThrown = false;
	    players[0].advancedSafely();
	    playerHelper throwingAgainst = null;
	    for(int i = players.length-1; i >= 1; i--)
	    {
		if(players[i] != null)
		{
		    players[i].advancedSafely();
		    boolean hold = players[i].hold(false);
		    if(!hold && !ballThrown)
		    {
			throwingAgainst = players[i];
		    }
		    else if(ballThrown)
		    {
			players[i].advancedSafely();
		    }
		}
	    }
	    if(throwingAgainst != null)
	    {
		if(r.nextInt(20) > 13)
		{
		    throwingAgainst.setOut();
		    outs++;
		}
		else
		{
		    throwingAgainst.advancedSafely();
		}
	    }

	}
	for(int i = 0; i < players.length; i++)
	{
	    if(players[i] != null)
	    {
		if(players[i].hasScored())runsScored.add(players[i].getPlayer());
		else if(players[i].isOut())continue;
		else if(players[i].getFinalBase() == 3)thirdBase = players[i].getPlayer();
		else if(players[i].getFinalBase() == 2)secondBase = players[i].getPlayer();
		else if(players[i].getFinalBase() == 1)firstBase = players[i].getPlayer();
		else 
		{
		    System.err.println("Error, player not on valid base, player " + i + " on base " + players[i].getFinalBase() + errorProbable);
		    System.exit(1);
		}


	    }
	}

	return result;
    }


}

class playerHelper
{
    private PlayerOnBase player;
    private boolean hasScored;
    private int currentBase;
    private double timeToNextBase;
    private boolean isOut;
    public playerHelper(PlayerOnBase player, int startingBase)
    {
	this.player = player;
	currentBase = startingBase;
	hasScored = false;
	isOut = false;
    }
    public void setOut()
    {
	isOut = true;
    }
    public boolean isOut()
    {
	return isOut;
    }
    public void changeTime(double d)
    {
	System.out.print(player + " change time" + d);
	timeToNextBase += d;
	System.out.println(" which means they have " + timeToNextBase + " remaining.");
    }
    public void setTime(double d)
    {
	System.out.println(player + " started with time" + d);
	timeToNextBase = d;

    }
    public double getTime()
    {
	return timeToNextBase;
    }
    public PlayerOnBase getPlayer()
    {
	return player;
    }
    public int getFinalBase()
    {
	return currentBase;
    }
    public boolean hasScored()
    {
	return hasScored;
    }
    public void advancedSafely()
    {
	currentBase++;
	if(currentBase == 4)hasScored = true;
    }
    public boolean hold(boolean b)
    {
	if(b)
	{
	    return player.getPlayer().advance(currentBase);
	}
	else if(!b && !hasScored)
	{
	    Random r = new Random();
	    return r.nextInt(100) > 50;
	}
	return false;
    }
}

