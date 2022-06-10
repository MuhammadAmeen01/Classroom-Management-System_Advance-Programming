import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import BusinessLogic.Main;
import BusinessLogic.Student;
import BusinessLogic.admin;
import BusinessLogic.teacher;
import CustomExceptions.FileNotFound;

public class NegativeTestClassroom {

	
	
	
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
	//----------------------------------------------
	
	@Test
	public void negAddStudentTest() throws ClassNotFoundException, SQLException
	{
		m=new Main();
		//m.addAdmin("Gulsher", "gulsher@gmail.com", "123");
		//m.a.createClassroom(1, "Adv programming");
		
		m.c.Student_account("ameen", "ameen@gmail.com", "123", "1");
		boolean output=m.c.authStudent("ameen1@gmail.com", "123");
		boolean expected=true;
		
		Assert.assertEquals(expected,output);
		
		
	}
	
	
	//----------------------------------------------
	@Test
	
	public void NegauthAdmin() {
		
		String mail;
		admin a=m.c.searchAdmin("gulsher@gmail.com", "1234");
		//mail=a.getTeacher_email();
		Assert.assertEquals(a,null);
		
	}
	public NegativeTestClassroom() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void authTeacherNeg() {
		
		String mail;
		boolean a=m.c.authTeacher("gulsher@gmail.com", "1234");
		
		Assert.assertEquals(a,true);
		
	}
	
	
	@Test
	public void TestGetTeacherNeg() {
		
		teacher temp;
		temp=m.c.getTeacherObj("gulsherKhan@gmail.com");
		//String s=temp.getTeacher_email();
		Assert.assertEquals(temp,null);
		
	}
	
	@Test
	public void TestGetStudentObj() {
		
		Student temp;
		temp=m.c.getStudentObj("ameenKhan@gmail1.com");
		//String s=temp.getEmail();
		Assert.assertEquals(temp,null);
		
	}
	
	
	@Test
	public void AddTeachertestNeg() throws SQLException, ClassNotFoundException, FileNotFound {
		
		m=new Main();
		//m.addAdmin("Gulsher", "gulsher@gmail.com", "123");
		//m.a.createClassroom(1, "Adv programming");
		
		m.c.TeacherAccount("12345", "12345@gmail.com", "123");
		m.c.getAdmin().addTeacher("12345@gmail.com");
		//String output=m.c.getTeachers().get(1).getTeacher_email();
		m=new Main();
		String output=m.c.getTeacherObj("1234@gmail.com").getTeacher_email();
		String expected="12345@gmail.com";
		
		Assert.assertEquals(expected,output);
		
		
		
		
	}
	

}
