
public class Inning
{
    private int full, part;
    public Inning(int full, int part)
    {
	this.full = full;
	this.part = part;
    }
    public String toString()
    {
	return "" + full + "." + part;
    }
}
