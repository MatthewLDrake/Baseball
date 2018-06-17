import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pitch
{
    private pitchResult result;
    private pitcher pitcher;
    private player batter;
    private int balls, strikes;
    private pitchType selectedPitch;
    private ArrayList<pitchObj> controlList;
    private ArrayList<pitchObj> movementList;
    private ArrayList<pitchObj> batterList;
    private int aim;
    private Random r;
    private final int INSIDE_STRIKE = 1, OUTER_STRIKE = 2, OUTER_EDGE = 3, DIRT = 4;
    private double movementModifier, velocityModifier;
    public Pitch(pitcher pitcher, player batter, int balls, int strikes)
    {
	r = new Random();
	this.batter = batter;
	this.pitcher = pitcher;
	this.balls = balls;
	this.strikes = strikes;
	movementList = new ArrayList<pitchObj>();
	controlList = new ArrayList<pitchObj>();
	batterList = new ArrayList<pitchObj>();
	selectPitch();
	result = throwPitch();
    }
    private pitchResult throwPitch()
    {
	pitcherPitchRatings pitcherRatings = pitcher.getPitchRatings(selectedPitch);
	batterPitchRatings batterRatings = batter.getBatterRatingsVs(selectedPitch);

	double baseStrike, baseHitability;

	boolean closeToTarget = sucessfulPitch(pitcherRatings);

	if(closeToTarget)
	{
	    if(aim == INSIDE_STRIKE)
	    {
		baseStrike = 95;
		baseHitability = 75;
	    }
	    else if(aim == OUTER_STRIKE)
	    {
		baseStrike = 55;
		baseHitability = 40;
	    }
	    else if(aim  == OUTER_EDGE)
	    {
		baseStrike = 40;
		baseHitability = 30;
	    }
	    else
	    {
		baseStrike = 0;
		baseHitability = 5;
	    }
	}
	else
	{
	    if(aim == INSIDE_STRIKE)
	    {
		baseStrike = 60;
		baseHitability = 75;
	    }
	    else if(aim == OUTER_STRIKE)
	    {
		if(r.nextBoolean())
		{
		    baseStrike = 35;
		    baseHitability = 35;
		}
		else
		{
		    baseStrike = 65;
		    baseHitability = 75;
		}

	    }
	    else if(aim  == OUTER_EDGE)
	    {
		if(r.nextBoolean())
		{
		    baseStrike = 75;
		    baseHitability = 75;
		}
		else
		{
		    baseStrike = 0;
		    baseHitability = 10;
		}
	    }
	    else
	    {
		if(r.nextBoolean())
		{
		    return pitchResult.WILD_PITCH;

		}
		else
		{
		    baseStrike = 40;
		    baseHitability = 75;
		}
	    }
	}
	int randNumber = r.nextInt(100);
	boolean isStrike = randNumber < baseStrike;

	if(batterSwung(isStrike, batterRatings, pitcherRatings))
	{
	    if(batterMadeContact(baseHitability, batterRatings))
	    {
		if((r.nextBoolean() && r.nextBoolean() & r.nextBoolean()) || (baseHitability < 40 && r.nextBoolean()))
		    return pitchResult.FOUL;
		else
		{
		    double hitType = batterRatings.getContactValue() - (movementModifier+velocityModifier) + r.nextInt(30) - 20;

		    if(hitType > 35)
		    {
			double groundBallProbability = (pitcherRatings.getGroundBallPercentage()+batterRatings.getGroundballPercent())/2;
			if(r.nextInt(100)-5 > groundBallProbability)return pitchResult.STRONG_FLYBALL;
			return pitchResult.STRONG_GROUNDBALL;
		    }
		    else if(hitType > 18.5)
		    {
			double groundBallProbability = (pitcherRatings.getGroundBallPercentage()+batterRatings.getGroundballPercent())/2;
			if(r.nextInt(100)-5 > groundBallProbability)return pitchResult.FLYBALL;
			return pitchResult.GROUNDBALL;
		    }
		    else
			return pitchResult.POPUP;
		}
	    }
	    else return pitchResult.STRIKE;
	}
	else
	{
	    if(isStrike)return pitchResult.STRIKE;
	    else return pitchResult.BALL;
	}

    }
    private boolean batterMadeContact(double baseHitability, batterPitchRatings batterRatings)
    {
	double modifier = batterRatings.getContactValue()-50;
	modifier = modifier/10;

	return (baseHitability + modifier) > r.nextInt(100);

    }
    private boolean batterSwung(boolean isStrike, batterPitchRatings batterRatings, pitcherPitchRatings selectedPitch)
    {
	movementModifier = (selectedPitch.getMovement()/10.0)*2.0;
	velocityModifier = selectedPitch.getVelocity()/20.0;
	double takeRating = 0.0;
	if(isStrike)
	{
	    takeRating = batterRatings.getGoodSwingRating() - movementModifier - velocityModifier;
	    return r.nextInt(100) < takeRating;
	}
	else
	{
	    takeRating = batterRatings.getBadTakeRating()  - movementModifier - velocityModifier;
	    return r.nextInt(100) > takeRating;
	}


    }
    private boolean sucessfulPitch(pitcherPitchRatings pitcherRatings)
    {
	int success = r.nextInt(130);
	return pitcherRatings.getControl() > success;
    }
    public pitchResult getResult()
    {
	return result;
    }
    private void selectPitch()
    {
	HashMap<pitchType, pitcherPitchRatings> temp = pitcher.getPitches();

	for(Map.Entry<pitchType, pitcherPitchRatings> m:temp.entrySet())
	{  
	    movementList.add(new pitchObj(m.getKey(), m.getValue().getMovement()));
	    controlList.add(new pitchObj(m.getKey(), m.getValue().getControl()));
	    batterList.add(new pitchObj(m.getKey(), batter.getBattingAverageVs(m.getKey())));
	} 
	Collections.sort(movementList);
	Collections.sort(controlList);
	Collections.sort(batterList);

	ArrayList<pitchType> finalList = new ArrayList<pitchType>();
	ArrayList<Integer> aims = new ArrayList<Integer>();
	// hitter's count
	if((balls >= 2 && strikes == 0) || (balls == 3 && strikes == 1))
	{
	    for(int i = 0; i < controlList.size(); i++)
	    {
		finalList.add(controlList.get(i).getPitch());
		aims.add(INSIDE_STRIKE);
		aims.add(OUTER_STRIKE);
		aims.add(OUTER_EDGE);

		int teamNum = r.nextInt(aims.size()*aims.size()*aims.size()*aims.size());
		teamNum = (aims.size()-1)-(int)Math.floor(Math.pow(teamNum, .25));
		aim =  aims.get(teamNum);

	    }

	}
	// pitcher's count
	else if(balls <= 1 && strikes == 2)
	{
	    ArrayList<pitchObj> tempList = new ArrayList<pitchObj>();
	    for(int i = 0 ; i < movementList.size(); i++)
	    {
		int j = 0;
		while(j < batterList.size())
		{
		    if(movementList.get(i).getPitch().equals(batterList.get(j).getPitch())) break; 

		    j++;
		}
		tempList.add(new pitchObj(movementList.get(i).getPitch(), ((double) i + j)/ 2.0));

	    }
	    Collections.sort(tempList);
	    for(int i = 0; i < tempList.size(); i++)
	    {
		finalList.add(tempList.get(i).getPitch());
	    }
	    aims.add(OUTER_EDGE);
	    aims.add(OUTER_STRIKE);
	    aims.add(DIRT);
	    aims.add(INSIDE_STRIKE);


	    int teamNum = r.nextInt(aims.size()*aims.size());
	    teamNum = (aims.size()-1)-(int)Math.floor(Math.sqrt(teamNum));
	    aim =  aims.get(teamNum);
	}
	// neutral count
	else
	{
	    ArrayList<pitchObj> tempList = new ArrayList<pitchObj>();
	    for(int i = 0 ; i < movementList.size(); i++)
	    {
		int j = 0;
		while(j < batterList.size())
		{
		    if(movementList.get(i).getPitch().equals(batterList.get(j).getPitch())) break; 

		    j++;
		}
		tempList.add(new pitchObj(movementList.get(i).getPitch(), ((double) i + j)/ 3.0));

	    }
	    Collections.sort(tempList);
	    for(int i = 0; i < tempList.size(); i++)
	    {
		finalList.add(tempList.get(i).getPitch());
	    }
	    aims.add(OUTER_STRIKE);
	    aims.add(OUTER_EDGE);
	    aims.add(INSIDE_STRIKE);
	    aims.add(DIRT);



	    int teamNum = r.nextInt(aims.size()*aims.size());
	    teamNum = (aims.size()-1)-(int)Math.floor(Math.sqrt(teamNum));
	    aim =  aims.get(teamNum);
	}

	int teamNum = r.nextInt(finalList.size()*finalList.size());
	teamNum = (finalList.size()-1)-(int)Math.floor(Math.sqrt(teamNum));
	selectedPitch =  finalList.get(teamNum);

    }
    public pitchType lastPitchType()
    {
	return selectedPitch;
    }
}


class pitchObj implements Comparable<pitchObj>
{
    private double value;
    private pitchType pitch;
    public pitchObj(pitchType pitch, double value)
    {
	this.pitch = pitch;
	this.value = value;
    }
    public pitchType getPitch()
    {
	return pitch;
    }
    public double getValue()
    {
	return value;
    }
    @Override
    public int compareTo(pitchObj o)
    {
	return (int) (value - o.getValue());
    }

}

