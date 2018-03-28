import java.util.HashMap;

public class adultPitcher extends adultPlayer implements pitcher
{
	private HashMap<pitchType, pitcherPitchRatings> pitches;
	public adultPitcher()
	{
		super(0);
		pitches = new HashMap<pitchType, pitcherPitchRatings>();
		pitches.put(pitchType.FASTBALL, new pitcherPitchRatings(90, 50, 50, 50));
		
	}
	public HashMap<pitchType, pitcherPitchRatings> getPitches()
	{
		return pitches;
	}
	@Override
	public pitcherPitchRatings getPitchRatings(pitchType selectedPitch)
	{
		return pitches.get(selectedPitch);
	}
}
