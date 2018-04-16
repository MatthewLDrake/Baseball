import java.util.ArrayList;

public class currentTeam
{
	private ArrayList<player> battingOrder;
	private pitcher pitcher;
	private player catcher, firstBase, secondBase, thirdBase, shortStop, leftFielder, rightFielder, centerFielder;
	public String teamName;
	public currentTeam(team team)
	{
		teamName = team.toString();
		battingOrder = new ArrayList<player>();
		for(int i = 0; i < team.getBattingOrder().length;i++)
		{
			battingOrder.add(team.getBattingOrder()[i]);
		}
		catcher = team.getStartingCatcher();
		firstBase = team.getStartingFirstBase();
		secondBase = team.getStartingSecondBase();
		thirdBase = team.getStartingThirdBase();
		shortStop = team.getStartingShortStop();
		leftFielder = team.getStartingLeftFielder();
		centerFielder = team.getStartingCenterFielder();
		rightFielder = team.getStartingRightFielder();
		pitcher = team.getNextStarter();
	}
	public String toString()
	{
		return teamName;
	}
	public player getCatcher()
	{
		return catcher;
	}
	public player getFirstBase()
	{
		return firstBase;
	}
	public player getSecondBase()
	{
		return secondBase;
	}
	public player getThirdBase()
	{
		return thirdBase;
	}
	public player getPitcherAsPlayer()
	{
		return (player) pitcher;
	}
	public player getShortStop()
	{
		return shortStop;
	}
	public player getLeftField()
	{
		return leftFielder;
	}
	public player getCenterField()
	{
		return centerFielder;
	}
	public player getRightField()
	{
		return rightFielder;
	}
	public player getNext()
	{
		player next = battingOrder.remove(0);
		battingOrder.add(next);
		return next;
	}
	public ArrayList<player> getBattingOrder()
	{
		return battingOrder;
	}
	public void replacePlayer(player toReplace, player replaceWith)
	{
		ArrayList<player> tempOrder = new ArrayList<player>();
		
		while( battingOrder.size() != 0)
		{
			if(battingOrder.get(0).equals(toReplace))tempOrder.add(replaceWith);
			else tempOrder.add(battingOrder.get(0));
			battingOrder.remove(0);
		}
	}
	public pitcher getPitcher()
	{
		return pitcher;
	}
	public void setStartingPitcher(pitcher adultPitcher)
	{
		this.pitcher = adultPitcher;
		
	}
	
}
