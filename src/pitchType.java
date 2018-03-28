
public enum pitchType
{
	FOUR_SEAM_FASTBALL(83, 101, 0 , 10), CHANGEUP(68, 80, 30 , 50), CURVEBALL(55, 76, 40 , 100), CUTTER(82, 94, 20 , 45), FORKBALL(82, 94, 40 , 100), KNUCKLEBALL(50, 70, 80 , 100), KNUCKLECURVE(63, 78, 75 , 95), SCREWBALL(82, 94, 20 , 45), SINKER(77, 88, 35 , 63), SLIDER(82, 94, 20 , 45), SPLITTER(79, 89, 35 , 55), TWO_SEAM_FASTBALL(85, 97, 20 , 50);
	pitchType(int minVelocity, int maxVelocity, int minMovement, int maxMovement) 
	{
		this.minVelocity = minVelocity;
		this.maxMovement = maxMovement;
		this.maxVelocity = maxVelocity;
		this.minMovement = minMovement;
	}

	private final int minVelocity, maxVelocity, minMovement, maxMovement;

	public int minVeloctiy() {
		return minVelocity;
	}
	public int maxVelocity() {
		return maxVelocity;
	}
	public int minMovement() {
		return minMovement;
	}
	public int maxMovement() {
		return maxMovement;
	}
}
