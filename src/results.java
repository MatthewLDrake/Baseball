public class results
    {
	private Team winner;
	private int teamOneWins, teamTwoWins;
	public results(Team winner, int teamOneWins, int teamTwoWins)
	{
	    this.winner = winner;
	    this.teamOneWins = teamOneWins;
	    this.teamTwoWins = teamTwoWins;
	}
	public Team getWinner()
	{
	    return winner;
	}
	public int getTeamOneWins()
	{
	    return teamOneWins;
	}
	public int getTeamTwoWins()
	{
	    return teamTwoWins;
	}
    }