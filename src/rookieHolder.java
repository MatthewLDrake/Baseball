import java.util.ArrayList;
public class rookieHolder
{
	private team team;
	private ArrayList<player> players;
	public rookieHolder(team team)
	{
		this.team = team;
		players = new ArrayList<player>();
	}
	public void addPlayer(player player)
	{
		players.add(player);
	}
}
