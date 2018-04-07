import java.util.Random;

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
			Random r = new Random();

			double timeToMeetingPoint = Math.abs(r.nextGaussian() * 2)+2;
			double distanceToBall = Math.abs(r.nextGaussian() * 10);

			double speed = 50;

			double timeLeft = timeToMeetingPoint - findTime(speed, distanceToBall);

			//System.out.println(timeToMeetingPoint + " " + distanceToBall + " " + timeLeft + " " + findTime(50, 90));

			System.out.println(Math.abs(r.nextGaussian() * 2)+4.5);

			BallInPlay test = new BallInPlay(pitchResult.FLYBALL, batting.getNext(), null, null, null, fielding, 0, pitchType.FOUR_SEAM_FASTBALL);
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
	private static double findTime(double speed, double distanceToBall)
	{
		Random r = new Random();
		double feetPerSecond = (3/50) * speed + 24 + r.nextDouble()-.5;
		double result = distanceToBall / feetPerSecond;
		return result;
	}
}
