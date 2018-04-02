import java.util.ArrayList;

public class draft
{
	private int totalRounds;
	private ArrayList<player> rookies;
	private ArrayList<rookieHolder> teams;
	public draft(int rounds, ArrayList<team> teams, ArrayList<player> players, boolean firstDraft)
	{
		totalRounds = rounds;
		this.teams = new ArrayList<rookieHolder>();
		for(int i = 0; i < teams.size(); i++)
		{
			this.teams.add(new rookieHolder(teams.get(i), firstDraft));
		}
		rookies = players;
		
		executeDraft();
		
		for(int i = 0; i < teams.size(); i++)
		{
			this.teams.get(i).distributePlayers();
			System.out.println(this.teams.get(i).size());
		}
	}
	private void executeDraft()
	{
		for(int i = 1; i < totalRounds+1; i++)
		{
			executeRound(i);
		}
	}
	private void executeRound(int roundNum)
	{
		int startNum, endNum, incrementor;
		if(roundNum % 2 == 0)
		{
			startNum = 31;
			endNum = -1;
			incrementor = -1;
		}
		else
		{
			startNum = 0;
			endNum = 32;
			incrementor = 1;
		}
		while(startNum != endNum)
		{
			int position = teams.get(startNum).getPositionToDraft();
			if(position <= 8)
			{
				int maxOverall = 0, index = 0;
				for(int i = 0; i < rookies.size(); i++)
				{
					int tempOverall = rookies.get(i).getOverall(position);
					if(tempOverall > maxOverall)
					{
						maxOverall = tempOverall;
						index = i;
					}
				}
				teams.get(startNum).addPlayer(rookies.remove(index));
			}
			else if (position == 9)
			{
				int maxOverall = 0, index = 0;
				for(int i = 0; i < rookies.size(); i++)
				{
					if(rookies.get(i).getPosition() == 9)
					{
						int tempOverall = rookies.get(i).getOverall(position);
						if(tempOverall > maxOverall)
						{
							maxOverall = tempOverall;
							index = i;
						}
					}
					
				}
				teams.get(startNum).addPlayer(rookies.remove(index));
			}
			else if(position == 10)
			{
				int maxOverall = 0, index = 0;
				for(int i = 0; i < rookies.size(); i++)
				{
					if(rookies.get(i).getPosition() == 10)
					{
						int tempOverall = rookies.get(i).getOverall(position);
						if(tempOverall > maxOverall)
						{
							maxOverall = tempOverall;
							index = i;
						}
					}
					
				}
				teams.get(startNum).addPlayer(rookies.remove(index));
			}
			// corner (1 and 3)
			else if(position == 11)
			{
				int maxOverall = 0, index = 0;
				for(int i = 0; i < rookies.size(); i++)
				{
					int firstBaseOverall = rookies.get(i).getOverall(1);
					int thirdBaseOverall = rookies.get(i).getOverall(3);
					int tempOverall = Math.max(firstBaseOverall, thirdBaseOverall);
					if(tempOverall > maxOverall)
					{
						maxOverall = tempOverall;
						index = i;
					}
				}
				teams.get(startNum).addPlayer(rookies.remove(index));
			}
			// inside (2 and 4)
			else if(position == 12)
			{
				int maxOverall = 0, index = 0;
				for(int i = 0; i < rookies.size(); i++)
				{
					int secondBaseOverall = rookies.get(i).getOverall(2);
					int shortStopOverall = rookies.get(i).getOverall(4);
					int tempOverall = Math.max(secondBaseOverall, shortStopOverall);
					if(tempOverall > maxOverall)
					{
						maxOverall = tempOverall;
						index = i;
					}
				}
				teams.get(startNum).addPlayer(rookies.remove(index));
			}
			// outfielder (5, 6, and 7)
			else if(position == 13)
			{
				int maxOverall = 0, index = 0;
				for(int i = 0; i < rookies.size(); i++)
				{
					int leftFieldOverall = rookies.get(i).getOverall(5);
					int rightFieldOverall = rookies.get(i).getOverall(6);
					int centerFieldOverall = rookies.get(i).getOverall(7);
					int tempOverall = Math.max(Math.max(leftFieldOverall, centerFieldOverall), rightFieldOverall);
					if(tempOverall > maxOverall)
					{
						maxOverall = tempOverall;
						index = i;
					}
				}
				teams.get(startNum).addPlayer(rookies.remove(index));
			}
			// best non pitcher
			else
			{
				int maxOverall = 0, index = 0;
				for(int i = 0; i < rookies.size(); i++)
				{
					if(rookies.get(i) instanceof pitcher)continue;
					int tempOverall = rookies.get(i).getOverall(-1);
					if(tempOverall > maxOverall)
					{
						maxOverall = tempOverall;
						index = i;
					}
				}
				teams.get(startNum).addPlayer(rookies.remove(index));
			}
			
			
			startNum += incrementor;
		}
	}
	
}
