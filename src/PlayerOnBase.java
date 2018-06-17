
public class PlayerOnBase
{
	private player player;
	private pitcher pitcher;
	private boolean reachedOnError;
	public PlayerOnBase(player player, pitcher pitcher)
	{
		this.player = player;
		this.pitcher = pitcher;
		reachedOnError = false;
	}
	public player getPlayer()
	{
		return player;
	}
	public pitcher getPitcher()
	{
		return pitcher;
	}
	public boolean getReachedOnError()
	{
		return reachedOnError;
	}
	public void setReachedOnError()
	{
		reachedOnError = true;
	}
}
