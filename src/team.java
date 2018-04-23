import java.io.Serializable;
import java.util.ArrayList;

public interface team extends Serializable, Comparable<team>, Iterable<player>
{
    public void addPlayer(player player);
    public boolean hasDH();
    public player getPlayer(int playerNum);
    public void removePlayer(int playerNum);
    public ArrayList<player> getAllPlayers();
    public String toString();
    public int getSize();
    public void addWin(int i);
    public void addLoss(int i);
    public int getDivision();
    public void addConferenceWin(int i);
    public void addConferenceLoss(int i);
    public void addDivisionWin(int i);
    public void addDivisionLoss(int i);
    public void addRuns(int i);
    public void addRunsAgainst(int i);
    public player[] getBattingOrder();
    public player getStartingFirstBase();
    public player getStartingSecondBase();
    public player getStartingThirdBase();
    public player getStartingShortStop();
    public player getStartingLeftFielder();
    public player getStartingCenterFielder();
    public player getStartingRightFielder();
    public player getStartingCatcher();
    public void addStartingPitcher(pitcher pitcher);
    public void addReliefPitcher(pitcher pitcher);
    public pitcher getNextStarter();
    public int getWins();
    public int getLosses();
    public int getRunsScored();
    public int getRunsAgainst();
    public void setDivisionRank(int i);
    public void setConferenceRank(int i);
    public void setLeagueRank(int i);
    public int getDivisionRank();
    public int getConferenceRank();
    public int getLeagueRank();
    public void verifyPositions();
    public void setBattingOrder();
    public void addConferenceChampionship();
    public void addChampionship();
    public void offseason();
    public int getDynastyWins();
    public int getDynastyLosses();
    public int getChampionships();
    public int getConferenceChampionships();
}
