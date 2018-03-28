
public class pitcherPitchRatings
{
	private double velocity, control, movement;
	private double groundBallPercentage;
	public pitcherPitchRatings(double velocity, double control, double movement, double groundBallPercentage)
	{
		this.velocity = velocity;
		this.control = control;
		this.movement = movement;
		this.groundBallPercentage = groundBallPercentage;
	}
	public double getGroundBallPercentage()
	{
		return groundBallPercentage;
	}
	public double getVelocity()
	{
		return velocity;
	}
	public double getControl()
	{
		return control;		
	}
	public double getMovement()
	{
		return movement;
	}
}
