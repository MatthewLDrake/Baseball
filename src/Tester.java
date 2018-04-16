import java.util.HashMap;
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
		
		fielding.setStartingPitcher(new adultPitcher());
		int outs = 0, runs = 0, halfInnings = 0;

		HashMap<atBatResult, Integer> map = new HashMap<atBatResult, Integer>();
		for(atBatResult result : atBatResult.values())
		{
			map.put(result, 0);
		}
		map.put(null, 0);
		for(int i = 0; i < 1000; i++)
		{
			Random r = new Random();

			double timeToMeetingPoint = Math.abs(r.nextGaussian() * 2)+2;
			double distanceToBall = Math.abs(r.nextGaussian() * 10);

			double speed = 50;
			double timeLeft = timeToMeetingPoint - findTime(speed, distanceToBall);

			double feetPerSecond = (30.0/50.0) * 100 + 100 + r.nextDouble()*2- 1;

			//System.out.println(timeToMeetingPoint + " " + distanceToBall + " " + timeLeft + " " + findTime(50, 90));

			adultPlayer fastRunner = new adultPlayer(i);
			adultPlayer slowerRunner = new adultPlayer(i);
			fastRunner.setSpeed(80);
			
			BallInPlay test = new BallInPlay(pitchResult.GROUNDBALL, batting.getNext(), r.nextBoolean() ? null : r.nextBoolean() ? fastRunner : slowerRunner, r.nextBoolean() ? null : r.nextBoolean() ? fastRunner : slowerRunner, r.nextBoolean() ? null : r.nextBoolean() ? fastRunner : slowerRunner, fielding, outs, pitchType.FOUR_SEAM_FASTBALL);



			atBatResult temp = test.result;

			outs = test.outs;
			if(outs == 3)
			{
				outs = 0;
				halfInnings++;
			}

			runs += test.runsScored;

			map.put(temp, map.get(temp) + 1);

		}


		for(atBatResult result : atBatResult.values())
		{
			System.out.println(result + ": " + map.get(result));
		}
		System.out.println("We had " + map.get(null) + " null results");
		
		System.out.println("There were " + halfInnings + " half innings, and " + runs + " runs were scored.");

	}
	private static double findTime(double speed, double distanceToBall)
	{
		Random r = new Random();
		double feetPerSecond = (3/50) * speed + 24 + r.nextDouble()-.5;
		double result = distanceToBall / feetPerSecond;
		return result;
	}
}
