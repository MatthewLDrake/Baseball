public class game
{
    private currentTeam awayTeam, homeTeam;
    private int homeTeamScore, awayTeamScore;
    private boolean bottomHalf;
    private int inning;
    private int outs;
    private player firstBase, secondBase, thirdBase, currentBatter;
    private pitcher currentPitcher;
    public game(team teamOne, team teamTwo)
    {
	this.homeTeam = new currentTeam(teamOne);
	this.awayTeam = new currentTeam(teamTwo);

	currentPitcher = awayTeam.getPitcher();

	bottomHalf = false;
	inning = 1;
	outs = 0;

	playGame();

    }
    private void playGame()
    {
	while(!gameIsOver(true))
	{
	    inning(awayTeam, homeTeam);

	    inning++;

	}
	//System.out.println("Game Over, Final Score:\t" + awayTeam + ": " + awayTeamScore + "\t" + homeTeam + ": "+ homeTeamScore);

    }
    private void inning(currentTeam battingTeam, currentTeam fieldingTeam)
    {
	bottomHalf = false;
	while(outs != 3)
	{
	    currentBatter = battingTeam.getNext();
	    atBat(battingTeam, fieldingTeam);
	}
	currentPitcher = battingTeam.getPitcher();
	firstBase = null;
	secondBase = null;
	thirdBase = null;
	outs = 0;
	bottomHalf = true;
	while(outs != 3 && !gameIsOver(false))
	{
	    currentBatter = fieldingTeam.getNext();
	    atBat(fieldingTeam, battingTeam);
	}
	currentPitcher = fieldingTeam.getPitcher();
	firstBase = null;
	secondBase = null;
	thirdBase = null;
	outs = 0;
    }
    private void atBat(currentTeam battingTeam, currentTeam fieldingTeam)
    {
	int balls = 0, strikes = 0;

	while(true)
	{
	    Pitch thePitch = new Pitch(currentPitcher, currentBatter, balls, strikes);
	    pitchResult pitch = thePitch.getResult();

	    if(pitch == pitchResult.BALL || (pitch == pitchResult.WILD_PITCH && balls == 3))
	    {
		balls++;
		if(balls == 4)
		{
		    boolean rbi = false;
		    if(firstBase != null)
		    {
			if(secondBase != null)
			{
			    if(thirdBase != null)
			    {
				currentPitcher.addRun(true);
				rbi = true;
				if(bottomHalf)homeTeamScore++;
				else awayTeamScore++;
			    }
			    thirdBase = secondBase;
			}
			secondBase = firstBase;
		    }
		    firstBase = currentBatter;
		    currentBatter.addAtBat(atBatResult.WALK, thePitch.lastPitchType(), rbi ? 1 : 0);
		    currentPitcher.addWalk();
		    
		    return;
		}

	    }
	    else if(pitch == pitchResult.STRIKE)
	    {
		strikes++;
		if(strikes == 3)
		{
		    outs++;
		    currentBatter.addAtBat(atBatResult.STRIKEOUT, thePitch.lastPitchType(), 0);
		    currentPitcher.addStrikeOut();
		    return;
		}
	    }
	    else if(pitch == pitchResult.FOUL)
	    {
		if(strikes != 2)strikes++;
	    }
	    else if(pitch == pitchResult.WILD_PITCH)
	    {
		balls++;
		if(thirdBase != null)
		{
		    currentPitcher.addRun(false);
		    currentBatter.addRBI();
		    if(bottomHalf)homeTeamScore++;
		    else awayTeamScore++;
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

	    }
	    else
	    {
		contactMade(pitch, fieldingTeam, thePitch.lastPitchType());
		return;
	    }
	}

    }
    private void contactMade(pitchResult pitch, currentTeam fieldingTeam, pitchType lastPitch)
    {
	int previousOuts = outs;
	
	BallInPlay play = new BallInPlay(pitch, currentBatter, firstBase, secondBase, thirdBase, fieldingTeam, outs, lastPitch);

	firstBase = play.firstBase;
	secondBase = play.secondBase;
	thirdBase = play.thirdBase;
	outs = play.outs;

	currentPitcher.addOut(outs-previousOuts);
	
	currentBatter.addAtBat(play.result, lastPitch, play.runsScored);

	if(bottomHalf)homeTeamScore += play.runsScored;
	else awayTeamScore += play.runsScored;
	currentPitcher.addRun(play.runsScored, !play.result.equals(atBatResult.ERROR));
	currentBatter.addAtBat(play.result, lastPitch, outs == 3 && play.result.equals(atBatResult.ERROR) ? 0 : play.runsScored);
	
	

    }
    private boolean gameIsOver(boolean flag)
    {
	if(!flag) return (inning >= 9 && bottomHalf && homeTeamScore > awayTeamScore);
	return (inning > 9 && bottomHalf && homeTeamScore > awayTeamScore) || (inning > 9 && homeTeamScore != awayTeamScore);
    }
    public boolean getWinner()
    {
	return homeTeamScore > awayTeamScore;
    }
    public int getHomeTeamScore()
    {
	return homeTeamScore;
    }
    public int getAwayTeamScore()
    {
	return awayTeamScore;
    }
}
