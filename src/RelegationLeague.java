import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class RelegationLeague
{
	private static createLeagues create;
	private static College college;
	private static ArrayList<Team> majorDivisionA = new ArrayList<Team>();
	private static ArrayList<Team> majorDivisionB = new ArrayList<Team>();
	private static ArrayList<Team> majorDivisionC = new ArrayList<Team>();
	private static ArrayList<Team> majorDivisionD = new ArrayList<Team>();
	private static ArrayList<Team> majorDivisionE = new ArrayList<Team>();
	private static ArrayList<Team> majorDivisionF = new ArrayList<Team>();
	private static ArrayList<Team> majorDivisionG = new ArrayList<Team>();
	private static ArrayList<Team> majorDivisionH = new ArrayList<Team>();
	private static ArrayList<Team> majorConferenceA = new ArrayList<Team>();
	private static ArrayList<Team> majorConferenceB = new ArrayList<Team>();
	private static ArrayList<Team> majorLeague = new ArrayList<Team>();

	private static ArrayList<Team> tripleADivisionA = new ArrayList<Team>();
	private static ArrayList<Team> tripleADivisionB = new ArrayList<Team>();
	private static ArrayList<Team> tripleADivisionC = new ArrayList<Team>();
	private static ArrayList<Team> tripleADivisionD = new ArrayList<Team>();
	private static ArrayList<Team> tripleADivisionE = new ArrayList<Team>();
	private static ArrayList<Team> tripleADivisionF = new ArrayList<Team>();
	private static ArrayList<Team> tripleADivisionG = new ArrayList<Team>();
	private static ArrayList<Team> tripleADivisionH = new ArrayList<Team>();
	private static ArrayList<Team> tripleAConferenceA = new ArrayList<Team>();
	private static ArrayList<Team> tripleAConferenceB = new ArrayList<Team>();
	private static ArrayList<Team> tripleALeague = new ArrayList<Team>();

	private static ArrayList<Team> doubleADivisionA = new ArrayList<Team>();
	private static ArrayList<Team> doubleADivisionB = new ArrayList<Team>();
	private static ArrayList<Team> doubleADivisionC = new ArrayList<Team>();
	private static ArrayList<Team> doubleADivisionD = new ArrayList<Team>();
	private static ArrayList<Team> doubleADivisionE = new ArrayList<Team>();
	private static ArrayList<Team> doubleADivisionF = new ArrayList<Team>();
	private static ArrayList<Team> doubleADivisionG = new ArrayList<Team>();
	private static ArrayList<Team> doubleADivisionH = new ArrayList<Team>();
	private static ArrayList<Team> doubleAConferenceA = new ArrayList<Team>();
	private static ArrayList<Team> doubleAConferenceB = new ArrayList<Team>();
	private static ArrayList<Team> doubleALeague = new ArrayList<Team>();

	private static ArrayList<Team> singleADivisionA = new ArrayList<Team>();
	private static ArrayList<Team> singleADivisionB = new ArrayList<Team>();
	private static ArrayList<Team> singleADivisionC = new ArrayList<Team>();
	private static ArrayList<Team> singleADivisionD = new ArrayList<Team>();
	private static ArrayList<Team> singleADivisionE = new ArrayList<Team>();
	private static ArrayList<Team> singleADivisionF = new ArrayList<Team>();
	private static ArrayList<Team> singleADivisionG = new ArrayList<Team>();
	private static ArrayList<Team> singleADivisionH = new ArrayList<Team>();
	private static ArrayList<Team> singleAConferenceA = new ArrayList<Team>();
	private static ArrayList<Team> singleAConferenceB = new ArrayList<Team>();
	private static ArrayList<Team> singleALeague = new ArrayList<Team>();
	private static PrintWriter majorChampionships, tripleAChampionships, doubleAChampionships, singleAChampionships;
	
	private static Team[] top4SingleA, top4DoubleA, top4TripleA;
	private static Team[] bottom4DoubleA, bottom4TripleA, bottom4Majors;
	
	public static void main(String[] args)
	{
		FileUtils.cleanDirectory("Team");
		boolean newLeague = true;
		if(newLeague)
		{
			top4SingleA = new Team[2]; 
			top4DoubleA = new Team[2]; 
			top4TripleA = new Team[2];
			bottom4DoubleA = new Team[2]; 
			bottom4TripleA = new Team[2]; 
			bottom4Majors = new Team[2];
			create = new createLeagues();
			college = new College();
			TeamNameGenerator TeamGen = new TeamNameGenerator();
			for(int i = 0; i < 128; i++)
			{
				String TeamName = TeamGen.getName();
				boolean flag = true;
				while(flag)
				{
					flag = false;
					for(int j = 0; j < create.size();j++)
					{
						if(TeamName.equals(create.getTeam(j, 0)))
						{
							flag = true;
							TeamName = TeamGen.getName();
							break;
						}
					}
				}
				create.addTeam(new Team(TeamName, i, i % 4));

			}

			PlayerGenerator gen = new PlayerGenerator();
			new RelegationDraft(create.getAllTeams(), gen.getPlayers(), true);		



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
		for(int j = 0; j < 100; j++)
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
				
				for(int i = 0; i < majorLeague.size();i++)
				{
					majorLeague.get(i).addTeamResults(j+1, i+1);
					tripleALeague.get(i).addTeamResults(j+1, i+1);
					doubleALeague.get(i).addTeamResults(j+1, i+1);
					singleALeague.get(i).addTeamResults(j+1, i+1);
				}
				
				bottom4DoubleA[0] = doubleAConferenceA.get(doubleAConferenceA.size()-1);
				bottom4DoubleA[1] = doubleAConferenceB.get(doubleAConferenceB.size()-1);
				
				bottom4TripleA[0] = tripleAConferenceA.get(tripleAConferenceA.size()-1);
				bottom4TripleA[1] = tripleAConferenceB.get(tripleAConferenceB.size()-1);
				
				bottom4Majors[0] = majorConferenceA.get(majorConferenceA.size()-1);
				bottom4Majors[1] = majorConferenceB.get(majorConferenceB.size()-1);
				
				doAllPlayoffs();
				
				System.out.println("Getting Promoted: ");
				
				System.out.println("From Single A: " + top4SingleA[0] + " and " + top4SingleA[1]);
				System.out.println("From Double A: " + top4DoubleA[0] + " and " + top4DoubleA[1]);
				System.out.println("From Triple A: " + top4TripleA[0] + " and " + top4TripleA[1]);

				System.out.println("Getting Demoted: ");
				
				System.out.println("From Double A: " + bottom4DoubleA[0] + " and " + bottom4DoubleA[1]);
				System.out.println("From Triple A: " + bottom4TripleA[0] + " and " + bottom4TripleA[1]);
				System.out.println("From the Majors: " + bottom4Majors[0] + " and " + bottom4Majors[1]);
				
				create.swapTeams(bottom4DoubleA, top4SingleA);
				create.swapTeams(bottom4TripleA, top4DoubleA);
				create.swapTeams(bottom4Majors, top4TripleA);
				

				offseason();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			// else load

		}

		for(int i = 0; i < majorLeague.size(); i++)
		{
			majorLeague.get(i).close();
			tripleALeague.get(i).close();
			doubleALeague.get(i).close();
			singleALeague.get(i).close();
		}

		majorChampionships.close();
		tripleAChampionships.close();
		doubleAChampionships.close();
		singleAChampionships.close();

	}
	private static void offseason()
	{
		ArrayList<Team> allTeams = new ArrayList<Team>();
		for(int i = 0; i < majorLeague.size(); i++)
		{
			allTeams.add(singleALeague.get(i));
			allTeams.add(doubleALeague.get(i));
			allTeams.add(tripleALeague.get(i));
			allTeams.add(majorLeague.get(i));	
		}
		new RelegationDraft(allTeams, college.getNewProPlayers(), false);
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
		ArrayList<Team> majorDivisionWinners = new ArrayList<Team>();
		majorDivisionWinners.add(majorDivisionA.get(0));
		majorDivisionWinners.add(majorDivisionB.get(0));
		majorDivisionWinners.add(majorDivisionC.get(0));
		majorDivisionWinners.add(majorDivisionD.get(0));

		ArrayList<Team> majorWildCard = new ArrayList<Team>();
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

		Team[] majorConferenceAPlayoffTeams = new Team[6];
		majorConferenceAPlayoffTeams[0] = majorDivisionWinners.get(0);
		majorConferenceAPlayoffTeams[1] = majorDivisionWinners.get(1);
		majorConferenceAPlayoffTeams[2] = majorDivisionWinners.get(2);
		majorConferenceAPlayoffTeams[3] = majorDivisionWinners.get(3);
		majorConferenceAPlayoffTeams[4] = majorWildCard.get(0);
		majorConferenceAPlayoffTeams[5] = majorWildCard.get(1);

		majorDivisionWinners = new ArrayList<Team>();
		majorDivisionWinners.add(majorDivisionE.get(0));
		majorDivisionWinners.add(majorDivisionF.get(0));
		majorDivisionWinners.add(majorDivisionG.get(0));
		majorDivisionWinners.add(majorDivisionH.get(0));

		majorWildCard = new ArrayList<Team>();
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

		Team[] majorConferenceBPlayoffTeams = new Team[6];
		majorConferenceBPlayoffTeams[0] = majorDivisionWinners.get(0);
		majorConferenceBPlayoffTeams[1] = majorDivisionWinners.get(1);
		majorConferenceBPlayoffTeams[2] = majorDivisionWinners.get(2);
		majorConferenceBPlayoffTeams[3] = majorDivisionWinners.get(3);
		majorConferenceBPlayoffTeams[4] = majorWildCard.get(0);
		majorConferenceBPlayoffTeams[5] = majorWildCard.get(1);

		doPlayoffs(majorConferenceAPlayoffTeams, majorConferenceBPlayoffTeams, majorChampionships);

		ArrayList<Team> tripleADivisionWinners = new ArrayList<Team>();
		tripleADivisionWinners.add(tripleADivisionA.get(0));
		tripleADivisionWinners.add(tripleADivisionB.get(0));
		tripleADivisionWinners.add(tripleADivisionC.get(0));
		tripleADivisionWinners.add(tripleADivisionD.get(0));

		ArrayList<Team> tripleAWildCard = new ArrayList<Team>();
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

		Team[] tripleAConferenceAPlayoffTeams = new Team[6];
		tripleAConferenceAPlayoffTeams[0] = tripleADivisionWinners.get(0);
		tripleAConferenceAPlayoffTeams[1] = tripleADivisionWinners.get(1);
		tripleAConferenceAPlayoffTeams[2] = tripleADivisionWinners.get(2);
		tripleAConferenceAPlayoffTeams[3] = tripleADivisionWinners.get(3);
		tripleAConferenceAPlayoffTeams[4] = tripleAWildCard.get(0);
		tripleAConferenceAPlayoffTeams[5] = tripleAWildCard.get(1);

		tripleADivisionWinners = new ArrayList<Team>();
		tripleADivisionWinners.add(tripleADivisionE.get(0));
		tripleADivisionWinners.add(tripleADivisionF.get(0));
		tripleADivisionWinners.add(tripleADivisionG.get(0));
		tripleADivisionWinners.add(tripleADivisionH.get(0));

		tripleAWildCard = new ArrayList<Team>();
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

		Team[] tripleAConferenceBPlayoffTeams = new Team[6];
		tripleAConferenceBPlayoffTeams[0] = tripleADivisionWinners.get(0);
		tripleAConferenceBPlayoffTeams[1] = tripleADivisionWinners.get(1);
		tripleAConferenceBPlayoffTeams[2] = tripleADivisionWinners.get(2);
		tripleAConferenceBPlayoffTeams[3] = tripleADivisionWinners.get(3);
		tripleAConferenceBPlayoffTeams[4] = tripleAWildCard.get(0);
		tripleAConferenceBPlayoffTeams[5] = tripleAWildCard.get(1);

		top4TripleA = doPlayoffs(tripleAConferenceAPlayoffTeams, tripleAConferenceBPlayoffTeams, tripleAChampionships);

		ArrayList<Team> doubleADivisionWinners = new ArrayList<Team>();
		doubleADivisionWinners.add(doubleADivisionA.get(0));
		doubleADivisionWinners.add(doubleADivisionB.get(0));
		doubleADivisionWinners.add(doubleADivisionC.get(0));
		doubleADivisionWinners.add(doubleADivisionD.get(0));

		ArrayList<Team> doubleAWildCard = new ArrayList<Team>();
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

		Team[] doubleAConferenceAPlayoffTeams = new Team[6];
		doubleAConferenceAPlayoffTeams[0] = doubleADivisionWinners.get(0);
		doubleAConferenceAPlayoffTeams[1] = doubleADivisionWinners.get(1);
		doubleAConferenceAPlayoffTeams[2] = doubleADivisionWinners.get(2);
		doubleAConferenceAPlayoffTeams[3] = doubleADivisionWinners.get(3);
		doubleAConferenceAPlayoffTeams[4] = doubleAWildCard.get(0);
		doubleAConferenceAPlayoffTeams[5] = doubleAWildCard.get(1);

		doubleADivisionWinners = new ArrayList<Team>();
		doubleADivisionWinners.add(doubleADivisionE.get(0));
		doubleADivisionWinners.add(doubleADivisionF.get(0));
		doubleADivisionWinners.add(doubleADivisionG.get(0));
		doubleADivisionWinners.add(doubleADivisionH.get(0));

		doubleAWildCard = new ArrayList<Team>();
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

		Team[] doubleAConferenceBPlayoffTeams = new Team[6];
		doubleAConferenceBPlayoffTeams[0] = doubleADivisionWinners.get(0);
		doubleAConferenceBPlayoffTeams[1] = doubleADivisionWinners.get(1);
		doubleAConferenceBPlayoffTeams[2] = doubleADivisionWinners.get(2);
		doubleAConferenceBPlayoffTeams[3] = doubleADivisionWinners.get(3);
		doubleAConferenceBPlayoffTeams[4] = doubleAWildCard.get(0);
		doubleAConferenceBPlayoffTeams[5] = doubleAWildCard.get(1);

		top4DoubleA = doPlayoffs(doubleAConferenceAPlayoffTeams, doubleAConferenceBPlayoffTeams, doubleAChampionships);

		ArrayList<Team> singleADivisionWinners = new ArrayList<Team>();
		singleADivisionWinners.add(singleADivisionA.get(0));
		singleADivisionWinners.add(singleADivisionB.get(0));
		singleADivisionWinners.add(singleADivisionC.get(0));
		singleADivisionWinners.add(singleADivisionD.get(0));

		ArrayList<Team> singleAWildCard = new ArrayList<Team>();
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

		Team[] singleAConferenceAPlayoffTeams = new Team[6];
		singleAConferenceAPlayoffTeams[0] = singleADivisionWinners.get(0);
		singleAConferenceAPlayoffTeams[1] = singleADivisionWinners.get(1);
		singleAConferenceAPlayoffTeams[2] = singleADivisionWinners.get(2);
		singleAConferenceAPlayoffTeams[3] = singleADivisionWinners.get(3);
		singleAConferenceAPlayoffTeams[4] = singleAWildCard.get(0);
		singleAConferenceAPlayoffTeams[5] = singleAWildCard.get(1);

		singleADivisionWinners = new ArrayList<Team>();
		singleADivisionWinners.add(singleADivisionE.get(0));
		singleADivisionWinners.add(singleADivisionF.get(0));
		singleADivisionWinners.add(singleADivisionG.get(0));
		singleADivisionWinners.add(singleADivisionH.get(0));

		singleAWildCard = new ArrayList<Team>();
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

		Team[] singleAConferenceBPlayoffTeams = new Team[6];
		singleAConferenceBPlayoffTeams[0] = singleADivisionWinners.get(0);
		singleAConferenceBPlayoffTeams[1] = singleADivisionWinners.get(1);
		singleAConferenceBPlayoffTeams[2] = singleADivisionWinners.get(2);
		singleAConferenceBPlayoffTeams[3] = singleADivisionWinners.get(3);
		singleAConferenceBPlayoffTeams[4] = singleAWildCard.get(0);
		singleAConferenceBPlayoffTeams[5] = singleAWildCard.get(1);

		top4SingleA = doPlayoffs(singleAConferenceAPlayoffTeams, singleAConferenceBPlayoffTeams, singleAChampionships);
	}
	private static Team[] doPlayoffs(Team[] firstConference, Team[] secondConference, PrintWriter championships)
	{
		Team[] topFour = new Team[2];
		Team firstOneWinner = doRound(firstConference[2],firstConference[5]).getWinner();
		Team firstTwoWinner = doRound(firstConference[3], firstConference[4]).getWinner();

		Team secondOneWinner = doRound(secondConference[2],secondConference[5]).getWinner();
		Team secondTwoWinner = doRound(secondConference[3], secondConference[4]).getWinner();

		Team firstThreeWinner = doRound(firstConference[1], firstOneWinner).getWinner();
		Team firstFourWinner = doRound(firstConference[0], firstTwoWinner).getWinner();

		Team secondThreeWinner = doRound(secondConference[1], secondOneWinner).getWinner();
		Team secondFourWinner = doRound(secondConference[0], secondTwoWinner).getWinner();

		Team firstFiveWinner, secondFiveWinner;

		if(firstThreeWinner.getLeagueRank() > firstFourWinner.getLeagueRank())firstFiveWinner = doRound(firstFourWinner, firstThreeWinner).getWinner();
		else firstFiveWinner = doRound(firstThreeWinner, firstFourWinner).getWinner();

		if(secondThreeWinner.getLeagueRank() > secondFourWinner.getLeagueRank())secondFiveWinner = doRound(secondFourWinner, secondThreeWinner).getWinner();
		else secondFiveWinner = doRound(secondThreeWinner, secondFourWinner).getWinner();

		results championshipWinner;

		firstFiveWinner.addConferenceChampionship();
		secondFiveWinner.addConferenceChampionship();
		
		topFour[0] = firstFiveWinner;
		topFour[1] = secondFiveWinner;

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
		return topFour;
	}
	private static results doRound(Team TeamOne, Team TeamTwo)
	{

		int TeamOneWins = 0, TeamTwoWins = 0;

		game newGame = new game(TeamOne, TeamTwo);

		if(newGame.getWinner())TeamOneWins++;
		else TeamTwoWins++;

		newGame = new game(TeamOne, TeamTwo);

		if(newGame.getWinner())TeamOneWins++;
		else TeamTwoWins++;

		newGame = new game(TeamTwo, TeamOne);

		if(newGame.getWinner())TeamTwoWins++;
		else TeamOneWins++;

		newGame = new game(TeamTwo, TeamOne);

		if(newGame.getWinner())TeamTwoWins++;
		else TeamOneWins++;

		if(TeamOneWins != 4 && TeamTwoWins != 4)
		{
			newGame = new game(TeamTwo, TeamOne);

			if(newGame.getWinner())TeamTwoWins++;
			else TeamOneWins++;
			if(TeamOneWins != 4 && TeamTwoWins != 4)
			{
				newGame = new game(TeamOne, TeamTwo);

				if(newGame.getWinner())TeamOneWins++;
				else TeamTwoWins++;
				if(TeamOneWins != 4 && TeamTwoWins != 4)
				{
					newGame = new game(TeamOne, TeamTwo);

					if(newGame.getWinner())TeamOneWins++;
					else TeamTwoWins++;
				}
			}
		}


		return new results(TeamOneWins == 4 ? TeamOne : TeamTwo, TeamOneWins, TeamTwoWins);
	}

	private static void calculateStandings() throws IOException
	{
		PrintWriter majorStandings = new PrintWriter("majorStandings.csv"), tripleAStandings = new PrintWriter("tripleAStandings.csv"), doubleAStandings = new PrintWriter("doubleAStandings.csv"), singleAStandings = new PrintWriter("singleAStandings.csv");

		majorDivisionA = new ArrayList<Team>();
		majorDivisionB = new ArrayList<Team>();
		majorDivisionC = new ArrayList<Team>();
		majorDivisionD = new ArrayList<Team>();
		majorDivisionE = new ArrayList<Team>();
		majorDivisionF = new ArrayList<Team>();
		majorDivisionG = new ArrayList<Team>();
		majorDivisionH = new ArrayList<Team>();
		majorConferenceA = new ArrayList<Team>();
		majorConferenceB = new ArrayList<Team>();
		majorLeague = new ArrayList<Team>();

		tripleADivisionA = new ArrayList<Team>();
		tripleADivisionB = new ArrayList<Team>();
		tripleADivisionC = new ArrayList<Team>();
		tripleADivisionD = new ArrayList<Team>();
		tripleADivisionE = new ArrayList<Team>();
		tripleADivisionF = new ArrayList<Team>();
		tripleADivisionG = new ArrayList<Team>();
		tripleADivisionH = new ArrayList<Team>();
		tripleAConferenceA = new ArrayList<Team>();
		tripleAConferenceB = new ArrayList<Team>();
		tripleALeague = new ArrayList<Team>();

		doubleADivisionA = new ArrayList<Team>();
		doubleADivisionB = new ArrayList<Team>();
		doubleADivisionC = new ArrayList<Team>();
		doubleADivisionD = new ArrayList<Team>();
		doubleADivisionE = new ArrayList<Team>();
		doubleADivisionF = new ArrayList<Team>();
		doubleADivisionG = new ArrayList<Team>();
		doubleADivisionH = new ArrayList<Team>();
		doubleAConferenceA = new ArrayList<Team>();
		doubleAConferenceB = new ArrayList<Team>();
		doubleALeague = new ArrayList<Team>();

		singleADivisionA = new ArrayList<Team>();
		singleADivisionB = new ArrayList<Team>();
		singleADivisionC = new ArrayList<Team>();
		singleADivisionD = new ArrayList<Team>();
		singleADivisionE = new ArrayList<Team>();
		singleADivisionF = new ArrayList<Team>();
		singleADivisionG = new ArrayList<Team>();
		singleADivisionH = new ArrayList<Team>();
		singleAConferenceA = new ArrayList<Team>();
		singleAConferenceB = new ArrayList<Team>();
		singleALeague = new ArrayList<Team>();

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

		ArrayList<ArrayList<Team>> majorLeagueDivisions = new ArrayList<ArrayList<Team>>();
		majorLeagueDivisions.add(majorDivisionA);
		majorLeagueDivisions.add(majorDivisionB);
		majorLeagueDivisions.add(majorDivisionC);
		majorLeagueDivisions.add(majorDivisionD);
		majorLeagueDivisions.add(majorDivisionE);
		majorLeagueDivisions.add(majorDivisionF);
		majorLeagueDivisions.add(majorDivisionG);
		majorLeagueDivisions.add(majorDivisionH);

		ArrayList<ArrayList<Team>> tripleALeagueDivisions = new ArrayList<ArrayList<Team>>();
		tripleALeagueDivisions.add(tripleADivisionA);
		tripleALeagueDivisions.add(tripleADivisionB);
		tripleALeagueDivisions.add(tripleADivisionC);
		tripleALeagueDivisions.add(tripleADivisionD);
		tripleALeagueDivisions.add(tripleADivisionE);
		tripleALeagueDivisions.add(tripleADivisionF);
		tripleALeagueDivisions.add(tripleADivisionG);
		tripleALeagueDivisions.add(tripleADivisionH);

		ArrayList<ArrayList<Team>> doubleALeagueDivisions = new ArrayList<ArrayList<Team>>();
		doubleALeagueDivisions.add(doubleADivisionA);
		doubleALeagueDivisions.add(doubleADivisionB);
		doubleALeagueDivisions.add(doubleADivisionC);
		doubleALeagueDivisions.add(doubleADivisionD);
		doubleALeagueDivisions.add(doubleADivisionE);
		doubleALeagueDivisions.add(doubleADivisionF);
		doubleALeagueDivisions.add(doubleADivisionG);
		doubleALeagueDivisions.add(doubleADivisionH);

		ArrayList<ArrayList<Team>> singleALeagueDivisions = new ArrayList<ArrayList<Team>>();
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
	private static void printStats(ArrayList<Team> league, PrintWriter stats) throws IOException
	{
		// 0 is atBats, 1 is hits, 2 is doubles, 3 is triples, 4 is Home Runs, 5 is RBI's, 6 is Strikeouts, 7 is walks
		for(Team t:league)
		{
			stats.println(t + "'s Player Name,At Bats,Hits,Batting Average,Doubles,Triples,Home Runs,RBI's,Strikeouts,Walks,Innings Pitched,ERA,Earned Runs Allowed,Runs Allowed,Strikeouts,Walks,Wins,Losses,Saves,Blown Saves");
			for(player p:t)
			{
				stats.printf("%s,%d,%d,.%03.0f,%d,%d,%d,%d,%d,%d", p.getPositionAsString() + " " + p, p.getTotalAtBats(), p.getTotalHits(), p.getBattingAverage()*1000,p.getDoubles(),p.getTriples(),p.getHomeRuns(),p.getRBIs(),p.getStrikeOuts(),p.getWalks());
				if(p instanceof pitcher)
				{
					pitcher pitch = (pitcher)p;
					stats.printf(",%s,%01.2f,%d,%d,%d,%d,%d,%d,%d,%d\n", pitch.getInnings(),pitch.getERAAsDouble(),pitch.getEarnedRuns(),pitch.getRunsAllowed(),pitch.getStrikeOutsPitched(),pitch.getWalksGiven(), pitch.getWins(), pitch.getLosses(),pitch.getSaves(),pitch.getBlownSaves());
				}
				else stats.println();
			}
		}

	}
	private static void printTeamInfo(PrintWriter majorStandings, Team team) throws IOException
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
