package BusinessLogic;

import java.sql.SQLException;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import CustomExceptions.FileNotFound;
import DataBase.FileHandling;
import DataBase.Oracle_DataBase;

@Entity
@Table(name = "aattendanceitem")
public class AttendanceItem 
{
	@Id
	int std_id;
	int coursecode;
	int date_id;
	int presence;

	
	
	public AttendanceItem(int std,int courseCode,int dateId,int presence1) throws SQLException, FileNotFound
	{
	
		std_id=std;
		coursecode=courseCode;
		date_id=dateId;
		presence=presence1;
		Oracle_DataBase.addAttendanceItem(this);
		FileHandling.addAttendanceItem(this);
		
		
	}
	public AttendanceItem() throws SQLException
	{
	
		
		
	}
	public int getstd_id() {
		return std_id;
	}
	public void setstd_id(int item)
	{
		std_id=item;
	}
	public int getcoursecode() {
		return coursecode;
	}
	public void setcoursecode(int item)
	{
		coursecode=item;
	}
	public int getdate_id() {
	return date_id;	
	}
	public void setdate_id(int item)
	{
		date_id=item;
	}
	
	
	public int getpresence() {
		return presence;
	}
	public void setpresence(int item)
	{
		presence=item;
	}
	

}