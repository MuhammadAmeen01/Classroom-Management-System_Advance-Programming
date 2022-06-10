package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import DataBase.Oracle_DataBase;

@Entity

public class Assessment 
{
	@Id
	private int a_id;
	@Transient
	private Date assign_date;
	@Transient
	private Date due_date;
	private int total_marks;
	private int weightage;
	private String text;
	
	//constructors
	public Assessment()
	{
		if(a_id == 0)
			a_id = 1;
		else
			a_id+=1;
		due_date = new Date();
		assign_date = new Date();
		weightage = 0;
		total_marks = 0;
		text = null;
//		Oracle_DataBase.addAssessement(this);
	}
	Assessment(int ID,String Assign_Date, String Due_Date, int Total_Marks, int Weightage, String text)
	{
		
		a_id = ID;
		
        String[] arr_Assign_Date = Assign_Date.split("/", 3);
//        System.out.println("Array of Assign Date Components: ");
//        for(String a : arr_Assign_Date)
//        	System.out.println(a);
		assign_date = new Date(Integer.parseInt(arr_Assign_Date[0]), Integer.parseInt(arr_Assign_Date[1]), Integer.parseInt(arr_Assign_Date[2]));

		String[] arr_Due_Date = Due_Date.split("/", 3);
//        System.out.println("Array of Due Date Components: ");
//        for(String a : arr_Due_Date)
//        	System.out.println(a);
        due_date = new Date(Integer.parseInt(arr_Due_Date[0]), Integer.parseInt(arr_Due_Date[1]), Integer.parseInt(arr_Due_Date[2]));
		
//		due_date = new Date(Integer.parseInt(Due_Date), Integer.parseInt(Due_Month), Integer.parseInt(Due_Year));
		total_marks = Total_Marks;
		weightage = Weightage;
		this.text = text;
	}
	
	Assessment(int ID, Date Assign_Date, Date Due_Date, int Total_Marks, int Weightage)
	{
		if(a_id == 0)
		{
			a_id = 1;
		}
		else
		{
			a_id+=1;
		}
		assign_date = Assign_Date;
		due_date = Due_Date;
		total_marks = Total_Marks;
		weightage = Weightage;
	}
	public int getAssessment_id() 
	{
		return a_id;
	}

	public void set_Assessment_ID(int ID) 
	{
		a_id = ID;
	}

	public String get_Assign_Date() 
	{
    	return assign_date.getFullDate();
	}
    
	public void set_Assign_Date(String Date, String Month, String Year) 
	{
		assign_date.setDay(Date);
		assign_date.setDay(Month);
		assign_date.setDay(Year);
	}
	
	public String get_Due_Date() 
	{
    	return due_date.getFullDate();
	}
    
	public void set_Due_Date(String Date, String Month, String Year) 
	{
		due_date.setDay(Date);
		due_date.setDay(Month);
		due_date.setDay(Year);
	}
	public void set_Due_Date(String Date) 
	{
        String[] arr_Assign_Date = Date.split("/", 3);
		
		due_date.setDay(arr_Assign_Date[0]);
		due_date.setDay(arr_Assign_Date[1]);
		due_date.setDay(arr_Assign_Date[2]);
	}
	
	public String getText() 
	{
		return text;
	}
	
	public void setText(String text) 
	{
		this.text = text;
	}
	
	public int getTotal_marks() 
	{
		return total_marks;
	}

	public void setTotal_marks(int total_marks) 
	{
		this.total_marks = total_marks;
	}

	public int getWeightage() 
	{
		return weightage;
	}

	public void setWeightage(int weightage) 
	{
		this.weightage = weightage;
	}
		
	
	//ftns
	void displayAssessment()
	{
		System.out.println("Assessement ID: "+getAssessment_id());
		System.out.println("Assessment assign date: "+get_Assign_Date());
		System.out.println("Assessment due date: "+get_Due_Date());
		System.out.println("Total Marks: "+total_marks);
		System.out.println("Weightage: "+weightage);

	}

	
}
