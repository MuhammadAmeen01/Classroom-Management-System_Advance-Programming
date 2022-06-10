package UI;

import BusinessLogic.Student;
import BusinessLogic.teacher;

public final class Theme {
	
	
	//private  String Tname;
	private String feedBG;
	private String AllBG;
		//false is light theme
	boolean currentTheme;
	private final static Theme INSTANCE = new Theme();
	//private String Tmail;

	public Theme() {
		// TODO Auto-generated constructor stub
		feedBG="-fx-background-color: mintcream; -fx-background-radius: 15";
		AllBG="-fx-background-color: gainsboro; -fx-background-radius: 15";
		currentTheme=false;	
	}

	public String getFeedBG() {
		return feedBG;
	}

	public void setFeedBG(String feedBG) {
		this.feedBG = feedBG;
	}

	public String getAllBG() {
		return AllBG;
	}

	public void setAllBG(String allBG) {
		AllBG = allBG;
	}

	public static Theme getInstance() {
		return INSTANCE;
	}
	public void setTheme() {
		if(this.currentTheme==false) {
			
			feedBG="-fx-background-color: mintcream; -fx-background-radius: 15";
			AllBG="-fx-background-color: gainsboro; -fx-background-radius: 15";
			this.currentTheme=true;
			
		}
		else if(this.currentTheme==true) {
			
			feedBG="-fx-background-color: black; -fx-background-radius: 15";
			AllBG="-fx-background-color: grey; -fx-background-radius: 15";
			this.currentTheme=false;
		}
		
		
		
	}

	
	
	
	

}
