import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import BusinessLogic.Announcement;
import BusinessLogic.Assessment;
import BusinessLogic.AssessmentCatalog;
import BusinessLogic.Main;
import BusinessLogic.Student;
import BusinessLogic.admin;
import BusinessLogic.teacher;
import CustomExceptions.FileNotFound;

public class CourseClassroom_test {
	Main m;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		m=new Main();
		m.addAdmin("Gulsher", "gulsher@gmail.com", "123");
		m.a.createClassroom(1, "Adv programming");
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddTeachertest() throws SQLException, ClassNotFoundException, FileNotFound {
		
		m=new Main();
		//m.addAdmin("Gulsher", "gulsher@gmail.com", "123");
		//m.a.createClassroom(1, "Adv programming");
		
		m.c.TeacherAccount("12345", "12345@gmail.com", "123");
		m.c.getAdmin().addTeacher("12345@gmail.com");
		//String output=m.c.getTeachers().get(1).getTeacher_email();
		m=new Main();
		String output=m.c.getTeacherObj("12345@gmail.com").getTeacher_email();
		String expected="12345@gmail.com";
		
		Assert.assertEquals(expected,output);
		
		
		
		
	}
	
	@Test
	public void AddStudentTest() throws SQLException, ClassNotFoundException {
		
		//m=new Main();
		//m.addAdmin("Gulsher", "gulsher@gmail.com", "123");
		//m.a.createClassroom(1, "Adv programming");
		
		m.c.Student_account("ameen", "ameen@gmail.com", "123", "1");
		boolean output=m.c.authStudent("ameen@gmail.com", "123");
		boolean expected=true;
		
		Assert.assertEquals(expected,output);
		
		
		
		
	}
	

	
	@Test
	
	public void addAnnouncementTest() throws  SQLException, ClassNotFoundException {
		int s = -1;
		m=new Main();
		m.c.addAnnouncement(0, "Hello World2");//Announcement Strinig
		ArrayList<Announcement> a=m.c.getAnnounce();
		for(int i=0;i<a.size();i++){
			
			if(a.get(i).getAnnounccement_id()==1) {
				s=a.get(i).getAnnounccement_id();
			}
			
		}
		
		Assert.assertEquals(1,s);
		
		
	}
	
	@Test
	public void AdminTest() {
		
		String a_name=m.c.getAdmin().getTeacher_name();
		Assert.assertEquals("Gulsher",a_name);
		
		
	}
	
	@Test
	public void authAdmin() {
		
		String mail;
		admin a=m.c.searchAdmin("gulsher@gmail.com", "123");
		mail=a.getTeacher_email();
		Assert.assertEquals("gulsher@gmail.com",mail);
		
	}
	
	@Test
	public void authTeacher() {
		
		String mail;
		boolean a=m.c.authTeacher("gulsher@gmail.com", "123");
		
		Assert.assertEquals(a,true);
		
	}
	
	
	@Test
	public void TestGetTeacher() {
		
		teacher temp;
		temp=m.c.getTeacherObj("gulsher@gmail.com");
		String s=temp.getTeacher_email();
		Assert.assertEquals(s,"gulsher@gmail.com");
		
	}
	
	@Test
	public void TestGetStudentObj() throws ClassNotFoundException, SQLException {
		m=new Main();
		Student temp;
		temp=m.c.getStudentObj("ameen@gmail.com");
		String s=temp.getEmail();
		Assert.assertEquals(s,"ameen@gmail.com");
		
	}
	
	@Test
	public void TestRmv() throws SQLException, ClassNotFoundException {
		
		m.c.removeTeacher("12345@gmail.com");
		m=new Main();
		teacher output=m.c.getTeacherObj("12345@gmail.com");
		//String expected="12345@gmail.com";
		Assert.assertEquals(output,null);
		
		
	}
	
	@Test
	public void WithDrawStd() throws SQLException, ClassNotFoundException {
		m.c.Student_account("ammar", "ammar@gmail.com", "123", "1");
		m.c.Withdraw(2);
		m=new Main();

		Student temp;
		temp=m.c.getStudentObj("ammar@gmail.com");
		//String s=temp.getEmail();
		Assert.assertEquals(temp,null);
		//Assert.assertEquals(output,null);
		
		
	}
	
	
	@Test
	public void AddQuiz() throws SQLException, ClassNotFoundException, FileNotFound {
		boolean x=false;
		m.c.addQuiz(0, "1/1/1", "1/1/1", 20, 5, "Hello world");
		ArrayList<Assessment>a=AssessmentCatalog.getObj();
		for(int i=0;i<a.size();i++) {
			
			if(a.get(i).getText().equals("Hello world")) {
				
				x=true;
				
			}
			
			
			
		}
		Assert.assertEquals(x,true);
		
		
		
	}
	
	@Test
	public void AddAssess() throws SQLException, ClassNotFoundException, FileNotFound {
		boolean x=false;
		m.c.addAssignment(0, "1/1/1", "1/1/1", 20, 5, "Hello world2");
		ArrayList<Assessment>a=AssessmentCatalog.getObj();
		for(int i=0;i<a.size();i++) {
			
			if(a.get(i).getText().equals("Hello world2")) {
				
				x=true;
				
			}
			
			
			
		}
		Assert.assertEquals(x,true);
		
		
		
	}
	
	@Test
	public void AddMark() throws SQLException, ClassNotFoundException, FileNotFound {
		boolean x=false;
		ArrayList<Integer>arr=new ArrayList<Integer>();
		arr.add(1);
		
		int ret=m.c.MarkAttendance("1/1/2","gulsher@gmail.com", arr);
	
		Assert.assertEquals(ret,1);
		
		
		
	}
	
	@Test
	public void AddUpdateMarkAtten() throws SQLException, ClassNotFoundException {
		boolean x=false;
		ArrayList<Integer>arr=new ArrayList<Integer>();
		arr.add(1);
		
		int ret=m.c.UpdateAttendance("1/1/1","gulsher@gmail.com", arr);
	
		Assert.assertEquals(ret,1);
		
		
		
	}
	

	
	
	
	
	
	

}
