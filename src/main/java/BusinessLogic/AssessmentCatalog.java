package BusinessLogic;

import java.util.ArrayList;

import CustomExceptions.FileNotFound;
import DataBase.FileHandling;
import DataBase.Oracle_DataBase;

public class AssessmentCatalog 
{
	static ArrayList<Assessment> obj = new ArrayList<Assessment>();
	
	static public void addAssessment(Assessment assessment) throws FileNotFound
	{
		obj.add(assessment);
		Oracle_DataBase.addAssessment(assessment);
		FileHandling.addAssessment(assessment);
	}

	public static ArrayList<Assessment> getObj() {
		return obj;
	}

	public static void setObj(ArrayList<Assessment> obj) {
		AssessmentCatalog.obj = obj;
	}
	
	
	
}
