package BusinessLogic;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import DataBase.Oracle_DataBase;

@Entity

public class AssessmentItem 
{
	@Id
	private int std_id;
	private int a_id;
	private int obtained_marks;
	private String submission_link;
	@Transient
	private Date submission_date; 
	
	public AssessmentItem()
	{
		std_id = 0;
		a_id = 0;
		obtained_marks = 0;
		setSubmission_date(null);
		submission_link = "";
	}
	AssessmentItem(int Std_ID, int ID, Date Submission_Date, int Obtained_Marks, String Link)
	{
		a_id = ID;
		std_id = Std_ID;
		setSubmission_date(Submission_Date);
		obtained_marks = Obtained_Marks;
		submission_link = Link;
	}
	AssessmentItem(int std,int a_id)
	{
		this.std_id = std;
		this.a_id = a_id;
		obtained_marks = 0;
		submission_link = "";
		setSubmission_date(null);
	}
	
	public void setSubmission(String link)
	{
		submission_link = link;
	}
	public String getSubmssion()
	{
		return submission_link;
	}
	public AssessmentItem getAssessmentItem()
	{
		return this;
	}
	public int getStd_id() {
		return std_id;
	}

	public void setStd_id(int std_id) {
		this.std_id = std_id;
	}

	public int getAssessment_id() {
		return a_id;
	}

	public void setAssessment_id(int assessment_id) {
		this.a_id = assessment_id;
	}

	public int getMarksobtained() {
		return obtained_marks;
	}

	public void setMarksobtained(int marksobtained) {
		this.obtained_marks = marksobtained;
	}
	public String getSubmission_date() 
	{
		return submission_date.getFullDate();
	}
	public void setSubmission_date(Date submission_date) 
	{
		this.submission_date = submission_date;
	}
	public void set_Submission_Date(String Date) 
	{
		try
		{
			String[] arr_Assign_Date = Date.split("/", 3);
		
			submission_date.setDay(arr_Assign_Date[0]);
			submission_date.setDay(arr_Assign_Date[1]);
			submission_date.setDay(arr_Assign_Date[2]);
		}
		catch(Exception e)
		{
			System.out.println("Submission date not set");
		}
	}


}