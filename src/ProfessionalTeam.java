import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
public class ProfessionalTeam implements team
{
    private ArrayList<player> players;
    private boolean hasDH;
    private String teamName;
    private int conferenceChampionships, championships;
    private int teamNum;
    private int wins, divisionWins, conferenceWins;
    private int losses, divisionLosses, conferenceLosses;
    private int runs, runsAgainst;
    private pitcher[] pitchingRotation;
    private player[] battingOrder;
    private int placeInRotation;
    private player startingCatcher, startingFirstBase, startingSecondBase, startingThirdBase, startingShortStop, startingLeftFielder, startingRightFielder, startingCenterFielder;
    private pitcher spotStarter, closer, setup, reliever;
    private pitcher[] middleRelievers;
    private int divisionRank, conferenceRank, leagueRank;
    private Random r;
    private ArrayList<player> backups;
    private int dynastyWins, dynastyLosses;
    public ProfessionalTeam(String teamName, int teamNum)
    {
	players = new ArrayList<player>();
	backups = new ArrayList<player>();
	this.teamName = teamName;
	r = new Random();
	this.teamNum = teamNum;
	hasDH = this.teamNum > 16;
	pitchingRotation = new pitcher[5];
	middleRelievers = new pitcher[3];
	battingOrder = new player[8];
	placeInRotation = 0;
    }

    public void addPlayer(player player)
    {
	players.add(player);

	switch (player.getPosition())
	{
	case 1:
	    if(startingFirstBase == null || player.compareTo(startingFirstBase) > 0)
	    {
		startingFirstBase = player;
	    }
	    else trySecondaryPositions(player);
	    break;
	case 2:
	    if(startingSecondBase == null || player.compareTo(startingSecondBase) > 0)
	    {
		startingSecondBase = player;
	    }
	    else trySecondaryPositions(player);
	    break;
	case 3:
	    if(startingThirdBase == null || player.compareTo(startingThirdBase) > 0)
	    {
		startingThirdBase = player;
	    }
	    else trySecondaryPositions(player);
	    break;
	case 4:
	    if(startingShortStop == null || player.compareTo(startingShortStop) > 0)
	    {
		startingShortStop = player;
	    }
	    else trySecondaryPositions(player);
	    break;
	case 5:
	    if(startingLeftFielder == null || player.compareTo(startingLeftFielder) > 0)
	    {
		startingLeftFielder = player;
	    }
	    else trySecondaryPositions(player);
	    break;
	case 6:
	    if(startingRightFielder == null || player.compareTo(startingFirstBase) > 0)
	    {
		startingRightFielder = player;
	    }
	    else trySecondaryPositions(player);
	    break;
	case 7:
	    if(startingCenterFielder == null || player.compareTo(startingCenterFielder) > 0)
	    {
		startingCenterFielder = player;
	    }
	    else trySecondaryPositions(player);
	    break;
	case 8:
	    if(startingCatcher == null || player.compareTo(startingCatcher) > 0)
	    {
		startingCatcher = player;

	    }
	    else trySecondaryPositions(player);
	    break;
	default:
	    break;
	}


    }
    private void trySecondaryPositions(player player)
    {
	//TODO: Complete this
	backups.add(player);

    }
    public boolean hasDH()
    {
	return hasDH;
    }
    public player getPlayer(int playerNum)
    {
	return players.get(playerNum);
    }
    public void removePlayer(int playerNum)
    {
	players.remove(playerNum);
    }
    public ArrayList<player> getAllPlayers()
    {
	return players;
    }
    public String toString()
    {
	return teamName;
    }
    public int getSize()
    {
	return players.size();
    }
    @Override
    public void addWin(int i)
    {
	wins += i;

    }
    @Override
    public void addLoss(int i)
    {
	losses += i;

    }
    @Override
    public int getDivision()
    {
	if(teamNum < 8)return 0;
	else if(teamNum < 16)return 1;
	else if(teamNum < 24)return 2;
	else return 3;
    }
    @Override
    public void addConferenceWin(int i)
    {
	conferenceWins += i;

    }
    @Override
    public void addConferenceLoss(int i)
    {
	conferenceLosses += i;

    }
    @Override
    public void addDivisionWin(int i)
    {
	divisionWins += i;

    }
    @Override
    public void addDivisionLoss(int i)
    {
	divisionLosses += i;

    }
    @Override
    public void addRuns(int i)
    {
	runs += i;

    }
    @Override
    public void addRunsAgainst(int i)
    {
	runsAgainst += i;

    }
    @Override
    public player[] getBattingOrder()
    {
	return battingOrder;
    }

    @Override
    public player getStartingFirstBase()
    {
	return startingFirstBase;
    }
    @Override
    public player getStartingSecondBase()
    {
	return startingSecondBase;
    }
    @Override
    public player getStartingThirdBase()
    {
	return startingThirdBase;
    }
    @Override
    public player getStartingShortStop()
    {
	return startingShortStop;
    }
    @Override
    public player getStartingLeftFielder()
    {
	return startingLeftFielder;
    }
    @Override
    public player getStartingCenterFielder()
    {
	return startingCenterFielder;
    }
    @Override
    public player getStartingRightFielder()
    {
	return startingRightFielder;
    }
    public player getStartingCatcher()
    {
	return startingCatcher;
    }
    @Override
    public void addStartingPitcher(pitcher pitcher)
    {
	players.add((player)pitcher);
	for(int i = 0; i < pitchingRotation.length; i++)
	{
	    if(pitchingRotation[i] == null)
	    {
		pitchingRotation[i] = pitcher;
		return;
	    }
	}
	System.out.println(((player)pitcher).toString());
	spotStarter = pitcher;

    }
    public void addReliefPitcher(pitcher pitcher)
    {
	players.add((player) pitcher);
	if(closer == null)
	{
	    closer = pitcher;
	}
	else if(setup == null)
	{
	    setup = pitcher;
	}
	else
	{
	    for(int i = 0; i < middleRelievers.length; i++)
	    {
		if(middleRelievers[i] == null)
		{
		    middleRelievers[i] = pitcher;
		    return;
		}
	    }
	    reliever = pitcher;
	}
    }

    public pitcher getNextStarter()
    {
	pitcher retVal = null;
	// check if struggling
	if(placeInRotation == 4 && r.nextInt(10) < 3)retVal = spotStarter;
	else retVal = pitchingRotation[placeInRotation];
	placeInRotation = (placeInRotation+1)%5;
	if(retVal == null)
	{
	    System.out.println("Problem");
	}
	return retVal;
    }
    public int getWins()
    {
	return wins;
    }
    public int getLosses()
    {
	return losses;
    }
    public int getRunsScored()
    {
	return runs;
    }
    public int getRunsAgainst()
    {
	return runsAgainst;
    }

    @Override
    public int compareTo(team otherTeam)
    {
	if(this.wins == otherTeam.getWins())
	{
	    if(this.getRunsScored()-this.getRunsAgainst() == otherTeam.getRunsScored()-otherTeam.getRunsAgainst())
	    {
		if(this.getRunsScored() == otherTeam.getRunsScored())return this.toString().compareTo(otherTeam.toString());
		return otherTeam.getRunsScored()-this.getRunsScored();
	    }
	    return (otherTeam.getRunsScored()-otherTeam.getRunsAgainst()) - (this.getRunsScored()-this.getRunsAgainst());
	}
	return otherTeam.getWins()-this.wins;
    }

    @Override
    public void setDivisionRank(int i)
    {
	divisionRank = i;

    }

    @Override
    public void setConferenceRank(int i)
    {
	conferenceRank = i;

    }

    @Override
    public void setLeagueRank(int i)
    {
	leagueRank = i;

    }

    @Override
    public int getDivisionRank()
    {
	return divisionRank;

    }

    @Override
    public int getConferenceRank()
    {
	return conferenceRank;

    }

    @Override
    public int getLeagueRank()
    {
	return leagueRank;

    }

    @Override
    public Iterator<player> iterator()
    {
	return new PlayersIterator(players);
    }
    private class PlayersIterator implements Iterator<player>
    {
	private ArrayList<player> players;
	private int loc;
	public PlayersIterator(ArrayList<player> players)
	{
	    this.players = players;
	}
	@Override
	public boolean hasNext()
	{
	    return loc < players.size();
	}

	@Override
	public player next()
	{
	    player retVal = players.get(loc);
	    loc++;
	    return retVal;
	}

    }
    @Override
    public void verifyPositions()
    {
	if(startingFirstBase == null)
	{
	    for(int i = 0; i < backups.size(); i++)
	    {
		backups.get(i).setPositionToOrderBy(1);
	    }
	    Collections.sort(backups);
	    startingFirstBase = backups.remove(0);
	}
	if(startingSecondBase == null)
	{
	    for(int i = 0; i < backups.size(); i++)
	    {
		backups.get(i).setPositionToOrderBy(2);
	    }
	    Collections.sort(backups);
	    startingSecondBase = backups.remove(0);
	}
	if(startingThirdBase == null)
	{
	    for(int i = 0; i < backups.size(); i++)
	    {
		backups.get(i).setPositionToOrderBy(3);
	    }
	    Collections.sort(backups);
	    if(backups.size() == 0)
	    {
	    	printPlayers();
	    }
	    startingThirdBase = backups.remove(0);
	}
	if(startingShortStop == null)
	{
	    for(int i = 0; i < backups.size(); i++)
	    {
		backups.get(i).setPositionToOrderBy(4);
	    }
	    Collections.sort(backups);
	    startingShortStop = backups.remove(0);
	}
	if(startingLeftFielder == null )
	{
	    for(int i = 0; i < backups.size(); i++)
	    {
		backups.get(i).setPositionToOrderBy(5);
	    }
	    Collections.sort(backups);
	    startingLeftFielder = backups.remove(0);
	}
	if(startingRightFielder == null)
	{
	    for(int i = 0; i < backups.size(); i++)
	    {
		backups.get(i).setPositionToOrderBy(6);
	    }
	    Collections.sort(backups);
	    startingRightFielder = backups.remove(0);
	}
	if(startingCenterFielder == null)
	{
	    for(int i = 0; i < backups.size(); i++)
	    {
		backups.get(i).setPositionToOrderBy(7);
	    }
	    Collections.sort(backups);
	    startingCenterFielder = backups.remove(0);
	}
	if(startingCatcher == null)
	{
	    for(int i = 0; i < backups.size(); i++)
	    {
		backups.get(i).setPositionToOrderBy(8);
	    }
	    Collections.sort(backups);
	    startingCatcher = backups.remove(0);

	}

    }

    private void printPlayers()
	{
		for(int i = 0; i < players.size(); i++)
		{
			System.out.println(players.get(i).getPositionAsString() + ": " + players.get(i));
		}
		
	}

	@Override
    public void setBattingOrder()
    {
	ArrayList<player> starters = getStarters();
	ArrayList<battingPlayers> players = new ArrayList<battingPlayers>();
	int speedIndex = 0;
	double speedMax = 0;
	for(int i = 0; i < starters.size(); i++)
	{
	    if(starters.get(i).getSpeedRating() > speedMax)
	    {
		speedMax = starters.get(i).getSpeedRating();
		speedIndex = i;
	    }
	    double averageContactRating = 0;
	    double averagePowerRating = 0;
	    double averageSwingRating = 0;
	    double averageTakeRating = 0;
	    int count = 0;
	    for(pitchType type : pitchType.values())
	    {
		averageContactRating += starters.get(i).getRatingsVsPitch(type).getContactValue();
		averagePowerRating += starters.get(i).getRatingsVsPitch(type).getPowerRating();
		averageSwingRating += starters.get(i).getRatingsVsPitch(type).getGoodSwingRating();
		averageTakeRating += starters.get(i).getRatingsVsPitch(type).getBadTakeRating();
		count++;

	    }
	    averageContactRating = averageContactRating/count;
	    averagePowerRating = averagePowerRating/count;
	    averageSwingRating = averageSwingRating/count;
	    averageTakeRating = averageTakeRating/count;

	    players.add(new battingPlayers(starters.get(i), averageContactRating, averagePowerRating, averageSwingRating, averageTakeRating));
	}
	battingOrder[0] = starters.remove(speedIndex);
	players.remove(speedIndex);
	Collections.sort(players);
	battingOrder[1] = players.remove(0).getPlayer();
	Collections.sort(players);
	battingOrder[2] = players.remove(0).getPlayer();
	battingOrder[3] = players.remove(0).getPlayer();
	Collections.sort(players);
	for(int i = 4; i < battingOrder.length; i++)
	{
	    battingOrder[i] = players.remove(0).getPlayer();
	}

	for(int i = 0; i < battingOrder.length; i++)
	{
	    if(battingOrder[i] == null)
	    {
		System.out.println(i + " doesn't have a spot");
		System.exit(1);
	    }
	}
    }

    private ArrayList<player> getStarters()
    {
	ArrayList<player> retVal = new ArrayList<player>();
	retVal.add(startingCatcher);
	retVal.add(startingCenterFielder);
	retVal.add(startingRightFielder);
	retVal.add(startingLeftFielder);
	retVal.add(startingFirstBase);
	retVal.add(startingSecondBase);
	retVal.add(startingThirdBase);
	retVal.add(startingShortStop);

	return retVal;
    }
    public void addConferenceChampionship()
    {
	conferenceChampionships++;
    }
    public void addChampionship()
    {
	championships++;
    }
    public void offseason()
    {
	dynastyWins += wins;
	dynastyLosses += losses;
	
	for(int i = 0; i < players.size(); i ++)
	{
	    players.get(i).offseason();
	}
	
	wins = 0;
	losses = 0;
    }
    public int getDynastyWins()
    {
	return dynastyWins;
    }
    public int getDynastyLosses()
    {
	return dynastyLosses;
    }
    public int getChampionships()
    {
	return championships;
	
    }
    public int getConferenceChampionships()
    {
	return conferenceChampionships;
    }
    private class battingPlayers implements Comparable<battingPlayers>
    {
	private player player;
	private double contactRating, powerRating, swingRating, takeRating;
	private int compareBy;
	public battingPlayers(player player, double contactRating, double powerRating, double swingRating, double takeRating)
	{
	    this.player = player;
	    this.contactRating = contactRating;
	    this.powerRating = powerRating;
	    this.swingRating = swingRating;
	    this.takeRating = takeRating;
	}
	private void setCompareBy(int compareBy)
	{
	    this.compareBy = compareBy;
	}
	public player getPlayer()
	{
	    return player;
	}
	public double getContactRating()
	{
	    return contactRating;
	}
	public double getSwingRating()
	{
	    return swingRating;
	}
	public double getPowerRating()
	{
	    return powerRating;
	}
	public double getTakeRating()
	{
	    return takeRating;
	}



	@Override
	public int compareTo(battingPlayers other)
	{
	    if(compareBy == 0)
	    {
		compareBy++;
		double temp = other.getContactRating();
		if(temp - this.getContactRating() > 0)return 1;
		else if(temp - this.getContactRating() < 0)return -1;
		else return 0;
	    }
	    else if(compareBy == 1)
	    {
		compareBy++;
		double temp = other.getPowerRating();
		if(temp - this.getPowerRating() > 0)return 1;
		else if(temp - this.getPowerRating() < 0)return -1;
		else return 0;
	    }
	    else
	    {
		double otherRating, currentRating;

		otherRating = other.getPowerRating() * .33 + other.getContactRating() *.33 + other.getTakeRating() * .16 * other.getSwingRating() * .17;
		currentRating = getPowerRating() * .33 + getContactRating() *.33 + getTakeRating() * .16 * getSwingRating() * .17;


		if(otherRating - currentRating > 0)return 1;
		else if(otherRating - currentRating < 0)return -1;
		else return 0;

	    }
	}
    }
}
