import java.util.ArrayList;

public class draft
{
	private int totalRounds;
	private ArrayList<team> teams;
	private ArrayList<player> rookies;
	public draft(int rounds, ArrayList<team> teams, ArrayList<player> players)
	{
		totalRounds = rounds;
		this.teams = teams;
		executeDraft();
		
	}
	private void executeDraft()
	{
		for(int i = 0; i < totalRounds; i++)
		{
			
		}
	}
	
}
