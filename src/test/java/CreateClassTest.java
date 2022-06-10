import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import BusinessLogic.Main;
import org.junit.Assert;

public class CreateClassTest {
	Main m;
	
	@Before
	public void setUP() throws ClassNotFoundException, SQLException {
		
		m=new Main();
		
		
		
	}
	
	@Test
	public void testAddAdmin() throws SQLException {
		
		m.addAdmin("Gulsher", "gulsherkhan@gmail.com", "123");
		String expected_string="gulsherkhan@gmail.com";
		String output=m.a.getTeacher_email();
		
		Assert.assertEquals(expected_string,output);
		
	}
	

	@Test
	public void testCreateClassroom() throws SQLException {
		
		//m.addAdmin("Gulsher", "gulsherkhan@gmail.com", "123");
		m.a.createClassroom(0,"Advance programming");
		String expected_string="Advance programming";
		String output=m.c.getCname();
		
		Assert.assertEquals(expected_string,output);
		
	}
	
	
	@Test	//Negative
	public void testNegCreateClassroom() throws SQLException {
		
		//m.addAdmin("Gulsher", "gulsherkhan@gmail.com", "123");
		m.a.createClassroom(0,"SDA");
		String expected_string="Advance programming";
		String output=m.c.getCname();
		
		Assert.assertEquals(expected_string,output);
		
	}
	
	
	
	
	
	
	public CreateClassTest() {
		// TODO Auto-generated constructor stub
	}

}
