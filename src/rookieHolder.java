
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class rookieHolder
{
	private team team;
	private ArrayList<player> players;
	private boolean[] majorStarters;
	private boolean[] majorStartingPitchers;
	private boolean[] majorRelievers;
	// backup catcher, two backup infielders, one backup outfielder, one best available
	private boolean[] majorBackups;
	private boolean[] tripleAStarters;
	private boolean[] tripleAStartingPitchers;
	private boolean[] tripleARelievers;
	private boolean[] tripleABackups;
	private boolean[] doubleAStarters;
	private boolean[] doubleAStartingPitchers;
	private boolean[] doubleARelievers;
	private boolean[] doubleABackups;
	private boolean[] singleAStarters;
	private boolean[] singleAStartingPitchers;
	private boolean[] singleARelievers;
	private boolean[] singleABackups;
	private Random r;
	private boolean firstStage, secondStage, thirdStage, fourthStage;
	private boolean firstDraft;
	private int mostRecentIntendedLeague;
	public rookieHolder(team team, boolean firstDraft)
	{
		this.firstDraft = firstDraft;
		r= new Random();
		this.team = team;
		players = new ArrayList<player>();


		if(firstDraft)
		{
			firstStage = true;
			secondStage = false;
			thirdStage = false;
			fourthStage = false;
			majorStarters = new boolean[8];
			majorStartingPitchers = new boolean[5];
			majorRelievers = new boolean[7];
			majorBackups = new boolean[5];
			tripleAStarters = new boolean[8];
			tripleAStartingPitchers = new boolean[5];
			tripleARelievers = new boolean[7];
			tripleABackups = new boolean[5];
			doubleAStarters = new boolean[8];
			doubleAStartingPitchers = new boolean[5];
			doubleARelievers = new boolean[7];
			doubleABackups = new boolean[5];
			singleAStarters = new boolean[8];
			singleAStartingPitchers = new boolean[5];
			singleARelievers = new boolean[7];
			singleABackups = new boolean[8];
		}
	}
	public int size()
	{
		return players.size();
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
				secondStage = true;
			}
			if(result.getX() == 0)
			{
				mostRecentIntendedLeague = 0;
				majorStarters[result.getY()-1] = true;
				return result.getY();
			}
			else if(result.getX() == 1)
			{
				mostRecentIntendedLeague = 0;
				majorStartingPitchers[result.getY()] = true;
				return 9;
			}
			else
			{
				mostRecentIntendedLeague = 0;
				majorRelievers[result.getY()] = true;
				if(result.getY() == 0)return 9; // 6th starter
				return 10;
			}

		}
		else if(secondStage)
		{
			ArrayList<Point> points = new ArrayList<Point>();

			for(int i = 0; i < tripleAStarters.length; i++)
			{
				if(!tripleAStarters[i])
				{
					points.add(new Point(0,i+1));
				}
			}
			for(int i = 0; i < tripleAStartingPitchers.length; i++)
			{
				if(!tripleAStartingPitchers[i])
				{
					points.add(new Point(1,i));
				}
			}
			for(int i = 0; i < tripleARelievers.length; i++)
			{
				if(!tripleARelievers[i])
				{
					points.add(new Point(2,i));
				}
			}
			for(int i = 0; i < majorBackups.length; i++)
			{
				if(!majorBackups[i])
				{
					points.add(new Point(3, i));
				}
			}
			Point result = points.get(r.nextInt(points.size()));
			if(points.size() == 1)
			{
				secondStage = false;
				thirdStage = true;
			}

			if(result.getX() == 0)
			{
				mostRecentIntendedLeague = 1;
				tripleAStarters[result.getY()-1] = true;
				return result.getY();
			}
			else if(result.getX() == 1)
			{
				mostRecentIntendedLeague = 1;
				tripleAStartingPitchers[result.getY()] = true;
				return 9;
			}
			else if(result.getX() == 2)
			{
				mostRecentIntendedLeague = 1;
				tripleARelievers[result.getY()] = true;
				if(result.getY() == 0)return 9; // 6th starter
				return 10;
			}
			else
			{
				mostRecentIntendedLeague = 0;
				majorBackups[result.getY()] = true;
				if(result.getY() == 0)return 8;
				else return result.getY() + 10;
			}
		}
		else if(thirdStage)
		{
			ArrayList<Point> points = new ArrayList<Point>();

			for(int i = 0; i < doubleAStarters.length; i++)
			{
				if(!doubleAStarters[i])
				{
					points.add(new Point(0,i+1));
				}
			}
			for(int i = 0; i < doubleAStartingPitchers.length; i++)
			{
				if(!doubleAStartingPitchers[i])
				{
					points.add(new Point(1,i));
				}
			}
			for(int i = 0; i < doubleARelievers.length; i++)
			{
				if(!doubleARelievers[i])
				{
					points.add(new Point(2,i));
				}
			}
			for(int i = 0; i < tripleABackups.length; i++)
			{
				if(!tripleABackups[i])
				{
					points.add(new Point(3, i));
				}
			}
			Point result = points.get(r.nextInt(points.size()));
			if(points.size() == 1)
			{
				thirdStage = false;
				fourthStage = true;
			}

			if(result.getX() == 0)
			{
				mostRecentIntendedLeague = 2;
				doubleAStarters[result.getY()-1] = true;
				return result.getY();
			}
			else if(result.getX() == 1)
			{
				mostRecentIntendedLeague = 2;
				doubleAStartingPitchers[result.getY()] = true;
				return 9;
			}
			else if(result.getX() == 2)
			{
				mostRecentIntendedLeague = 2;
				doubleARelievers[result.getY()] = true;
				if(result.getY() == 0)return 9; // 6th starter
				return 10;
			}
			else
			{
				mostRecentIntendedLeague = 1;
				tripleABackups[result.getY()] = true;
				if(result.getY() == 0)return 8;
				else return result.getY() + 10;
			}
		}
		else if(fourthStage)
		{
			ArrayList<Point> points = new ArrayList<Point>();

			for(int i = 0; i < singleAStarters.length; i++)
			{
				if(!singleAStarters[i])
				{
					points.add(new Point(0,i+1));
				}
			}
			for(int i = 0; i < singleAStartingPitchers.length; i++)
			{
				if(!singleAStartingPitchers[i])
				{
					points.add(new Point(1,i));
				}
			}
			for(int i = 0; i < singleARelievers.length; i++)
			{
				if(!singleARelievers[i])
				{
					points.add(new Point(2,i));
				}
			}
			for(int i = 0; i < doubleABackups.length; i++)
			{
				if(!doubleABackups[i])
				{
					points.add(new Point(3, i));
				}
			}
			Point result = points.get(r.nextInt(points.size()));
			if(points.size() == 1)
			{
				fourthStage = false;
			}

			if(result.getX() == 0)
			{
				mostRecentIntendedLeague = 3;
				singleAStarters[result.getY()-1] = true;
				return result.getY();
			}
			else if(result.getX() == 1)
			{
				mostRecentIntendedLeague = 3;
				singleAStartingPitchers[result.getY()] = true;
				return 9;
			}
			else if(result.getX() == 2)
			{
				mostRecentIntendedLeague = 3;
				singleARelievers[result.getY()] = true;
				if(result.getY() == 0)return 9; // 6th starter
				return 10;
			}
			else
			{
				mostRecentIntendedLeague = 2;
				doubleABackups[result.getY()] = true;
				if(result.getY() == 0)return 8;
				else return result.getY() + 10;
			}
		}
		else
		{
			mostRecentIntendedLeague = 3;
			ArrayList<Point> points = new ArrayList<Point>();
			for(int i = 0; i < singleABackups.length; i++)
			{
				if(!singleABackups[i])
				{
					points.add(new Point(3, i));
				}
			}
			Point result = points.get(r.nextInt(points.size()));
			singleABackups[result.getY()] = true;
			if(result.getY() == 0)return 8;
			else return result.getY() + 10;
		}
	}
	public void addPlayer(player player)
	{
		players.add(player);
		player.setIntendedLeague(mostRecentIntendedLeague);
	}
	public void distributePlayers()
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
			System.out.println(startingPitchers.size() + " " + reliefPitchers.size());
			for(int i = 0; i < 6; i++)
			{
				team.addStartingPitcher((pitcher) startingPitchers.get(i));
				team.addReliefPitcher((pitcher) reliefPitchers.get(i));
			}
			MajorLeagueTeam temp = (MajorLeagueTeam) team;
			MinorLeagueTeam tripleA = temp.getTripleATeam();
			for(int i = 6; i < 12; i++)
			{
				tripleA.addStartingPitcher((pitcher) startingPitchers.get(i));
				tripleA.addReliefPitcher((pitcher) reliefPitchers.get(i));
			}
			MinorLeagueTeam doubleA = temp.getDoubleATeam();
			for(int i = 12; i < 18; i++)
			{
				doubleA.addStartingPitcher((pitcher) startingPitchers.get(i));
				doubleA.addReliefPitcher((pitcher) reliefPitchers.get(i));
			}
			MinorLeagueTeam singleA = temp.getSingleATeam();
			for(int i = 18; i < 24; i++)
			{
				singleA.addStartingPitcher((pitcher) startingPitchers.get(i));
				singleA.addReliefPitcher((pitcher) reliefPitchers.get(i));
			}
			for(int i = 0; i < positionPlayers.size(); i++)
			{
				System.out.print(positionPlayers.get(i).size() + " ");
				for(int j = 0; j < positionPlayers.get(i).size();j++)
				{
					player tempPlayer = positionPlayers.get(i).get(j);
					int league = tempPlayer.getIntendedLeague();
					
					if(league == 0)
					{
						team.addPlayer(tempPlayer);
					}
					else if(league == 1)
					{
						tripleA.addPlayer(tempPlayer);
					}
					else if(league == 2)
					{
						doubleA.addPlayer(tempPlayer);
					}
					else singleA.addPlayer(tempPlayer);
				}
				
			}
			
			System.out.println(team.getSize() + " " + tripleA.getSize() + " " + doubleA.getSize() + " " + singleA.getSize());
		}
		
	}

}
class Point
{
	private int x, y;
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	int getX()
	{
		return x;
	}
	int getY()
	{
		return y;
	}
}
