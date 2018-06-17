public class MinorLeagueTeam extends Team
{
	private MajorLeagueTeam affiliate;
	public MinorLeagueTeam(MajorLeagueTeam affiliate, String teamName, boolean hasDH, int teamNum)
	{
		super(teamName, teamNum, 1);
		this.affiliate = affiliate;
	}
	public MajorLeagueTeam getAffiliate()
	{
		return affiliate;
	}

	
}
