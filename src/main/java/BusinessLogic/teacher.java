package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

import CustomExceptions.FileNotFound;
import DataBase.Oracle_DataBase;

@MappedSuperclass
@Table(name = "tteacher")
public class teacher
{
	
			//Attributes
	@Id
	int id;
	String teacher_name;
	String teacher_email;
	String password;
	int teacher_status;
	@Transient
	static int att_id;
	
//	@Column
//    @ElementCollection(targetClass=AttendanceItem.class)
	ArrayList<AttendanceItem> Attend = new ArrayList<AttendanceItem>();

		//	Attributes
		
		
		//Constructors
	public teacher() 
	{
			
			
			
	}
		
	public teacher(String name,String email,String password) 
	{
		this.id=0;
		teacher_name=name;								//Parametrized
		teacher_email=email;
		this.password=password;
		att_id=0;
	}
		
	public teacher(int id,String name,String email,String password) 
	{
		this.id=id;
		teacher_name=name;								//Parametrized
		teacher_email=email;
		this.password=password;
		att_id=0;
			
	}
		
	public teacher(String name,String email) 
	{	
		teacher_name=name;								//Parametrized
		teacher_email=email;
		att_id=0;		
	}
		
	public teacher(int id,String name,String email,String password,int stat) 
	{
		this.id=id;
		teacher_name=name;								//Parametrized
		teacher_email=email;
		this.password=password;
		teacher_status=stat;
		att_id=0;
	}	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getTeacher_name() 
	{
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) 
	{
		this.teacher_name = teacher_name;
	}

	public String getTeacher_email()
	{
			return teacher_email;
		}

		public void setTeacher_email(String teacher_email) {
			this.teacher_email = teacher_email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getTeacher_status() {
			return teacher_status;
		}

		public void setTeacher_status(int teacher_status) {
			this.teacher_status = teacher_status;
		}

		public Announcement addAnnouncments(int c,String text) 
		{
			
			Announcement temp=new Announcement(c,text);
			Oracle_DataBase.addAnnouncement(temp);

			return temp;
			
			
		}
		
		
		
		Attendance CreateAttendance(String date) throws SQLException, FileNotFound
		{
			int check=0;
			for(int i=0; i<AttendanceCatalog.AttendanceCat.size();i++)
			{
				if(AttendanceCatalog.AttendanceCat.get(i).date.equals(date))
				{
					check=1;
					break;
				}
			}
			
			if(check==0)
			{
			System.out.print("Creating Attendance\n");
			att_id=AttendanceCatalog.AttendanceCat.size();
			
			System.out.println("Date: "+att_id+1);
			Attendance att1=new Attendance(date,att_id+1);
			AttendanceCatalog.addAttendance(att1);
			return att1;
			}
			else
			{
				return null;
			}
		}
		ArrayList<AttendanceItem> MarkAttendance(Attendance att,int courseCode,ArrayList<Student>s,ArrayList<Integer>check) throws SQLException, FileNotFound
		{
			
			for(int i=0; i<s.size(); i++)
			{
				AttendanceItem att1=new AttendanceItem(s.get(i).getStudent_id(),courseCode,att.AttendanceId,check.get(i).intValue());
				Attend.add(att1);
			}
			
			
			
			return Attend;
			
			
			
			
		}
		
		//////////////////////////new ftn to add assessment
		public ArrayList<AssessmentItem> addAssessment(ArrayList<Student> students,String assign_date,String due_date,int total_marks,int weightage, String text) throws FileNotFound
		{
			ArrayList<AssessmentItem> list = new ArrayList<AssessmentItem>();
			int ID = AssessmentCatalog.obj.size();
			
			Assessment obj = new Assessment(ID, assign_date, due_date, total_marks, weightage, text);
			AssessmentCatalog.addAssessment(obj);
			
			for(int i=0;i<students.size();i++)
			{
				AssessmentItem newItem = new AssessmentItem(students.get(i).getStudent_id(),ID);
				list.add(newItem);
			}
			
			Oracle_DataBase.addAssessmentItem(list);
			
			return list;
			
		}
		
		void UpdateAttendance(Attendance att,ArrayList<Student>s,ArrayList<Integer>check) throws SQLException
		{
		
			for(int i=0; i<s.size(); i++)
			{
				
				System.out.println("Presence: "+check.get(i).intValue()+"StudentID: "+s.get(i).getStudent_id()+"  " +"Attendacneid:"+att.AttendanceId);
			   Oracle_DataBase.updateAttendanceitem(s.get(i).getStudent_id(),att.AttendanceId,check.get(i).intValue());   
			}
			
			
			
		//	return Attend;
			
			
			
			
		}
		
		
		
		
		

		public static void main(String[] args) {
			

		}//Main

	}//Class


