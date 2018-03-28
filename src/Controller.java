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
				
				
				team majorLeagueTeam = new ProfessionalTeam(teamName, i);
				create.addTeam(majorLeagueTeam);
				String[] teamNames = teamGen.getRelatedName(teamGen.getRecentLocation(), teamGen.getRecentName(), teamGen.usedState());
				
				team minorLeagueTeam = new MinorLeagueTeam((ProfessionalTeam) majorLeagueTeam, teamNames[0], i < 16);
				create.addTeam(minorLeagueTeam);
				((ProfessionalTeam) majorLeagueTeam).addFarmSystemTeam((MinorLeagueTeam)minorLeagueTeam);

				minorLeagueTeam = new MinorLeagueTeam((ProfessionalTeam) majorLeagueTeam, teamNames[1], i < 16);
				create.addTeam(minorLeagueTeam);
				((ProfessionalTeam) majorLeagueTeam).addFarmSystemTeam((MinorLeagueTeam)minorLeagueTeam);

				minorLeagueTeam = new MinorLeagueTeam((ProfessionalTeam) majorLeagueTeam, teamNames[2], i < 16);
				create.addTeam(minorLeagueTeam);
				((ProfessionalTeam) majorLeagueTeam).addFarmSystemTeam((MinorLeagueTeam)minorLeagueTeam);
			}
			
			PlayerGenerator gen = new PlayerGenerator();
					
			new draft(100, create.getMajors(), gen.getPlayers(), true);
			
			create.getTeam(0,0).addPlayer(new adultPitcher());
			create.getTeam(1, 0).addPlayer(new adultPitcher());
			for(int i = 1; i < 9; i++)
			{
				create.getTeam(0, 0).addPlayer(new adultPlayer(i));
				create.getTeam(1, 0).addPlayer(new adultPlayer(i));
			}
			
			
			executeGame(false, 0,1,0);
			
		}
	
		// else load


		
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
			create.getTeam(i, level).addRuns(newGame.getAwayTeamScore());
			create.getTeam(j, level).addRuns(newGame.getHomeTeamScore());
			create.getTeam(j, level).addRunsAgainst(newGame.getAwayTeamScore());
			create.getTeam(i, level).addRunsAgainst(newGame.getHomeTeamScore());

			
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
