package BusinessLogic;

import java.sql.SQLException;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import DataBase.Oracle_DataBase;

@Entity
@Table(name = "aattendance")
public class Attendance 
{
	String date;
	@Id
	int AttendanceId;
	
	Attendance(String date1,int id) throws SQLException
	{
		date = date1;
		AttendanceId=id;
		
		
		System.out.println(this.AttendanceId);
		Oracle_DataBase.addAttendance(this);
	}
	public Attendance()
	{
		
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setAttendanceId(int attendanceId) {
		AttendanceId = attendanceId;
	}
	public String getDate()
	{
		return date;
	}
	public int getAttendanceId()
	{
		return AttendanceId;
	}
	
	
}