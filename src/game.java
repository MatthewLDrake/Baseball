import java.util.ArrayList;

public class game
{
	private currentTeam awayTeam, homeTeam;
	private int homeTeamScore, awayTeamScore;
	private boolean bottomHalf;
	private int inning;
	private int outs;
	private PlayerOnBase firstBase, secondBase, thirdBase, currentBatter;
	private pitcher currentPitcher;
	private pitcher opposingPitcher;
	private pitcher winningPitcher, losingPitcher;
	public game(Team teamOne, Team teamTwo) 
	{
		this.homeTeam = new currentTeam(teamOne, teamTwo.hasDH());
		this.awayTeam = new currentTeam(teamTwo, teamTwo.hasDH());

		currentPitcher = awayTeam.getPitcher();
		opposingPitcher = homeTeam.getPitcher();

		bottomHalf = false;
		inning = 1;
		outs = 0;

		playGame();
		for(player p:teamOne)
		{
			if(p instanceof pitcher)
			{
				pitcher pitch = (pitcher)p;
				pitch.endGame();
			}
			else
			{
				p.endGame();
			}
		}
		for(player p:teamTwo)
		{
			if(p instanceof pitcher)
			{
				pitcher pitch = (pitcher)p;
				pitch.endGame();
			}
			else
			{
				p.endGame();
			}

		}
	}
	private void playGame()
	{
		while(!gameIsOver(true))
		{
			inning(awayTeam, homeTeam);

			inning++;

		}
		winningPitcher.addWin();
		losingPitcher.addLoss();

		//System.out.println("Game Over, Final Score:\t" + awayTeam + ": " + awayTeamScore + "\t" + homeTeam + ": "+ homeTeamScore);

	}
	private void inning(currentTeam battingTeam, currentTeam fieldingTeam)
	{
		boolean losingTeamBatting = false;
		bottomHalf = false;
		losingTeamBatting = awayTeamScore <= homeTeamScore;
		boolean saveOpportunity = outs == 0 && inning > 8 && homeTeamScore - awayTeamScore > 0 && homeTeamScore - awayTeamScore < 4;
		currentPitcher = fieldingTeam.changePitcher(saveOpportunity);
		while(outs != 3)
		{
			
			currentBatter = new PlayerOnBase(battingTeam.getNext(), currentPitcher);
			atBat(battingTeam, fieldingTeam);
		}
		if(saveOpportunity && homeTeamScore - awayTeamScore > 0)
		{
			currentPitcher.successfulSave();
		}
		saveOpportunity = false;
		if(losingTeamBatting && awayTeamScore > homeTeamScore)
		{
			winningPitcher = opposingPitcher;
			losingPitcher = currentPitcher;
		}
		opposingPitcher = currentPitcher;
		currentPitcher = battingTeam.getPitcher();
		firstBase = null;
		secondBase = null;
		thirdBase = null;
		outs = 0;
		bottomHalf = true;
		losingTeamBatting = awayTeamScore >= homeTeamScore;
		saveOpportunity = outs == 0 && inning > 8 && awayTeamScore - homeTeamScore > 0 && awayTeamScore - homeTeamScore < 4;
		currentPitcher = battingTeam.changePitcher(saveOpportunity);
		while(outs != 3 && !gameIsOver(false))
		{
			
			currentBatter = new PlayerOnBase(fieldingTeam.getNext(), currentPitcher);;
			atBat(fieldingTeam, battingTeam);
		}
		
		if(saveOpportunity && awayTeamScore - homeTeamScore > 0)
		{
			currentPitcher.successfulSave();
		}
		
		if(losingTeamBatting && awayTeamScore < homeTeamScore)
		{
			winningPitcher = opposingPitcher;
			losingPitcher = currentPitcher;
		}
		opposingPitcher = currentPitcher;
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
			Pitch thePitch = new Pitch(currentPitcher, currentBatter.getPlayer(), balls, strikes);
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
					currentBatter.getPlayer().addAtBat(atBatResult.WALK, thePitch.lastPitchType(), rbi ? 1 : 0);
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
					currentBatter.getPlayer().addAtBat(atBatResult.STRIKEOUT, thePitch.lastPitchType(), 0);
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
					currentBatter.getPitcher().addRun(!currentBatter.getReachedOnError());
					currentBatter.getPlayer().addRBI();
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

		ArrayList<PlayerOnBase> runsScored = play.runsScored;

		currentBatter.getPlayer().addAtBat(play.result, lastPitch, runsScored.size());

		for(int i = 0; i < runsScored.size(); i++)
		{
			runsScored.get(i).getPitcher().addRun(1, !runsScored.get(i).getReachedOnError());
		}

		if(bottomHalf)homeTeamScore += runsScored.size();
		else awayTeamScore += runsScored.size();
		//currentPitcher.addRun(play.runsScored, !play.result.equals(atBatResult.ERROR));
		currentBatter.getPlayer().addAtBat(play.result, lastPitch, outs == 3 && play.result.equals(atBatResult.ERROR) ? 0 : runsScored.size());



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
