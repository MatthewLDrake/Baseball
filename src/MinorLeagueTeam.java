import java.util.ArrayList;
import java.util.Random;
public class MinorLeagueTeam implements team
{
	private ArrayList<player> players;
	private boolean hasDH;
	private String teamName;
	private ProfessionalTeam majorAffiliate;
	private int wins, divisionWins, conferenceWins;
	private int losses, divisionLosses, conferenceLosses;
	private int runs, runsAgainst;
	private pitcher[] pitchingRotation;
	private pitcher spotStarter, closer, setup, reliever;
	private pitcher[] middleRelievers;
	private int placeInRotation;
	private Random r;
	public MinorLeagueTeam(ProfessionalTeam affiliate, String teamName, boolean hasDH)
	{
		placeInRotation = 0;
		r = new Random();
		pitchingRotation = new pitcher[5];
		middleRelievers = new pitcher[3];
		this.hasDH = hasDH;
		this.teamName = teamName;
		majorAffiliate = affiliate;
		players = new ArrayList<player>();
	}

	@Override
	public void addPlayer(player player)
	{
		players.add(player);
		
	}

	@Override
	public boolean hasDH()
	{
		return hasDH;
	}

	@Override
	public player getPlayer(int playerNum)
	{
		return players.get(playerNum);
	}

	@Override
	public void removePlayer(int playerNum)
	{
		players.remove(playerNum);
		
	}

	@Override
	public ArrayList<player> getAllPlayers()
	{
		return players;
	}

	@Override
	public int getSize()
	{
		return players.size();
	}
	
	@Override
	public String toString()
	{
		return teamName;
	}
	@Override
	public void addWin(int i)
	{
		wins += i;
		
	}
	@Override
	public void addLoss(int i)
	{
		losses += i;
		
	}
	@Override
	public int getDivision()
	{
		return majorAffiliate.getDivision();
	}
	@Override
	public void addConferenceWin(int i)
	{
		conferenceWins += i;
		
	}
	@Override
	public void addConferenceLoss(int i)
	{
		conferenceLosses += i;
		
	}
	@Override
	public void addDivisionWin(int i)
	{
		divisionWins += i;
		
	}
	@Override
	public void addDivisionLoss(int i)
	{
		divisionLosses += i;
		
	}
	@Override
	public void addRuns(int i)
	{
		runs += i;
		
	}
	@Override
	public void addRunsAgainst(int i)
	{
		runsAgainst += i;
		
	}

	@Override
	public player[] getBattingOrder()
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public player getStartingFirstBase()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public player getStartingSecondBase()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public player getStartingThirdBase()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public player getStartingShortStop()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public player getStartingLeftFielder()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public player getStartingCenterFielder()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public player getStartingRightFielder()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public player getStartingCatcher()
	{
		return null;
	}
	public void addStartingPitcher(pitcher pitcher)
	{
		players.add((player) pitcher);
		for(int i = 0; i < pitchingRotation.length; i++)
		{
			if(pitchingRotation[i] == null)
			{
				pitchingRotation[i] = pitcher;
				return;
			}
		}
		spotStarter = pitcher;
		
	}
	public void addReliefPitcher(pitcher pitcher)
	{
		players.add((player) pitcher);
		if(closer == null)
		{
			closer = pitcher;
		}
		else if(setup == null)
		{
			setup = pitcher;
		}
		else
		{
			for(int i = 0; i < middleRelievers.length; i++)
			{
				if(middleRelievers[i] == null)
				{
					middleRelievers[i] = pitcher;
					return;
				}
			}
			reliever = pitcher;
		}
	}
	public pitcher getNextStarter()
	{
		pitcher retVal = null;
		// check if struggling
		if(placeInRotation == 4 && r.nextInt(10) < 3)retVal = spotStarter;
		else retVal = pitchingRotation[placeInRotation];
		placeInRotation = (placeInRotation+1)%5;
		
		return retVal;
	}
}
