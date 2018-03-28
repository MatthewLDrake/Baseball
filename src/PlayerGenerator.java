import java.util.ArrayList;
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
		    
		    player temp = new adultPitcher(pitches, name, batting, r.nextInt(41)+30, Math.round(r.nextGaussian()*10+55), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+75), Math.round(r.nextGaussian()*5+45), 9);
			
			list.add(temp);
		}
		
	}
	private HashMap<pitchType, batterPitchRatings> generateHitter(int i)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private void generateFirst(int i)
	{
		// TODO Auto-generated method stub
		
	}
	private void generateSecond(int i)
	{
		// TODO Auto-generated method stub
		
	}
	private void generateShort(int i)
	{
		// TODO Auto-generated method stub
		
	}
	private void generateThird(int i)
	{
		// TODO Auto-generated method stub
		
	}
	private void generateRight(int i)
	{
		// TODO Auto-generated method stub
		
	}
	private void generateCenter(int i)
	{
		// TODO Auto-generated method stub
		
	}
	private void generateLeft(int i)
	{
		// TODO Auto-generated method stub
		
	}

	public ArrayList<player> getPlayers()
	{
		return list;
	}
	
}
