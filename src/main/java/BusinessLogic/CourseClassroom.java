package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import CustomExceptions.FileNotFound;
import DataBase.Oracle_DataBase;

@Entity
@Table(name = "ccourseclassroom")
public class CourseClassroom 
{
	@Id
	private int course_id;
	private String Cname;
	@Transient
	private admin Admin;
	@Transient
	static int x;
	@Transient
	static int announce_count;
	@Transient
	static int std_y;
	
	@Transient
	ArrayList<Student> students = new ArrayList<Student>();
	@Transient
	ArrayList<teacher> teachers = new ArrayList<teacher>();
	
	@Transient
	ArrayList<AssessmentItem> quiz = new ArrayList<AssessmentItem>();
	@Transient
	ArrayList<AssessmentItem> assignment = new ArrayList<AssessmentItem>();
	@Transient
	ArrayList<AttendanceItem> attendance=new ArrayList<AttendanceItem>();
	
	@Transient
	AttendanceCatalog ac;
	
	@Transient
	ArrayList<Announcement> announ = new ArrayList<Announcement>();
 	
	//constructors
	public CourseClassroom() throws ClassNotFoundException, SQLException
	{
		if(Oracle_DataBase.getmyClass() != null)
		{
			Admin = Oracle_DataBase.getAdmin();
			students = Oracle_DataBase.getStudentListDataBase();
			teachers = Oracle_DataBase.getTeachers();
			
			announ = Oracle_DataBase.getAnnouncementList();
			
			AttendanceCatalog.AttendanceCat = Oracle_DataBase.getAttendance();
			
			attendance = Oracle_DataBase.getAttendanceItem();
			
			AssessmentCatalog.obj = Oracle_DataBase.getAssessment();
			
			assignment = Oracle_DataBase.getAssignmentItem();
			quiz = Oracle_DataBase.getQuizItem();
			
			
		}
	}
	
	public CourseClassroom(int courseId,admin a)
	{
		x=0;
		std_y=0;
		Admin = a;
		course_id = courseId;
		Cname="myClassroom";
	}
	
	public CourseClassroom(int courseId,String n,admin a)
	{
		x=0;
		std_y=0;
		Admin = a;
		course_id = courseId;
		Cname=n;
	}
	
	public CourseClassroom(int courseId,String name) throws ClassNotFoundException, SQLException{
		x=Oracle_DataBase.getTeachersAccounts();
		
		x=x+1;
		announce_count=Oracle_DataBase.getAnnouncementList().size();
		announce_count=announce_count+1;
		Admin = null;
		course_id = courseId;
		Cname=name;
		teachers=Oracle_DataBase.getTeachers();
		students=Oracle_DataBase.getStudentListDataBase();
		announ=Oracle_DataBase.getAnnouncementList();
		std_y=students.size();
		
		AssessmentCatalog.obj = Oracle_DataBase.getAssessment();
		
		
		assignment = Oracle_DataBase.getAssignmentItem();
		quiz = Oracle_DataBase.getQuizItem();
		ac=new AttendanceCatalog();
		attendance=Oracle_DataBase.getAttendanceitem();

		
		//Orcale_Database.
		
	}
	//getter setter
	public int getCourse_id() 
	{
		return course_id;
	}

	public void setCourse_id(int course_id) 
	{
		this.course_id = course_id;
	}

	public String getCname() 
	{
		return Cname;
	}

	public void setCname(String cname) 
	{
		Cname = cname;
	}

	public admin getAdmin() 
	{
		return Admin;
	}

	public void setAdmin(admin admin) 
	{
		Admin = admin;
	}

	public ArrayList<Student> getStudents() 
	{
		return students;
	}

	public void setStudents(ArrayList<Student> students) 
	{
		this.students = students;
	}

	public ArrayList<teacher> getTeachers() 
	{
		return teachers;
	}

	public void setTeachers(ArrayList<teacher> teachers) 
	{
		this.teachers = teachers;
	}

	public ArrayList<AssessmentItem> getQuiz() 
	{
		return quiz;
	}

	public void setQuiz(ArrayList<AssessmentItem> quiz) 
	{
		this.quiz = quiz;
	}

	public ArrayList<AssessmentItem> getAssignment() 
	{
		return assignment;
	}

	public void setAssignment(ArrayList<AssessmentItem> assignment) 
	{
		this.assignment = assignment;
	}
	
	
	//-------------------------------------------------------------------
							//ADD TEACHER
	//-------------------------------------------------------------------
	public boolean addTeacher(String name, String email) throws SQLException, FileNotFound 
	{
		
		teacher temp=null;
		try 
		{
			temp = Admin.addTeacher(email);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			return false;
		}
		
		if(temp!=null) 
		{
			//Oracle_DataBase.addFaculty(temp);
			teachers.add(temp);
			
			return true;
		} 
		else 
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
	}
				
						//REMOVE TEACHER
	
	public void removeTeacher(String email) throws SQLException {
		
		//try {
			
			teachers=Admin.rmvTeacher(email, teachers);
			
			
			
			
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
	}
	
	
	
	//--------------------------------------------------------------
						//ADD ANNOUNCEMENTS
	//-------------------------------------------------------------/
	public void addAnnouncement(int teach_id, String text)
	{
		for(int i = 0;i < teachers.size();i++)
		{
			if(teachers.get(i).getId() == teach_id)
			{
				announ.add(teachers.get(i).addAnnouncments(announce_count,text));
			}
		}
	}
	
	//----------------------------------------------------------------------
						//SEACRCH ADMIN
	//----------------------------------------------------------------------
	
	
	public admin searchAdmin(String e,String password) 
	{	
		if(e.equals(Admin.getTeacher_email())&&password.equals(Admin.getPassword()))
		{
			
			return Admin;
			
		}
		else 
		{
			return null;
		}
	}
	
	
	//----------------------------------------------------------------------
							//Search for Teacher
	//----------------------------------------------------------------------
	
	public boolean TeacherAccount(String name,String email, String password) {
		teacher temp=new teacher(x,name,email,password);
		CourseClassroom.x++;
		try 
		{
			Oracle_DataBase.createTeacherAccount(temp);
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
	}
	
	///-----------------------------StudentAccount-------------------
	public boolean Student_account(String name,String email, String password,String CourseCode) throws SQLException 
	{
		Student temp=new Student(std_y+1,name,email,password);
		CourseClassroom.std_y++;
		//Oracle_DataBase.addStudent(temp);
		return true;
		
	}
	
	//-------------------------------------------------------------------------
							//Authenticate Teaceher
	//-------------------------------------------------------------------------
	public boolean authTeacher(String email,String password) 
	{
		teacher temp=null;
		for(int i=0;i<teachers.size();i++) {
			temp=teachers.get(i);
			System.out.println(temp.teacher_email+" p "+temp.password+"\n");
			if(email.equals(temp.teacher_email)&&password.equals(temp.password)) {
				return true;
			}
			
		}
		return false;	
	}
	

	public boolean authStudent(String email,String password) throws SQLException 
	{
		Student temp=null;
		System.out.print(students);
		for(int i=0;i<students.size();i++) 
		{
			temp=students.get(i);
			System.out.println(temp.getEmail()+" p "+temp.getPassword()+"\n");
			System.out.println(email+" p "+password+"\n");
			if(email.equals(temp.getEmail())&&password.equals(temp.getPassword())) 
			{
				return true;
			}
			
		}
		return false;
	}
	//-------------------------------------------------------------------------
					//	getTeacher object
	//-------------------------------------------------------------------------
	
	public teacher getTeacherObj(String email) {
		teacher temp=null;
		for(int i=0;i<teachers.size();i++) {
			temp=teachers.get(i);
			System.out.println(temp.teacher_email+" p "+temp.password+"\n");
			if(email.equals(temp.teacher_email)) {
				return temp;
			}
			
		}
		return null;
		
		
	}
	
	/////////////////////getStudentObject////////////////////////////////////////////
	
	public Student getStudentObj(String email) 
	{
		Student temp=null;
		for(int i=0;i<students.size();i++) 
		{
			temp=students.get(i);
			System.out.println(temp.getEmail()+" p "+temp.getPassword()+"\n");
			if(email.equals(temp.getEmail())) 
			{
				return temp;
			}	
		}
		return null;	
	}
	

	
	//--------------------------------------------------------------------------
				// student uploads assignment
	//------------------------------------------------------------------------
	public void UploadAssignment(int std_id,int assessment_id,String file)
	{
		for(int i=0;i<assignment.size();i++)
		{
			if(assignment.get(i).getAssessment_id() == assessment_id && assignment.get(i).getStd_id() == std_id)
			{
				assignment.get(i).setSubmission(file);
			}
		}
		
		for(int i=0;i<quiz.size();i++)
		{
			if(quiz.get(i).getAssessment_id() == assessment_id && quiz.get(i).getStd_id() == std_id)
			{
				quiz.get(i).setSubmission(file);
			}
		}
	}

	///////////////MArk Attendance/////////////////////////
	//----------------------------MarkAttendance------------------------------------------
	//----------------------------MarkAttendance------------------------------------------
	public int MarkAttendance(String date,String t_email,ArrayList<Integer>check) throws SQLException, FileNotFound
	{
		System.out.print("MArking Attendance\n");
		teacher t=new teacher();
		for(int i=0; i<teachers.size();i++)
		{
			if(teachers.get(i).getTeacher_email().equalsIgnoreCase(t_email))
			{
				t=teachers.get(i);
				break;
			}
		}
		Attendance att=t.CreateAttendance(date);
		if(att!=null)
		{
		attendance=t.MarkAttendance(att, course_id,students,check);
		return 1;
		}
		else
		{
			System.out.print("Date Already Exists");
			return 0;
		}
		
		//	Attendance att=AttendanceCatalog.getAttendance(id);
		
	}
	
	public ArrayList<Announcement> getAnnounce() 
	{
		return announ; 	
	}
	
//-----------------------------------------------------------------------
//Add assessment
//-----------------------------------------------------------------------
	public void addQuiz(int teach_id ,String assign_date,String due_date,int total_marks,int weightage, String text) throws FileNotFound
	{
		ArrayList<AssessmentItem> list = new ArrayList<AssessmentItem>();

		for(int i=0 ; i<teachers.size() ; i++)
		{
			if(teachers.get(i).getId() == teach_id)
			{
				list = teachers.get(i).addAssessment(students,assign_date,due_date,total_marks,weightage,text);
			}
		}
		for(int i=0;i<list.size();i++)
		{
			quiz.add(list.get(i));
		}
	}
	public void addAssignment(int teach_id ,String assign_date,String due_date,int total_marks,int weightage, String text) throws FileNotFound
	{
		ArrayList<AssessmentItem> list = new ArrayList<AssessmentItem>();
		System.out.println("Teacher Cheking cheking\n\n\n");
		for(int i=0 ; i<teachers.size() ; i++)
		{
			if(teachers.get(i).getId() == teach_id)
			{
				System.out.println("Teacher_id="+teachers.get(i).getId());
				list = teachers.get(i).addAssessment(students,assign_date,due_date,total_marks,weightage,text);
			}
		}
		for(int i=0;i<list.size();i++)
		{
			assignment.add(list.get(i));
		}
	}
	//---------------------------------------------------------
	//to withdraw from course
	//----------------------------------------------------------
	public void Withdraw(int std_id)
	{
		//to remove student
		for(int i=0;i<students.size();i++)
		{
			if(students.get(i).getStudent_id() == std_id)
			{
				students.remove(i);
			}
		}
		//to remove assignmentItem of the student
		for(int i=0;i<assignment.size();i++)
		{
			if(assignment.get(i).getStd_id() == std_id)
			{
				assignment.remove(i);
			}
		}
		//to remove quizItem of the student
		for(int i=0;i<quiz.size();i++)
		{
			if(quiz.get(i).getStd_id() == std_id)
			{
				quiz.remove(i);
			}
		}
		//to remove attendance of studdent
		for(int i=0;i<attendance.size();i++)
		{
			if(attendance.get(i).getstd_id() == std_id)
			{
				attendance.remove(i);
			}
		}
		
		//removing from database also
		Oracle_DataBase.removeAssessmentItem(std_id);
		Oracle_DataBase.removeAttendanceItem(std_id);
		Oracle_DataBase.removeStudent(std_id);
	}
	
	////////////update Attendance
	public int UpdateAttendance(String date,String t_email,ArrayList<Integer>check) throws SQLException
	{
		System.out.print("Updating Attendance\n");
		teacher t=new teacher();
		for(int i=0; i<teachers.size();i++)
		{
			if(teachers.get(i).getTeacher_email().equalsIgnoreCase(t_email))
			{
				t=teachers.get(i);
				break;
			}
		}
		Attendance att=this.getAttendance(date);
		if(att!=null)
		{
		t.UpdateAttendance(att,students,check);
		return 1;
		}
		else
		{
			System.out.print("Doesn't Already Exists");
			return 0;
		}
		
		//	Attendance att=AttendanceCatalog.getAttendance(id);
		
	}
	
	
////////////////getAttendance///////////////////////////
	public Attendance getAttendance(String Date)
	{
        return AttendanceCatalog.getAttendance(Date);
	}
	
	public ArrayList<AttendanceItem> getAttendanceItem() throws SQLException
	{
		this.attendance=Oracle_DataBase.getAttendanceitem();

		return this.attendance;		
	}
	
}
