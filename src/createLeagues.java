import java.io.Serializable;
import java.util.ArrayList;
public class createLeagues implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Team> majorLeagueTeams;
	private ArrayList<Team> tripleATeams;
	private ArrayList<Team> doubleATeams;
	private ArrayList<Team> singleATeams;
	private int size;
	public createLeagues()
	{
		majorLeagueTeams = new ArrayList<Team>();
		tripleATeams = new ArrayList<Team>();
		doubleATeams = new ArrayList<Team>();
		singleATeams = new ArrayList<Team>();
	}
	public ArrayList<Team> getMajors()
	{
		return majorLeagueTeams;
	}
	public int size()
	{
		return size/4;
	}
	public void addTeam(Team team)
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
	public Team getTeam(int teamNum, int level)
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
	public ArrayList<Team> getAllTeams()
	{
		ArrayList<Team> retVal = new ArrayList<Team>();
		retVal.addAll(majorLeagueTeams);
		retVal.addAll(tripleATeams);
		retVal.addAll(doubleATeams);
		retVal.addAll(singleATeams);
		return retVal;
	}
	public void swapTeams(Team[] bottom, Team[] top)
	{
		bottom[0].demoted();
		bottom[1].demoted();
		top[0].promoted();
		top[1].promoted();
		if(doubleATeams.remove(bottom[0]))
		{
			doubleATeams.remove(bottom[1]);
			singleATeams.remove(top[0]);
			singleATeams.remove(top[1]);
			doubleATeams.add(top[0]);
			singleATeams.add(bottom[0]);
			singleATeams.add(bottom[1]);
			doubleATeams.add(top[1]);
		}
		else if(tripleATeams.remove(bottom[0]))
		{
			tripleATeams.remove(bottom[1]);
			doubleATeams.remove(top[0]);
			doubleATeams.remove(top[1]);
			tripleATeams.add(top[0]);
			doubleATeams.add(bottom[0]);
			doubleATeams.add(bottom[1]);
			tripleATeams.add(top[1]);
		}
		else
		{
			majorLeagueTeams.remove(bottom[0]);
			majorLeagueTeams.remove(bottom[1]);
			tripleATeams.remove(top[0]);
			tripleATeams.remove(top[1]);
			majorLeagueTeams.add(top[0]);
			tripleATeams.add(bottom[0]);
			tripleATeams.add(bottom[1]);
			majorLeagueTeams.add(top[1]);
		}
		
	}
	

}
