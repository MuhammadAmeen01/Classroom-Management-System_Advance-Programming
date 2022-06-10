package DataBase;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import BusinessLogic.Announcement;
import BusinessLogic.Assessment;
import BusinessLogic.AssessmentItem;
import BusinessLogic.Attendance;
import BusinessLogic.AttendanceItem;
import BusinessLogic.CourseClassroom;
import BusinessLogic.Date;
import BusinessLogic.Student;
import BusinessLogic.admin;
import BusinessLogic.teacher;

import org.hibernate.*;

public class hibernate extends PersistanceManager
{
	Configuration con;
	
	SessionFactory sf1;
	SessionFactory sf2;
	SessionFactory sf3;
	SessionFactory sf4;
	SessionFactory sf5;
	SessionFactory sf6;
	SessionFactory sf7;
	SessionFactory sf8;
	SessionFactory sf9;
	SessionFactory sf10;
	
	Session session1;
	Session session2;
	Session session3;
	Session session4;
	Session session5;
	Session session6;
	Session session7;
	Session session8;
	Session session9;
	Session session10;
	
	Transaction trans1;
	Transaction trans2;
	Transaction trans3;
	Transaction trans4;
	Transaction trans5;
	Transaction trans6;
	Transaction trans7;
	Transaction trans8;
	Transaction trans9;
	Transaction trans10;
	
	public hibernate()
	{
		con = new Configuration();
		//Student
		con.configure().addAnnotatedClass(Date.class);
		sf10 = con.buildSessionFactory();
		session10 = sf10.openSession();
		trans10 = session10.beginTransaction();
		//teacher
		con.configure().addAnnotatedClass(teacher.class);
		sf1 = con.buildSessionFactory();
		session1 = sf1.openSession();
		trans1 = session1.beginTransaction();
		//admin
		con.configure().addAnnotatedClass(admin.class);
		sf2 = con.buildSessionFactory();
		session2 = sf2.openSession();
		trans2 = session2.beginTransaction();
		//announcement
		con.configure().addAnnotatedClass(Announcement.class);
		sf3 = con.buildSessionFactory();
		session3 = sf3.openSession();
		trans3 = session3.beginTransaction();
		//Assessment
		con.configure().addAnnotatedClass(Assessment.class);
		sf4 = con.buildSessionFactory();
		session4 = sf4.openSession();
		trans4 = session4.beginTransaction();
		//AssessmentItem
		con.configure().addAnnotatedClass(AssessmentItem.class);
		sf5 = con.buildSessionFactory();
		session5 = sf5.openSession();
		trans5 = session5.beginTransaction();
		//Attendance
		con.configure().addAnnotatedClass(Attendance.class);
		sf6 = con.buildSessionFactory();
		session6 = sf6.openSession();
		trans6 = session6.beginTransaction();
		//Attendanceitem
		con.configure().addAnnotatedClass(AttendanceItem.class);
		sf7 = con.buildSessionFactory();
		session7 = sf7.openSession();
		trans7 = session7.beginTransaction();
		//CourseClassroom
		con.configure().addAnnotatedClass(CourseClassroom.class);
		sf8 = con.buildSessionFactory();
		session8 = sf8.openSession();
		trans8 = session8.beginTransaction();
		//Student
		con.configure().addAnnotatedClass(Student.class);
		sf9 = con.buildSessionFactory();
		session9 = sf9.openSession();
		trans9 = session9.beginTransaction();
		
	}
	public static void main(String[] args)
	{
		
		
		
	}
}
