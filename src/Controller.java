import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller
{
	private static createLeagues create;
	public static void main(String[] args)
	{
		boolean newLeague = true;
		if(newLeague)
		{
			create = new createLeagues();
			TeamNameGenerator teamGen = new TeamNameGenerator();
			for(int i = 0; i < 32; i++)
			{
				String teamName = teamGen.getName();
				boolean flag = true;
				while(flag)
				{
					flag = false;
					for(int j = 0; j < create.size();j++)
					{
						if(teamName.equals(create.getTeam(j, 0)))
						{
							flag = true;
							teamName = teamGen.getName();
							break;
						}
					}
				}


				team majorLeagueTeam = new MajorLeagueTeam(teamName, i);
				create.addTeam(majorLeagueTeam);
				String[] teamNames = teamGen.getRelatedName(teamGen.getRecentLocation(), teamGen.getRecentName(), teamGen.usedState());

				team minorLeagueTeam = new MinorLeagueTeam((MajorLeagueTeam) majorLeagueTeam, teamNames[0], i < 16, i);
				create.addTeam(minorLeagueTeam);
				((MajorLeagueTeam) majorLeagueTeam).addFarmSystemTeam((MinorLeagueTeam)minorLeagueTeam);

				minorLeagueTeam = new MinorLeagueTeam((MajorLeagueTeam) majorLeagueTeam, teamNames[1], i < 16, i);
				create.addTeam(minorLeagueTeam);
				((MajorLeagueTeam) majorLeagueTeam).addFarmSystemTeam((MinorLeagueTeam)minorLeagueTeam);

				minorLeagueTeam = new MinorLeagueTeam((MajorLeagueTeam) majorLeagueTeam, teamNames[2], i < 16, i);
				create.addTeam(minorLeagueTeam);
				((MajorLeagueTeam) majorLeagueTeam).addFarmSystemTeam((MinorLeagueTeam)minorLeagueTeam);
			}

			PlayerGenerator gen = new PlayerGenerator();

			new draft(100, create.getMajors(), gen.getPlayers(), true);			



		}


		Schedule schedule = new Schedule();

		while(schedule.isNotDone())
		{
			Pair[] games = schedule.getNextSeries();
			for(int numGame = 0; numGame < 3; numGame++)
			for(int i = 0; i < games.length; i++)
			{
				executeGame(false, games[i].x, games[i].y, 0);
				executeGame(false, games[i].x, games[i].y, 1);
				executeGame(false, games[i].x, games[i].y, 2);
				executeGame(false, games[i].x, games[i].y, 3);
			}
		}

		try
		{
			PrintWriter majorStandings = new PrintWriter("majorStandings.csv"), tripleAStandings = new PrintWriter("tripleAStandings.csv"), doubleAStandings = new PrintWriter("doubleAStandings.csv"), singleAStandings = new PrintWriter("singleAStandings.csv");

			int division = 0;

			for(int i = 0; i < create.size(); i++)
			{
				if(i % 4 == 0)
				{
					majorStandings.println("Division " + (char)('A' + division) + ",Wins,Losses,Runs Scored,Runs Against");
					tripleAStandings.println("Division " + (char)('A' + division) + ",Wins,Losses,Runs Scored,Runs Against");
					doubleAStandings.println("Division " + (char)('A' + division) + ",Wins,Losses,Runs Scored,Runs Against");
					singleAStandings.println("Division " + (char)('A' + division) + ",Wins,Losses,Runs Scored,Runs Against");
					division++;
				}
				printTeamInfo(majorStandings, create.getTeam(i, 0));
				printTeamInfo(tripleAStandings, create.getTeam(i, 1));
				printTeamInfo(doubleAStandings, create.getTeam(i, 2));
				printTeamInfo(singleAStandings, create.getTeam(i, 3));
				
			}
			
			majorStandings.close();
			tripleAStandings.close();
			doubleAStandings.close();
			singleAStandings.close();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// else load



	}
	private static void printTeamInfo(PrintWriter majorStandings, team team) throws IOException
	{
		majorStandings.println(team + "," + team.getWins() +  "," + team.getLosses() + "," + team.getRunsScored() + "," + team.getRunsAgainst());
		
	}
	private static boolean executeGame(boolean b, int i, int j, int level)
	{
		boolean retVal = false;/*
		int away = create.getTeam(i, level).lastThreeGames(-1);
		int home = create.getTeam(j, level).lastThreeGames(-1);*/

		game newGame = new game(create.getTeam(i, level), create.getTeam(j, level));

		try
		{

			retVal = newGame.getWinner();
			//writer.println(votingList);
			if(newGame.getWinner())
			{
				create.getTeam(i, level).addWin(1);
				create.getTeam(j, level).addLoss(1);
				if((i < 16 && j < 16) || (i > 15 && j > 15))
				{
					create.getTeam(i, level).addConferenceWin(1);
					create.getTeam(j, level).addConferenceLoss(1);
				}
				if(create.getTeam(i, level).getDivision() == create.getTeam(j, level).getDivision())
				{
					create.getTeam(i, level).addDivisionWin(1);
					create.getTeam(j, level).addDivisionLoss(1);
				}
			}
			else 
			{
				//writer.println(create.getTeam(j, level).toString() + " wins! The score was " + newGame.getHomeTeamScore() + " - " + newGame.getAwayTeamScore());
				create.getTeam(j, level).addWin(1);
				create.getTeam(i, level).addLoss(1);
				if((i < 16 && j < 16) || (i > 15 && j > 15))
				{
					create.getTeam(j, level).addConferenceWin(1);
					create.getTeam(i, level).addConferenceLoss(1);
				}
				if(create.getTeam(i, level).getDivision() == create.getTeam(j, level).getDivision())
				{
					create.getTeam(j, level).addDivisionWin(1);
					create.getTeam(i, level).addDivisionLoss(1);
				}

			}
			//gameResults.println("," + create.getTeam(i, level).toString() + "," + newGame.getAwayTeamScore() + "," + create.getTeam(j, level).toString()+ "," + newGame.getHomeTeamScore());
			create.getTeam(j, level).addRuns(newGame.getAwayTeamScore());
			create.getTeam(i, level).addRuns(newGame.getHomeTeamScore());
			create.getTeam(i, level).addRunsAgainst(newGame.getAwayTeamScore());
			create.getTeam(j, level).addRunsAgainst(newGame.getHomeTeamScore());


		}
		catch(Exception E)
		{
			E.printStackTrace();
			System.out.println("Fuck");
		}

		for(int k = 0; k < create.getTeam(i, level).getSize(); k++)
		{
			create.getTeam(i, level).getPlayer(k).resetGameStats();
		}
		for(int k = 0; k < create.getTeam(j, level).getSize(); k++)
		{
			create.getTeam(j, level).getPlayer(k).resetGameStats();
		}
		return retVal;
	}
}
