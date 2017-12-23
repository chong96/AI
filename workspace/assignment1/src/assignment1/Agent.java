package assignment1;

public class Agent {

	private String location;

	public Agent() {
		
	}
	
	public void execute(String location, Environment environment) {

		if(location == "Left" && environment.isDirty(location)) {
			environment.setLeft();
			System.out.println("Cleaning...Cleaned.");
			System.out.println("Score +5");
			System.out.println("Moving Right");
			System.out.println("Score -1\n");
			location = "Right";
			this.location = "Right";

		} else if (location == "Right" && environment.isDirty(location)) { 
			environment.setRight();
			System.out.println("Cleaning...Cleaned.");
			System.out.println("Score +5");
			System.out.println("Moving Left");
			System.out.println("Score -1\n");
			location = "Left";
			this.location = "Left";

		} else if (location == "Left" && !environment.isDirty(location)) {
			System.out.println("Already Clean...Moving Right.");
			System.out.println("Score -1\n");
			location = "Right";
			this.location = "Right";
			
		} else {
			System.out.println("Already Clean...Moving Left.");
			System.out.println("Score -1\n");
			location = "Left";
			this.location = "Left";
		}
		
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
}
