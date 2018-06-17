import java.io.File;

public class FileUtils
{
	public static void cleanDirectory(String directoryName)
	{
		File[] files = new File(directoryName).listFiles();
		
		for(int i = 0; i < files.length; i++)
		{
			files[i].delete();
		}
	}
}
