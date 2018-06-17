import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class NonConferenceGenerator
{
	public ArrayList<Pair[]> getGames()
	{
		Random r = new Random();
		boolean problem = true;
		ArrayList<Pair[]> retVal = null;
		while(problem)
		{
			problem = false;
			retVal = new ArrayList<Pair[]>();

			//NUM GAMES = 14

			for(int i = 0; i < 14; i++)
			{
				retVal.add(new Pair[70]);
			}



			boolean[][] previouslyPlayed = new boolean[140][140];
			for(int i = 0; i < previouslyPlayed.length; i++)
			{
				for(int j = 0; j < previouslyPlayed[i].length;j++)
				{
					if(i/14 == j/14)
					{
						previouslyPlayed[i][j] = true;
					}
				}
			}
			for(int i = 0; i < retVal.size(); i++)
			{
				ArrayList<Integer> teamsYetToPlay = count(140);
				Collections.shuffle(teamsYetToPlay);
				for(int j = 0; j < retVal.get(i).length; j++)
				{
					int firstTeam = teamsYetToPlay.remove(0);
					int secondTeam;
					int counter = 0;
					while(true)
					{
						secondTeam = teamsYetToPlay.get(r.nextInt(teamsYetToPlay.size()));
						if(!previouslyPlayed[firstTeam][secondTeam])break;
						counter++;
						if(counter == 10000)break;
					}
					if(counter == 10000)
					{
						problem = true;
						break;
					}
					previouslyPlayed[firstTeam][secondTeam] = true;
					previouslyPlayed[secondTeam][firstTeam] = true;
					retVal.get(i)[j] = new Pair(firstTeam, secondTeam);
				}
				if(problem)break;
			}
		}



		return retVal;
	}

	private ArrayList<Integer> count(int limit)
	{
		ArrayList<Integer> retVal = new ArrayList<Integer>();
		for(int i = 0; i < limit; i++)
		{
			retVal.add(i);
		}
		return retVal;
	}
}
