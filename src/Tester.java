import java.util.ArrayList;

public class Tester
{
	public static void main(String[] args)
	{
		team teamOne = new ProfessionalTeam("Waht", 0);
		team teamTwo = new ProfessionalTeam("Other",1);
		for(int i = 1; i < 9; i++)
		{
			teamOne.addPlayer(new adultPlayer(i));
			teamTwo.addPlayer(new adultPlayer(i));
		}
		
		teamOne.addPlayer(new adultPitcher());
		teamTwo.addPlayer(new adultPitcher());
		
		currentTeam batting = new currentTeam(teamOne);
		currentTeam fielding = new currentTeam(teamTwo);
		int outs = 0, hits = 0, errors = 0;
		
		
		
		for(int i = 0; i < 1000; i++)
		{
			player t = batting.getNext();
			
			if(t == null)
			{
				System.err.println("fuck 1");
				System.exit(1);
			}
			
			BallInPlay test = new BallInPlay(pitchResult.POPUP, batting.getNext(), null, null, null, fielding, 0);
			atBatResult temp = test.result;
			
			if(temp == atBatResult.OUT)
			{
				outs++;
			}
			else if(temp == atBatResult.ERROR)
			{
				errors++;
			}
			else hits++;
			
		}
		System.out.printf("hits: %d outs: %d errors: %d\n", hits, outs, errors);
		
		
	}
}
