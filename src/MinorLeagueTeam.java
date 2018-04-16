public class MinorLeagueTeam extends ProfessionalTeam
{
	private MajorLeagueTeam affiliate;
	public MinorLeagueTeam(MajorLeagueTeam affiliate, String teamName, boolean hasDH, int teamNum)
	{
		super(teamName, teamNum);
		this.affiliate = affiliate;
	}
	public MajorLeagueTeam getAffiliate()
	{
		return affiliate;
	}

	
}
