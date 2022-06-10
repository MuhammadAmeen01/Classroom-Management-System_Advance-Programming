package DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import BusinessLogic.Announcement;
import BusinessLogic.Assessment;
import BusinessLogic.AssessmentItem;
import BusinessLogic.Attendance;
import BusinessLogic.AttendanceItem;
import BusinessLogic.CourseClassroom;
import BusinessLogic.Student;
import BusinessLogic.admin;
import BusinessLogic.teacher;
import CustomExceptions.FileNotFound;

import java.util.StringTokenizer;  
public class FileHandling {

	
	
	public FileHandling()
	{
		
	    	
		
	}
	
	public void addCourseClassroom(CourseClassroom obj) throws IOException,FileNotFound
	{
		System.out.print("File writing\n");
		
		try {
			FileWriter writer=new FileWriter("ClassRoom.txt",true);
			writer.write(obj.getCname()+" "+obj.getCourse_id()+"\n");
			writer.close();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
		
	}
	//adding teacher
	static public void addFaculty(teacher obj) throws FileNotFound
	{
		try 
		{
		    FileWriter writer=new FileWriter("Teacher.txt",true);
		    writer.write(obj.getId()+" "+obj.getTeacher_name()+" "+obj.getTeacher_email()+" "+obj.getPassword()+" "+obj.getTeacher_status()+"\n");
		    writer.close();
		}
		catch(Exception e)
		{
			System.out.println("Faculty data add failed");
		}
	}
	//add admin
	public static void addAdmin(admin obj) throws FileNotFound
	{	
		try 
		{	
			FileWriter writer=new FileWriter("Admin.txt",true);
			
			writer.write(obj.getAdmin_id()+ " "+obj.getId()+"\n");
			writer.close();
			
			FileHandling.addFaculty(obj);
			
			System.out.println("Admin added successfully");
					
		}
		catch(Exception e) 
		{
			System.out.println("Admin NOT added");
		}
	}
	//add assessment
	public static void addAssessment(Assessment obj) throws FileNotFound
	{	
		try
		{
			
			
			FileWriter writer=new FileWriter("Assessment.txt",true);
			writer.write(obj.getAssessment_id()+" "+obj.getTotal_marks()+" "+obj.getWeightage()+" "+obj.get_Due_Date()+"\n");
			
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("Assessment data NOT saved");
		}
	}
	//add student
	public static void addStudent(Student obj) throws FileNotFound
	{
		try
		{
			
			FileWriter writer=new FileWriter("Student.txt",true);
			writer.write(obj.getStudent_id()+" "+obj.getStudentName()+" "+obj.getEmail()+" "+obj.getPassword()+"\n");
			
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("Student data NOT added");
		}
	}
	//add AssessmentItem single obj
	public static void addAssessmentItem(AssessmentItem obj) throws FileNotFound
	{
		
		try
		{
			FileWriter writer=new FileWriter("AssessmentItem.txt",true);
			writer.write(obj.getStd_id()+" "+obj.getAssessment_id()+" "+obj.getMarksobtained()+" "+obj.getSubmssion()+"\n");
			
			writer.close();
			
		}
		catch(Exception e)
		{
			System.out.println("AssessmentItem data NOT saved");
		}
	}
	//add AssessmentItem as List
	public static void addAssessmentItem(ArrayList<AssessmentItem> obj) throws FileNotFound
	{
		int size = obj.size();
		
		for(int i=0;i<size;i++)
		{
			FileHandling.addAssessmentItem(obj.get(i));
		}
	}
	//add attendance
	public static void addAttendance(Attendance obj) throws FileNotFound
	{
		try
		{
			
			FileWriter writer=new FileWriter("Attendance.txt",true);
			writer.write(obj.getAttendanceId()+" "+obj.getDate()+"\n");
			
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("Attendance Data NOT added");
		}
	}
	//to add attendance item as a obj
	public static void addAttendanceItem(AttendanceItem obj) throws FileNotFound
	{
			
		try
		{
			FileWriter writer=new FileWriter("AttendanceItem.txt",true);
			writer.write(obj.getstd_id()+" "+obj.getcoursecode()+obj.getdate_id()+obj.getpresence()+"\n");
			
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("attendanceitem data NOT added");
		}
	}
	//attendacne as a list
	public static void addAttendanceItem(ArrayList<AttendanceItem> obj) throws FileNotFound
	{
		int size = obj.size();
		
		for(int i=0;i<size;i++)
		{
			FileHandling.addAttendanceItem(obj.get(i));
		}
	}
	//add announcement as obj
	public static void addAnnouncement(Announcement obj) throws FileNotFound
	{
		try
		{
			
			FileWriter writer=new FileWriter("Annoucements.txt",true);
			writer.write(obj.getAnnounccement_id()+" "+obj.getText()+"\n");
			
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("Announcement Data NOT added");
		}
	}
	//add Announcement as list
	public static void addAnnouncement(ArrayList<Announcement> obj) throws FileNotFound
	{
		for(int i=0;i<obj.size();i++)
		{
			FileHandling.addAnnouncement(obj.get(i));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////ftns to get data from database///////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//to get teacher as a list
	
	
	
	public static ArrayList<teacher> getTeachers() throws FileNotFound
	{
		
		ArrayList<teacher>temp=new ArrayList<teacher>();
		try 
		{
	      File myObj = new File("Teacher.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	    	teacher t=new teacher();
	        String data = myReader.nextLine();
	        StringTokenizer st = new StringTokenizer(data," ");
	        int i=0;
	        while (st.hasMoreTokens()) {  
	        	
	            System.out.println();
	            if(i==0)
	            {
	            	 t.setId(Integer.parseInt(st.nextToken()));
	            	 i++;
	            }
	            if(i==1)
	            {
	            	t.setTeacher_name(st.nextToken());
	            	i++;
	            }
	            if(i==2)
	            {
	            	t.setTeacher_email(st.nextToken());
	            	i++;
	            }
	            if(i==3)
	            {
	            	t.setPassword(st.nextToken());
	            	i++;
	            }
	            if(i==4)
	            {
	            	t.setTeacher_status(Integer.parseInt(st.nextToken()));
	            	i=0;
	            	temp.add(t);
	            }
	           
	        }  
	        
	        
	        
	        System.out.println(data);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		return temp;
	}
			
	//to get a single teacher obj
	public static teacher getTeacher(int teach_id) throws FileNotFound
	{
		try
		{	
			ArrayList<teacher>arr=FileHandling.getTeachers();
			
			
			for(int i=0; i<arr.size(); i++)
			{
				if(arr.get(i).getId()==teach_id)
				{
					return arr.get(i);
				}
			}
			return null;			
		}
		catch(Exception e)
		{
			System.out.println("Single teacher get query failed from file");
			return null;
		}
	}
	
	//To get array list of announcement
	public static ArrayList<Announcement> getAnnouncementList() throws FileNotFound
	{

			ArrayList<Announcement>temp=new ArrayList<Announcement>();
			try {
		      File myObj = new File("Announcement.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	  Announcement t=new Announcement();
		        String data = myReader.nextLine();
		        StringTokenizer st = new StringTokenizer(data," ");
		        int i=0;
		        while (st.hasMoreTokens()) {  
		        	
		            System.out.println();
		            if(i==0)
		            {
		            	 t.setAnnounccement_id(Integer.parseInt(st.nextToken()));
		            	 i++;
		            }
		            if(i==1)
		            {
		            	t.setText(st.nextToken());
			            
		            	i=0;
		            	temp.add(t);
		            }
		        }  
		      }
		        

			return temp;
		
		}
		catch(Exception e)
		{
			System.out.println("Announcement list get failed!");
			return null;
		}
	}
	//get announcement as an object
	public static Announcement getAnnouncement(int announ_id) throws FileNotFound
	{
		   ArrayList<Announcement>ann=FileHandling.getAnnouncementList();
		  // Announcement newnode=new Announcement();
		   for(int i=0; i<ann.size(); i++)
		   {
			   if(ann.get(i).getAnnounccement_id()==announ_id)
			   {
				   return ann.get(i);
			   }
		   }
		   
		   
		   
			return null;			
		

	}
	
	//to get course classroom
	public static CourseClassroom getmyClass() throws FileNotFound, FileNotFoundException, ClassNotFoundException, SQLException 
	{
	
		      File myObj = new File("CourseClassroom.txt");
		      Scanner myReader = new Scanner(myObj);
		      
		      while (myReader.hasNextLine()) {
		    	CourseClassroom t=new CourseClassroom();
		        String data = myReader.nextLine();
		        StringTokenizer st = new StringTokenizer(data," ");
		        int i=0;
		        while (st.hasMoreTokens()) {  
		        	
		            System.out.println();
		            if(i==0)
		            {
		            	 t.setCourse_id(Integer.parseInt(st.nextToken()));
		            	 i++;
		            }
		            if(i==1)
		            {
		            	t.setCname(st.nextToken());
			            
		            	i=0;
		            	return t;
		            }
		           

		            
		           
		        }  
		      }
			return null;
		
		      
	}
	//to get admin
	public static admin getAdmin() throws FileNotFound
	{
		try
		{
		      File myObj = new File("Admin.txt");
		      Scanner myReader = new Scanner(myObj);
		      int t_id=0;
		      admin t=new admin();
		      while (myReader.hasNextLine()) {
		    	
		        String data = myReader.nextLine();
		        StringTokenizer st = new StringTokenizer(data," ");
		        int i=0;
		        while (st.hasMoreTokens()) {  
		        	
		            System.out.println();
		            if(i==0)
		            {
		            	 t.setAdmin_id(Integer.parseInt(st.nextToken()));
		            	 i++;
		            }
		            if(i==1)
		            {
		            	
			            t_id=Integer.parseInt(st.nextToken());
			            t.setId(t_id);
		            	i=0;
		            	break;
		            }
		           

		            
		           
		        }  
		      }
		      
		      
		    myReader.close();
		    ArrayList<teacher>arr=FileHandling.getTeachers();
		    for(int i=0; i<arr.size(); i++)
		    {
		    	if(arr.get(i).getId()==t_id)
		    	{
		    		t.setTeacher_name(arr.get(i).getTeacher_name());
		    		t.setTeacher_email(arr.get(i).getTeacher_email());
		    		t.setPassword(arr.get(i).getPassword());
		    		t.setTeacher_status(arr.get(i).getTeacher_status());
		    		break;
		    	}
		    }
		 
		      
		      
		   
		    
		    
		    
		    
			return t;
	
			
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
		      
			  File myObj = new File("Student.txt");
			  ArrayList<Student>stdarr=new ArrayList<Student>();
		      Scanner myReader = new Scanner(myObj);
		      int t_id=0;
		      admin t=new admin();
		      while (myReader.hasNextLine()) {
		    	Student std=new Student();
		        String data = myReader.nextLine();
		        StringTokenizer st = new StringTokenizer(data," ");
		        int i=0;
		        while (st.hasMoreTokens()) {  
		        	
		           
		            if(i==0)
		            {
		            	 std.setStudent_id(Integer.parseInt(st.nextToken()));
		            	 i++;
		            }
		            if(i==1)
		            {
		            	
			            std.setStudentName(st.nextToken());
			            i++;
		            }
		            if(i==2)
		            {
		            	std.setEmail(st.nextToken());
		            	i++;
		            }
		            if(i==3)
		            	
		            {
		            	std.setPassword(st.nextToken());
		            	stdarr.add(std);
		            	i=0;
		            }
		           

		            
		           
		        }  
		      }
		      myReader.close();
		      return stdarr;
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
		   ArrayList<Student>arr=FileHandling.getStudentListDataBase();
		   for(int i=0; i<arr.size(); i++)
		   {
			   if(arr.get(i).getStudent_id()==std_id)
			   {
				   return arr.get(i);
			   }
		   }
		   
		//	return newnode;		
		}
		catch(Exception e)
		{
			System.out.println("Single Student get query failed");
			return null;
		}
		return null;
	}
	
	public static boolean checkTeacherAcc(String email) throws SQLException, FileNotFoundException
	{
		
		
	      File myObj = new File("TeacherAccounts.txt");
	      Scanner myReader = new Scanner(myObj);
	      ArrayList<teacher>temp=new ArrayList<teacher>();
	      while (myReader.hasNextLine()) {
	    	teacher t=new teacher();
	        String data = myReader.nextLine();
	        StringTokenizer st = new StringTokenizer(data," ");
	        int i=0;
	        while (st.hasMoreTokens()) {  
	        	
	            System.out.println();
	            if(i==0)
	            {
	            	 t.setId(Integer.parseInt(st.nextToken()));
	            	 i++;
	            }
	            if(i==1)
	            {
	            	t.setTeacher_name(st.nextToken());
	            	i++;
	            }
	            if(i==2)
	            {
	            	t.setTeacher_email(st.nextToken());
	            	i++;
	            }
	            if(i==3)
	            {
	            	t.setPassword(st.nextToken());
	            	i++;
	            }
	            if(i==4)
	            {
	            	t.setTeacher_status(Integer.parseInt(st.nextToken()));
	            	i=0;
	            	temp.add(t);
	            }
	           
	        }  
	        
	        
	        
	        System.out.println(data);
	      }
	      myReader.close();
	      
	      for(int i=0; i<temp.size(); i++)
	      {
	    	  if(temp.get(i).getTeacher_email().equalsIgnoreCase(email))
	    	  {
	    		 
	    		  return true;
	    		  
	    	  }
	      }
		return false;
		
		
	}
	
	public static void createTeacherAccount(teacher obj) throws SQLException, IOException{
		
		FileWriter fd=new FileWriter("TeacherAccounts.txt",true);
		fd.write(obj.getId()+" "+obj.getTeacher_name()+"  "+obj.getTeacher_email()+" "+obj.getPassword());
		fd.close();
		
		
		//try 
		//{
			/*String sql="INSERT INTO teacherAccounts(teacher_i,name,email,password) VALUES (?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,obj.getId());
			System.out.println("ID:"+obj.getId());
			statement.setString(2,obj.getTeacher_name());
			System.out.print("2");
			statement.setString(3,obj.getTeacher_email());
			System.out.print("3");
			statement.setString(4,obj.getPassword());
			System.out.print("4");
			statement.executeUpdate();*/
		//}
		/*catch(Exception e) 
		{
			System.out.println("Faculty data add Failed!");
		}*/
		
	}
	
	
	////////////yeh wala func smjh ni ayya////////////////////////////////////////
	
/*	public static int getTeachersAccounts() throws ClassNotFoundException, SQLException{
		
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
	}*/
//---------------------------------------------------------------------------------
	
	
	public static teacher getTeacherAcc(String email) throws SQLException, FileNotFoundException
	{
		
	      File myObj = new File("TeacherAccounts.txt");
	      Scanner myReader = new Scanner(myObj);
	      ArrayList<teacher>temp=new ArrayList<teacher>();
	      while (myReader.hasNextLine()) {
	    	teacher t=new teacher();
	        String data = myReader.nextLine();
	        StringTokenizer st = new StringTokenizer(data," ");
	        int i=0;
	        while (st.hasMoreTokens()) {  
	        	
	            System.out.println();
	            if(i==0)
	            {
	            	 t.setId(Integer.parseInt(st.nextToken()));
	            	 i++;
	            }
	            if(i==1)
	            {
	            	t.setTeacher_name(st.nextToken());
	            	i++;
	            }
	            if(i==2)
	            {
	            	t.setTeacher_email(st.nextToken());
	            	i++;
	            }
	            if(i==3)
	            {
	            	t.setPassword(st.nextToken());
	            	i++;
	            }
	            if(i==4)
	            {
	            	t.setTeacher_status(Integer.parseInt(st.nextToken()));
	            	i=0;
	            	temp.add(t);
	            }
	           
	        }  
	        
	        
	        
	        System.out.println(data);
	      }
	      myReader.close();
	      
	      for(int i=0; i<temp.size(); i++)
	      {
	    	  if(temp.get(i).getTeacher_email().equalsIgnoreCase(email))
	    	  {
	    		 
	    		  return new teacher(temp.get(i).getId(),temp.get(i).getTeacher_name(),temp.get(i).getTeacher_email(),temp.get(i).getPassword());
	    		  
	    	  }
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
	
	public static ArrayList<Attendance> getAttendance() throws SQLException, FileNotFoundException
	{
		
		
		 File myObj = new File("TeacherAccounts.txt");
	      Scanner myReader = new Scanner(myObj);
	      ArrayList<Attendance>temp=new ArrayList<Attendance>();
	      while (myReader.hasNextLine()) {
	    	  Attendance t=new Attendance();
	        String data = myReader.nextLine();
	        StringTokenizer st = new StringTokenizer(data," ");
	        int i=0;
	        while (st.hasMoreTokens()) {  
	        	
	            System.out.println();
	            if(i==0)
	            {
	            	 t.setAttendanceId(Integer.parseInt(st.nextToken()));
	            	 i++;
	            }
	            if(i==1)
	            {
	            	t.setDate(st.nextToken());
	            	temp.add(t);
	            	i=0;
	            }
	        }  
		
		
	}
		return temp;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////UPDATE ftns/////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void updateAssessItemObtainedMarks(ArrayList<AssessmentItem>list) throws IOException
	{
		//update obtained marks of given assessment id and student
		FileWriter fd=new FileWriter("AssessmentItem.txt",true);
		for(int i=0; i<list.size(); i++)
		{
			AssessmentItem att=list.get(i);
			fd.write(att.getStd_id()+" "+att.getAssessment_id()+" "+att.getMarksobtained()+" "+att.getSubmssion()+" "+att.getSubmission_date());
			
			
		}
		fd.close();
		
		/*try
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
		}*/
	}
	public static void updateAssessmentWeightage(ArrayList<Assessment>list)
	{
		//update assessment item with new weightage
		try
		{
			
			//FileWriter fd=new FileWriter("Assessment.txt");
			for(int i=0; i<list.size(); i++)
			{
				FileHandling.addAssessment(list.get(i));
			}
		/*	String sql="UPDATE assessment SET weightage = ? WHERE assessment_id = ?";
			// ? means a variable will replace this blank?
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setFloat(1,newWeightage);
			statement.setInt(2,assess_id);
			int row=statement.executeUpdate();
			if(row>0)
			{
				System.out.print("Assessment Weightage has been updated\n");
			}*/
		}
		catch(Exception e)
		{
			System.out.println("Assessment NOT updated");
		}
	}
	public static void updateAssessmentTotalMarks(ArrayList<Assessment>list)
	{
		//update assessment item with new total marks
		try
		{
			for(int i=0; i<list.size(); i++)
			{
				FileHandling.addAssessment(list.get(i));
			}
		}
		catch(Exception e)
		{
			System.out.println("Assessment NOT updated");
		}
	}
	public static void updateAssessmentDueDate(ArrayList<Assessment>list)
	{
		//update assessment item with new due date

			try
			{
				for(int i=0; i<list.size(); i++)
				{
					FileHandling.addAssessment(list.get(i));
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
	
	public static void removeTeacher(String email) throws FileNotFound
	{
		ArrayList<teacher>arr=FileHandling.getTeachers();
		
		
		for(int i=0; i<arr.size(); i++)
		{
			if(arr.get(i).getTeacher_email().equalsIgnoreCase(email))
			{
				arr.remove(i);
			}
		}
		for(int i=0; i<arr.size();i++)
		{
			FileHandling.addFaculty(arr.get(i));
		}
		
		
	}
		
		
	////////////////////////Attendance Item
	
	public static ArrayList<AttendanceItem> getAttendanceitem() throws SQLException, FileNotFoundException
	{
		ArrayList<AttendanceItem>temp=new ArrayList<AttendanceItem>();
	      
		  File myObj = new File("AttendanceItem.txt");
		//  ArrayList<Student>stdarr=new ArrayList<Student>();
	      Scanner myReader = new Scanner(myObj);
	      int t_id=0;
	      admin t=new admin();
	      while (myReader.hasNextLine()) {
	    	  AttendanceItem std=new AttendanceItem();
	        String data = myReader.nextLine();
	        StringTokenizer st = new StringTokenizer(data," ");
	        int i=0;
	        while (st.hasMoreTokens()) {  
	        	
	           
	            if(i==0)
	            {
	            	 std.setstd_id(Integer.parseInt(st.nextToken()));
	            	
	            	 i++;
	            }
	            if(i==1)
	            {
	            	
		            std.setcoursecode(Integer.parseInt(st.nextToken()));
		            i++;
	            }
	            if(i==2)
	            {
	            	std.setdate_id(Integer.parseInt(st.nextToken()));
	            	i++;
	            	
	            }
	            if(i==3)
	            	
	            {
	            	std.setpresence(Integer.parseInt(st.nextToken()));
	            	i=0;
	            	temp.add(std);
	            }
	           

	            
	           
	        }  
	      }
	      myReader.close();
		
		return temp;
	}
	
	public static void updateAttendanceitem(ArrayList<AttendanceItem>arr) throws FileNotFound
	{
	
		for(int i=0; i<arr.size(); i++)
		{
			FileHandling.addAttendanceItem(arr.get(i));
		}
		
		
		
	}
		
	
	
	
	
	
	
	
	
	
	
}
