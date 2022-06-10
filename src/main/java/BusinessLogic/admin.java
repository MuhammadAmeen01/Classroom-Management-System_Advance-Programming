package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import CustomExceptions.FileNotFound;
import DataBase.FileHandling;
import DataBase.Oracle_DataBase;

@Entity
@Table(name = "aadmin")

public class admin extends teacher
{
	int admin_id;
	int count;
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public admin() {
		
		
		
	}

	public admin(int i,String name, String email,String password) {
		
		super(i,name,email,password);
		admin_id=i;
		count++;
	
	}
	
	
	
	
	
	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	//------------------------------------------------------------------
	
						//ADD TEACHER
	
	//------------------------------------------------------------------
	
	public teacher addTeacher(String email) throws SQLException, FileNotFound {
		teacher temp=Oracle_DataBase.getTeacherAcc(email);
		//c=new teacher(count,name,email,"123");
		count++;
		//Oracle_DataBase db=new Oracle_DataBase();
		if(temp!=null) {
			Oracle_DataBase.addFaculty(temp);
			FileHandling.addFaculty(temp);
		}
		
		return temp;	
	}
	
	
	//------------------------------------------------------------------
							//REMOVE TEACHER
	//------------------------------------------------------------------
	public ArrayList<teacher> rmvTeacher(String email,ArrayList<teacher>allTeachers) throws SQLException {
		
		for(int i=0;i<allTeachers.size();i++) {
			
			if(email.equals(allTeachers.get(i).getTeacher_email())){
				System.out.println(email);
				allTeachers.remove(i);
				Oracle_DataBase.removeTeacher(email);
				
			}//if
			
		}//for
		return allTeachers;
		
	}
		
	
	
	//------------------------------------------------------------------
		
						//CREATE CLASSROOM
	
	//------------------------------------------------------------------
	public CourseClassroom createClassroom(int course_id) {
		
		CourseClassroom temp= new CourseClassroom(course_id,this);
		return temp;
		
	}
	
	public CourseClassroom createClassroom(int course_id,String name) {
		
		CourseClassroom temp= new CourseClassroom(course_id,name,this);
		Oracle_DataBase.addCourseClassroom(temp);
		return temp;
		
	}
	
	
	
}
