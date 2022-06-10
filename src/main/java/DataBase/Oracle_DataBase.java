package DataBase;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BusinessLogic.Announcement;
import BusinessLogic.Assessment;
import BusinessLogic.AssessmentItem;
import BusinessLogic.Attendance;
import BusinessLogic.AttendanceItem;
import BusinessLogic.CourseClassroom;
//import BusinessLogic.Date;
import BusinessLogic.Student;
import BusinessLogic.admin;
import BusinessLogic.teacher;

public class Oracle_DataBase extends PersistanceManager
{
	//attributes
	
	//constructors
	public Oracle_DataBase() throws SQLException
	{
		//Database connection
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded successfully");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYED","scott","1234");
			
			System.out.println("Connection Established");
		}
		catch(Exception e)
		{
			System.out.println("Database Connection Failed!");
		}
		
		Statement stmt = con.createStatement();
		Statement stmt1 = con.createStatement();
		
		//uncomment to drop schema
		/*
		System.out.println("Dropping schema tables");
		try
		{
			stmt.executeQuery("DROP TABLE student CASCADE CONSTRAINTS");
			System.out.println("student table dropped");
		}
		catch(Exception e)
		{
			System.out.println("student table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE teacher CASCADE CONSTRAINTS");
			System.out.println("teacher table dropped");
		}
		catch(Exception e)
		{
			System.out.println("teacher table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE admin CASCADE CONSTRAINTS");
			System.out.println("admin table dropped");
		}
		catch(Exception e)
		{
			System.out.println("admin table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE classroom CASCADE CONSTRAINTS");
			System.out.println("classroom table dropped");
		}
		catch(Exception e)
		{
			System.out.println("classroom table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE attendance CASCADE CONSTRAINTS");
			System.out.println("attendance table dropped");
		}
		catch(Exception e)
		{
			System.out.println("attendance table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE assessment CASCADE CONSTRAINTS");
			System.out.println("assessment table dropped");
		}
		catch(Exception e)
		{
			System.out.println("assessment table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE announcement CASCADE CONSTRAINTS");
			System.out.println("announcement table dropped");
		}
		catch(Exception e)
		{
			System.out.println("announcement table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE attendanceitem CASCADE CONSTRAINTS");
			System.out.println("attendanceitem table dropped");
		}
		catch(Exception e)
		{
			System.out.println("attendanceitem table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE assessmentitem CASCADE CONSTRAINTS");
			System.out.println("assessmentitem table dropped");
		}
		catch(Exception e)
		{
			System.out.println("assessmentitem table does not exist, so it cannot be dropped");
		}
		try
		{
			stmt.executeQuery("DROP TABLE teacherAccounts CASCADE CONSTRAINTS");
			System.out.println("teacherAccounts table dropped");
		}
		catch(Exception e)
		{
			System.out.println("teacherAccounts table does not exist, so it cannot be dropped");
		}*/
		
		//creating schema
		try
		{
			
			
			System.out.println("Creating tables");
			//Student
			ResultSet rs;
			stmt.executeQuery("CREATE TABLE student"
							+ "(std_id NUMBER(10) PRIMARY KEY,"
							+ "name VARCHAR(30),"
							+ "email VARCHAR(30) UNIQUE,"
							+ "password VARCHAR(30))");
			//Teacher table
			stmt1.executeQuery("CREATE TABLE teacher"
								+ "(teacher_id NUMBER(10) PRIMARY KEY,"
								+ "name VARCHAR(30),"
								+ "email VARCHAR(40) UNIQUE,"
								+ "password VARCHAR(20),"
								+ "status NUMBER(2))");
			
			//Admin table
			rs=stmt.executeQuery("CREATE TABLE admin"
								+ "(admin_id NUMBER(10) PRIMARY KEY,"
								+ "teacher_id NUMBER(10))");
			
			//Classroom
			rs=stmt.executeQuery("CREATE TABLE classroom"
								+ "(class_id NUMBER(10) PRIMARY KEY,"
								+ "name VARCHAR(30))");
			
			//Attendance table
			rs=stmt.executeQuery("CREATE TABLE attendance"
								+ "(date_id NUMBER(10) PRIMARY KEY,"
								+ "datte VARCHAR(10))");
			//Assessment table
			rs=stmt.executeQuery("CREATE TABLE assessment"
							+ "(assessment_id NUMBER(10) PRIMARY KEY,"
							+ "totalmarks NUMBER(10),"
							+ "weightage NUMBER(10),"
							+ "duedate VARCHAR(20),"
							+ "description VARCHAR(30))");
			//Announcement table
			rs=stmt.executeQuery("CREATE TABLE announcement"
								+ "(announcement_id NUMBER(10) PRIMARY KEY,"
								+ "announ VARCHAR(500))");
			//Attendance Item
			rs=stmt.executeQuery("CREATE TABLE attendanceitem"
								+ "(std_id NUMBER(10),"
								+ "coursecode NUMBER(10),"
								+ "date_id NUMBER(10),"
								+ "presence NUMBER(10))");
			//Assessment Item
			rs=stmt.executeQuery("CREATE TABLE assessmentitem"
								+ "(std_id NUMBER(10),"
								+ "coursecode NUMBER(10),"
								+ "assessment_id NUMBER(10),"
								+ "marksobtained NUMBER(10),"
								+ "submission_link VARCHAR(20),"
								+ "submission_date VARCHAR(20))");

			rs=stmt.executeQuery("CREATE TABLE teacherAccounts"
					+ "(teacher_i NUMBER(10) PRIMARY KEY,"
					+ "name VARCHAR(30),"
					+ "email VARCHAR(40) UNIQUE,"
					+ "password VARCHAR(20))");
				
			
			
			System.out.println("Schema created");
			
			//Table modification
			//admin item
			rs=stmt.executeQuery("ALTER TABLE admin\r\n"
					+ "			ADD ( CONSTRAINT ad_tec_fk\r\n"
					+ "			        	 FOREIGN KEY (teacher_id)\r\n"
					+ "			          	  REFERENCES teacher(teacher_id)\r\n"
					+ "			    )");
			//attendacne item
			rs=stmt.executeQuery("ALTER TABLE attendanceitem\r\n"
					+ "			ADD ( CONSTRAINT atten_std_fk\r\n"
					+ "			        	 FOREIGN KEY (std_id)\r\n"
					+ "			          	  REFERENCES student(std_id),\r\n"
					+ "			      CONSTRAINT atten_course_fk\r\n"
					+ "					 FOREIGN KEY (coursecode)\r\n"
					+ "					 REFERENCES classroom(class_id),\r\n"
					+ "			      CONSTRAINT atten_date_fk\r\n"
					+ "					 FOREIGN KEY (date_id)\r\n"
					+ "					 REFERENCES attendance(date_id)  \r\n"
					+ "			    )");
			//assessment item
			rs=stmt.executeQuery("ALTER TABLE assessmentitem\r\n"
					+ "			ADD ( CONSTRAINT a_std_fk\r\n"
					+ "			        	 FOREIGN KEY (std_id)\r\n"
					+ "			          	  REFERENCES student(std_id),\r\n"
					+ "			      CONSTRAINT a_course_fk\r\n"
					+ "					 FOREIGN KEY (coursecode)\r\n"
					+ "					 REFERENCES classroom(class_id),\r\n"
					+ "			      CONSTRAINT a_date_fk\r\n"
					+ "					 FOREIGN KEY (assessment_id)\r\n"
					+ "					 REFERENCES assessment(assessment_id)  \r\n"
					+ "			    )");
			
			System.out.println("Table altered");
			//con.commit();
			
		}
		catch(Exception e)
		{
			System.out.println("Schema table(s) already present");
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////to ftns to store data in data base//////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//add classroom
	public static void addCourseClassroom(CourseClassroom obj)
	{
		
		try 
		{	
			String sql="INSERT INTO classroom(class_id,name) VALUES (?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getCourse_id());
			statement.setString(2,obj.getCname());
			statement.executeUpdate();
		}
		catch(Exception e) 
		{
			System.out.println("Course classroom NOT added");
		}
		
	}
	//adding teacher
	static public void addFaculty(teacher obj) throws SQLException
	{
		try 
		{
			String sql="INSERT INTO teacher(teacher_id,name,email,password) VALUES (?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getId());
			statement.setString(2,obj.getTeacher_name());
			statement.setString(3,obj.getTeacher_email());
			statement.setString(4,obj.getPassword());
			//statement.setInt(5, obj.getTeacher_status());
			statement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Faculty data add failed");
		}
	}
	//add admin
	public static void addAdmin(admin obj)
	{	
		try 
		{	
			String sql="INSERT INTO teacher(teacher_id,name,email,password) VALUES (?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getId());
			statement.setString(2,obj.getTeacher_name());
			statement.setString(3,obj.getTeacher_email());
			statement.setString(4,obj.getPassword());
			statement.executeUpdate();
			
			String sql2="INSERT INTO admin(admin_id,teacher_id) VALUES (?,?)";
			
			PreparedStatement statement1=con.prepareStatement(sql2);
			statement1.setInt(1,obj.getAdmin_id());
			statement1.setInt(2,obj.getId());
			statement1.executeUpdate();
			
			System.out.println("Admin added successfully");
					
		}
		catch(Exception e) 
		{
			System.out.println("Admin NOT added");
		}
	}
	//add assessment
	public static void addAssessment(Assessment obj)
	{	
		try
		{
			String sql="INSERT INTO assessment(assessment_id,totalmarks,weightage,duedate,description) VALUES (?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, obj.getAssessment_id());
			statement.setInt(2, obj.getTotal_marks());
			statement.setInt(3, obj.getWeightage());
			statement.setString(4, obj.get_Due_Date());
			statement.setString(5, obj.getText());
			
			int rowsInserted = statement.executeUpdate();
	
			if(rowsInserted>0)
			{
				System.out.println("Assessment saved");
			}
		}
		catch(Exception e)
		{
			System.out.println("Assessment data NOT saved");
		}
	}
	//add student
	public static void addStudent(Student obj)
	{
		try
		{
			String sql="INSERT INTO student (std_id,name,email,password) Values (?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getStudent_id());
			statement.setString(2,obj.getStudentName());
			statement.setString(3, obj.getEmail());
			statement.setString(4, obj.getPassword());
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("Student Data has been inserted\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Student data NOT added");
		}
	}
	//add AssessmentItem single obj
	public static void addAssessmentItem(AssessmentItem obj)
	{
		
		try
		{
			String sql="INSERT INTO assessmentitem(std_id,coursecode,assessment_id,marksobtained,submission_link,submission_date) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
	
			statement.setInt(1, obj.getStd_id());
			statement.setInt(2, 300);
			statement.setInt(3, obj.getAssessment_id());
			statement.setInt(4, obj.getMarksobtained());
			statement.setString(5, obj.getSubmssion());
			statement.setString(6, obj.getSubmission_date());
			
			int rowsInserted = statement.executeUpdate();
	
			if(rowsInserted>0)
			{
				System.out.println("Assessmentitem saved");
			}
		}
		catch(Exception e)
		{
			System.out.println("AssessmentItem data NOT saved");
		}
	}
	//add AssessmentItem as List
	public static void addAssessmentItem(ArrayList<AssessmentItem> obj)
	{
		int size = obj.size();
		
		for(int i=0;i<size;i++)
		{
			addAssessmentItem(obj.get(i));
		}
	}
	//add attendance
	public static void addAttendance(Attendance obj)
	{
		try
		{
			String sql="INSERT INTO attendance (date_id,datte) Values(?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getAttendanceId());
			statement.setString(2,obj.getDate());
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("Attendance Data has been inserted\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Attendance Data NOT added");
		}
	}
	//to add attendance item as a obj
	public static void addAttendanceItem(AttendanceItem obj)
	{
		
		    System.out.println(obj.getstd_id());
		    System.out.println(obj.getcoursecode());
		    System.out.println(obj.getdate_id());
		    System.out.println(obj.getpresence());
			
		try
		{
			String sql="INSERT INTO attendanceitem (std_id,coursecode,date_id,presence) Values(?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getstd_id());
			statement.setInt(2,obj.getcoursecode());
			statement.setInt(3,obj.getdate_id());
			statement.setInt(4,obj.getpresence());
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("AttendanceItem Data has been inserted\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("attendanceitem data NOT added");
		}
	}
	//attendacne as a list
	public static void addAttendanceItem(ArrayList<AttendanceItem> obj)
	{
		int size = obj.size();
		
		for(int i=0;i<size;i++)
		{
			addAttendanceItem(obj.get(i));
		}
	}
	//add announcement as obj
	public static void addAnnouncement(Announcement obj)
	{
		try
		{
			String sql="INSERT INTO announcement(announcement_id,announ) Values(?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getAnnounccement_id());
			statement.setString(2,obj.getText());
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("Announcement Data has been inserted\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Announcement Data NOT added");
		}
	}
	//add Announcement as list
	public static void addAnnouncement(ArrayList<Announcement> obj)
	{
		for(int i=0;i<obj.size();i++)
		{
			addAnnouncement(obj.get(i));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////ftns to get data from database///////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//to get teacher as a list
	
	
	
	public static ArrayList<teacher> getTeachers() throws ClassNotFoundException, SQLException{
		
		ArrayList<teacher>temp=new ArrayList<teacher>();
		
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from teacher");
		
		while(rs.next()) 
		{
			int id=rs.getInt("teacher_id");
			String name=rs.getString("name");
			String email=rs.getString("email");
			String pass=rs.getString("password");
			int s=rs.getInt("status");
			temp.add(new teacher(id,name,email,pass,s));
		}
		return temp;
	}
	/*public static ArrayList<teacher> getTeachers() throws ClassNotFoundException, SQLException
	{
		try
		{
			ArrayList<teacher>temp=new ArrayList<teacher>();
		
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from teacher");
		
			while(rs.next()) 
			{
				int id=rs.getInt("teacher_id");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String pass=rs.getString("password");
				int s=rs.getInt("status");
				temp.add(new teacher(id,name,email,pass,s));
			}
			return temp;
		}
		catch(Exception e)
		{
			System.out.println("Failed to get teacher list from database");
			return null;
		}
	}*/
	//to get a single teacher obj
	public static teacher getTeacher(int teach_id)
	{
		try
		{	
			String sql="SELECT * FROM teacher WHERE teacher_id = ?";
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, teach_id);
			
			ResultSet result=st.executeQuery();
			teacher newnode = new teacher();
				
			while(result.next())
			{	
				newnode.setId(result.getInt("teacher_id"));
				newnode.setTeacher_name(result.getString("name"));
				newnode.setPassword(result.getString("password"));
				newnode.setTeacher_email(result.getString("email"));
				newnode.setTeacher_status(result.getInt("status"));
			}
			return newnode;			
		}
		catch(Exception e)
		{
			System.out.println("Single teacher get query failed from database");
			return null;
		}
	}
	
	//To get array list of announcement
	public static ArrayList<Announcement> getAnnouncementList()
	{
		try
		{
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery("Select * from announcement");
			ArrayList<Announcement>AnnouncementList=new ArrayList<Announcement>();
			while(result.next())
			{
				Announcement newnode=new Announcement();
				newnode.setAnnounccement_id(result.getInt("announcement_id"));
				newnode.setText(result.getString("announ"));
				AnnouncementList.add(newnode);
				//newnode.display(newnode);
			}
			return AnnouncementList;
		}
		catch(Exception e)
		{
			System.out.println("Announcement list get failed!");
			return null;
		}
	}
	//get announcement as an object
	public static Announcement getAnnouncement(int announ_id)
	{
		try
		{	
			String sql="SELECT * FROM announcement WHERE announcement_id = ?";
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, announ_id);
			
			ResultSet result=st.executeQuery();
			Announcement newnode=new Announcement();
				
			while(result.next())
			{	
				newnode.setAnnounccement_id(result.getInt("announcement_id"));
				newnode.setText(result.getString("announ"));
				//newnode.display(newnode);
			}
			return newnode;			
		}
		catch(Exception e)
		{
			System.out.println("Single Announcement get query failed");
			return null;
		}
	}
	
	//to get course classroom
	public static CourseClassroom getmyClass() throws SQLException, ClassNotFoundException 
	{
		try
		{
			CourseClassroom temp=null;
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from classroom");
		
			while(rs.next()) 
			{
				int id=rs.getInt("class_id");
				String name=rs.getString("name");
			
				temp=new CourseClassroom(id,name);
			}
			return temp;
		}
		catch(Exception e)
		{
			System.out.println("Failed to get courseclassroom from database");
			return null;
		}
	}
	//to get admin
	public static admin getAdmin() throws SQLException, ClassNotFoundException 
	{
		try
		{
			int id = 0;
			admin temp=null;
		
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from admin");
			while(rs.next()) {
				int ad_id=rs.getInt("admin_id");
				id=rs.getInt("teacher_id");
				//System.out.println(temp.getTeacher_name());
			}
			Statement smt1=con.createStatement();
			ResultSet rs1=smt1.executeQuery("select * from teacher where teacher_id=0");
		
			while(rs1.next()) 
			{
				int t_id=rs1.getInt("teacher_id");
				String name=rs1.getString("name");
				String email=rs1.getString("email");
				String pass=rs1.getString("password");
				int s=rs1.getInt("status");
				temp=new admin(t_id,name,email,pass);	
			}
		
			//System.out.println(temp.getTeacher_name());
			//temp=new admin(ad_id,id);
			return temp;
		}
		catch(Exception e)
		{
			System.out.println("Failed to get admin from database");
			return null;
		}
	}
	
	//to get student as an arraylist
	public static ArrayList<Student>getStudentListDataBase() throws SQLException
	{
		try
		{
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery("Select * from student");
			ArrayList<Student>StudentList=new ArrayList<Student>();
			while(result.next())
			{
				Student newnode=new Student();
				newnode.setEmail(result.getString("email"));
				newnode.setPassword(result.getString("password"));
				newnode.setStudentName(result.getString("name"));
				newnode.setStudent_id(result.getInt("std_id"));
				StudentList.add(newnode);
				//newnode.display(newnode);
			}
			return StudentList;
		}
		catch(Exception e)
		{
			System.out.println("Failed to get student arraylist from database");
			return null;
		}
	}
	//to get a single student obj from database
	public static Student getStudent(int std_id)
	{
		try
		{	
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery("Select * from student");
			Student newnode=new Student();
			while(result.next())
			{
				newnode.setEmail(result.getString("email"));
				newnode.setPassword(result.getString("password"));
				newnode.setStudentName(result.getString("name"));
				newnode.setStudent_id(result.getInt("std_id"));
				//newnode.display(newnode);
			}
			return newnode;		
		}
		catch(Exception e)
		{
			System.out.println("Single Student get query failed");
			return null;
		}
	}
	
	public static boolean checkTeacherAcc(String email) throws SQLException
	{
		
		ArrayList<teacher>temp=new ArrayList<teacher>();
		
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from teacherAccounts");
		
		while(rs.next()) 
		{
			int id=rs.getInt("teacher_i");
			String name=rs.getString("name");
			String e_mail=rs.getString("email");
			String pass=rs.getString("password");
			//int s=rs.getInt("status");
			if(email.equals(e_mail)) {
				return true;
			}
			temp.add(new teacher(id,name,email,pass));
		}
		return false;
		
	}
	
	public static void createTeacherAccount(teacher obj) throws SQLException{
		
		//try 
		//{
			String sql="INSERT INTO teacherAccounts(teacher_i,name,email,password) VALUES (?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getId());
			System.out.println("ID:"+obj.getId());
			statement.setString(2,obj.getTeacher_name());
			System.out.print("2");
			statement.setString(3,obj.getTeacher_email());
			System.out.print("3");
			statement.setString(4,obj.getPassword());
			System.out.print("4");
			statement.executeUpdate();
		//}
		/*catch(Exception e) 
		{
			System.out.println("Faculty data add Failed!");
		}*/
		
	}
	
	public static int getTeachersAccounts() throws ClassNotFoundException, SQLException{
		
		ArrayList<teacher>temp=new ArrayList<teacher>();
		
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from teacherAccounts");
		
		while(rs.next()) 
		{
			int id=rs.getInt("teacher_i");
			String name=rs.getString("name");
			String email=rs.getString("email");
			String pass=rs.getString("password");
			//int s=rs.getInt("status");
			temp.add(new teacher(id,name,email,pass));
		}
		return temp.size();
		//return temp;
	}
//---------------------------------------------------------------------------------
	
	
	public static teacher getTeacherAcc(String email) throws SQLException
	{
		
		ArrayList<teacher>temp=new ArrayList<teacher>();
		
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from teacherAccounts");
		
		while(rs.next()) 
		{
			int id=rs.getInt("teacher_i");
			String name=rs.getString("name");
			String e_mail=rs.getString("email");
			String pass=rs.getString("password");
			//int s=rs.getInt("status");
			if(email.equals(e_mail)) {
				return new teacher(id,name,email,pass);
			}
			temp.add(new teacher(id,name,email,pass));
		}
		return null;
		
	}

	/*public static boolean checkTeacherAcc(String email) throws SQLException
	{
		
		ArrayList<teacher>temp=new ArrayList<teacher>();
		
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from teacherAccounts");
		
		while(rs.next()) 
		{
			int id=rs.getInt("teacher_i");
			String name=rs.getString("name");
			String e_mail=rs.getString("email");
			String pass=rs.getString("password");
			//int s=rs.getInt("status");
			if(email.equals(e_mail)) {
				return true;
			}
			temp.add(new teacher(id,name,email,pass));
		}
		return false;
		
	}
	
	public static void createTeacherAccount(teacher obj) throws SQLException{
		
		try 
		{
			String sql="INSERT INTO teacherAccounts(teacher_i,name,email,password) VALUES (?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getId());
			System.out.println("1"+obj.getId());
			statement.setString(2,obj.getTeacher_name());
			System.out.print("2");
			statement.setString(3,obj.getTeacher_email());
			System.out.print("3");
			statement.setString(4,obj.getPassword());
			System.out.print("4");
			statement.executeUpdate();
		}
		catch(Exception e) 
		{
			System.out.println("Teacher create account Failed!");
		}
		
	}
	
	public static int getTeachersAccounts() throws ClassNotFoundException, SQLException
	{
		try
		{
			ArrayList<teacher>temp=new ArrayList<teacher>();
		
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from teacherAccounts");
		
			while(rs.next()) 
			{
				int id=rs.getInt("teacher_i");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String pass=rs.getString("password");
				//int s=rs.getInt("status");
				temp.add(new teacher(id,name,email,pass));
			}
			return temp.size();
		}
		catch(Exception e)
		{
			System.out.println("Teachers account get failed");
			return 0;
		}
		//return temp;
	}
//---------------------------------------------------------------------------------
	
	
	public static teacher getTeacherAcc(String email) throws SQLException
	{
		try
		{
		
			ArrayList<teacher>temp=new ArrayList<teacher>();
		
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from teacherAccounts");
		
			while(rs.next()) 
			{
				int id=rs.getInt("teacher_i");
				String name=rs.getString("name");
				String e_mail=rs.getString("email");
				String pass=rs.getString("password");
				//int s=rs.getInt("status");
				if(email.equals(e_mail)) {
					return new teacher(id,name,email,pass);
				}
				temp.add(new teacher(id,name,email,pass));
			}
			return null;
		}
		catch(Exception e)
		{
			System.out.println("Teacher get failed");
			return null;
		}
		
	}*/
	
	/*public static ArrayList<Attendance> getAttendance() throws SQLException
	{
		ArrayList<Attendance>temp=new ArrayList<Attendance>();
		
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from Attendance");
		
		while(rs.next()) 
		{
			Attendance att=new Attendance();
			att.setAttendanceId(rs.getInt("date_id"));
			att.setDate(rs.getString("datte"));

			temp.add(att);
		}
		
		return temp;
	}*/
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////UPDATE ftns/////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void updateAssessItemObtainedMarks(int assess_id, int student_id, float newObtainedMarks)
	{
		//update obtained marks of given assessment id and student
		try
		{
			String sql="UPDATE assessmentitem SET marksobtained = ? WHERE std_id = ? AND assessment_id = ?";
			// ? means a variable will replace this blank?
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setFloat(1,newObtainedMarks);
			statement.setInt(2,student_id);
			statement.setFloat(3,assess_id);
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("Student's Assessment Item has been updated\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Student Assessment Item NOT updated");
		}
	}
	public static void updateAssessmentWeightage(int assess_id, int newWeightage)
	{
		//update assessment item with new weightage
		try
		{
			String sql="UPDATE assessment SET weightage = ? WHERE assessment_id = ?";
			// ? means a variable will replace this blank?
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setFloat(1,newWeightage);
			statement.setInt(2,assess_id);
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("Assessment Weightage has been updated\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Assessment NOT updated");
		}
	}
	public static void updateAssessmentTotalMarks(int assess_id,int totMarks)
	{
		//update assessment item with new total marks
		try
		{
			String sql="UPDATE assessment SET totalmarks = ? WHERE assessment_id = ?";
			// ? means a variable will replace this blank?
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setFloat(1,totMarks);
			statement.setInt(2,assess_id);
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("Assessment Total Marks has been updated\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Assessment NOT updated");
		}
	}
	public static void updateAssessmentDueDate(int assess_id, String day, String month, String year)
	{
		//update assessment item with new due date
		try
		{
			String sql="UPDATE assessment SET duedate = ? WHERE assessment_id = ?";
			// ? means a variable will replace this blank?
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1,day+"-"+month+"-"+year);
			statement.setInt(2,assess_id);
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("Assessment Due Date has been updated\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Assessment NOT updated");
		}
	}
	
	//---------------------------------------------------------------------
	//						REMOVE TEACHER
	//---------------------------------------------------------------------
	
	public static void removeTeacher(String email) throws SQLException 
	{
		try
		{
			String sql="DELETE from teacher where email=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1,email);
			//System.out.print(email);
			statement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Teacher delete failed");
		}

		
	}
	
	//to withdraw from course
	public static void removeStudent(int std_id)
	{
		try
		{
			String sql="DELETE from student where std_id=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,std_id);
			statement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Student deletion failed");
		}
	}
	public static void removeAttendanceItem(int std_id)
	{
		try
		{
			String sql="DELETE from attendanceitem where std_id=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,std_id);
			statement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Student AttendancetItem deletion failed");
		}
	}
	public static void removeAssessmentItem(int std_id)
	{
		try
		{
			String sql="DELETE from assessmentitem where std_id=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,std_id);
			statement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Student AssessmenttItem deletion failed");
		}
	}
	
	//--------------------------------------------------------------------
	//---------------------------more getters-----------------------------
	//--------------------------------------------------------------------
	public static ArrayList<AttendanceItem> getAttendanceItem() throws SQLException
	{
		try
		{
			ArrayList<AttendanceItem>temp=new ArrayList<AttendanceItem>();
		
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from attendanceitem");
		
			while(rs.next()) 
			{
				AttendanceItem att = new AttendanceItem();
				att.setstd_id(rs.getInt("std_id"));
				att.setcoursecode(rs.getInt("coursecode"));
				att.setdate_id(rs.getInt("date_id"));
				att.setpresence(rs.getInt("presence"));

				temp.add(att);
			}
			return temp;
		}
		catch(Exception e)
		{
			System.out.println("AttendanceItem get failed");
			return null;
		}
	}
	
	public static ArrayList<Assessment> getAssessment() throws SQLException
	{
		try
		{
			ArrayList<Assessment>temp=new ArrayList<Assessment>();
		
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from assessment");
		
			while(rs.next()) 
			{
				Assessment att = new Assessment();
				
				att.set_Assessment_ID(rs.getInt("assessment_id"));
				att.setTotal_marks(rs.getInt("totalmarks"));
				att.setText(rs.getString("description"));
				att.setWeightage(rs.getInt("weightage"));
				att.set_Due_Date(rs.getString("duedate"));

				temp.add(att);
			}
			return temp;
		}
		catch(Exception e)
		{
			System.out.println("Assessment get failed");
			return null;
		}
	}
	
	public static ArrayList<AssessmentItem> getAssignmentItem() throws SQLException
	{
		try
		{
			ArrayList<AssessmentItem>temp=new ArrayList<AssessmentItem>();
		
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from assessmentitem ai JOIN assessment a ON ai.assessment_id = a.assessment_id WHERE a.weightage > 5");
		
			while(rs.next()) 
			{
				AssessmentItem att = new AssessmentItem();
				
				att.setAssessment_id(rs.getInt("assessment_id"));
				att.set_Submission_Date(rs.getString("submission_date"));
				att.setSubmission(rs.getString("submission_link"));
				att.setMarksobtained(rs.getInt("marksobtained"));
				att.setStd_id(rs.getInt("std_id"));

				temp.add(att);
			}
			return temp;
		}
		catch(Exception e)
		{
			System.out.println("AssignmentItem get failed");
			return null;
		}
		
	}
	public static ArrayList<AssessmentItem> getQuizItem() throws SQLException
	{
		try
		{
			ArrayList<AssessmentItem>temp=new ArrayList<AssessmentItem>();
		
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from assessmentitem ai JOIN assessment a ON ai.assessment_id = a.assessment_id WHERE a.weightage <= 5");
		
			while(rs.next()) 
			{
				AssessmentItem att = new AssessmentItem();
				
				att.setAssessment_id(rs.getInt("assessment_id"));
				att.set_Submission_Date(rs.getString("submission_date"));
				att.setSubmission(rs.getString("submission_link"));
				att.setMarksobtained(rs.getInt("marksobtained"));
				att.setStd_id(rs.getInt("std_id"));

				temp.add(att);
			}
			return temp;
		}
		catch(Exception e)
		{
			System.out.println("QuizItem get failed");
			return null;
		}
		
	}
	
	public static ArrayList<Attendance> getAttendance() throws SQLException
	{
		ArrayList<Attendance>temp=new ArrayList<Attendance>();
		
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from Attendance");
		
		while(rs.next()) 
		{
			Attendance att=new Attendance();
			att.setAttendanceId(rs.getInt("date_id"));
			att.setDate(rs.getString("datte"));

			temp.add(att);
		}
		
		return temp;
	}	








	
	////////////////////////Attendance Item
	
	public static ArrayList<AttendanceItem> getAttendanceitem() throws SQLException
	{
	ArrayList<AttendanceItem>temp=new ArrayList<AttendanceItem>();
		
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from attendanceitem");
		
		while(rs.next()) 
		{
			AttendanceItem att=new AttendanceItem();
			att.setstd_id(rs.getInt(1));
			att.setcoursecode(rs.getInt(2));
			att.setdate_id(rs.getInt(3));
			att.setpresence(rs.getInt(4));

			temp.add(att);
		}
		
		return temp;
	}
	
	public static void updateAttendanceitem(int std_id,int date_id,int presence) throws SQLException
	{
	
		System.out.println("Presence1: "+presence+"StudentID1: "+std_id+"  " +"Attendacneid1:"+date_id);
		//	Statement smt=con.createStatement();
		String str="UPDATE attendanceitem SET presence = ? WHERE std_id = ? AND date_id = ?";
	 	PreparedStatement statement=con.prepareStatement(str);
	
		
	        statement.setInt(1,presence);
	        statement.setInt(2,std_id);
	        statement.setInt(3,date_id);
	        
			statement.executeUpdate();
		
		
		
	}
		
	
	
	
}
