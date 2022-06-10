package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import DataBase.Oracle_DataBase;

@Entity
@Table(name = "sstuddent")
public class Student 
{
	@Id
	private int std_id;
	private String name;
	private String email;
	private String password;
	public Student(){
		name="";
		email="";
		password="";

		
		
	}
	public Student(int id,String n,String e,String p) throws SQLException{
		
		name=n;
		std_id=id;
		email=e;
		password=p;
		Oracle_DataBase.addStudent(this);
		

		
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setStudent_id(int rol)
	{
		std_id=rol;
	}
	public void setStudentName(String n)
	{
		name=n;
	}
	
	
	public int getStudent_id()
	{
		return std_id;
	}
	public String getStudentName()
	{
		return name;
	}

	void getAttendanceList(String date_id)
	{
		Attendance a = AttendanceCatalog.getAttendance(date_id);
	}
	
	
   
}