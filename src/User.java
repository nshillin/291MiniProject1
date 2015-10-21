
public class User {
	private String userName;
	private Boolean airlineAgent = true;
	
	public User(String name)
	{
		userName = name;
		airlineAgent = false;
	}
	
	public Boolean IsAirlineAgent(){
		return airlineAgent;
	}

}
