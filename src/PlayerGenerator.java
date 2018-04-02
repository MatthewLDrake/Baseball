import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
public class PlayerGenerator
{
	private ArrayList<player> list;
	private Random r;
	private NameGenerator gen;
	public PlayerGenerator()
	{
		r = new Random();
		list = new ArrayList<player>();
		gen = new NameGenerator();
		generate();

	}
	private void generate()
	{
		// 800 Starters

		generateStarters(800);

		// 800 Relievers

		generateRelievers(800);

		// 300 Catchers

		generateCatchers(300);

		// 250 First

		generateFirst(250);

		// 250 Second
		generateSecond(250);

		// 250 Short
		generateShort(250);
		// 250 Third
		generateThird(250);
		// 250 Left
		generateLeft(250);
		// 250 Center
		generateCenter(250);
		// 250 Right
		generateRight(250);
		
		for(int i = 0; i < list.size(); i++)
		{
			list.get(i).setPositionToOrderBy(-1);
		}
		Collections.sort(list);
		
		for(int i = 0; i < 1000; i++)
		{
			System.out.println(list.get(i).getPositionAsString() + ". " + list.get(i) + " " + list.get(i).getOverall(-1)); 
		}
		
		
	}
	// HashMap<pitchType, pitcherPitchRatings> pitches, String name,HashMap<pitchType, batterPitchRatings> pitchStats, double speedRating, double fieldingRating, double throwPower, double throwAccuracy, int pos)

	private void generateStarters(int max)
	{
		for(int i = 0; i < max; i++)
		{
			HashMap<pitchType, pitcherPitchRatings> pitches = new HashMap<pitchType, pitcherPitchRatings>();
			pitches.put(pitchType.FOUR_SEAM_FASTBALL,generatePitch(pitchType.FOUR_SEAM_FASTBALL));

			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType, batterPitchRatings> batting;

			if(r.nextInt(35) == 14)batting = generateHitter(4);
			else if(r.nextBoolean())batting = generateHitter(2);
			else batting = generateHitter(1);

			player temp = new adultPitcher(pitches, name, batting, r.nextInt(41)+30, Math.round(r.nextGaussian()*10+55), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*2+87), 9);


			list.add(temp);
		}


	}
	private pitcherPitchRatings generatePitch(pitchType type)
	{
		return new pitcherPitchRatings(r.nextInt((type.maxVelocity() - type.minVeloctiy()) + 1) + type.minVeloctiy(), r.nextInt((80) + 1) + 10, r.nextInt((type.maxMovement() - type.minMovement()) + 1) + type.minMovement(),r.nextInt((50) + 1) + 25);		
	}
	private void generateCatchers(int max)
	{
		for(int i = 0; i < max; i++)
		{
			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = generateHitter((int) Math.max(Math.min(5, Math.round(r.nextGaussian()+3)),1));

			player temp = new adultPlayer(name, batting, Math.round(r.nextGaussian()*10+55), Math.round(r.nextGaussian()*10+55), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+45), 8, null);


			list.add(temp);
		}

	}
	private void generateRelievers(int max)
	{
		for(int i = 0; i < max; i++)
		{
			HashMap<pitchType, pitcherPitchRatings> pitches = new HashMap<pitchType, pitcherPitchRatings>();
			pitches.put(pitchType.FOUR_SEAM_FASTBALL,generatePitch(pitchType.FOUR_SEAM_FASTBALL));

			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = null;

			if(r.nextInt(100) == 14)batting = generateHitter(4);
			else if(r.nextBoolean() && !r.nextBoolean())batting = generateHitter(2);
			else batting = generateHitter(1);

			player temp = new adultPitcher(pitches, name, batting, r.nextInt(41)+30, Math.round(r.nextGaussian()*10+55), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+45), 10);

			list.add(temp);
		}

	}
	private HashMap<pitchType, batterPitchRatings> generateHitter(int skill)
	{
		HashMap<pitchType, batterPitchRatings> retVal = new HashMap<pitchType, batterPitchRatings>();
		for(pitchType pitch : pitchType.values())
		{
			double goodSwingRating = 0, badTakeRating = 0, contactValue = 0, powerValue = 0, groundBallPercent = 0;

			switch (skill)
			{
			case 1:
				goodSwingRating = Math.round(Math.max(5, r.nextGaussian()*10 + 20));
				badTakeRating = Math.round(Math.max(5, r.nextGaussian()*10 + 20));
				contactValue = Math.round(Math.max(5, r.nextGaussian()*10 + 20));
				powerValue = Math.round(Math.max(5, r.nextGaussian()*10 + 20));
				break;
			case 2:
				goodSwingRating = Math.round(Math.max(5, r.nextGaussian()*10 + 35));
				badTakeRating = Math.round(Math.max(5, r.nextGaussian()*10 + 35));
				contactValue = Math.round(Math.max(5, r.nextGaussian()*10 + 35));
				powerValue = Math.round(Math.max(5, r.nextGaussian()*10 + 35));
				break;
			case 3:
				goodSwingRating = Math.round(Math.max(5, r.nextGaussian()*10 + 50));
				badTakeRating = Math.round(Math.max(5, r.nextGaussian()*10 + 50));
				contactValue = Math.round(Math.max(5, r.nextGaussian()*10 + 50));
				powerValue = Math.round(Math.max(5, r.nextGaussian()*10 + 50));
				break;
			case 4:
				goodSwingRating = Math.round(Math.max(5, r.nextGaussian()*10 + 65));
				badTakeRating = Math.round(Math.max(5, r.nextGaussian()*10 + 65));
				contactValue = Math.round(Math.max(5, r.nextGaussian()*10 + 65));
				powerValue = Math.round(Math.max(5, r.nextGaussian()*10 + 65));
				break;
			case 5:
				goodSwingRating = Math.round(Math.max(5, r.nextGaussian()*10 + 75));
				badTakeRating = Math.round(Math.max(5, r.nextGaussian()*10 + 75));
				contactValue = Math.round(Math.max(5, r.nextGaussian()*10 + 75));
				powerValue = Math.round(Math.max(5, r.nextGaussian()*10 + 75));
				break;
			}

			groundBallPercent = Math.round(r.nextGaussian()*15+50);
			// double goodSwingRating, double badTakeRating, double contactValue, double powerValue, double groundballPercent
			retVal.put(pitch,new batterPitchRatings(goodSwingRating, badTakeRating, contactValue, powerValue, groundBallPercent));
		}
		return retVal;
	}
	private void generateFirst(int max)
	{
		for(int i = 0; i < max; i++)
		{
			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = generateHitter((int) Math.max(Math.min(5, Math.round(r.nextGaussian()+3)),1));

			player temp = new adultPlayer(name, batting, Math.round(r.nextGaussian()*15+60), Math.round(r.nextGaussian()*10+55), Math.round(r.nextGaussian()*5+45), Math.round(r.nextGaussian()*5+45), Math.round(r.nextGaussian()*5+65), 1, null);

			list.add(temp);
		}


	}
	private void generateSecond(int max)
	{
		for(int i = 0; i < max; i++)
		{
			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = generateHitter((int) Math.max(Math.min(5, Math.round(r.nextGaussian()+3)),1));

			player temp = new adultPlayer(name, batting, Math.max(Math.min(100, Math.round(r.nextGaussian()*15+60)),30), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+55), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+45), 2, null);


			list.add(temp);
		}


	}
	private void generateShort(int max)
	{
		for(int i = 0; i < max; i++)
		{
			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = generateHitter((int) Math.max(Math.min(5, Math.round(r.nextGaussian()+3)),1));

			player temp = new adultPlayer(name, batting, Math.round(r.nextGaussian()*11+60), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*10+65), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+45), 4, null);


			list.add(temp);
		}


	}
	private void generateThird(int max)
	{
		for(int i = 0; i < max; i++)
		{
			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = generateHitter((int) Math.max(Math.min(5, Math.round(r.nextGaussian()+3)),1));

			player temp = new adultPlayer(name, batting, Math.round(r.nextGaussian()*10+55), Math.round(r.nextGaussian()*10+55), Math.round(r.nextGaussian()*5+70), Math.round(r.nextGaussian()*5+70), Math.round(r.nextGaussian()*5+45), 3, null);


			list.add(temp);
		}


	}
	private void generateRight(int max)
	{
		for(int i = 0; i < max; i++)
		{
			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = generateHitter((int) Math.max(Math.min(5, Math.round(r.nextGaussian()+3)),1));

			player temp = new adultPlayer(name, batting, Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*10+42.5), Math.round(r.nextGaussian()*7.5+80), Math.round(r.nextGaussian()*15+60), Math.round(r.nextGaussian()*5+45), 6, null);


			list.add(temp);
		}


	}
	private void generateCenter(int max)
	{
		for(int i = 0; i < max; i++)
		{
			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = generateHitter((int) Math.max(Math.min(5, Math.round(r.nextGaussian()+3)),1));

			player temp = new adultPlayer(name, batting, Math.min(100, Math.round(r.nextGaussian()*5+80)), Math.round(r.nextGaussian()*12.5+60), Math.round(r.nextGaussian()*7.5+80), Math.round(r.nextGaussian()*15+65), Math.round(r.nextGaussian()*5+45), 7, null);


			list.add(temp);
		}


	}
	private void generateLeft(int max)
	{
		for(int i = 0; i < max; i++)
		{
			String name = gen.generateName(Gender.MALE).toString();

			HashMap<pitchType,batterPitchRatings> batting = generateHitter((int) Math.max(Math.min(5, Math.round(r.nextGaussian()+3)),1));

			player temp = new adultPlayer(name, batting, Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*12.5+60), Math.round(r.nextGaussian()*7.5+77.5), Math.round(r.nextGaussian()*15+65), Math.round(r.nextGaussian()*5+45), 5, null);


			list.add(temp);
		}


	}

	public ArrayList<player> getPlayers()
	{
		return list;
	}

}
