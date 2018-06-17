import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class College
{
	private static CollegeConference acc, bigTen, bigTwelve, sec, pac12, american, mac, mwc, cusa, sunbelt;
	private static ArrayList<CollegeTeam> teams;
	private static ArrayList<CollegeConference> conferences;
	public College()
	{
		teams = new ArrayList<CollegeTeam>();

		acc = new CollegeConference("ACC");
		bigTen = new CollegeConference("Big 10");
		bigTwelve = new CollegeConference("Big 12");
		sec = new CollegeConference("SEC");
		pac12 = new CollegeConference("PAC 12");
		american = new CollegeConference("American");
		mac = new CollegeConference("MAC");
		mwc = new CollegeConference("MWC");
		cusa = new CollegeConference("Conference USA");
		sunbelt = new CollegeConference("Sun Belt");

		acc.add(new CollegeTeam("Clemson Tigers", "Clemson", 0,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("NC State Wolfpack", "NC State", 1,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Wake Forest Demon Deacons", "Wake Forest", 2,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Boston College Eagles", "BC", 3,TeamRank.HIGHLEVEL));
		acc.add(new CollegeTeam("Louisville Cardinals", "Louisville", 4,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Florida State Seminoles", "Florida State", 5,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Syracuse Orange", "Syracuse", 6,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Miami Hurricanes", "Miami", 7,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Virginia Tech Hokies", "Virginia Tech", 8,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Georgia Tech Yellow Jackes", "Georgia Tech", 9,TeamRank.HIGHLEVEL));
		acc.add(new CollegeTeam("Pittsburgh Panthers", "Pittsburgh", 10,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Virginia Cavaliers", "Virgiina", 11,TeamRank.MIDLEVEL));
		acc.add(new CollegeTeam("Duke Blue Devils", "Duke", 12,TeamRank.LOWLEVEL));
		acc.add(new CollegeTeam("North Carolina Tar Heels", "North Carolna", 13,TeamRank.MIDLEVEL));

		american.add(new CollegeTeam("Central Florida Knights", "UCF", 14,TeamRank.HIGHLEVEL));
		american.add(new CollegeTeam("South Florida Bulls", "USF", 15,TeamRank.MIDLEVEL));
		american.add(new CollegeTeam("Temple Owls", "Temple", 16,TeamRank.LOWLEVEL));
		american.add(new CollegeTeam("East Carolina Pirates", "East Carolina", 17,TeamRank.LOWLEVEL));
		american.add(new CollegeTeam("Cincinatti Bearcats", "Cincinatti", 18,TeamRank.BOTTOMFEEDER));
		american.add(new CollegeTeam("Connecticut Huskies", "UCONN", 19,TeamRank.MIDLEVEL));
		american.add(new CollegeTeam("Memphis Tigers", "Memphis", 20,TeamRank.LOWLEVEL));
		american.add(new CollegeTeam("Houston Cougars", "Houston", 21,TeamRank.LOWLEVEL));
		american.add(new CollegeTeam("Navy Midshipmen", "Navy", 22,TeamRank.HIGHLEVEL));
		american.add(new CollegeTeam("Southern Methodist Mustangs", "SMU", 23,TeamRank.BOTTOMFEEDER));
		american.add(new CollegeTeam("Tulane Green Wave", "Tulane", 24,TeamRank.LOWLEVEL));
		american.add(new CollegeTeam("Tulsa Green Wave", "Tulsa", 25,TeamRank.LOWLEVEL));
		american.add(new CollegeTeam("UMass Minutemen", "UMASS", 26,TeamRank.HIGHLEVEL));
		american.add(new CollegeTeam("Army Black Knights", "Army", 27,TeamRank.HIGHLEVEL));

		bigTwelve.add(new CollegeTeam("Oklahoma Sooners", "Oklahoma", 28,TeamRank.MIDLEVEL));
		bigTwelve.add(new CollegeTeam("Texas Christian Horned Frogs", "TCU", 29,TeamRank.HIGHLEVEL));
		bigTwelve.add(new CollegeTeam("Oklahoma State Cowboys", "Oklahoma State", 30,TeamRank.MIDLEVEL));
		bigTwelve.add(new CollegeTeam("Texas Longhorns", "Texas", 31,TeamRank.HIGHLEVEL));
		bigTwelve.add(new CollegeTeam("West Virginia Moutaineers", "West Virginia", 32,TeamRank.MIDLEVEL));
		bigTwelve.add(new CollegeTeam("Kansas State Wildcats", "Kansas State", 33,TeamRank.LOWLEVEL));
		bigTwelve.add(new CollegeTeam("Iowa State Cyclones", "Iowa State", 34,TeamRank.LOWLEVEL));
		bigTwelve.add(new CollegeTeam("Texas Tech Red Raiders", "Texas Tech", 35,TeamRank.LOWLEVEL));
		bigTwelve.add(new CollegeTeam("Baylor Bears", "Baylor", 36,TeamRank.HIGHLEVEL));
		bigTwelve.add(new CollegeTeam("Kansas Jayhawks", "Kansas", 37,TeamRank.MIDLEVEL));
		bigTwelve.add(new CollegeTeam("Brigham Young Cougars", "BYU", 38,TeamRank.MIDLEVEL));
		bigTwelve.add(new CollegeTeam("Notre Dame Fighting Irish", "Notre Dame", 39,TeamRank.HIGHLEVEL));
		bigTwelve.add(new CollegeTeam("Illinois State Redbirds", "Illinois State", 40,TeamRank.LOWLEVEL));
		bigTwelve.add(new CollegeTeam("Indiana State Sycamores", "Indiana State", 41,TeamRank.LOWLEVEL));

		bigTen.add(new CollegeTeam("Ohio State Buckeyes", "Ohio States", 42,TeamRank.LOWLEVEL));
		bigTen.add(new CollegeTeam("Penn State Nittany Lions", "Penn State", 43,TeamRank.MIDLEVEL));
		bigTen.add(new CollegeTeam("Michigan State Spartans", "Michigan State", 44,TeamRank.MIDLEVEL));
		bigTen.add(new CollegeTeam("Michigan Wolverines", "Michigan", 45,TeamRank.MIDLEVEL));
		bigTen.add(new CollegeTeam("Rutgers Scarlet Knights", "Rutgers", 46,TeamRank.HIGHLEVEL));
		bigTen.add(new CollegeTeam("Maryland Terrapins", "Maryland", 47,TeamRank.LOWLEVEL));
		bigTen.add(new CollegeTeam("Indiana Hoosiers", "Indiana", 48,TeamRank.MIDLEVEL));
		bigTen.add(new CollegeTeam("Wisconsin Badgers", "Wisconsin", 49,TeamRank.ELITE));
		bigTen.add(new CollegeTeam("Northwestern Wildcats", "Northwestern", 50,TeamRank.BOTTOMFEEDER));
		bigTen.add(new CollegeTeam("Purdue Boilermakers", "Purdue", 51,TeamRank.LOWLEVEL));
		bigTen.add(new CollegeTeam("Iowa Hawkeyes", "Iowa", 52,TeamRank.MIDLEVEL));
		bigTen.add(new CollegeTeam("Nebraska Cornhuskers", "Nebraska", 53,TeamRank.MIDLEVEL));
		bigTen.add(new CollegeTeam("Minnesota Golden Gophers", "Minnesota", 54,TeamRank.HIGHLEVEL));
		bigTen.add(new CollegeTeam("Illinois Fighting Illini", "Illinois", 55,TeamRank.HIGHLEVEL));

		cusa.add(new CollegeTeam("Florida Atlantic Owls", "FAU", 56,TeamRank.MIDLEVEL));
		cusa.add(new CollegeTeam("Florida International Golden Panthers", "FIU", 57,TeamRank.LOWLEVEL));
		cusa.add(new CollegeTeam("Marshall Thundering Herd", "Marshall", 58,TeamRank.BOTTOMFEEDER));
		cusa.add(new CollegeTeam("Western Kentucky Hilltoppers", "Western Kentucky", 59,TeamRank.HIGHLEVEL));
		cusa.add(new CollegeTeam("Middle Tennessee Blue Raiders", "Middle Tennessee", 60,TeamRank.LOWLEVEL));
		cusa.add(new CollegeTeam("Old Dominion Monarchs", "Old Dominion", 61,TeamRank.BOTTOMFEEDER));
		cusa.add(new CollegeTeam("Charlotte 49ers", "Charlotte", 62,TeamRank.BOTTOMFEEDER));
		cusa.add(new CollegeTeam("North Texas Mean Green", "North Texas", 63,TeamRank.LOWLEVEL));
		cusa.add(new CollegeTeam("Alabama Birmingham Blazers", "UAB", 64,TeamRank.LOWLEVEL));
		cusa.add(new CollegeTeam("Southern Mississippi Golden Eagles", "Southern Miss", 65,TeamRank.MIDLEVEL));
		cusa.add(new CollegeTeam("Louisiana Tech Bulldogs", "LA Tech", 66,TeamRank.MIDLEVEL));
		cusa.add(new CollegeTeam("Texas San Antonio Roadrunners", "UTSA", 67,TeamRank.LOWLEVEL));
		cusa.add(new CollegeTeam("Rice Owls", "Rice", 68,TeamRank.MIDLEVEL));
		cusa.add(new CollegeTeam("Texas El Paso Miners", "UTEP", 69,TeamRank.LOWLEVEL));

		mac.add(new CollegeTeam("Akron Zips", "Akron", 70,TeamRank.LOWLEVEL));
		mac.add(new CollegeTeam("Ohio Bobcats", "Ohio", 71,TeamRank.MIDLEVEL));
		mac.add(new CollegeTeam("Miami RedHawks", "Miami (OH)", 72,TeamRank.MIDLEVEL));
		mac.add(new CollegeTeam("Buffalo Bulls", "Buffalo", 73,TeamRank.LOWLEVEL));
		mac.add(new CollegeTeam("Bowling Green Falcons", "Bowling Green", 74,TeamRank.MIDLEVEL));
		mac.add(new CollegeTeam("Kent State Golden Flashes", "Kent State", 75,TeamRank.MIDLEVEL));
		mac.add(new CollegeTeam("Toledo Rockets", "Toledo", 76,TeamRank.LOWLEVEL));
		mac.add(new CollegeTeam("Central Michigan Chippewas", "Central Michigan", 77,TeamRank.MIDLEVEL));
		mac.add(new CollegeTeam("Northern Illinois Huskies", "Northern Illinois", 78,TeamRank.LOWLEVEL));
		mac.add(new CollegeTeam("Western Michigan Broncos", "Western Michigan", 79,TeamRank.HIGHLEVEL));
		mac.add(new CollegeTeam("Eastern Michigan Eagles", "Eastern Michigan", 80,TeamRank.MIDLEVEL));
		mac.add(new CollegeTeam("Ball State Cardinals", "Ball State", 81,TeamRank.MIDLEVEL));
		mac.add(new CollegeTeam("Wisconsin Milwaukee Panthers", "UW Milwaukee", 82,TeamRank.ELITE));
		mac.add(new CollegeTeam("Wisconsin Green Bay", "UW Green Bay", 83,TeamRank.ELITE));

		mwc.add(new CollegeTeam("Boise State Broncos", "Boise State", 84,TeamRank.MIDLEVEL));
		mwc.add(new CollegeTeam("Wyoming Cowboys", "Wyoming", 85,TeamRank.LOWLEVEL));
		mwc.add(new CollegeTeam("Colorado State Rams", "Colardo State", 86,TeamRank.LOWLEVEL));
		mwc.add(new CollegeTeam("Air Force Falcons", "Air Force", 87,TeamRank.MIDLEVEL));
		mwc.add(new CollegeTeam("Utah State Aggies", "Utah State", 88,TeamRank.BOTTOMFEEDER));
		mwc.add(new CollegeTeam("New Mexico Lobos", "New Mexico", 89,TeamRank.BOTTOMFEEDER));
		mwc.add(new CollegeTeam("Fresno State Bulldogs", "Fresno State", 90,TeamRank.MIDLEVEL));
		mwc.add(new CollegeTeam("San Diego State Aztecs", "San Diego State", 91,TeamRank.MIDLEVEL));
		mwc.add(new CollegeTeam("Nevada Las Vegas Rebels", "UNLV", 92,TeamRank.LOWLEVEL));
		mwc.add(new CollegeTeam("Nevada Wolfpack", "Nevada", 93,TeamRank.BOTTOMFEEDER));
		mwc.add(new CollegeTeam("Hawaii Rainbow Warriors", "Hawaii", 94,TeamRank.BOTTOMFEEDER));
		mwc.add(new CollegeTeam("San Jose State Spartans", "San Jose State", 95,TeamRank.LOWLEVEL));
		mwc.add(new CollegeTeam("California Santa Cruz Banana Slugs", "UC Santa Cruz", 96,TeamRank.HIGHLEVEL));
		mwc.add(new CollegeTeam("California Santa Barbara Gauchos", "UC Santa Barbara", 97,TeamRank.BOTTOMFEEDER));

		pac12.add(new CollegeTeam("Stanford Cardinal", "Stanford", 98,TeamRank.HIGHLEVEL));
		pac12.add(new CollegeTeam("Washington Huskies", "Washington", 99,TeamRank.MIDLEVEL));
		pac12.add(new CollegeTeam("Washington State Cougars", "Washington State", 100,TeamRank.HIGHLEVEL));
		pac12.add(new CollegeTeam("Oregon Ducks", "Oregon", 101,TeamRank.MIDLEVEL));
		pac12.add(new CollegeTeam("California Golden Bears", "California", 102,TeamRank.HIGHLEVEL));
		pac12.add(new CollegeTeam("Oregon State Beavers", "Oregon State", 103,TeamRank.HIGHLEVEL));
		pac12.add(new CollegeTeam("South California Trojans", "USC", 104,TeamRank.MIDLEVEL));
		pac12.add(new CollegeTeam("Arizona State Sun Devils", "Arizona State", 105,TeamRank.BOTTOMFEEDER));
		pac12.add(new CollegeTeam("Arizona Wildcats", "Arizona", 106,TeamRank.ELITE));
		pac12.add(new CollegeTeam("California Los Angeles Bruins", "UCLA", 107,TeamRank.MIDLEVEL));
		pac12.add(new CollegeTeam("Utah Utes", "Utah", 108,TeamRank.MIDLEVEL));
		pac12.add(new CollegeTeam("Colorado Buffaloes", "Colorado", 109,TeamRank.MIDLEVEL));
		pac12.add(new CollegeTeam("California Irvine Anteaters", "UC Irvine", 110,TeamRank.ELITE));
		pac12.add(new CollegeTeam("Idaho Vandals", "Idaho", 111,TeamRank.HIGHLEVEL));

		sec.add(new CollegeTeam("Georgia Bulldogs", "Georgia", 112,TeamRank.HIGHLEVEL));
		sec.add(new CollegeTeam("South Carolina Gamecocks", "South Carolina", 113,TeamRank.HIGHLEVEL));
		sec.add(new CollegeTeam("Kentucky Wildcats", "Kentucky", 114,TeamRank.HIGHLEVEL));
		sec.add(new CollegeTeam("Missouri Tigers", "Missouri", 115,TeamRank.LOWLEVEL));
		sec.add(new CollegeTeam("Florida Gators", "Florida", 116,TeamRank.MIDLEVEL));
		sec.add(new CollegeTeam("Vanderbilt Commodores", "Vanderbilt", 117,TeamRank.BOTTOMFEEDER));
		sec.add(new CollegeTeam("Tennessee Volunteers", "Tennessee", 118,TeamRank.MIDLEVEL));
		sec.add(new CollegeTeam("Auburn Tigers", "Auburn", 119,TeamRank.LOWLEVEL));
		sec.add(new CollegeTeam("Alabama Crimson Tide", "Alabama", 120,TeamRank.HIGHLEVEL));
		sec.add(new CollegeTeam("Louisiana State Tigers", "LSU", 121,TeamRank.MIDLEVEL));
		sec.add(new CollegeTeam("Mississippi State Bulldogs", "Mississippi State", 122,TeamRank.HIGHLEVEL));
		sec.add(new CollegeTeam("Texas A&M Aggies", "Texas A&M", 123,TeamRank.LOWLEVEL));
		sec.add(new CollegeTeam("Ole Miss Rebels", "Ole Miss", 124,TeamRank.HIGHLEVEL));
		sec.add(new CollegeTeam("Arkansas Razorbacks", "Arkansas", 125,TeamRank.MIDLEVEL));

		sunbelt.add(new CollegeTeam("Appalachian State Mountaineers", "Appalachian State", 126,TeamRank.BOTTOMFEEDER));
		sunbelt.add(new CollegeTeam("Arkansas State Red Wolves", "Arkansas State", 127,TeamRank.BOTTOMFEEDER));
		sunbelt.add(new CollegeTeam("Costal Carolina Chanticleers", "Costal Carolina", 128,TeamRank.LOWLEVEL));
		sunbelt.add(new CollegeTeam("Georgia Southern Eagles", "Georgia Southern", 129,TeamRank.LOWLEVEL));
		sunbelt.add(new CollegeTeam("Georgia State Panthers", "Georgia State", 130,TeamRank.HIGHLEVEL));
		sunbelt.add(new CollegeTeam("Louisiana Lafayette Ragin' Cajuns", "UL Lafayette", 131,TeamRank.MIDLEVEL));
		sunbelt.add(new CollegeTeam("Louisiana Monroe Warhawks", "UL Monroe", 132,TeamRank.LOWLEVEL));
		sunbelt.add(new CollegeTeam("South Alabama Jaguars", "South Alabama", 133,TeamRank.BOTTOMFEEDER));
		sunbelt.add(new CollegeTeam("Texas State Bobcats", "Texas State", 134,TeamRank.BOTTOMFEEDER));
		sunbelt.add(new CollegeTeam("Troy Trojans", "Troy", 135,TeamRank.LOWLEVEL));
		sunbelt.add(new CollegeTeam("North Carolina Wilmington Seahawks", "UNC Wilmington", 136,TeamRank.BOTTOMFEEDER));
		sunbelt.add(new CollegeTeam("Gonzaga Bulldogs", "Gonzaga", 137,TeamRank.LOWLEVEL));
		sunbelt.add(new CollegeTeam("Harvard Crimson", "Harvard", 138,TeamRank.HIGHLEVEL));
		sunbelt.add(new CollegeTeam("Yale Bulldogs", "Yale", 139,TeamRank.HIGHLEVEL));

		conferences = new ArrayList<CollegeConference>();
		conferences.add(american);
		conferences.add(bigTen);
		conferences.add(cusa);
		conferences.add(acc);
		conferences.add(pac12);
		conferences.add(bigTwelve);
		conferences.add(mac);
		conferences.add(mwc);
		conferences.add(sec);
		conferences.add(sunbelt);

		for(int i = 0; i < conferences.size(); i++)
		{
			for(int j = 0; j < conferences.get(i).size();j++)
			{
				teams.add(conferences.get(i).get(j));
				conferences.get(i).get(j).fillPlayers(true);
			}
		}
	}
	public ArrayList<player> getNewProPlayers()
	{
		ArrayList<player> retVal = new ArrayList<player>();
		for(int i = 0; i < conferences.size(); i++)
		{
			for(int j = 0; j < conferences.get(i).size();j++)
			{
				retVal.addAll(conferences.get(i).get(j).getProPlayers());
				conferences.get(i).get(j).fillPlayers(false);
			}
		}

		return retVal;
	}
	public void playSeason()
	{
		CollegeSchedule schedule = new CollegeSchedule();
		ArrayList<Pair[]> games = schedule.getSchedule();

		for(int i = 0; i < games.size(); i++)
		{
			Pair[] currentGames = games.get(i);
			int gamesInSeries = 2;
			if(currentGames[0].conferenceGame)gamesInSeries++;
			for(int numGame = 0; numGame < gamesInSeries; numGame++)
				for(int j = 0; j < currentGames.length; j++)
				{
					executeGame(false, currentGames[j].x, currentGames[j].y);
				}
		}
		try
		{
			printCollegeStandings();
		}
		catch (Exception E)
		{

		}

	}
	private void printCollegeStandings() throws Exception
	{
		PrintWriter collegeStandings = new PrintWriter("College Standings.csv");
		@SuppressWarnings("unchecked")
		ArrayList<CollegeTeam> temp = (ArrayList<CollegeTeam>) teams.clone();
		Collections.sort(temp);
		for(int i = 0; i < conferences.size();i++)
		{
			temp.get(i).setLeagueRank(i+1);
		}
		for(int i = 0; i < conferences.size();i++)
		{
			CollegeConference curr = conferences.get(i);
			Collections.sort(curr);
			collegeStandings.println(curr + ",Wins,Losses,Runs Scored,Runs Against,Conference Rank,League Rank");
			printConference(collegeStandings, curr);
		}
		collegeStandings.close();
	}
	private void printConference(PrintWriter collegeStandings, CollegeConference curr)
	{
		for(int i = 0; i < curr.size(); i++)
		{
			CollegeTeam team = curr.get(i);
			team.setConferenceRank(i+1);
			collegeStandings.println(team + "," + team.getWins() +  "," + team.getLosses() + "," + team.getConferenceRank() + "," + team.getLeagueRank());

		}
		
		
	}
	private boolean executeGame(boolean b, int i, int j)
	{
		boolean retVal = false;/*
		int away = teams.get(i).lastThreeGames(-1);
		int home = teams.get(j).lastThreeGames(-1);*/

		game newGame = new game(teams.get(i), teams.get(j));

		try
		{

			retVal = newGame.getWinner();
			//writer.println(votingList);
			if(newGame.getWinner())
			{
				teams.get(i).addWin(1);
				teams.get(j).addLoss(1);
				if((i < 16 && j < 16) || (i > 15 && j > 15))
				{
					teams.get(i).addConferenceWin(1);
					teams.get(j).addConferenceLoss(1);
				}
				if(teams.get(i).getDivision() == teams.get(j).getDivision())
				{
					teams.get(i).addDivisionWin(1);
					teams.get(j).addDivisionLoss(1);
				}
			}
			else 
			{
				//writer.println(teams.get(j).toString() + " wins! The score was " + newGame.getHomeTeamScore() + " - " + newGame.getAwayTeamScore());
				teams.get(j).addWin(1);
				teams.get(i).addLoss(1);
				if((i < 16 && j < 16) || (i > 15 && j > 15))
				{
					teams.get(j).addConferenceWin(1);
					teams.get(i).addConferenceLoss(1);
				}
				if(teams.get(i).getDivision() == teams.get(j).getDivision())
				{
					teams.get(j).addDivisionWin(1);
					teams.get(i).addDivisionLoss(1);
				}

			}
			//gameResults.println("," + teams.get(i).toString() + "," + newGame.getAwayTeamScore() + "," + teams.get(j).toString()+ "," + newGame.getHomeTeamScore());
			teams.get(j).addRuns(newGame.getAwayTeamScore());
			teams.get(i).addRuns(newGame.getHomeTeamScore());
			teams.get(i).addRunsAgainst(newGame.getAwayTeamScore());
			teams.get(j).addRunsAgainst(newGame.getHomeTeamScore());


		}
		catch(Exception E)
		{
			E.printStackTrace();
			System.out.println("Fuck");
		}

		for(int k = 0; k < teams.get(i).getSize(); k++)
		{
			teams.get(i).getPlayer(k).resetGameStats();
		}
		for(int k = 0; k < teams.get(j).getSize(); k++)
		{
			teams.get(j).getPlayer(k).resetGameStats();
		}
		return retVal;
	}
}
