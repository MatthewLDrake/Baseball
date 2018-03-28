import java.util.ArrayList;

public class MinorLeagueTeam implements team
{
	private ArrayList<player> players;
	private boolean hasDH;
	private String teamName;
	private ProfessionalTeam majorAffiliate;
	private int wins, divisionWins, conferenceWins;
	private int losses, divisionLosses, conferenceLosses;
	private int runs, runsAgainst;
	public MinorLeagueTeam(ProfessionalTeam affiliate, String teamName, boolean hasDH)
	{
		this.hasDH = hasDH;
		this.teamName = teamName;
		majorAffiliate = affiliate;
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
	public pitcher getStartingPitcher()
	{
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
}
