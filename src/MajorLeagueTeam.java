

public class MajorLeagueTeam extends Team
{
	private MinorLeagueTeam[] farmSystem;
	public MajorLeagueTeam(String teamName, int teamNum)
	{
		super(teamName, teamNum, 0);
		farmSystem = new MinorLeagueTeam[3];
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
	public MinorLeagueTeam getTripleATeam()
	{
		return farmSystem[0];
		
	}
	public MinorLeagueTeam getDoubleATeam()
	{
		return farmSystem[1];
		
	}
	public MinorLeagueTeam getSingleATeam()
	{
		return farmSystem[2];
		
	}
}
