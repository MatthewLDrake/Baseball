import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

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


		TreeMap<Integer, Integer> values = new TreeMap<Integer, Integer>();
		NameGenerator gen = new NameGenerator();
		for(int i = 0; i < 1000; i++)
		{
			Random r = new Random();
			System.out.println(gen.generateName(Gender.MALE).toString());
			int num = (int)Math.round(Math.max(5, r.nextGaussian()*10 + 25));

			if(values.containsKey(num))
				values.put(num, values.get(num) + 1);
			else
				values.put(num, 1);

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
		for (Integer name: values.keySet()){

			String key =name.toString();
			String value = values.get(name).toString();  
			System.out.println(key + " " + value);  


		} 

		System.out.printf("hits: %d outs: %d errors: %d\n", hits, outs, errors);


	}
}
