import java.util.ArrayList;
public class ProfessionalTeam implements team
{
	private ArrayList<player> players;
	private boolean hasDH;
	private String teamName;
	private MinorLeagueTeam[] farmSystem;
	private int teamNum;
	private int wins, divisionWins, conferenceWins;
	private int losses, divisionLosses, conferenceLosses;
	private int runs, runsAgainst;
	private pitcher[] pitchingRotation;
	private player[] battingOrder;
	private int placeInRotation;
	private player startingCatcher, startingFirstBase, startingSecondBase, startingThirdBase, startingShortStop, startingLeftFielder, startingRightFielder, startingCenterFielder;
	public ProfessionalTeam(String teamName, int teamNum)
	{
		players = new ArrayList<player>();
		this.teamName = teamName;

		this.teamNum = teamNum;
		hasDH = this.teamNum > 16;
		farmSystem = new MinorLeagueTeam[3];
		pitchingRotation = new pitcher[5];
		battingOrder = new player[9];
		placeInRotation = 0;
	}
	public void addFarmSystemTeam(MinorLeagueTeam team)
	{
		for(int i = 0; i < farmSystem.length; i++)
		{
			if(farmSystem[i] == null)
			{
				farmSystem[i] = team;
				break;
			}
		}
	}
	public void addPlayer(player player)
	{
		players.add(player);



		if(player.getPosition() == 0)
		{
			pitchingRotation[0] = (pitcher) player;
			battingOrder[8] = player;
			return;
		}
		switch (player.getPosition())
		{
		case 1:
			if(startingFirstBase == null || player.compareTo(startingFirstBase) > 0)
			{
				startingFirstBase = player;
			}
			else trySecondaryPositions(player);
			break;
		case 2:
			if(startingSecondBase == null || player.compareTo(startingSecondBase) > 0)
			{
				startingSecondBase = player;
			}
			else trySecondaryPositions(player);
			break;
		case 3:
			if(startingThirdBase == null || player.compareTo(startingThirdBase) > 0)
			{
				startingThirdBase = player;
			}
			else trySecondaryPositions(player);
			break;
		case 4:
			if(startingShortStop == null || player.compareTo(startingShortStop) > 0)
			{
				startingShortStop = player;
			}
			else trySecondaryPositions(player);
			break;
		case 5:
			if(startingLeftFielder == null || player.compareTo(startingLeftFielder) > 0)
			{
				startingLeftFielder = player;
			}
			else trySecondaryPositions(player);
			break;
		case 6:
			if(startingRightFielder == null || player.compareTo(startingFirstBase) > 0)
			{
				startingFirstBase = player;
			}
			else trySecondaryPositions(player);
			break;
		case 7:
			if(startingFirstBase == null || player.compareTo(startingFirstBase) > 0)
			{
				startingFirstBase = player;
			}
			else trySecondaryPositions(player);
			break;
		case 8:
			if(startingCatcher == null || player.compareTo(startingCatcher) > 0)
			{
				startingCatcher = player;

			}
			else trySecondaryPositions(player);
			break;
		default:
			break;
		}

		for(int i = 0; i < battingOrder.length; i++)
		{
			if(battingOrder[i] == null)
			{
				battingOrder[i] = player;
				break;
			}
		}


	}
	private void trySecondaryPositions(player player)
	{
		// TODO Auto-generated method stub

	}
	public boolean hasDH()
	{
		return hasDH;
	}
	public player getPlayer(int playerNum)
	{
		return players.get(playerNum);
	}
	public void removePlayer(int playerNum)
	{
		players.remove(playerNum);
	}
	public ArrayList<player> getAllPlayers()
	{
		return players;
	}
	public String toString()
	{
		return teamName;
	}
	public int getSize()
	{
		return players.size();
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
		if(teamNum < 8)return 0;
		else if(teamNum < 16)return 1;
		else if(teamNum < 24)return 2;
		else return 3;
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
		return battingOrder;
	}
	public pitcher getStartingPitcher()
	{
		return pitchingRotation[placeInRotation++];
	}
	@Override
	public player getStartingFirstBase()
	{
		return startingFirstBase;
	}
	@Override
	public player getStartingSecondBase()
	{
		return startingSecondBase;
	}
	@Override
	public player getStartingThirdBase()
	{
		return startingThirdBase;
	}
	@Override
	public player getStartingShortStop()
	{
		return startingShortStop;
	}
	@Override
	public player getStartingLeftFielder()
	{
		return startingLeftFielder;
	}
	@Override
	public player getStartingCenterFielder()
	{
		return startingCenterFielder;
	}
	@Override
	public player getStartingRightFielder()
	{
		return startingRightFielder;
	}
	public player getStartingCatcher()
	{
		return startingCatcher;
	}
}
