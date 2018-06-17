import java.util.Random;

public class CollegePlayerGenerator
{
	private static CollegePlayerGenerator myObj;
	private Random r;
	private CollegePlayerGenerator()
	{
		r = new Random();
	}

	public static CollegePlayerGenerator getInstance()
	{
		if(myObj == null)
		{
			myObj = new CollegePlayerGenerator();
		}
		return myObj;
	}

	public pitcher generateStartingPitcher(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleStarter(age, redShirt, year);
			case 2:
				return generatePoorStarter(age, redShirt, year);
			case 3:
				return generateBelowAverageStarter(age, redShirt, year);
			case 4:
				return generateAverageStarter(age, redShirt, year);
			case 5:
				return generateAboveAverageStarter(age, redShirt, year);
			case 6:
				return generateGoodStarter(age, redShirt, year);
			case 7:
				return generateEliteStarter(age, redShirt, year);
		}		
			return null;
	}

	private pitcher generateEliteStarter(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateGoodStarter(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateAboveAverageStarter(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateAverageStarter(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateBelowAverageStarter(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generatePoorStarter(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateTerribleStarter(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public pitcher generateReliefPitcher(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleReliever(age, redShirt, year);
			case 2:
				return generatePoorReliever(age, redShirt, year);
			case 3:
				return generateBelowAverageReliever(age, redShirt, year);
			case 4:
				return generateAverageReliever(age, redShirt, year);
			case 5:
				return generateAboveAverageReliever(age, redShirt, year);
			case 6:
				return generateGoodReliever(age, redShirt, year);
			case 7:
				return generateEliteReliever(age, redShirt, year);
		}
		return null;
	}

	private pitcher generateEliteReliever(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateGoodReliever(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateAboveAverageReliever(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateAverageReliever(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateBelowAverageReliever(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generatePoorReliever(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private pitcher generateTerribleReliever(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public player generateFirstBase(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleFirstBase(age, redShirt, year);
			case 2:
				return generatePoorFirstBase(age, redShirt, year);
			case 3:
				return generateBelowAverageFirstBase(age, redShirt, year);
			case 4:
				return generateAverageFirstBase(age, redShirt, year);
			case 5:
				return generateAboveAverageFirstBase(age, redShirt, year);
			case 6:
				return generateGoodFirstBase(age, redShirt, year);
			case 7:
				return generateEliteFirstBase(age, redShirt, year);
		}
		return null;
	}

	private player generateEliteFirstBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateGoodFirstBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAboveAverageFirstBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAverageFirstBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateBelowAverageFirstBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generatePoorFirstBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateTerribleFirstBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public player generateSecondBase(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleSecondBase(age, redShirt, year);
			case 2:
				return generatePoorSecondBase(age, redShirt, year);
			case 3:
				return generateBelowAverageSecondBase(age, redShirt, year);
			case 4:
				return generateAverageSecondBase(age, redShirt, year);
			case 5:
				return generateAboveAverageSecondBase(age, redShirt, year);
			case 6:
				return generateGoodSecondBase(age, redShirt, year);
			case 7:
				return generateEliteSecondBase(age, redShirt, year);
		}
		return null;
	}
	private player generateEliteSecondBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateGoodSecondBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAboveAverageSecondBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAverageSecondBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateBelowAverageSecondBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generatePoorSecondBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateTerribleSecondBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}
	public player generateThirdBase(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleThirdBase(age, redShirt, year);
			case 2:
				return generatePoorThirdBase(age, redShirt, year);
			case 3:
				return generateBelowAverageThirdBase(age, redShirt, year);
			case 4:
				return generateAverageThirdBase(age, redShirt, year);
			case 5:
				return generateAboveAverageThirdBase(age, redShirt, year);
			case 6:
				return generateGoodThirdBase(age, redShirt, year);
			case 7:
				return generateEliteThirdBase(age, redShirt, year);
		}
		return null;
	}
	private player generateEliteThirdBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateGoodThirdBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAboveAverageThirdBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAverageThirdBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateBelowAverageThirdBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generatePoorThirdBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateTerribleThirdBase(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}
	public player generateShortStop(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleShortStop(age, redShirt, year);
			case 2:
				return generatePoorShortStop(age, redShirt, year);
			case 3:
				return generateBelowAverageShortStop(age, redShirt, year);
			case 4:
				return generateAverageShortStop(age, redShirt, year);
			case 5:
				return generateAboveAverageShortStop(age, redShirt, year);
			case 6:
				return generateGoodShortStop(age, redShirt, year);
			case 7:
				return generateEliteShortStop(age, redShirt, year);
		}
		return null;
	}
	private player generateEliteShortStop(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateGoodShortStop(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAboveAverageShortStop(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAverageShortStop(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateBelowAverageShortStop(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generatePoorShortStop(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateTerribleShortStop(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public player generateCatcher(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleCatcher(age, redShirt, year);
			case 2:
				return generatePoorCatcher(age, redShirt, year);
			case 3:
				return generateBelowAverageCatcher(age, redShirt, year);
			case 4:
				return generateAverageCatcher(age, redShirt, year);
			case 5:
				return generateAboveAverageCatcher(age, redShirt, year);
			case 6:
				return generateGoodCatcher(age, redShirt, year);
			case 7:
				return generateEliteCatcher(age, redShirt, year);
		}
		return null;
	}
	private player generateEliteCatcher(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateGoodCatcher(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAboveAverageCatcher(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAverageCatcher(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateBelowAverageCatcher(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generatePoorCatcher(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateTerribleCatcher(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public player generateCenterFielder(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleCenterFielder(age, redShirt, year);
			case 2:
				return generatePoorCenterFielder(age, redShirt, year);
			case 3:
				return generateBelowAverageCenterFielder(age, redShirt, year);
			case 4:
				return generateAverageCenterFielder(age, redShirt, year);
			case 5:
				return generateAboveAverageCenterFielder(age, redShirt, year);
			case 6:
				return generateGoodCenterFielder(age, redShirt, year);
			case 7:
				return generateEliteCenterFielder(age, redShirt, year);
		}
		return null;
	}
	private player generateEliteCenterFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateGoodCenterFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAboveAverageCenterFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAverageCenterFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateBelowAverageCenterFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generatePoorCenterFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateTerribleCenterFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public player generateRightFielder(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleRightFielder(age, redShirt, year);
			case 2:
				return generatePoorRightFielder(age, redShirt, year);
			case 3:
				return generateBelowAverageRightFielder(age, redShirt, year);
			case 4:
				return generateAverageRightFielder(age, redShirt, year);
			case 5:
				return generateAboveAverageRightFielder(age, redShirt, year);
			case 6:
				return generateGoodRightFielder(age, redShirt, year);
			case 7:
				return generateEliteRightFielder(age, redShirt, year);
		}
		return null;
	}
	private player generateEliteRightFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateGoodRightFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAboveAverageRightFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAverageRightFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateBelowAverageRightFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generatePoorRightFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateTerribleRightFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public player generateLeftFielder(int type, boolean firstTime)
	{
		int age = 18;
		boolean redShirt = false;
		int year = 0;
		if(firstTime)
		{
			year = r.nextInt(4);
			if(r.nextBoolean() && r.nextBoolean())
			{
				redShirt = true;
				year++;
			}
			age += year;
		}

		switch(type)
		{
			case 1:
				return generateTerribleLeftFielder(age, redShirt, year);
			case 2:
				return generatePoorLeftFielder(age, redShirt, year);
			case 3:
				return generateBelowAverageLeftFielder(age, redShirt, year);
			case 4:
				return generateAverageLeftFielder(age, redShirt, year);
			case 5:
				return generateAboveAverageLeftFielder(age, redShirt, year);
			case 6:
				return generateGoodLeftFielder(age, redShirt, year);
			case 7:
				return generateEliteLeftFielder(age, redShirt, year);
		}
		return null;
	}
	private player generateEliteLeftFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateGoodLeftFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAboveAverageLeftFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateAverageLeftFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateBelowAverageLeftFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generatePoorLeftFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private player generateTerribleLeftFielder(int age, boolean redShirt, int year)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
