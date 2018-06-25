import java.util.ArrayList;
import java.util.Random;

public class CollegeTeam extends Team
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CollegePlayerGenerator gen;
	private TeamRank rank;
	private Random r;
	public CollegeTeam(String teamName, String abbrevation, int teamNum, TeamRank rank)
	{
		super(teamName, teamNum, -1);
		gen = CollegePlayerGenerator.getInstance();
		this.rank = rank;
		r = new Random();
	}
	
	public ArrayList<player> getProPlayers()
	{		
		ArrayList<player> retVal = new ArrayList<player>();
		ArrayList<player> returningPlayers = new ArrayList<player>();
		for(int i = 0; i < players.size();i++)
		{
			CollegePlayer current = (CollegePlayer)players.get(i);
			boolean flag = false;
			if(current.goPro())
			{
				retVal.add(players.get(i));
				flag = true;
			}
			
			if(!flag && !current.didGraduate())
			{
				returningPlayers.add(players.get(i));
			}
			
		}
		players = returningPlayers;
		
		return retVal;
	}
	/*
	 * 0-4 Starting Pitchers
	 * 5 Spot Starter
	 * 6 Closer
	 * 7 Setup
	 * 8-10 Middle Reliever
	 * 11 Reliever
	 * 12-19 Starting Position Players
	 * 20 Backup Catcher
	 * 21 Backup Corner Infielder
	 * 22 Backup Inside Infielder
	 * 23 Backup Outfielder
	 * 24 Backup Position Player
	 */
	public void fillPlayers(boolean firstTime)
	{
		if(!positionsFilled[0])
		{
			pitcher addedPlayer = gen.generateStartingPitcher(getType(), firstTime, this);
			addStartingPitcher(addedPlayer);
		}
		if(!positionsFilled[1])
		{
			pitcher addedPlayer = gen.generateStartingPitcher(getType(), firstTime, this);
			addStartingPitcher(addedPlayer);
		}
		if(!positionsFilled[2])
		{
			pitcher addedPlayer = gen.generateStartingPitcher(getType(), firstTime, this);
			addStartingPitcher(addedPlayer);
		}
		if(!positionsFilled[3])
		{
			pitcher addedPlayer = gen.generateStartingPitcher(getType(), firstTime, this);
			addStartingPitcher(addedPlayer);
		}
		if(!positionsFilled[4])
		{
			pitcher addedPlayer = gen.generateStartingPitcher(getType(), firstTime, this);
			addStartingPitcher(addedPlayer);
		}
		if(!positionsFilled[5])
		{
			pitcher addedPlayer = gen.generateStartingPitcher(getType(), firstTime, this);
			addStartingPitcher(addedPlayer);
		}
		if(!positionsFilled[6])
		{
			pitcher addedPlayer = gen.generateReliefPitcher(getType(), firstTime, this);
			addReliefPitcher(addedPlayer);
		}
		if(!positionsFilled[7])
		{
			pitcher addedPlayer = gen.generateReliefPitcher(getType(), firstTime, this);
			addReliefPitcher(addedPlayer);
		}
		if(!positionsFilled[8])
		{
			pitcher addedPlayer = gen.generateReliefPitcher(getType(), firstTime, this);
			addReliefPitcher(addedPlayer);
		}
		if(!positionsFilled[9])
		{
			pitcher addedPlayer = gen.generateReliefPitcher(getType(), firstTime, this);
			addReliefPitcher(addedPlayer);
		}
		if(!positionsFilled[10])
		{
			pitcher addedPlayer = gen.generateReliefPitcher(getType(), firstTime, this);
			addReliefPitcher(addedPlayer);
		}
		if(!positionsFilled[11])
		{
			pitcher addedPlayer = gen.generateReliefPitcher(getType(), firstTime, this);
			addReliefPitcher(addedPlayer);
		}
		if(!positionsFilled[12])
		{
			player addedPlayer = gen.generateFirstBase(getType(), firstTime, this);
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[13])
		{
			player addedPlayer = gen.generateSecondBase(getType(), firstTime, this);
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[14])
		{
			player addedPlayer = gen.generateThirdBase(getType(), firstTime, this);
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[15])
		{
			player addedPlayer = gen.generateShortStop(getType(), firstTime, this);
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[16])
		{
			player addedPlayer = gen.generateLeftFielder(getType(), firstTime, this);
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[17])
		{
			player addedPlayer = gen.generateRightFielder(getType(), firstTime, this);
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[18])
		{
			player addedPlayer = gen.generateCenterFielder(getType(), firstTime, this);
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[19] || !positionsFilled[20])
		{
			player addedPlayer = gen.generateCatcher(getType(), firstTime, this);
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[21])
		{
			player addedPlayer = null;
			if(r.nextBoolean())
			{
				addedPlayer = gen.generateFirstBase(getType(), firstTime, this);
			}
			else
			{
				addedPlayer = gen.generateThirdBase(getType(), firstTime, this);
			}
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[22])
		{
			player addedPlayer = null;
			if(r.nextBoolean())
			{
				addedPlayer = gen.generateFirstBase(getType(), firstTime, this);
			}
			else
			{
				addedPlayer = gen.generateShortStop(getType(), firstTime, this);
			}
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[23])
		{
			player addedPlayer = null;
			int i = r.nextInt(3);
			if(i == 0)
			{
				addedPlayer = gen.generateFirstBase(getType(), firstTime, this);
			}
			else if(i == 1)
			{
				addedPlayer = gen.generateThirdBase(getType(), firstTime, this);
			}
			else
			{
				addedPlayer = gen.generateCenterFielder(getType(), firstTime, this);
			}
			addPlayer(addedPlayer);
		}
		if(!positionsFilled[24])
		{
			player addedPlayer = null;
			int pos = r.nextInt(8);
			if(pos == 0)
			{
				addedPlayer = gen.generateFirstBase(getType(), firstTime, this);
			}
			else if(pos ==1)
			{
				addedPlayer = gen.generateSecondBase(getType(), firstTime, this);
			}
			else if(pos == 2)
			{
				addedPlayer = gen.generateThirdBase(getType(), firstTime, this);
			}
			else if(pos == 3)
			{
				addedPlayer = gen.generateShortStop(getType(), firstTime, this);
			}
			else if(pos == 4)
			{
				addedPlayer = gen.generateLeftFielder(getType(), firstTime, this);
			}
			else if(pos == 5)
			{
				addedPlayer = gen.generateRightFielder(getType(), firstTime, this);
			}
			else if(pos == 6)
			{
				addedPlayer = gen.generateCenterFielder(getType(), firstTime, this);
			}
			else if(pos == 7)
			{
				addedPlayer = gen.generateCatcher(getType(), firstTime, this);
			}
			addPlayer(addedPlayer);
		}
	}

	private int getType()
	{
		return rank.getResult(r.nextInt(rank.sum()));
		
	}
	public void advanceYear()
	{
		for(int i = 0; i < players.size();i++)
		{
			CollegePlayer current = (CollegePlayer)players.get(i);
			current.advanceYear();
			current.progress();
		}
	}


}
