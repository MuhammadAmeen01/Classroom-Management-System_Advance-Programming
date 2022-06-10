package BusinessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aannouncement")
public class Announcement {
	
	@Id
	private int announccement_id;
	private String text;
	
	public Announcement() 
	{
		
	}
	
	public Announcement(int count,String desc) 
	{
		announccement_id=count;
		setText(desc);
	}
	//getter setter
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getAnnounccement_id() {
		return announccement_id;
	}

	public void setAnnounccement_id(int announccement_id) {
		this.announccement_id = announccement_id;
	}
	
}
