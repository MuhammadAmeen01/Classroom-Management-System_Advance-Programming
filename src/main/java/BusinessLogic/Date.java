package BusinessLogic;

import java.util.Calendar;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.text.SimpleDateFormat;

public class Date 
{
	private int day;
	private int month;
	private int year;
	
    public Date(int day, int month, int year) throws IllegalArgumentException
    {
        if (!isValid(day, month, year)) throw new IllegalArgumentException();
        this.year = year;
        this.month = month;
        this.day = day;     
    }
    
    /**
     * Overloaded Constructor that takes a ISO8601 date string
     * @param dateISO8601 
     * @throws IllegalArgumentException if invalid
     */
    public Date(String dateISO8601) throws IllegalArgumentException {
        String[] s = dateISO8601.split("-");
        if (s.length != 3) throw new IllegalArgumentException();
        int yy = Integer.parseInt(s[0]);
        int mm = Integer.parseInt(s[1]);
        int dd = Integer.parseInt(s[2]);
        if (!isValid(yy, mm, dd)) throw new IllegalArgumentException();
        this.year = yy;
        this.month = mm;
        this.day = dd;
    }
    
    /**
     * default constructor that uses today's Date()
     */
    public Date() {
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        java.util.Date x = currentDate.getTime();
        SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
        this.year = Integer.parseInt(formatyear.format(x));
        SimpleDateFormat formatmonth = new SimpleDateFormat("MM");
        this.month = Integer.parseInt(formatmonth.format(x));     
        SimpleDateFormat formatdd = new SimpleDateFormat("dd");
        this.day = Integer.parseInt(formatdd.format(x));       
    }
    
	public int getDay() {
        return this.day;
    }
    
	public void setDay(String Date)
	{
		day = Integer.parseInt(Date);
	}
    // Getter for month
    public int getMonth() {
        return this.month;
    }
    
    public void setMonth(String Month)
	{
		day = Integer.parseInt(Month);
	}

    //Getter for year
    public int getYear() {
        return this.year;
    }   
    public void seYear(String Year)
	{
		day = Integer.parseInt(Year);
	}
    /*
		Returns full date as a String in dd/mm/yyyy format 
	*/
    public String getFullDate()
    {
    	return getDay()+"/"+getMonth()+"/"+getYear();
    }
    
    /*
      	Compare two dates
      	Return true if 'this' is earlier than d
     */
    public boolean compareTo(Date d) {
    	int day1 = d.getDay();
        int month1 = d.getMonth();
        int year1 = d.getYear();
        return (this.year <= year1) && (this.month <= month1) && (this.day <= day1);
    }

    
    public static boolean isLeap(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;     
    }    
    
    
    /*
      	Check if given year/month/day is valid
		Return true if it is valid date, false if invalid
     */
    
    public static boolean isValid(int day, int month, int year) {
        if (year < 0) return false;
        if ((month < 1) || (month > 12)) return false;
        if ((day < 1) || (day > 31)) return false;
        switch (month) {
            case 1: return true;
            case 2: return (isLeap(year) ? day <= 29 : day <= 28);
            case 3: return true;
            case 4: return day < 31;
            case 5: return true;
            case 6: return day < 31;
            case 7: return true;
            case 8: return true;
            case 9: return day < 31;
            case 10: return true;
            case 11: return day < 31;
            default: return true;
        }
    }
    


}

