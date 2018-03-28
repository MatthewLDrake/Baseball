import java.util.ArrayList;

public class draft
{
	private int totalRounds;
	private ArrayList<player> rookies;
	private boolean firstDraft;
	private ArrayList<rookieHolder> teams;
	public draft(int rounds, ArrayList<team> teams, ArrayList<player> players, boolean firstDraft)
	{
		totalRounds = rounds;
		for(int i = 0; i < teams.size(); i++)
		{
			this.teams.add(new rookieHolder(teams.get(i)));
		}
		rookies = players;
		
		this.firstDraft = firstDraft;
		
		executeDraft();
		
	}
	private void executeDraft()
	{
		for(int i = 0; i < totalRounds; i++)
		{
			
		}
	}
	
}
