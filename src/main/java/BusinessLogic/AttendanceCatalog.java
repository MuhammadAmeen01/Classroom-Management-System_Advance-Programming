package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import CustomExceptions.FileNotFound;
import DataBase.FileHandling;
import DataBase.Oracle_DataBase;

public class AttendanceCatalog {
    
	static ArrayList<Attendance>AttendanceCat=new ArrayList<Attendance>();;
	public AttendanceCatalog() throws SQLException {
		
		
		AttendanceCat=Oracle_DataBase.getAttendance();
		//data  database se aaega
		//AttendanceCat=AttendanceCat.getAttendance();
		
		
		// TODO Auto-generated constructor stub
	}

	public static Attendance getAttendance(String date_id)
	 {
		for(int i=0; i<AttendanceCat.size(); i++)
		{
			if(AttendanceCat.get(i).date.equalsIgnoreCase(date_id))
			{
				return AttendanceCat.get(i);
			}
			
			
		}
		return null;
		 
	 }
	 public static void addAttendance(Attendance att) throws FileNotFound
	 {
		 AttendanceCat.add(att);
		 Oracle_DataBase.addAttendance(att);
		 FileHandling.addAttendance(att);
	 }
	
	
}






























