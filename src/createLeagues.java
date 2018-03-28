import java.io.Serializable;
import java.util.ArrayList;
public class createLeagues implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<team> majorLeagueTeams;
	private ArrayList<team> tripleATeams;
	private ArrayList<team> doubleATeams;
	private ArrayList<team> singleATeams;
	private int size;
	public createLeagues()
	{
		majorLeagueTeams = new ArrayList<team>();
		tripleATeams = new ArrayList<team>();
		doubleATeams = new ArrayList<team>();
		singleATeams = new ArrayList<team>();
	}
	public ArrayList<team> getMajors()
	{
		return majorLeagueTeams;
	}
	public int size()
	{
		return size/4;
	}
	public void addTeam(team team)
	{
		if(size % 4 == 0)
		{
			majorLeagueTeams.add(team);
		}
		else if(size % 4 == 1)
		{
			tripleATeams.add(team);
		}
		else if(size % 4 == 2)
		{
			doubleATeams.add(team);
		}
		else if(size % 4 == 3)
		{
			singleATeams.add(team);
		}
		size++;
	}
	// 0 is majors, 1 is tripleA, 2 is doubleA, 3 is single A, 4 is college
	public team getTeam(int teamNum, int level)
	{
		switch(level)
		{
		case 0:
			return majorLeagueTeams.get(teamNum);
		case 1:
			return tripleATeams.get(teamNum);
		case 2:
			return doubleATeams.get(teamNum);
		case 3:
			return singleATeams.get(teamNum);
		}
		return null;
		
	}
	

}
