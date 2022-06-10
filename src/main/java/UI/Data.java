package UI;

import BusinessLogic.Student;
import BusinessLogic.teacher;

public final class Data {
	
	
	//private  String Tname;
	private teacher t;
	private Student s;
	private final static Data INSTANCE = new Data();
	//private String Tmail;

	public Data() {
		// TODO Auto-generated constructor stub
		t=null;
		s=null;
	}

	public teacher getT() {
		return t;
	}

	public void setT(teacher t) {
		this.t = t;
	}

	public static Data getInstance() {
		return INSTANCE;
	}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}
	
	
	

}
