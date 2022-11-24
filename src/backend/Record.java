package backend;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Record implements Serializable {
	private String playerName;
	private Time time;
	
	public Record(String name, Time t) {
		playerName = name;
		time = t;	
	}
	
	public String getName() {return playerName;}
	public Time getTime() {return time;}
	
//	public void setName() {}
//	public void setTime() {}
}
