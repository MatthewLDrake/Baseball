import java.util.ArrayList;
public class CollegeConference extends ArrayList<CollegeTeam>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	public CollegeConference(String name)
	{
		this.name = name;
	}
	public String toString()
	{
		return name;
	}
}
