import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Controller
{
    private static createLeagues create;
    private static ArrayList<team> majorDivisionA = new ArrayList<team>();
    private static ArrayList<team> majorDivisionB = new ArrayList<team>();
    private static ArrayList<team> majorDivisionC = new ArrayList<team>();
    private static ArrayList<team> majorDivisionD = new ArrayList<team>();
    private static ArrayList<team> majorDivisionE = new ArrayList<team>();
    private static ArrayList<team> majorDivisionF = new ArrayList<team>();
    private static ArrayList<team> majorDivisionG = new ArrayList<team>();
    private static ArrayList<team> majorDivisionH = new ArrayList<team>();
    private static ArrayList<team> majorConferenceA = new ArrayList<team>();
    private static ArrayList<team> majorConferenceB = new ArrayList<team>();
    private static ArrayList<team> majorLeague = new ArrayList<team>();

    private static ArrayList<team> tripleADivisionA = new ArrayList<team>();
    private static ArrayList<team> tripleADivisionB = new ArrayList<team>();
    private static ArrayList<team> tripleADivisionC = new ArrayList<team>();
    private static ArrayList<team> tripleADivisionD = new ArrayList<team>();
    private static ArrayList<team> tripleADivisionE = new ArrayList<team>();
    private static ArrayList<team> tripleADivisionF = new ArrayList<team>();
    private static ArrayList<team> tripleADivisionG = new ArrayList<team>();
    private static ArrayList<team> tripleADivisionH = new ArrayList<team>();
    private static ArrayList<team> tripleAConferenceA = new ArrayList<team>();
    private static ArrayList<team> tripleAConferenceB = new ArrayList<team>();
    private static ArrayList<team> tripleALeague = new ArrayList<team>();

    private static ArrayList<team> doubleADivisionA = new ArrayList<team>();
    private static ArrayList<team> doubleADivisionB = new ArrayList<team>();
    private static ArrayList<team> doubleADivisionC = new ArrayList<team>();
    private static ArrayList<team> doubleADivisionD = new ArrayList<team>();
    private static ArrayList<team> doubleADivisionE = new ArrayList<team>();
    private static ArrayList<team> doubleADivisionF = new ArrayList<team>();
    private static ArrayList<team> doubleADivisionG = new ArrayList<team>();
    private static ArrayList<team> doubleADivisionH = new ArrayList<team>();
    private static ArrayList<team> doubleAConferenceA = new ArrayList<team>();
    private static ArrayList<team> doubleAConferenceB = new ArrayList<team>();
    private static ArrayList<team> doubleALeague = new ArrayList<team>();

    private static ArrayList<team> singleADivisionA = new ArrayList<team>();
    private static ArrayList<team> singleADivisionB = new ArrayList<team>();
    private static ArrayList<team> singleADivisionC = new ArrayList<team>();
    private static ArrayList<team> singleADivisionD = new ArrayList<team>();
    private static ArrayList<team> singleADivisionE = new ArrayList<team>();
    private static ArrayList<team> singleADivisionF = new ArrayList<team>();
    private static ArrayList<team> singleADivisionG = new ArrayList<team>();
    private static ArrayList<team> singleADivisionH = new ArrayList<team>();
    private static ArrayList<team> singleAConferenceA = new ArrayList<team>();
    private static ArrayList<team> singleAConferenceB = new ArrayList<team>();
    private static ArrayList<team> singleALeague = new ArrayList<team>();
    private static PrintWriter majorChampionships, tripleAChampionships, doubleAChampionships, singleAChampionships;
   // @SuppressWarnings("static-access")
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
	try
	{
	    majorChampionships = new PrintWriter("Major Championships.csv");
	    majorChampionships.println("Conference A Team,Games Won,,Games Won,Conference B Team");

	    tripleAChampionships = new PrintWriter("Triple A Championships.csv");
	    tripleAChampionships.println("Conference A Team,Games Won,,Games Won,Conference B Team");

	    doubleAChampionships = new PrintWriter("Double A Championships.csv");
	    doubleAChampionships.println("Conference A Team,Games Won,,Games Won,Conference B Team");

	    singleAChampionships = new PrintWriter("Single A Championships.csv");
	    singleAChampionships.println("Conference A Team,Games Won,,Games Won,Conference B Team");
	}
	catch (FileNotFoundException e1)
	{
	    e1.printStackTrace();
	}
	for(int j = 0; j < 5; j++)
	{
	    System.out.println(j);
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
		//BallInPlay b = new BallInPlay();
		//System.out.printf("Popups: %d at bats, %d hits = .%03.0f\nGround Balls: %d at bats, %d hits = .%.0f\nFlyBalls: %d at bats, %d hits = .%.0f\n",
		//    b.popupAtBats, b.popupHits, ((double)b.popupHits/(double)b.popupAtBats)*1000 ,b.groundBallAtBats, b.groundBallHits, ((double)b.groundBallHits/(double)b.groundBallAtBats)*1000, b.flyballAtBats, b.flyballHits, ((double)b.flyballHits/(double)b.flyballAtBats)*1000);

		doAllPlayoffs();

		offseason();
	    }
	    catch (Exception e)
	    {
		e.printStackTrace();
	    }
	    // else load

	}
	
	System.out.println("List of winners: ");
	for(int i = 0; i < majorLeague.size(); i++)
	{
	    if(majorLeague.get(i).getChampionships() > 0)
	    {
		System.out.println(majorLeague.get(i)+ ": " + majorLeague.get(i).getChampionships());
	    }
	}
	
	majorChampionships.close();
	tripleAChampionships.close();
	doubleAChampionships.close();
	singleAChampionships.close();
    }
    private static void offseason()
    {
	for(int i = 0; i < majorLeague.size(); i++)
	{
	    majorLeague.get(i).offseason();
	    tripleALeague.get(i).offseason();
	    doubleALeague.get(i).offseason();
	    singleALeague.get(i).offseason();
	}

    }
    private static void doAllPlayoffs()
    {
	ArrayList<team> majorDivisionWinners = new ArrayList<team>();
	majorDivisionWinners.add(majorDivisionA.get(0));
	majorDivisionWinners.add(majorDivisionB.get(0));
	majorDivisionWinners.add(majorDivisionC.get(0));
	majorDivisionWinners.add(majorDivisionD.get(0));

	ArrayList<team> majorWildCard = new ArrayList<team>();
	majorWildCard.add(majorDivisionA.get(1));
	majorWildCard.add(majorDivisionB.get(1));
	majorWildCard.add(majorDivisionC.get(1));
	majorWildCard.add(majorDivisionD.get(1));
	majorWildCard.add(majorDivisionA.get(2));
	majorWildCard.add(majorDivisionB.get(2));
	majorWildCard.add(majorDivisionC.get(2));
	majorWildCard.add(majorDivisionD.get(2));

	Collections.sort(majorDivisionWinners);
	Collections.sort(majorWildCard);

	team[] majorConferenceAPlayoffTeams = new team[6];
	majorConferenceAPlayoffTeams[0] = majorDivisionWinners.get(0);
	majorConferenceAPlayoffTeams[1] = majorDivisionWinners.get(1);
	majorConferenceAPlayoffTeams[2] = majorDivisionWinners.get(2);
	majorConferenceAPlayoffTeams[3] = majorDivisionWinners.get(3);
	majorConferenceAPlayoffTeams[4] = majorWildCard.get(0);
	majorConferenceAPlayoffTeams[5] = majorWildCard.get(1);

	majorDivisionWinners = new ArrayList<team>();
	majorDivisionWinners.add(majorDivisionE.get(0));
	majorDivisionWinners.add(majorDivisionF.get(0));
	majorDivisionWinners.add(majorDivisionG.get(0));
	majorDivisionWinners.add(majorDivisionH.get(0));

	majorWildCard = new ArrayList<team>();
	majorWildCard.add(majorDivisionE.get(1));
	majorWildCard.add(majorDivisionF.get(1));
	majorWildCard.add(majorDivisionG.get(1));
	majorWildCard.add(majorDivisionH.get(1));
	majorWildCard.add(majorDivisionE.get(2));
	majorWildCard.add(majorDivisionF.get(2));
	majorWildCard.add(majorDivisionG.get(2));
	majorWildCard.add(majorDivisionH.get(2));

	Collections.sort(majorDivisionWinners);
	Collections.sort(majorWildCard);

	team[] majorConferenceBPlayoffTeams = new team[6];
	majorConferenceBPlayoffTeams[0] = majorDivisionWinners.get(0);
	majorConferenceBPlayoffTeams[1] = majorDivisionWinners.get(1);
	majorConferenceBPlayoffTeams[2] = majorDivisionWinners.get(2);
	majorConferenceBPlayoffTeams[3] = majorDivisionWinners.get(3);
	majorConferenceBPlayoffTeams[4] = majorWildCard.get(0);
	majorConferenceBPlayoffTeams[5] = majorWildCard.get(1);

	doPlayoffs(majorConferenceAPlayoffTeams, majorConferenceBPlayoffTeams, majorChampionships);

	ArrayList<team> tripleADivisionWinners = new ArrayList<team>();
	tripleADivisionWinners.add(tripleADivisionA.get(0));
	tripleADivisionWinners.add(tripleADivisionB.get(0));
	tripleADivisionWinners.add(tripleADivisionC.get(0));
	tripleADivisionWinners.add(tripleADivisionD.get(0));

	ArrayList<team> tripleAWildCard = new ArrayList<team>();
	tripleAWildCard.add(tripleADivisionA.get(1));
	tripleAWildCard.add(tripleADivisionB.get(1));
	tripleAWildCard.add(tripleADivisionC.get(1));
	tripleAWildCard.add(tripleADivisionD.get(1));
	tripleAWildCard.add(tripleADivisionA.get(2));
	tripleAWildCard.add(tripleADivisionB.get(2));
	tripleAWildCard.add(tripleADivisionC.get(2));
	tripleAWildCard.add(tripleADivisionD.get(2));

	Collections.sort(tripleADivisionWinners);
	Collections.sort(tripleAWildCard);

	team[] tripleAConferenceAPlayoffTeams = new team[6];
	tripleAConferenceAPlayoffTeams[0] = tripleADivisionWinners.get(0);
	tripleAConferenceAPlayoffTeams[1] = tripleADivisionWinners.get(1);
	tripleAConferenceAPlayoffTeams[2] = tripleADivisionWinners.get(2);
	tripleAConferenceAPlayoffTeams[3] = tripleADivisionWinners.get(3);
	tripleAConferenceAPlayoffTeams[4] = tripleAWildCard.get(0);
	tripleAConferenceAPlayoffTeams[5] = tripleAWildCard.get(1);

	tripleADivisionWinners = new ArrayList<team>();
	tripleADivisionWinners.add(tripleADivisionE.get(0));
	tripleADivisionWinners.add(tripleADivisionF.get(0));
	tripleADivisionWinners.add(tripleADivisionG.get(0));
	tripleADivisionWinners.add(tripleADivisionH.get(0));

	tripleAWildCard = new ArrayList<team>();
	tripleAWildCard.add(tripleADivisionE.get(1));
	tripleAWildCard.add(tripleADivisionF.get(1));
	tripleAWildCard.add(tripleADivisionG.get(1));
	tripleAWildCard.add(tripleADivisionH.get(1));
	tripleAWildCard.add(tripleADivisionE.get(2));
	tripleAWildCard.add(tripleADivisionF.get(2));
	tripleAWildCard.add(tripleADivisionG.get(2));
	tripleAWildCard.add(tripleADivisionH.get(2));

	Collections.sort(tripleADivisionWinners);
	Collections.sort(tripleAWildCard);

	team[] tripleAConferenceBPlayoffTeams = new team[6];
	tripleAConferenceBPlayoffTeams[0] = tripleADivisionWinners.get(0);
	tripleAConferenceBPlayoffTeams[1] = tripleADivisionWinners.get(1);
	tripleAConferenceBPlayoffTeams[2] = tripleADivisionWinners.get(2);
	tripleAConferenceBPlayoffTeams[3] = tripleADivisionWinners.get(3);
	tripleAConferenceBPlayoffTeams[4] = tripleAWildCard.get(0);
	tripleAConferenceBPlayoffTeams[5] = tripleAWildCard.get(1);

	doPlayoffs(tripleAConferenceAPlayoffTeams, tripleAConferenceBPlayoffTeams, tripleAChampionships);

	ArrayList<team> doubleADivisionWinners = new ArrayList<team>();
	doubleADivisionWinners.add(doubleADivisionA.get(0));
	doubleADivisionWinners.add(doubleADivisionB.get(0));
	doubleADivisionWinners.add(doubleADivisionC.get(0));
	doubleADivisionWinners.add(doubleADivisionD.get(0));

	ArrayList<team> doubleAWildCard = new ArrayList<team>();
	doubleAWildCard.add(doubleADivisionA.get(1));
	doubleAWildCard.add(doubleADivisionB.get(1));
	doubleAWildCard.add(doubleADivisionC.get(1));
	doubleAWildCard.add(doubleADivisionD.get(1));
	doubleAWildCard.add(doubleADivisionA.get(2));
	doubleAWildCard.add(doubleADivisionB.get(2));
	doubleAWildCard.add(doubleADivisionC.get(2));
	doubleAWildCard.add(doubleADivisionD.get(2));

	Collections.sort(doubleADivisionWinners);
	Collections.sort(doubleAWildCard);

	team[] doubleAConferenceAPlayoffTeams = new team[6];
	doubleAConferenceAPlayoffTeams[0] = doubleADivisionWinners.get(0);
	doubleAConferenceAPlayoffTeams[1] = doubleADivisionWinners.get(1);
	doubleAConferenceAPlayoffTeams[2] = doubleADivisionWinners.get(2);
	doubleAConferenceAPlayoffTeams[3] = doubleADivisionWinners.get(3);
	doubleAConferenceAPlayoffTeams[4] = doubleAWildCard.get(0);
	doubleAConferenceAPlayoffTeams[5] = doubleAWildCard.get(1);

	doubleADivisionWinners = new ArrayList<team>();
	doubleADivisionWinners.add(doubleADivisionE.get(0));
	doubleADivisionWinners.add(doubleADivisionF.get(0));
	doubleADivisionWinners.add(doubleADivisionG.get(0));
	doubleADivisionWinners.add(doubleADivisionH.get(0));

	doubleAWildCard = new ArrayList<team>();
	doubleAWildCard.add(doubleADivisionE.get(1));
	doubleAWildCard.add(doubleADivisionF.get(1));
	doubleAWildCard.add(doubleADivisionG.get(1));
	doubleAWildCard.add(doubleADivisionH.get(1));
	doubleAWildCard.add(doubleADivisionE.get(2));
	doubleAWildCard.add(doubleADivisionF.get(2));
	doubleAWildCard.add(doubleADivisionG.get(2));
	doubleAWildCard.add(doubleADivisionH.get(2));

	Collections.sort(doubleADivisionWinners);
	Collections.sort(doubleAWildCard);

	team[] doubleAConferenceBPlayoffTeams = new team[6];
	doubleAConferenceBPlayoffTeams[0] = doubleADivisionWinners.get(0);
	doubleAConferenceBPlayoffTeams[1] = doubleADivisionWinners.get(1);
	doubleAConferenceBPlayoffTeams[2] = doubleADivisionWinners.get(2);
	doubleAConferenceBPlayoffTeams[3] = doubleADivisionWinners.get(3);
	doubleAConferenceBPlayoffTeams[4] = doubleAWildCard.get(0);
	doubleAConferenceBPlayoffTeams[5] = doubleAWildCard.get(1);

	doPlayoffs(doubleAConferenceAPlayoffTeams, doubleAConferenceBPlayoffTeams, doubleAChampionships);

	ArrayList<team> singleADivisionWinners = new ArrayList<team>();
	singleADivisionWinners.add(singleADivisionA.get(0));
	singleADivisionWinners.add(singleADivisionB.get(0));
	singleADivisionWinners.add(singleADivisionC.get(0));
	singleADivisionWinners.add(singleADivisionD.get(0));

	ArrayList<team> singleAWildCard = new ArrayList<team>();
	singleAWildCard.add(singleADivisionA.get(1));
	singleAWildCard.add(singleADivisionB.get(1));
	singleAWildCard.add(singleADivisionC.get(1));
	singleAWildCard.add(singleADivisionD.get(1));
	singleAWildCard.add(singleADivisionA.get(2));
	singleAWildCard.add(singleADivisionB.get(2));
	singleAWildCard.add(singleADivisionC.get(2));
	singleAWildCard.add(singleADivisionD.get(2));

	Collections.sort(singleADivisionWinners);
	Collections.sort(singleAWildCard);

	team[] singleAConferenceAPlayoffTeams = new team[6];
	singleAConferenceAPlayoffTeams[0] = singleADivisionWinners.get(0);
	singleAConferenceAPlayoffTeams[1] = singleADivisionWinners.get(1);
	singleAConferenceAPlayoffTeams[2] = singleADivisionWinners.get(2);
	singleAConferenceAPlayoffTeams[3] = singleADivisionWinners.get(3);
	singleAConferenceAPlayoffTeams[4] = singleAWildCard.get(0);
	singleAConferenceAPlayoffTeams[5] = singleAWildCard.get(1);

	singleADivisionWinners = new ArrayList<team>();
	singleADivisionWinners.add(singleADivisionE.get(0));
	singleADivisionWinners.add(singleADivisionF.get(0));
	singleADivisionWinners.add(singleADivisionG.get(0));
	singleADivisionWinners.add(singleADivisionH.get(0));

	singleAWildCard = new ArrayList<team>();
	singleAWildCard.add(singleADivisionE.get(1));
	singleAWildCard.add(singleADivisionF.get(1));
	singleAWildCard.add(singleADivisionG.get(1));
	singleAWildCard.add(singleADivisionH.get(1));
	singleAWildCard.add(singleADivisionE.get(2));
	singleAWildCard.add(singleADivisionF.get(2));
	singleAWildCard.add(singleADivisionG.get(2));
	singleAWildCard.add(singleADivisionH.get(2));

	Collections.sort(singleADivisionWinners);
	Collections.sort(singleAWildCard);

	team[] singleAConferenceBPlayoffTeams = new team[6];
	singleAConferenceBPlayoffTeams[0] = singleADivisionWinners.get(0);
	singleAConferenceBPlayoffTeams[1] = singleADivisionWinners.get(1);
	singleAConferenceBPlayoffTeams[2] = singleADivisionWinners.get(2);
	singleAConferenceBPlayoffTeams[3] = singleADivisionWinners.get(3);
	singleAConferenceBPlayoffTeams[4] = singleAWildCard.get(0);
	singleAConferenceBPlayoffTeams[5] = singleAWildCard.get(1);

	doPlayoffs(singleAConferenceAPlayoffTeams, singleAConferenceBPlayoffTeams, singleAChampionships);



    }
    private static void doPlayoffs(team[] firstConference, team[] secondConference, PrintWriter championships)
    {
	team firstOneWinner = doRound(firstConference[2],firstConference[5]).getWinner();
	team firstTwoWinner = doRound(firstConference[3], firstConference[4]).getWinner();

	team secondOneWinner = doRound(secondConference[2],secondConference[5]).getWinner();
	team secondTwoWinner = doRound(secondConference[3], secondConference[4]).getWinner();

	team firstThreeWinner = doRound(firstConference[1], firstOneWinner).getWinner();
	team firstFourWinner = doRound(firstConference[0], firstTwoWinner).getWinner();

	team secondThreeWinner = doRound(secondConference[1], secondOneWinner).getWinner();
	team secondFourWinner = doRound(secondConference[0], secondTwoWinner).getWinner();

	team firstFiveWinner, secondFiveWinner;

	if(firstThreeWinner.getLeagueRank() > firstFourWinner.getLeagueRank())firstFiveWinner = doRound(firstFourWinner, firstThreeWinner).getWinner();
	else firstFiveWinner = doRound(firstThreeWinner, firstFourWinner).getWinner();

	if(secondThreeWinner.getLeagueRank() > secondFourWinner.getLeagueRank())secondFiveWinner = doRound(secondFourWinner, secondThreeWinner).getWinner();
	else secondFiveWinner = doRound(secondThreeWinner, secondFourWinner).getWinner();

	results championshipWinner;

	firstFiveWinner.addConferenceChampionship();
	secondFiveWinner.addConferenceChampionship();

	if(firstFiveWinner.getLeagueRank() > secondFiveWinner.getLeagueRank())
	{
	    championshipWinner = doRound(secondFiveWinner, firstFiveWinner);
	    championships.println(firstFiveWinner + "," + championshipWinner.getTeamTwoWins()+",," + championshipWinner.getTeamOneWins()+ ","+secondFiveWinner);
	}
	else 
	{
	    championshipWinner = doRound(firstFiveWinner, secondFiveWinner);
	    championships.println(firstFiveWinner + "," + championshipWinner.getTeamOneWins()+",," + championshipWinner.getTeamTwoWins()+ ","+secondFiveWinner);
	}

	championshipWinner.getWinner().addChampionship();
    }
    private static results doRound(team teamOne, team teamTwo)
    {

	int teamOneWins = 0, teamTwoWins = 0;

	game newGame = new game(teamOne, teamTwo);

	if(newGame.getWinner())teamOneWins++;
	else teamTwoWins++;

	newGame = new game(teamOne, teamTwo);

	if(newGame.getWinner())teamOneWins++;
	else teamTwoWins++;

	newGame = new game(teamTwo, teamOne);

	if(newGame.getWinner())teamTwoWins++;
	else teamOneWins++;

	newGame = new game(teamTwo, teamOne);

	if(newGame.getWinner())teamTwoWins++;
	else teamOneWins++;

	if(teamOneWins != 4 && teamTwoWins != 4)
	{
	    newGame = new game(teamTwo, teamOne);

	    if(newGame.getWinner())teamTwoWins++;
	    else teamOneWins++;
	    if(teamOneWins != 4 && teamTwoWins != 4)
	    {
		newGame = new game(teamOne, teamTwo);

		if(newGame.getWinner())teamOneWins++;
		else teamTwoWins++;
		if(teamOneWins != 4 && teamTwoWins != 4)
		{
		    newGame = new game(teamOne, teamTwo);

		    if(newGame.getWinner())teamOneWins++;
		    else teamTwoWins++;
		}
	    }
	}


	return new results(teamOneWins == 4 ? teamOne : teamTwo, teamOneWins, teamTwoWins);
    }

    private static void calculateStandings() throws IOException
    {
	PrintWriter majorStandings = new PrintWriter("majorStandings.csv"), tripleAStandings = new PrintWriter("tripleAStandings.csv"), doubleAStandings = new PrintWriter("doubleAStandings.csv"), singleAStandings = new PrintWriter("singleAStandings.csv");

	majorDivisionA = new ArrayList<team>();
	majorDivisionB = new ArrayList<team>();
	majorDivisionC = new ArrayList<team>();
	majorDivisionD = new ArrayList<team>();
	majorDivisionE = new ArrayList<team>();
	majorDivisionF = new ArrayList<team>();
	majorDivisionG = new ArrayList<team>();
	majorDivisionH = new ArrayList<team>();
	majorConferenceA = new ArrayList<team>();
	majorConferenceB = new ArrayList<team>();
	majorLeague = new ArrayList<team>();

	tripleADivisionA = new ArrayList<team>();
	tripleADivisionB = new ArrayList<team>();
	tripleADivisionC = new ArrayList<team>();
	tripleADivisionD = new ArrayList<team>();
	tripleADivisionE = new ArrayList<team>();
	tripleADivisionF = new ArrayList<team>();
	tripleADivisionG = new ArrayList<team>();
	tripleADivisionH = new ArrayList<team>();
	tripleAConferenceA = new ArrayList<team>();
	tripleAConferenceB = new ArrayList<team>();
	tripleALeague = new ArrayList<team>();

	doubleADivisionA = new ArrayList<team>();
	doubleADivisionB = new ArrayList<team>();
	doubleADivisionC = new ArrayList<team>();
	doubleADivisionD = new ArrayList<team>();
	doubleADivisionE = new ArrayList<team>();
	doubleADivisionF = new ArrayList<team>();
	doubleADivisionG = new ArrayList<team>();
	doubleADivisionH = new ArrayList<team>();
	doubleAConferenceA = new ArrayList<team>();
	doubleAConferenceB = new ArrayList<team>();
	doubleALeague = new ArrayList<team>();

	singleADivisionA = new ArrayList<team>();
	singleADivisionB = new ArrayList<team>();
	singleADivisionC = new ArrayList<team>();
	singleADivisionD = new ArrayList<team>();
	singleADivisionE = new ArrayList<team>();
	singleADivisionF = new ArrayList<team>();
	singleADivisionG = new ArrayList<team>();
	singleADivisionH = new ArrayList<team>();
	singleAConferenceA = new ArrayList<team>();
	singleAConferenceB = new ArrayList<team>();
	singleALeague = new ArrayList<team>();

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

	    tripleADivisionA.add(create.getTeam(i,1));
	    tripleADivisionB.add(create.getTeam(i+4,1));
	    tripleADivisionC.add(create.getTeam(i+8,1));
	    tripleADivisionD.add(create.getTeam(i+12,1));
	    tripleADivisionE.add(create.getTeam(i+16,1));
	    tripleADivisionF.add(create.getTeam(i+20,1));
	    tripleADivisionG.add(create.getTeam(i+24,1));
	    tripleADivisionH.add(create.getTeam(i+28,1));

	    doubleADivisionA.add(create.getTeam(i,2));
	    doubleADivisionB.add(create.getTeam(i+4,2));
	    doubleADivisionC.add(create.getTeam(i+8,2));
	    doubleADivisionD.add(create.getTeam(i+12,2));
	    doubleADivisionE.add(create.getTeam(i+16,2));
	    doubleADivisionF.add(create.getTeam(i+20,2));
	    doubleADivisionG.add(create.getTeam(i+24,2));
	    doubleADivisionH.add(create.getTeam(i+28,2));

	    singleADivisionA.add(create.getTeam(i,3));
	    singleADivisionB.add(create.getTeam(i+4,3));
	    singleADivisionC.add(create.getTeam(i+8,3));
	    singleADivisionD.add(create.getTeam(i+12,3));
	    singleADivisionE.add(create.getTeam(i+16,3));
	    singleADivisionF.add(create.getTeam(i+20,3));
	    singleADivisionG.add(create.getTeam(i+24,3));
	    singleADivisionH.add(create.getTeam(i+28,3));
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
	    stats.println(t + "'s Player Name,At Bats,Hits,Batting Average,Doubles,Triples,Home Runs,RBI's,Strikeouts,Walks,Innings Pitched,ERA,Earned Runs Allowed,Runs Allowed,Strikeouts,Walks,Wins,Losses");
	    for(player p:t)
	    {
		stats.printf("%s,%d,%d,.%03.0f,%d,%d,%d,%d,%d,%d", p.getPositionAsString() + " " + p, p.getTotalAtBats(), p.getTotalHits(), p.getBattingAverage()*1000,p.getDoubles(),p.getTriples(),p.getHomeRuns(),p.getRBIs(),p.getStrikeOuts(),p.getWalks());
		if(p instanceof pitcher)
		{
		    pitcher pitch = (pitcher)p;
		    stats.printf(",%s,%01.2f,%d,%d,%d,%d,%d,%d\n", pitch.getInnings(),pitch.getERAAsDouble(),pitch.getEarnedRuns(),pitch.getRunsAllowed(),pitch.getStrikeOutsPitched(),pitch.getWalksGiven(), pitch.getWins(), pitch.getLosses());
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
