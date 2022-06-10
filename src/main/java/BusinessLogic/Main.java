package BusinessLogic;

import java.sql.SQLException;

import DataBase.FileHandling;
import DataBase.Oracle_DataBase;
import DataBase.PersistanceManager;

public class Main {
		
	public CourseClassroom c;
	public admin a;
	
	public Main() throws ClassNotFoundException, SQLException{
		FileHandling fh = new FileHandling();
		Oracle_DataBase db=new Oracle_DataBase();
		c=null;
		c=Oracle_DataBase.getmyClass();
		
		a=null;
		a=Oracle_DataBase.getAdmin();
		//System.out.print(a.teacher_email) ;
		if(a!=null) {
		c.setAdmin(a);
		}
		
	}
	
	public void addAdmin(String name,String email,String password) throws SQLException {
		Oracle_DataBase db=new Oracle_DataBase();
		a=new admin(0,name,email,password);
		Oracle_DataBase.addAdmin(a);
		
	}
	

	public static void main(String[] args) throws SQLException 
	{
		PersistanceManager obj = new Oracle_DataBase();
		// TODO Auto-generated method stub
		CourseClassroom c=null;
		admin a1=new admin();
		//a1.addTeacher("Gulsher", "gulsherkhan0707@gmail.com");
	}

}
