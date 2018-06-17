
public enum TeamRank
{
	BOTTOMFEEDER(40, 40, 10, 5, 2, 1, 0), LOWLEVEL(20, 30, 30, 10, 5, 2, 1), MIDLEVEL(2, 10, 20, 50, 20, 10, 2), HIGHLEVEL(0, 5, 10, 20, 30, 20, 7), ELITE(0, 0, 5, 20, 30, 50, 15);
	
	TeamRank(int terriblePlayer, int poorPlayer, int belowAveragePlayer, int averagePlayer, int aboveAveragePlayer, int goodPlayer, int elitePlayer)
	{
		players = new int[7];
	}
	private int[] players;
	public int sum()
	{
		int sum = 0;
		for(int i = 0; i < players.length; i ++)
		{
			sum += players[i];
		}
		return sum;
	}
	public int getResult(int num)
	{
		int sum = 0;
		for(int i = 0; i < players.length-1; i ++)
		{
			sum += players[i];
			if(sum > num)return i + 1;
		}
		return 7;
	}


}
