import java.util.HashMap;

public interface pitcher
{
	public HashMap<pitchType, pitcherPitchRatings> getPitches();

	public pitcherPitchRatings getPitchRatings(pitchType selectedPitch);
}
