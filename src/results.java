public class results
    {
	private team winner;
	private int teamOneWins, teamTwoWins;
	public results(team winner, int teamOneWins, int teamTwoWins)
	{
	    this.winner = winner;
	    this.teamOneWins = teamOneWins;
	    this.teamTwoWins = teamTwoWins;
	}
	public team getWinner()
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