import java.util.ArrayList;
import java.util.Random;

public class RelegationDraft
{
	private int totalRounds;
	private ArrayList<player> rookies;
	private ArrayList<TeamHolder> teams;
	public RelegationDraft(ArrayList<Team> teams, ArrayList<player> players, boolean firstDraft)
	{
		this.teams = new ArrayList<TeamHolder>();
		for(int i = 0; i < teams.size(); i++)
		{
			this.teams.add(new TeamHolder(teams.get(i), firstDraft));
		}
		this.rookies = players;
		totalRounds = 25;

		executeDraft();
		
		for(int i = 0; i < teams.size(); i++)
		{
			this.teams.get(i).doThings();
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
			startNum = 127;
			endNum = -1;
			incrementor = -1;
		}
		else
		{
			startNum = 0;
			endNum = 128;
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
class TeamHolder
{
	private Team team;
	private ArrayList<player> players;
	private boolean[] majorStarters;
	private boolean[] majorStartingPitchers;
	private boolean[] majorRelievers;
	// backup catcher, two backup infielders, one backup outfielder, one best available
	private boolean[] majorBackups;
	private Random r;
	private boolean firstStage;
	private boolean firstDraft;
	public TeamHolder(Team team, boolean firstDraft)
	{
		this.firstDraft = firstDraft;
		players = new ArrayList<player>();
		r= new Random();
		this.team = team;


		if(firstDraft)
		{
			firstStage = true;
			majorStarters = new boolean[8];
			majorStartingPitchers = new boolean[5];
			majorRelievers = new boolean[7];
			majorBackups = new boolean[5];

		}
	}
	public void doThings()
	{
		if(firstDraft)
		{
			ArrayList<player> startingPitchers = new ArrayList<player>();
			ArrayList<player> reliefPitchers = new ArrayList<player>();
			ArrayList<ArrayList<player>> positionPlayers = new ArrayList<ArrayList<player>>();
			for(int i = 0; i < 8; i++)
			{
				positionPlayers.add(new ArrayList<player>());
			}
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getPosition() == 9)
				{
					startingPitchers.add(players.get(i));
					
					players.get(i).setPositionToOrderBy(9);
				}
				else if(players.get(i).getPosition() == 10)
				{
					reliefPitchers.add(players.get(i));
					players.get(i).setPositionToOrderBy(10);
				}
				else
				{
					positionPlayers.get(players.get(i).getPosition()-1).add(players.get(i));
				}
			}
			for(int i = 0; i < 6; i++)
			{
				team.addStartingPitcher((pitcher) startingPitchers.get(i));
				team.addReliefPitcher((pitcher) reliefPitchers.get(i));
			}
			
			for(int i = 0; i < positionPlayers.size(); i++)
			{
				for(int j = 0; j < positionPlayers.get(i).size();j++)
				{
					player tempPlayer = positionPlayers.get(i).get(j);
					team.addPlayer(tempPlayer);
					
				}
				
			}
			
			team.verifyPositions();
			team.setBattingOrder();
		}
		
	
		
	}
	/*
	 * Return Values:
	 * 1 - First Base
	 * 2 - Second Base
	 * 3 - Third Base
	 * 4 - Short Stop
	 * 5 - Left Field
	 * 6 - Right Field
	 * 7 - Center Field
	 * 8 - Catcher
	 * 9 - Starting Pitcher
	 * 10 - Relief Pitcher
	 * 11 - Corner Infielder
	 * 12 - Inside Infielder
	 * 13 - Outfielder
	 * 14 - Best available non pitcher
	 */
	public int getPositionToDraft()
	{
		if(firstStage)
		{
			ArrayList<Point> points = new ArrayList<Point>();

			for(int i = 0; i < majorStarters.length; i++)
			{
				if(!majorStarters[i])
				{
					points.add(new Point(0,i+1));
				}
			}
			for(int i = 0; i < majorStartingPitchers.length; i++)
			{
				if(!majorStartingPitchers[i])
				{
					points.add(new Point(1,i));
				}
			}
			for(int i = 0; i < majorRelievers.length; i++)
			{
				if(!majorRelievers[i])
				{
					points.add(new Point(2,i));
				}
			}

			Point result = points.get(r.nextInt(points.size()));
			if(points.size() == 1)
			{
				firstStage = false;
			}
			if(result.getX() == 0)
			{
				majorStarters[result.getY()-1] = true;
				return result.getY();
			}
			else if(result.getX() == 1)
			{
				majorStartingPitchers[result.getY()] = true;
				return 9;
			}
			else
			{
				majorRelievers[result.getY()] = true;
				if(result.getY() == 0)return 9; // 6th starter
				return 10;
			}

		}
		else
		{
			ArrayList<Point> points = new ArrayList<Point>();


			for(int i = 0; i < majorBackups.length; i++)
			{
				if(!majorBackups[i])
				{
					points.add(new Point(3, i));
				}
			}
			Point result = points.get(r.nextInt(points.size()));

			majorBackups[result.getY()] = true;
			if(result.getY() == 0)return 8;
			else return result.getY() + 10;

		}
	}
	public void addPlayer(player player)
	{
		players.add(player);
	}
	public int size()
	{
		return players.size();
	}
	public String toString()
	{
		return team.toString();
	}
	
}
