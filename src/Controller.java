import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

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
	    calculateStandings();
	    BallInPlay b = new BallInPlay();
	    System.out.printf("Popups: %d at bats, %d hits = .%03.0f\nGround Balls: %d at bats, %d hits = .%.0f\nFlyBalls: %d at bats, %d hits = .%.0f\n",
		    b.popupAtBats, b.popupHits, ((double)b.popupHits/(double)b.popupAtBats)*1000 ,b.groundBallAtBats, b.groundBallHits, ((double)b.groundBallHits/(double)b.groundBallAtBats)*1000, b.flyballAtBats, b.flyballHits, ((double)b.flyballHits/(double)b.flyballAtBats)*1000);
	}
	catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// else load



    }
    private static void calculateStandings() throws IOException
    {
	PrintWriter majorStandings = new PrintWriter("majorStandings.csv"), tripleAStandings = new PrintWriter("tripleAStandings.csv"), doubleAStandings = new PrintWriter("doubleAStandings.csv"), singleAStandings = new PrintWriter("singleAStandings.csv");

	ArrayList<team> majorDivisionA = new ArrayList<team>();
	ArrayList<team> majorDivisionB = new ArrayList<team>();
	ArrayList<team> majorDivisionC = new ArrayList<team>();
	ArrayList<team> majorDivisionD = new ArrayList<team>();
	ArrayList<team> majorDivisionE = new ArrayList<team>();
	ArrayList<team> majorDivisionF = new ArrayList<team>();
	ArrayList<team> majorDivisionG = new ArrayList<team>();
	ArrayList<team> majorDivisionH = new ArrayList<team>();
	ArrayList<team> majorConferenceA = new ArrayList<team>();
	ArrayList<team> majorConferenceB = new ArrayList<team>();
	ArrayList<team> majorLeague = new ArrayList<team>();
	
	ArrayList<team> tripleADivisionA = new ArrayList<team>();
	ArrayList<team> tripleADivisionB = new ArrayList<team>();
	ArrayList<team> tripleADivisionC = new ArrayList<team>();
	ArrayList<team> tripleADivisionD = new ArrayList<team>();
	ArrayList<team> tripleADivisionE = new ArrayList<team>();
	ArrayList<team> tripleADivisionF = new ArrayList<team>();
	ArrayList<team> tripleADivisionG = new ArrayList<team>();
	ArrayList<team> tripleADivisionH = new ArrayList<team>();
	ArrayList<team> tripleAConferenceA = new ArrayList<team>();
	ArrayList<team> tripleAConferenceB = new ArrayList<team>();
	ArrayList<team> tripleALeague = new ArrayList<team>();
	
	ArrayList<team> doubleADivisionA = new ArrayList<team>();
	ArrayList<team> doubleADivisionB = new ArrayList<team>();
	ArrayList<team> doubleADivisionC = new ArrayList<team>();
	ArrayList<team> doubleADivisionD = new ArrayList<team>();
	ArrayList<team> doubleADivisionE = new ArrayList<team>();
	ArrayList<team> doubleADivisionF = new ArrayList<team>();
	ArrayList<team> doubleADivisionG = new ArrayList<team>();
	ArrayList<team> doubleADivisionH = new ArrayList<team>();
	ArrayList<team> doubleAConferenceA = new ArrayList<team>();
	ArrayList<team> doubleAConferenceB = new ArrayList<team>();
	ArrayList<team> doubleALeague = new ArrayList<team>();
	
	ArrayList<team> singleADivisionA = new ArrayList<team>();
	ArrayList<team> singleADivisionB = new ArrayList<team>();
	ArrayList<team> singleADivisionC = new ArrayList<team>();
	ArrayList<team> singleADivisionD = new ArrayList<team>();
	ArrayList<team> singleADivisionE = new ArrayList<team>();
	ArrayList<team> singleADivisionF = new ArrayList<team>();
	ArrayList<team> singleADivisionG = new ArrayList<team>();
	ArrayList<team> singleADivisionH = new ArrayList<team>();
	ArrayList<team> singleAConferenceA = new ArrayList<team>();
	ArrayList<team> singleAConferenceB = new ArrayList<team>();
	ArrayList<team> singleALeague = new ArrayList<team>();

	for(int i = 0; i < 4; i++)
	{
	    majorDivisionA.add(create.getTeam(i,0));
	    majorDivisionB.add(create.getTeam(i+4,0));
	    majorDivisionC.add(create.getTeam(i+8,0));
	    majorDivisionD.add(create.getTeam(i+12,0));
	    majorDivisionE.add(create.getTeam(i+16,0));
	    majorDivisionF.add(create.getTeam(i+20,0));
	    majorDivisionG.add(create.getTeam(i+24,0));
	    majorDivisionH.add(create.getTeam(i+28,0));
	    
	    tripleADivisionA.add(create.getTeam(i,0));
	    tripleADivisionB.add(create.getTeam(i+4,0));
	    tripleADivisionC.add(create.getTeam(i+8,0));
	    tripleADivisionD.add(create.getTeam(i+12,0));
	    tripleADivisionE.add(create.getTeam(i+16,0));
	    tripleADivisionF.add(create.getTeam(i+20,0));
	    tripleADivisionG.add(create.getTeam(i+24,0));
	    tripleADivisionH.add(create.getTeam(i+28,0));
	    
	    doubleADivisionA.add(create.getTeam(i,0));
	    doubleADivisionB.add(create.getTeam(i+4,0));
	    doubleADivisionC.add(create.getTeam(i+8,0));
	    doubleADivisionD.add(create.getTeam(i+12,0));
	    doubleADivisionE.add(create.getTeam(i+16,0));
	    doubleADivisionF.add(create.getTeam(i+20,0));
	    doubleADivisionG.add(create.getTeam(i+24,0));
	    doubleADivisionH.add(create.getTeam(i+28,0));
	    
	    singleADivisionA.add(create.getTeam(i,0));
	    singleADivisionB.add(create.getTeam(i+4,0));
	    singleADivisionC.add(create.getTeam(i+8,0));
	    singleADivisionD.add(create.getTeam(i+12,0));
	    singleADivisionE.add(create.getTeam(i+16,0));
	    singleADivisionF.add(create.getTeam(i+20,0));
	    singleADivisionG.add(create.getTeam(i+24,0));
	    singleADivisionH.add(create.getTeam(i+28,0));
	}

	ArrayList<ArrayList<team>> majorLeagueDivisions = new ArrayList<ArrayList<team>>();
	majorLeagueDivisions.add(majorDivisionA);
	majorLeagueDivisions.add(majorDivisionB);
	majorLeagueDivisions.add(majorDivisionC);
	majorLeagueDivisions.add(majorDivisionD);
	majorLeagueDivisions.add(majorDivisionE);
	majorLeagueDivisions.add(majorDivisionF);
	majorLeagueDivisions.add(majorDivisionG);
	majorLeagueDivisions.add(majorDivisionH);
	
	ArrayList<ArrayList<team>> tripleALeagueDivisions = new ArrayList<ArrayList<team>>();
	tripleALeagueDivisions.add(tripleADivisionA);
	tripleALeagueDivisions.add(tripleADivisionB);
	tripleALeagueDivisions.add(tripleADivisionC);
	tripleALeagueDivisions.add(tripleADivisionD);
	tripleALeagueDivisions.add(tripleADivisionE);
	tripleALeagueDivisions.add(tripleADivisionF);
	tripleALeagueDivisions.add(tripleADivisionG);
	tripleALeagueDivisions.add(tripleADivisionH);
	
	ArrayList<ArrayList<team>> doubleALeagueDivisions = new ArrayList<ArrayList<team>>();
	doubleALeagueDivisions.add(doubleADivisionA);
	doubleALeagueDivisions.add(doubleADivisionB);
	doubleALeagueDivisions.add(doubleADivisionC);
	doubleALeagueDivisions.add(doubleADivisionD);
	doubleALeagueDivisions.add(doubleADivisionE);
	doubleALeagueDivisions.add(doubleADivisionF);
	doubleALeagueDivisions.add(doubleADivisionG);
	doubleALeagueDivisions.add(doubleADivisionH);
	
	ArrayList<ArrayList<team>> singleALeagueDivisions = new ArrayList<ArrayList<team>>();
	singleALeagueDivisions.add(singleADivisionA);
	singleALeagueDivisions.add(singleADivisionB);
	singleALeagueDivisions.add(singleADivisionC);
	singleALeagueDivisions.add(singleADivisionD);
	singleALeagueDivisions.add(singleADivisionE);
	singleALeagueDivisions.add(singleADivisionF);
	singleALeagueDivisions.add(singleADivisionG);
	singleALeagueDivisions.add(singleADivisionH);

	
	for(int i = 0; i < majorLeagueDivisions.size()/2; i++)
	{
	    Collections.sort(majorLeagueDivisions.get(i));
	    majorConferenceA.addAll(majorLeagueDivisions.get(i));
	    Collections.sort(majorLeagueDivisions.get(i+4));
	    majorConferenceB.addAll(majorLeagueDivisions.get(i+4));
	    
	    Collections.sort(tripleALeagueDivisions.get(i));
	    tripleAConferenceA.addAll(tripleALeagueDivisions.get(i));
	    Collections.sort(tripleALeagueDivisions.get(i+4));
	    tripleAConferenceB.addAll(tripleALeagueDivisions.get(i+4));
	    
	    Collections.sort(doubleALeagueDivisions.get(i));
	    doubleAConferenceA.addAll(doubleALeagueDivisions.get(i));
	    Collections.sort(doubleALeagueDivisions.get(i+4));
	    doubleAConferenceB.addAll(doubleALeagueDivisions.get(i+4));
	    
	    Collections.sort(singleALeagueDivisions.get(i));
	    singleAConferenceA.addAll(singleALeagueDivisions.get(i));
	    Collections.sort(singleALeagueDivisions.get(i+4));
	    singleAConferenceB.addAll(singleALeagueDivisions.get(i+4));
	}
	
	Collections.sort(majorConferenceA);	
	Collections.sort(majorConferenceB);
	Collections.sort(tripleAConferenceA);
	Collections.sort(tripleAConferenceB);
	Collections.sort(doubleAConferenceA);
	Collections.sort(doubleAConferenceB);
	Collections.sort(singleAConferenceA);
	Collections.sort(singleAConferenceB);

	majorLeague.addAll(majorConferenceA);
	majorLeague.addAll(majorConferenceB);
	
	tripleALeague.addAll(tripleAConferenceA);
	tripleALeague.addAll(tripleAConferenceB);
	
	doubleALeague.addAll(doubleAConferenceB);
	doubleALeague.addAll(doubleAConferenceA);
	
	singleALeague.addAll(singleAConferenceB);
	singleALeague.addAll(singleAConferenceA);
	
	Collections.sort(majorLeague);
	Collections.sort(tripleALeague);
	Collections.sort(doubleALeague);
	Collections.sort(singleALeague);

	for(int i = 0; i < majorLeagueDivisions.size(); i++)
	{
	    for(int j = 0; j < majorLeagueDivisions.get(i).size(); j++)
	    {
		majorLeagueDivisions.get(i).get(j).setDivisionRank(j+1);
		tripleALeagueDivisions.get(i).get(j).setDivisionRank(j+1);
		doubleALeagueDivisions.get(i).get(j).setDivisionRank(j+1);
		singleALeagueDivisions.get(i).get(j).setDivisionRank(j+1);
	    }
	}
	for(int i = 0; i < majorConferenceA.size(); i++)
	{
	    majorConferenceA.get(i).setConferenceRank(i+1);
	    majorConferenceB.get(i).setConferenceRank(i+1);
	    tripleAConferenceA.get(i).setConferenceRank(i+1);
	    tripleAConferenceB.get(i).setConferenceRank(i+1);
	    doubleAConferenceA.get(i).setConferenceRank(i+1);
	    doubleAConferenceB.get(i).setConferenceRank(i+1);
	    singleAConferenceA.get(i).setConferenceRank(i+1);
	    singleAConferenceB.get(i).setConferenceRank(i+1);
	}
	for(int i = 0; i < majorLeague.size();i++)
	{
	    majorLeague.get(i).setLeagueRank(i+1);
	    tripleALeague.get(i).setLeagueRank(i+1);
	    doubleALeague.get(i).setLeagueRank(i+1);
	    singleALeague.get(i).setLeagueRank(i+1);
	}
	int division = 0;

	for(int i = 0; i < create.size(); i++)
	{
	    if(i % 4 == 0)
	    {
		majorStandings.println("Division " + (char)('A' + division) + ",Wins,Losses,Runs Scored,Runs Against,Division Rank,Conference Rank,League Rank");
		tripleAStandings.println("Division " + (char)('A' + division) + ",Wins,Losses,Runs Scored,Runs Against,Division Rank,Conference Rank,League Rank");
		doubleAStandings.println("Division " + (char)('A' + division) + ",Wins,Losses,Runs Scored,Runs Against,Division Rank,Conference Rank,League Rank");
		singleAStandings.println("Division " + (char)('A' + division) + ",Wins,Losses,Runs Scored,Runs Against,Division Rank,Conference Rank,League Rank");
		division++;
	    }
	    printTeamInfo(majorStandings, majorLeagueDivisions.get(division-1).get(i % 4));
	    printTeamInfo(tripleAStandings, tripleALeagueDivisions.get(division-1).get(i % 4));
	    printTeamInfo(doubleAStandings, doubleALeagueDivisions.get(division-1).get(i % 4));
	    printTeamInfo(singleAStandings, singleALeagueDivisions.get(division-1).get(i % 4));

	}



	majorStandings.close();
	tripleAStandings.close();
	doubleAStandings.close();
	singleAStandings.close();
	
	PrintWriter majorStats = new PrintWriter("majorStats.csv"), tripleAStats = new PrintWriter("tripleAStats.csv"), doubleAStats = new PrintWriter("doubleAStats.csv"), singleAStats = new PrintWriter("singleAStats.csv");

	
	printStats(majorLeague, majorStats);
	printStats(tripleALeague, tripleAStats);
	printStats(doubleALeague, doubleAStats);
	printStats(singleALeague, singleAStats);
	
	majorStats.close();
	tripleAStats.close();
	doubleAStats.close();
	singleAStats.close();
    }
    private static void printStats(ArrayList<team> league, PrintWriter stats) throws IOException
    {
	// 0 is atBats, 1 is hits, 2 is doubles, 3 is triples, 4 is Home Runs, 5 is RBI's, 6 is Strikeouts, 7 is walks
	for(team t:league)
	{
	    stats.println(t + "'s Player Name,At Bats,Hits,Batting Average,Doubles,Triples,Home Runs,RBI's,Strikeouts,Walks,Innings Pitched,ERA,Earned Runs Allowed,Runs Allowed,Strikeouts,Walks");
	    for(player p:t)
	    {
		stats.printf("%s,%d,%d,.%03.0f,%d,%d,%d,%d,%d,%d", p, p.getTotalAtBats(), p.getTotalHits(), p.getBattingAverage()*1000,p.getDoubles(),p.getTriples(),p.getHomeRuns(),p.getRBIs(),p.getStrikeOuts(),p.getWalks());
		if(p instanceof pitcher)
		{
		    pitcher pitch = (pitcher)p;
		    stats.printf(",%s,%01.2f,%d,%d,%d,%d\n", pitch.getInnings(),pitch.getERAAsDouble(),pitch.getEarnedRuns(),pitch.getRunsAllowed(),pitch.getStrikeOutsPitched(),pitch.getWalksGiven());
		}
		else stats.println();
	    }
	}
	
    }
    private static void printTeamInfo(PrintWriter majorStandings, team team) throws IOException
    {
	majorStandings.println(team + "," + team.getWins() +  "," + team.getLosses() + "," + team.getRunsScored() + "," + team.getRunsAgainst() + "," + team.getDivisionRank() + "," + team.getConferenceRank() + "," + team.getLeagueRank());

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
