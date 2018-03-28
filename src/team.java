import java.util.ArrayList;

public interface team
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
	public pitcher getStartingPitcher();
	public player getStartingFirstBase();
	public player getStartingSecondBase();
	public player getStartingThirdBase();
	public player getStartingShortStop();
	public player getStartingLeftFielder();
	public player getStartingCenterFielder();
	public player getStartingRightFielder();
	public player getStartingCatcher();
	
	
}
