import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TeamNameGenerator
{
	private ArrayList<Location> location;
	private ArrayList<String> teamNames;
	private Location recentLocation;
	private String recentName;
	private boolean state;
	public TeamNameGenerator()
	{
		locations();
		teamNames();
		recentLocation = null;
		recentName = "";
		state = false;
	}
	private void teamNames()
	{
		teamNames = new ArrayList<String>();
		teamNames.add("Eagles");
		teamNames.add("Tigers");
		teamNames.add("Bulldogs");
		teamNames.add("Panthers");
		teamNames.add("Wildcats");
		teamNames.add("Warriors");
		teamNames.add("Lions");
		teamNames.add("Indians");
		teamNames.add("Cougars");
		teamNames.add("Knights");
		teamNames.add("Mustangs");
		teamNames.add("Falcons");
		teamNames.add("Trojans");
		teamNames.add("Cardinals");
		teamNames.add("Vikings");
		teamNames.add("Pirates");
		teamNames.add("Raiders");
		teamNames.add("Rams");
		teamNames.add("Spartans");
		teamNames.add("Bears");
		teamNames.add("Hornets");
		teamNames.add("Patriots");
		teamNames.add("Hawks");
		teamNames.add("Crusaders");
		teamNames.add("Rebels");
		teamNames.add("Bobcats");
		teamNames.add("Saints");
		teamNames.add("Braves");
		teamNames.add("Blue Devils");
		teamNames.add("Titans");
		teamNames.add("Wolverines");
		teamNames.add("Jaguars");
		teamNames.add("Wolves");
		teamNames.add("Dragons");
		teamNames.add("Pioneers");
		teamNames.add("Chargers");
		teamNames.add("Rockets");
		teamNames.add("Huskies");
		teamNames.add("Red Devils");
		teamNames.add("Yellowjackets");
		teamNames.add("Chiefs");
		teamNames.add("Stars");
		teamNames.add("Comets");
		teamNames.add("Colts");
		teamNames.add("Lancers");
		teamNames.add("Rangers");
		teamNames.add("Broncos");
		teamNames.add("Giants");
		teamNames.add("Senators");
		teamNames.add("Bearcats");
		teamNames.add("Thunder");
		teamNames.add("Royals");
		teamNames.add("Storm");
		teamNames.add("Cowboys");
		teamNames.add("Cubs");
		teamNames.add("Cavaliers");
		teamNames.add("Golden Eagles");
		teamNames.add("Generals");
		teamNames.add("Owls");
		teamNames.add("Buccaneers");
		teamNames.add("Hurricanes");
		teamNames.add("Bruins");
		teamNames.add("Grizzlies");
		teamNames.add("Gators");
		teamNames.add("Bombers");
		teamNames.add("Red Raiders");
		teamNames.add("Flyers");
		teamNames.add("Lakers");
		teamNames.add("Miners");
		teamNames.add("Redskins");
		teamNames.add("Coyotes");
		teamNames.add("Longhorns");
		teamNames.add("Greyhounds");
		teamNames.add("Beavers");
		teamNames.add("Yellow Jackets");
		teamNames.add("Outlaws");
		teamNames.add("Reds");
		teamNames.add("Highlanders");
		teamNames.add("Sharks");
		teamNames.add("Oilers");
		teamNames.add("Jets");
		teamNames.add("Dodgers");
		teamNames.add("Mountaineers");
		teamNames.add("Red Sox");
		teamNames.add("Thunderbirds");
		teamNames.add("Blazers");
		teamNames.add("Clippers");
		teamNames.add("Aces");
		teamNames.add("Buffaloes");
		teamNames.add("Lightning");
		teamNames.add("Bluejays");
		teamNames.add("Gladiators");
		teamNames.add("Mavericks");
		teamNames.add("Monarchs");
		teamNames.add("Tornadoes");
		teamNames.add("Blues");
		teamNames.add("Cobras");
		teamNames.add("Bulls");
		teamNames.add("Express");
		teamNames.add("Stallions");
	}
	private void locations()
	{
		location = new ArrayList<Location>();
		location.add(new Location("New York", "New York"));
		location.add(new Location("Los Angeles", "California"));
		location.add(new Location("Chicago", "Illinois"));
		location.add(new Location("Houston", "Texas"));
		location.add(new Location("Phoenix", "Arizona"));
		location.add(new Location("Philadelphia", "Pennsylvania"));
		location.add(new Location("San Antonio", "Texas"));
		location.add(new Location("San Diego", "California"));
		location.add(new Location("Dallas", "Texas"));
		location.add(new Location("San Jose", "California"));
		location.add(new Location("Austin", "Texas"));
		location.add(new Location("Jacksonville", "Florida"));
		location.add(new Location("San Francisco", "California"));
		location.add(new Location("Columbus", "Ohio"));
		location.add(new Location("Indianapolis", "Indiana"));
		location.add(new Location("Fort Worth", "Texas"));
		location.add(new Location("Charlotte", "North Carolina"));
		location.add(new Location("Seattle", "Washington"));
		location.add(new Location("Denver", "Colorado"));
		location.add(new Location("El Paso", "Texas"));
		location.add(new Location("Washington", "District of Columbia"));
		location.add(new Location("Boston", "Massachusetts"));
		location.add(new Location("Detroit", "Michigan"));
		location.add(new Location("Nashville", "Tennessee"));
		location.add(new Location("Memphis", "Tennessee"));
		location.add(new Location("Portland", "Oregon"));
		location.add(new Location("Oklahoma City", "Oklahoma"));
		location.add(new Location("Las Vegas", "Nevada"));
		location.add(new Location("Louisville", "Kentucky"));
		location.add(new Location("Baltimore", "Maryland"));
		location.add(new Location("Milwaukee", "Wisconsin"));
		location.add(new Location("Albuquerque", "New Mexico"));
		location.add(new Location("Tucson", "Arizona"));
		location.add(new Location("Fresno", "California"));
		location.add(new Location("Sacramento", "California"));
		location.add(new Location("Mesa", "Arizona"));
		location.add(new Location("Kansas City", "Missouri"));
		location.add(new Location("Atlanta", "Georgia"));
		location.add(new Location("Long Beach", "California"));
		location.add(new Location("Colorado Springs", "Colorado"));
		location.add(new Location("Raleigh", "North Carolina"));
		location.add(new Location("Miami", "Florida"));
		location.add(new Location("Virginia Beach", "Virginia"));
		location.add(new Location("Omaha", "Nebraska"));
		location.add(new Location("Oakland", "California"));
		location.add(new Location("Minneapolis", "Minnesota"));
		location.add(new Location("Tulsa", "Oklahoma"));
		location.add(new Location("Arlington", "Texas"));
		location.add(new Location("New Orleans", "Louisiana"));
		location.add(new Location("Wichita", "Kansas"));
		location.add(new Location("Cleveland", "Ohio"));
		location.add(new Location("Tampa", "Florida"));
		location.add(new Location("Bakersfield", "California"));
		location.add(new Location("Aurora", "Colorado"));
		location.add(new Location("Honolulu", "Hawaii"));
		location.add(new Location("Anaheim", "California"));
		location.add(new Location("Santa Ana", "California"));
		location.add(new Location("Corpus Christi", "Texas"));
		location.add(new Location("Riverside", "California"));
		location.add(new Location("Lexington", "Kentucky"));
		location.add(new Location("St. Louis", "Missouri"));
		location.add(new Location("Stockton", "California"));
		location.add(new Location("Pittsburgh", "Pennsylvania"));
		location.add(new Location("Saint Paul", "Minnesota"));
		location.add(new Location("Cincinnati", "Ohio"));
		location.add(new Location("Anchorage", "Alaska"));
		location.add(new Location("Henderson", "Nevada"));
		location.add(new Location("Greensboro", "North Carolina"));
		location.add(new Location("Plano", "Texas"));
		location.add(new Location("Newark", "New Jersey"));
		location.add(new Location("Lincoln", "Nebraska"));
		location.add(new Location("Toledo", "Ohio"));
		location.add(new Location("Orlando", "Florida"));
		location.add(new Location("Chula Vista", "California"));
		location.add(new Location("Irvine", "California"));
		location.add(new Location("Fort Wayne", "Indiana"));
		location.add(new Location("Jersey City", "New Jersey"));
		location.add(new Location("Durham", "North Carolina"));
		location.add(new Location("St. Petersburg", "Florida"));
		location.add(new Location("Laredo", "Texas"));
		location.add(new Location("Buffalo", "New York"));
		location.add(new Location("Madison", "Wisconsin"));
		location.add(new Location("Lubbock", "Texas"));
		location.add(new Location("Chandler", "Arizona"));
		location.add(new Location("Scottsdale", "Arizona"));
		location.add(new Location("Glendale", "Arizona"));
		location.add(new Location("Reno", "Nevada"));
		location.add(new Location("Norfolk", "Virginia"));
		location.add(new Location("Winston–Salem", "North Carolina"));
		location.add(new Location("North Las Vegas", "Nevada"));
		location.add(new Location("Irving", "Texas"));
		location.add(new Location("Chesapeake", "Virginia"));
		location.add(new Location("Gilbert", "Arizona"));
		location.add(new Location("Hialeah", "Florida"));
		location.add(new Location("Garland", "Texas"));
		location.add(new Location("Fremont", "California"));
		location.add(new Location("Baton Rouge", "Louisiana"));
		location.add(new Location("Richmond", "Virginia"));
		location.add(new Location("Boise", "Idaho"));
		location.add(new Location("San Bernardino", "California"));
		location.add(new Location("Spokane", "Washington"));
		location.add(new Location("Des Moines", "Iowa"));
		location.add(new Location("Modesto", "California"));
		location.add(new Location("Birmingham", "Alabama"));
		location.add(new Location("Tacoma", "Washington"));
		location.add(new Location("Fontana", "California"));
		location.add(new Location("Rochester", "New York"));
		location.add(new Location("Oxnard", "California"));
		location.add(new Location("Moreno Valley", "California"));
		location.add(new Location("Fayetteville", "North Carolina"));
		location.add(new Location("Aurora", "Illinois"));
		location.add(new Location("Glendale", "California"));
		location.add(new Location("Yonkers", "New York"));
		location.add(new Location("Huntington Beach", "California"));
		location.add(new Location("Montgomery", "Alabama"));
		location.add(new Location("Amarillo", "Texas"));
		location.add(new Location("Little Rock", "Arkansas"));
		location.add(new Location("Akron", "Ohio"));
		location.add(new Location("Columbus", "Georgia"));
		location.add(new Location("Augusta", "Georgia"));
		location.add(new Location("Grand Rapids", "Michigan"));
		location.add(new Location("Shreveport", "Louisiana"));
		location.add(new Location("Salt Lake City", "Utah"));
		location.add(new Location("Huntsville", "Alabama"));
		location.add(new Location("Mobile", "Alabama"));
		location.add(new Location("Tallahassee", "Florida"));
		location.add(new Location("Grand Prairie", "Texas"));
		location.add(new Location("Overland Park", "Kansas"));
		location.add(new Location("Knoxville", "Tennessee"));
		location.add(new Location("Port St. Lucie", "Florida"));
		location.add(new Location("Worcester", "Massachusetts"));
		location.add(new Location("Brownsville", "Texas"));
		location.add(new Location("Tempe", "Arizona"));
		location.add(new Location("Santa Clarita", "California"));
		location.add(new Location("Newport News", "Virginia"));
		location.add(new Location("Cape Coral", "Florida"));
		location.add(new Location("Providence", "Rhode Island"));
		location.add(new Location("Fort Lauderdale", "Florida"));
		location.add(new Location("Chattanooga", "Tennessee"));
		location.add(new Location("Rancho Cucamonga", "California"));
		location.add(new Location("Oceanside", "California"));
		location.add(new Location("Santa Rosa", "California"));
		location.add(new Location("Garden Grove", "California"));
		location.add(new Location("Vancouver", "Washington"));
		location.add(new Location("Sioux Falls", "South Dakota"));
		location.add(new Location("Ontario", "California"));
		location.add(new Location("McKinney", "Texas"));
		location.add(new Location("Elk Grove", "California"));
		location.add(new Location("Jackson", "Mississippi"));
		location.add(new Location("Pembroke Pines", "Florida"));
		location.add(new Location("Salem", "Oregon"));
		location.add(new Location("Springfield", "Missouri"));
		location.add(new Location("Corona", "California"));
		location.add(new Location("Eugene", "Oregon"));
		location.add(new Location("Fort Collins", "Colorado"));
		location.add(new Location("Peoria", "Arizona"));
		location.add(new Location("Frisco", "Texas"));
		location.add(new Location("Cary", "North Carolina"));
		location.add(new Location("Lancaster", "California"));
		location.add(new Location("Hayward", "California"));
		location.add(new Location("Palmdale", "California"));
		location.add(new Location("Salinas", "California"));
		location.add(new Location("Alexandria", "Virginia"));
		location.add(new Location("Lakewood", "Colorado"));
		location.add(new Location("Springfield", "Massachusetts"));
		location.add(new Location("Pasadena", "Texas"));
		location.add(new Location("Sunnyvale", "California"));
		location.add(new Location("Macon", "Georgia"));
		location.add(new Location("Pomona", "California"));
		location.add(new Location("Hollywood", "Florida"));
		location.add(new Location("Kansas City", "Kansas"));
		location.add(new Location("Escondido", "California"));
		location.add(new Location("Clarksville", "Tennessee"));
		location.add(new Location("Joliet", "Illinois"));
		location.add(new Location("Rockford", "Illinois"));
		location.add(new Location("Torrance", "California"));
		location.add(new Location("Naperville", "Illinois"));
		location.add(new Location("Paterson", "New Jersey"));
		location.add(new Location("Savannah", "Georgia"));
		location.add(new Location("Bridgeport", "Connecticut"));
		location.add(new Location("Mesquite", "Texas"));
		location.add(new Location("Killeen", "Texas"));
		location.add(new Location("Syracuse", "New York"));
		location.add(new Location("McAllen", "Texas"));
		location.add(new Location("Pasadena", "California"));
		location.add(new Location("Bellevue", "Washington"));
		location.add(new Location("Fullerton", "California"));
		location.add(new Location("Orange", "California"));
		location.add(new Location("Dayton", "Ohio"));
		location.add(new Location("Miramar", "Florida"));
		location.add(new Location("Thornton", "Colorado"));
		location.add(new Location("West Valley City", "Utah"));
		location.add(new Location("Olathe", "Kansas"));
		location.add(new Location("Hampton", "Virginia"));
		location.add(new Location("Warren", "Michigan"));
		location.add(new Location("Midland", "Texas"));
		location.add(new Location("Waco", "Texas"));
		location.add(new Location("Charleston", "South Carolina"));
		location.add(new Location("Columbia", "South Carolina"));
		location.add(new Location("Denton", "Texas"));
		location.add(new Location("Carrollton", "Texas"));
		location.add(new Location("Surprise", "Arizona"));
		location.add(new Location("Roseville", "California"));
		location.add(new Location("Sterling Heights", "Michigan"));
		location.add(new Location("Murfreesboro", "Tennessee"));
		location.add(new Location("Gainesville", "Florida"));
		location.add(new Location("Cedar Rapids", "Iowa"));
		location.add(new Location("Visalia", "California"));
		location.add(new Location("Coral Springs", "Florida"));
		location.add(new Location("New Haven", "Connecticut"));
		location.add(new Location("Stamford", "Connecticut"));
		location.add(new Location("Thousand Oaks", "California"));
		location.add(new Location("Concord", "California"));
		location.add(new Location("Elizabeth", "New Jersey"));
		location.add(new Location("Lafayette", "Louisiana"));
		location.add(new Location("Kent", "Washington"));
		location.add(new Location("Topeka", "Kansas"));
		location.add(new Location("Simi Valley", "California"));
		location.add(new Location("Santa Clara", "California"));
		location.add(new Location("Athens", "Georgia"));
		location.add(new Location("Hartford", "Connecticut"));
		location.add(new Location("Victorville", "California"));
		location.add(new Location("Abilene", "Texas"));
		location.add(new Location("Norman", "Oklahoma"));
		location.add(new Location("Vallejo", "California"));
		location.add(new Location("Berkeley", "California"));
		location.add(new Location("Round Rock", "Texas"));
		location.add(new Location("Ann Arbor", "Michigan"));
		location.add(new Location("Fargo", "North Dakota"));
		location.add(new Location("Columbia", "Missouri"));
		location.add(new Location("Allentown", "Pennsylvania"));
		location.add(new Location("Evansville", "Indiana"));
		location.add(new Location("Beaumont", "Texas"));
		location.add(new Location("Odessa", "Texas"));
		location.add(new Location("Wilmington", "North Carolina"));
		location.add(new Location("Arvada", "Colorado"));
		location.add(new Location("Independence", "Missouri"));
		location.add(new Location("Provo", "Utah"));
		location.add(new Location("Lansing", "Michigan"));
		location.add(new Location("El Monte", "California"));
		location.add(new Location("Springfield", "Illinois"));
		location.add(new Location("Fairfield", "California"));
		location.add(new Location("Clearwater", "Florida"));
		location.add(new Location("Peoria", "Illinois"));
		location.add(new Location("Rochester", "Minnesota"));
		location.add(new Location("Carlsbad", "California"));
		location.add(new Location("Westminster", "Colorado"));
		location.add(new Location("West Jordan", "Utah"));
		location.add(new Location("Pearland", "Texas"));
		location.add(new Location("Richardson", "Texas"));
		location.add(new Location("Downey", "California"));
		location.add(new Location("Miami Gardens", "Florida"));
		location.add(new Location("Temecula", "California"));
		location.add(new Location("Costa Mesa", "California"));
		location.add(new Location("College Station", "Texas"));
		location.add(new Location("Elgin", "Illinois"));
		location.add(new Location("Murrieta", "California"));
		location.add(new Location("Gresham", "Oregon"));
		location.add(new Location("High Point", "North Carolina"));
		location.add(new Location("Antioch", "California"));
		location.add(new Location("Inglewood", "California"));
		location.add(new Location("Cambridge", "Massachusetts"));
		location.add(new Location("Lowell", "Massachusetts"));
		location.add(new Location("Manchester", "New Hampshire"));
		location.add(new Location("Billings", "Montana"));
		location.add(new Location("Pueblo", "Colorado"));
		location.add(new Location("Palm Bay", "Florida"));
		location.add(new Location("Centennial", "Colorado"));
		location.add(new Location("Richmond", "California"));
		location.add(new Location("Ventura", "California"));
		location.add(new Location("Pompano Beach", "Florida"));
		location.add(new Location("North Charleston", "South Carolina"));
		location.add(new Location("Everett", "Washington"));
		location.add(new Location("Waterbury", "Connecticut"));
		location.add(new Location("West Palm Beach", "Florida"));
		location.add(new Location("Boulder", "Colorado"));
		location.add(new Location("West Covina", "California"));
		location.add(new Location("Broken Arrow", "Oklahoma"));
		location.add(new Location("Clovis", "California"));
		location.add(new Location("Daly City", "California"));
		location.add(new Location("Lakeland", "Florida"));
		location.add(new Location("Santa Maria", "California"));
		location.add(new Location("Norwalk", "California"));
		location.add(new Location("Sandy Springs", "Georgia"));
		location.add(new Location("Hillsboro", "Oregon"));
		location.add(new Location("Green Bay", "Wisconsin"));
		location.add(new Location("Tyler", "Texas"));
		location.add(new Location("Wichita Falls", "Texas"));
		location.add(new Location("Lewisville", "Texas"));
		location.add(new Location("Burbank", "California"));
		location.add(new Location("Greeley", "Colorado"));
		location.add(new Location("San Mateo", "California"));
		location.add(new Location("El Cajon", "California"));
		location.add(new Location("Jurupa Valley", "California"));
		location.add(new Location("Rialto", "California"));
		location.add(new Location("Davenport", "Iowa"));
		location.add(new Location("League City", "Texas"));
		location.add(new Location("Edison", "New Jersey"));
		location.add(new Location("Davie", "Florida"));
		location.add(new Location("Las Cruces", "New Mexico"));
		location.add(new Location("South Bend", "Indiana"));
		location.add(new Location("Vista", "California"));
		location.add(new Location("Woodbridge", "New Jersey"));
		location.add(new Location("Renton", "Washington"));
		location.add(new Location("Lakewood", "New Jersey"));
		location.add(new Location("San Angelo", "Texas"));
		location.add(new Location("Clinton", "Michigan"));
		
	}
	public String getName()
	{
		Random r = new Random();
		int locationNum = r.nextInt(location.size() * location.size());
		locationNum = (location.size()-1) - (int)Math.floor(Math.sqrt(locationNum));
		int teamNum = r.nextInt(teamNames.size()*teamNames.size());
		teamNum = (teamNames.size()-1)-(int)Math.floor(Math.sqrt(teamNum));
		String name =  location.get(locationNum).getLocation() + " " + teamNames.get(teamNum);
		System.out.println(name);
		recentLocation = location.get(locationNum);
		recentName = teamNames.get(teamNum);
		state = location.get(locationNum).isState();
		return name;
	}
	public Location getRecentLocation()
	{
		return recentLocation;
	}
	public String getRecentName()
	{
		return recentName;
	}
	public boolean usedState()
	{
		return state;
	}
	public String[] getRelatedName(Location recentLocation2, String recentName2, boolean state)
	{
		String stateOfTeam = recentLocation2.state;
		ArrayList<String> temp = new ArrayList<String>();
		
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < this.teamNames.size();i++)
		{
			if(!recentName2.equals(teamNames.get(i)))names.add(teamNames.get(i));
		}
		Random r = new Random();
		
		int teamNum = r.nextInt(names.size()*names.size());
		teamNum = (names.size()-1)-(int)Math.floor(Math.sqrt(teamNum));
		String name =  names.get(teamNum);
		names.remove(name);
		
		temp.add(stateOfTeam + " " + name);
		if(!state)
		{
			temp.add(stateOfTeam + " " + recentName2);
		}
		else
		{
			temp.add(recentLocation2.city + " " + recentName2);
		}
		
		ArrayList<String> theLocations = new ArrayList<String>();
		for(int i = 0; i < location.size(); i++)
		{
			if(!location.get(i).equals(recentLocation2) && stateOfTeam.equals(location.get(i).state))
				theLocations.add(location.get(i).city);
		}
		if(theLocations.size() == 0)
		{
			teamNum = r.nextInt(names.size()*names.size());
			teamNum = (names.size()-1)-(int)Math.floor(Math.sqrt(teamNum));
			name =  names.get(teamNum);
			names.remove(name);
			
			temp.add(recentLocation2.city + " " + name);
			
		}
		else
		{
			teamNum = r.nextInt(names.size()*names.size());
			teamNum = (names.size()-1)-(int)Math.floor(Math.sqrt(teamNum));
			name =  names.get(teamNum);
			
			int locationNum = r.nextInt(theLocations.size() * theLocations.size());
			locationNum = (theLocations.size()-1) - (int)Math.floor(Math.sqrt(locationNum));
			
			temp.add(theLocations.get(locationNum) + " " + name);
		}
		
		Collections.shuffle(temp);
		
		for(int i = 0; i < 3;i++)System.out.println("\t" + temp.get(i));
		return temp.toArray(new String[3]);
	}
	
}
