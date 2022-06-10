package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import BusinessLogic.Announcement;
import BusinessLogic.Assessment;
import BusinessLogic.AssessmentCatalog;
import BusinessLogic.Attendance;
import BusinessLogic.AttendanceItem;
import BusinessLogic.Main;
import BusinessLogic.Student;
import BusinessLogic.admin;
import BusinessLogic.teacher;
import CustomExceptions.FileNotFound;
import DataBase.Oracle_DataBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller implements Initializable{

	public Stage appStage;
	public Scene scene;
	public Parent root;
	
	boolean adminValidation1=true;
	boolean adminValidation2=false;
	
	String item;
	
	admin myAdmin;
	
	teacher tempTeacher=null;
	Student tempStd=null;
	
	Assessment atemp;
	
	
	Main m;
	//
	//-----------Images------------------
	@FXML
	ImageView accIcon;
	
	Image imgSrc;
	/////////////////////
	
	@FXML
	DatePicker upDate;
	
	@FXML
	DatePicker dueDate;
	
	
	
	
	
	//----------------------------------
	@FXML
	AnchorPane classfeed;
	
	@FXML
	AnchorPane bannerpane;
	
	@FXML
	AnchorPane listpane;

	@FXML
	AnchorPane descriptionPane;
	
	@FXML
	AnchorPane OptionsPane;

	
	
	
	
	
	//----------------------------------
	
	//TEXTFIELDS
	@FXML
	TextField admin_name;
	
	@FXML
	TextField admin_email;
	
	@FXML
	PasswordField admin_password;
	
	@FXML
	TextField course_id;
	
	@FXML
	TextField course_name;
	
	@FXML
	TextField teacher_name;
	
	@FXML
	TextField teacher_email;
	
	@FXML
	TextField teacher_password;
	
	@FXML
	TextField announcement_title;
	
	@FXML
	TextField TotalMarks;
	
	@FXML
	TextField WeightAge;
	
	//---------------------
	
	// variables for student signup
    @FXML
    private TextField student_name;

    @FXML
    private TextField student_email;

    @FXML
    private TextField student_password;

    @FXML
    private TextField Student_course_id;

    @FXML
    private Button Register_Student;
	
	//---------------------
	
	//LABELS
	@FXML
	private Label success;
	
	@FXML
	private Label adminName;
	
	@FXML
	private Label teacherName;
	
	@FXML
	private Label courseName;
	
	
	
	@FXML
	private Label NameList;
	
	@FXML
	private Label EmailList;
	
	//-------------------------------------------------
	@FXML
	private TextArea itemDescription;
	
	@FXML
	private TextArea Assignment_Discription;
	
	//-------------------------------------------------
	
	//LISTVIEWS
	@FXML
	private ListView<String>teacherNames;
	
	@FXML
	private ListView<String>teacherEmails;

	@FXML
	private ListView<String>classroomItems;
	
	//---------------------
	
	  //checkBoxes
    @FXML
    private ListView<CheckBox> ListCheckBox= new ListView<CheckBox>();
    ArrayList<CheckBox> checkBoxes=new ArrayList<CheckBox>();
    
    
    @FXML
    private TextField Dateid;
    
	
    //DisplayAttendance
    @FXML
    private ListView<AttendanceItem> Listattendance= new ListView<AttendanceItem>();
	
	
	///------------------------
	
	
	//BUTTONS
	@FXML
	Button go;
	
	@FXML
	Button reg_teacher;		//button to register teacher in teachersignup.fxml

	@FXML
	Button add;
	
	
	//-------------------------------------
		//buttons to highlight
	@FXML
	Button announcement;
	
	@FXML
	Button assignments;
	
	@FXML
	Button quizes;
	
	@FXML
	Button attendence;
	
	//-------------------------------------
	// Teacher button opti
	
	@FXML
	Button addAnnoun;
	
	@FXML
	Button addAssess;	
	
	//-------------------------------------------------------------
	
	
    /*@FXML
    private ListView<CheckBox> ListCheckBox= new ListView<CheckBox>();
    ArrayList<CheckBox> checkBoxes=new ArrayList<CheckBox>();
    @FXML
    private TextField Dateid;
    
	
    //DisplayAttendance
    @FXML
    private ListView<AttendanceItem> Listattendance= new ListView<AttendanceItem>();*/
	
	
	///------------------------
	
	//Attendance
    @FXML
    private Button Display_att;

    @FXML
    private Button update_att;

    @FXML
    private Button Mark_att;

    @FXML
    private TextField Date_display;
    
    @FXML
    private TextField Date_ID2;
    @FXML
    private Button StudentAttendance;
    @FXML
    private Button Display_att1;


	
	
	//-------------------------------------------------------------
	int x=0;
	
	//function to change button colors
	
	public void btncolor() {
		
		announcement.setStyle("-fx-background-color: ; ");
		assignments.setStyle("-fx-background-color: ; ");
		quizes.setStyle("-fx-background-color: ; ");
		attendence.setStyle("-fx-background-color: ; ");
	}
	
	
	
	
	public void go_to_login(ActionEvent e) throws IOException, ClassNotFoundException, SQLException {
		m=new Main();
		x=1;
		System.out.print(x);
		if(m.c!= null) {
			Data d1=Data.getInstance();
			d1.setT(null);
			d1.setS(null);
			root=FXMLLoader.load(getClass().getResource("Login1.fxml"));
			appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
			scene=new Scene(root);
			appStage.setScene(scene);
			appStage.show();
			
		
		}
					//Goes to login or create classroom page depending on classroom null or not
		else {
			
			root=FXMLLoader.load(getClass().getResource("createClassroom.fxml"));
			appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
			scene=new Scene(root);
			appStage.setScene(scene);
			appStage.show();	
			
			
		}
		
	}	//go_to_login
	
	
	//----------------------------------------------
				//Admin Login
	//----------------------------------------------
	public void go_to_admin(ActionEvent e) throws IOException {
		
		root=FXMLLoader.load(getClass().getResource("AdminOptions.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();
		
		//adminName.setText(m.c.getAdmin().getTeacher_name());
		//System.out.println("Checkin"+myAdmin.getTeacher_name());
		
	}
	
	//----------------------------------------------
				//Teacher Login
	//----------------------------------------------
	
	
	public void go_to_teacherLogin(ActionEvent e) throws IOException {
		
		root=FXMLLoader.load(getClass().getResource("TeacherLogin.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();	
		
	}
	
	//---------------------------------------------
				//Teacher remove
	//---------------------------------------------

	public void go_to_teacherRemove(ActionEvent e) throws IOException{
		
		root=FXMLLoader.load(getClass().getResource("TeacherRemoval.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();
		
	}
	
	//----------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------
	
	//Student Signup
	public void StudentSignUP(ActionEvent e) throws IOException {
		
		root=FXMLLoader.load(getClass().getResource("StudentRegister.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();
		
	}
    @FXML
    void Student_Register(ActionEvent event) throws ClassNotFoundException, SQLException {
    	 m=new Main();
    	 String s=Integer.toString(m.c.getCourse_id());
    if(s.equalsIgnoreCase(this.Student_course_id.getText()))
     {
    	 m=new Main();
         boolean temp=m.c.Student_account(this.student_name.getText(), this.student_email.getText(), this.student_password.getText(), this.Student_course_id.getText());
         if(temp==true)
         {
        	 success.setText("Student Registered");
         }
         else
         {
        	 success.setText("Error");
         }
     }
     else
     {
    	 success.setText("Classroom Code is invalid");
     }
    	
    
    
     
    }
	
    @FXML
	public void validateStudentinfo() {		//To validate teacher registeration
		
		String regex = "[^(.+)@(.+)$]"; 
		//boolean x=false;
		boolean x=isValid(student_email.getText());
		student_email.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        //if (!newValue) { //when focus lost
	            if(x==true){
	                //when it not matches the pattern (1.0 - 6.0)
	                //set the textField empty
	            	Register_Student.setDisable(false);
	            	//course_id.setText("");
	            }
	            else {
	            	Register_Student.setDisable(true);
	            }
	       // }

	    });
		
	}
	
    @FXML
 	public void LoginStudent(ActionEvent e) throws ClassNotFoundException, SQLException, IOException {
 		
 		m=new Main();
 		Student temp2=null;
 		boolean temp=m.c.authStudent(this.student_email.getText(), this.student_password.getText());
 		
 		if(temp) {
			temp2=m.c.getStudentObj(student_email.getText());
			Data d1=Data.getInstance();
			d1.setS(temp2);
 			
 			root=FXMLLoader.load(getClass().getResource("ClassroomHome.fxml"));
 			appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
 			scene=new Scene(root);
 			appStage.setScene(scene);
 			appStage.show();
 			
 		}
 		else{
 			
 			success.setText("Login Failed. Invalid credentials");
 			
 		}
 		
 	}
 	@FXML
 	public void go_to_StudentLogin(ActionEvent e) throws IOException
 	{
 		root=FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
 		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
 		scene=new Scene(root);
 		appStage.setScene(scene);
 		appStage.show();
 	}
	
	
	public void go_to_createAssessment(ActionEvent e) throws IOException {
		
		
		root=FXMLLoader.load(getClass().getResource("AssessmentUpload.fxml"));
 		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
 		scene=new Scene(root);
 		appStage.setScene(scene);
 		appStage.show();
		
	}
	
	public void go_to_classroomHome(ActionEvent e) throws IOException {
		
		
		root=FXMLLoader.load(getClass().getResource("ClassroomHome.fxml"));
 		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
 		scene=new Scene(root);
 		appStage.setScene(scene);
 		appStage.show();
		
	}
	
	
	
 	
 	
	
	
	
	//--------------------------------------------------------------------------------
	
 	
 	
	
	//---------------------------------------------------------------------------------
	
	public void classroomCreate(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
		

	
		m=new Main();
		System.out.print(x);
		String a_name=admin_name.getText();	//getting text fields
		String a_mail=admin_email.getText();
		String a_pass=admin_password.getText();
		
		
		
		m.addAdmin(a_name, a_mail, a_pass);
		
		m.a.createClassroom(Integer.parseInt(course_id.getText()),course_name.getText());
		root=FXMLLoader.load(getClass().getResource("ClassCreated.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();
		
		
		
	}
	
	@FXML
	public void validate() {		//To validate Course ID
		
		course_id.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        //if (!newValue) { //when focus lost
	            if(course_id.getText().matches("[1-9]")&&adminValidation2){
	                //when it not matches the pattern (1.0 - 6.0)
	                //set the textField empty
	            	adminValidation1=true;
	            	go.setDisable(false);
	            	//course_id.setText("");
	            }
	            else {
	            	go.setDisable(true);
	            }
	       // }

	    });
		
	}
	
	
	
	@FXML
	public void validateAdminMail() {		//To validate teacher registeration
		
		boolean x=isValid(admin_email.getText());
		admin_email.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        //if (!newValue) { //when focus lost
	            if(x==true&&adminValidation1){
	                //when it not matches the pattern (1.0 - 6.0)
	                //set the textField empty
	            	adminValidation2=true;
	            	go.setDisable(false);
	            	//course_id.setText("");
	            }
	            else {
	            	go.setDisable(true);
	            }
	       // }

	    });
		
	}
	
	//---------------------------------------------------------------------------
	
	public void LoginAdmin(ActionEvent e) throws ClassNotFoundException, SQLException, IOException {
		m=new Main();
		
		myAdmin=m.c.searchAdmin(admin_email.getText(), admin_password.getText());
		if(myAdmin!=null) {
			
			root=FXMLLoader.load(getClass().getResource("AdminOptions.fxml"));
			appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
			scene=new Scene(root);
			appStage.setScene(scene);
			appStage.show();
			
		}
		else {
			
			System.out.print("not Ok");
		
		}
		
		
		
	}
	
	//----------------------------------------------------------------------------
	
	public void TeacherSignUP(ActionEvent e) throws IOException {
		
		root=FXMLLoader.load(getClass().getResource("teacherSignUp.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();
		
	}
	
	//-----------------------------------------------------------------------------
	
	public void LoginForTeacher(ActionEvent e) throws ClassNotFoundException, SQLException {
		
		m=new Main();
		boolean temp=m.c.TeacherAccount(teacher_name.getText(), teacher_email.getText(), teacher_password.getText());
	//	Data.Tname=teacher_name.getText();
		//Data.Tmail=teacher_email.getText();
		if(temp) {
			success.setText("Account successfully created");
		}
		else {
			success.setText("Account already exists");
		}
		
	}
	
	@FXML
	public void validateTeacherinfo() {		//To validate teacher registeration
		
		String regex = "[^(.+)@(.+)$]"; 
		//boolean x=false;
		boolean x=isValid(teacher_email.getText());
		teacher_email.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        //if (!newValue) { //when focus lost
	            if(x==true){
	                //when it not matches the pattern (1.0 - 6.0)
	                //set the textField empty
	            	reg_teacher.setDisable(false);
	            	//course_id.setText("");
	            }
	            else {
	            	reg_teacher.setDisable(true);
	            }
	       // }

	    });
		
	}
	
	public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	
	//-----------------------------------------------------------
					//ADD teacher to classroom
	//-----------------------------------------------------------
	
	public void go_to_addTeacher(ActionEvent e) throws IOException {
		
		root=FXMLLoader.load(getClass().getResource("addTeacher.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();
		
	}
	
	public void add_teacher_confirm(ActionEvent e) throws SQLException, ClassNotFoundException, FileNotFound {
		m=new Main();
		boolean dec=Oracle_DataBase.checkTeacherAcc(teacher_email.getText());
		
		if(dec==true) {
			
			m.a.addTeacher(teacher_email.getText());
			success.setText("Teacher is added in classroom");
			
		}
		else if(dec==false){
			
			success.setText("Teacher account does not exist or Account already added");
			
		}
		
	}
	
	public void removeTeacher(ActionEvent e) throws ClassNotFoundException, SQLException {
		m=new Main();
		try {
			if(!m.c.getAdmin().getTeacher_email().equals(teacher_email.getText())) {
			m=new Main();
			
			m.c.removeTeacher(teacher_email.getText());
			System.out.println(teacher_email.getText());
			success.setText("Teaacher removed");
			}
			else {
				success.setText("Admin Cant be removed");
			}
		} catch (ClassNotFoundException e1) {
			
			
			//e1.printStackTrace();
		} catch (SQLException e1) {
			success.setText("Account does not exist");
			//e1.printStackTrace();
		}
		
		
	}
	
	
	//----------------------------------------------------------------
							//Login Teacher
	//----------------------------------------------------------------
	public void LoginTeacher(ActionEvent e) throws ClassNotFoundException, SQLException, IOException {
		
		m=new Main();
		
		boolean temp=m.c.authTeacher(teacher_email.getText(), teacher_password.getText());
		
		teacher temp2=null;
		//d1.setTname(teacher_n);
		
		
		
		if(temp) {
			temp2=m.c.getTeacherObj(teacher_email.getText());
			Data d1=Data.getInstance();
			d1.setT(temp2);
			
			root=FXMLLoader.load(getClass().getResource("ClassroomHome.fxml"));
			//Data.Tname="h";
			//Controller c=loader.getController();
			
			appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
			scene=new Scene(root);
			appStage.setScene(scene);
			appStage.show();
			
		}
		else{
			
			success.setText("Login Failed. Invalid credentials");
			
		}
		
	}
	
	
	//------------------------------------------------------------------
							//Show List of all teachers
	//------------------------------------------------------------------
	public void ShowTeachers(ActionEvent e) throws ClassNotFoundException, SQLException {
		
		m=new Main();
		if(m.c.getTeachers()!=null&&teacherNames.getItems().size()==0) {
			for(int i=0;i<m.c.getTeachers().size();i++) {
				//System.out.println(e1.p_can.get(i).getName());
				NameList.setText("TeacherNames");
				EmailList.setText("Teacher Emails");
				teacherNames.getItems().add(m.c.getTeachers().get(i).getTeacher_name());
				teacherEmails.getItems().add(m.c.getTeachers().get(i).getTeacher_email());
				
			}
		}
		
	}
	
	
	public void addAnnouncement(ActionEvent e) throws ClassNotFoundException, SQLException {
		btncolor();
		itemDescription.setText("");
		itemDescription.setPromptText("Enter your announcement here");
		//m=new Main();
		//announcement_title.setVisible(true);
		//announcement_title.setDisable(false);
		itemDescription.setDisable(false);
		itemDescription.setEditable(true);
		add.setDisable(false);
		add.setVisible(true);
		
	}
	
	
	public void AnnouncementAdd(ActionEvent e) throws ClassNotFoundException, SQLException {
		//itemDescription.setText("");
		m=new Main();
		Data d1=Data.getInstance();
		teacher temp=d1.getT();
		m.c.addAnnouncement(temp.getId(),itemDescription.getText()+"\nFrom:"+temp.getTeacher_name());
		
		//announcement_title.setVisible(false);
		//announcement_title.setDisable(true);
		itemDescription.setDisable(true);
		itemDescription.setEditable(false);
		add.setDisable(true);
		add.setVisible(false);
		itemDescription.setText("");
		showAnnouncements(e);
		success.setText("Announcement Added");
		
	}
	
	public void showAnnouncements(ActionEvent e) throws ClassNotFoundException, SQLException {
	

		this.Display_att.setVisible(false);
 		this.update_att.setVisible(false);
 		this.Mark_att.setVisible(false);
 		this.Date_ID2.setVisible(false);
 		this.ListCheckBox.setVisible(false);
 		this.classroomItems.setVisible(true);
		
		
		btncolor();
		//assignments.setStyle("-fx-background-color: orchid; ");
		announcement.setStyle("-fx-background-color: orchid; ");
		m=new Main();
		classroomItems.getItems().clear();
		
		
		
		ArrayList<Announcement> myA=m.c.getAnnounce();
		if(myA!=null) {	
			for(int i=myA.size()-1;i>=0;i--) {
				classroomItems.getItems().add("\n");
				classroomItems.getItems().add(myA.get(i).getText());
			}
		}	
		
	}
	
	
	@FXML
	public void validateDesc() {		//To validate teacher registeration
		
		 
		//boolean x=false;
		//boolean x=isValid(teacher_email.getText());
		itemDescription.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        //if (newValue) { //when focus lost
	            if(itemDescription!=null&&itemDescription.getText()!=null&&itemDescription.getText().length()!=0&&itemDescription.getText().length()<=500){
	                //when it not matches the pattern (1.0 - 6.0)
	                //set the textField empty
	            	add.setDisable(false);
	            	//course_id.setText("");
	            }
	            else {
	            	add.setDisable(true);
	            }
	        //}

	    });
		
	}
	
	
	
	public void addAssignment(ActionEvent e) throws  SQLException, ClassNotFoundException, NumberFormatException, FileNotFound {
		m=new Main();
		String udate = upDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String ddate= dueDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));	
		System.out.println("\n"+ddate+"\n");
		m.c.addAssignment(tempTeacher.getId(), udate, ddate, Integer.parseInt(TotalMarks.getText()), Integer.parseInt(WeightAge.getText()), Assignment_Discription.getText());
		
		
	}
	
	
	public void showAssessments(ActionEvent e) throws ClassNotFoundException, SQLException {
		btncolor();
		
		this.Display_att.setVisible(false);
 		this.update_att.setVisible(false);
 		this.Mark_att.setVisible(false);
 		this.Date_ID2.setVisible(false);
 		this.ListCheckBox.setVisible(false);
 		this.classroomItems.setVisible(true);
		
		assignments.setStyle("-fx-background-color: orchid; ");
		m=new Main();
		classroomItems.getItems().clear();
		
		
		
		ArrayList<Assessment> myAssess=AssessmentCatalog.getObj();
		if(myAssess!=null) {	
			for(int i=myAssess.size()-1;i>=0;i--) {
				classroomItems.getItems().add("\n");
				classroomItems.getItems().add(AssessmentCatalog.getObj().get(i).getText());
			}
		}
		
		
	}
	
	
	
	
/*	@FXML
	public void AttendanceMenu(ActionEvent e) throws ClassNotFoundException, SQLException, IOException
	{
		root=FXMLLoader.load(getClass().getResource("MarkAttendance.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();
		
		
		
		
	}*/
	@FXML
	public void InitiateAttendance(ActionEvent e) throws ClassNotFoundException, SQLException, FileNotFound
	{
		System.out.print("Initiating Attendance\n");
		m=new Main();
		Data d1=Data.getInstance();
		this.tempTeacher=d1.getT();
		ArrayList<Integer>check=new ArrayList<Integer>();
		
		
		
		
	   for(int i=0;i<this.checkBoxes.size(); i++)
	   {
		   if(this.checkBoxes.get(i).isSelected()==true)
		   {
			   check.add(1);
		   }
		   else
		   {
			   check.add(0);
		   }
	   }
		
		m.c.MarkAttendance(Dateid.getText(),this.tempTeacher.getTeacher_email(),check);
		
		
		//success.setText("Attendacne Marked");
		
		
		
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//--------------------------------------------------------------------------------
	/////////////////////////////////////////////////////////////////////////////////
	
	@FXML
	public void MarkAttendance(ActionEvent e) throws ClassNotFoundException, SQLException, FileNotFound
	{
		System.out.print("Initiating Attendance\n");
		m=new Main();
		Data d1=Data.getInstance();
		this.tempTeacher=d1.getT();
		ArrayList<Integer>check=new ArrayList<Integer>();
		
		
		
		
	   for(int i=0;i<this.checkBoxes.size(); i++)
	   {
		   if(this.checkBoxes.get(i).isSelected()==true)
		   {
			   check.add(1);
		   }
		   else
		   {
			   check.add(0);
		   }
	   }
		
	   int i=m.c.MarkAttendance(this.Date_ID2.getText(),this.tempTeacher.getTeacher_email(),check);
		if(i==0)
		{
			success.setText("Attendance Already Exists");
		}
		
	}
	
	
	
	@FXML
	public void UpdateAttendance(ActionEvent e) throws ClassNotFoundException, SQLException
	{
		System.out.print("Updating Attendance\n");
		m=new Main();
		Data d1=Data.getInstance();
		this.tempTeacher=d1.getT();
		ArrayList<Integer>check=new ArrayList<Integer>();
		
		
		
		System.out.println("SizeCheckbox: "+this.checkBoxes.size());
	   for(int i=0;i<this.checkBoxes.size(); i++)
	   {
		   if(this.checkBoxes.get(i).isSelected()==true)
		   {
			   check.add(1);
		   }
		   else
		   {
			   check.add(0);
		   }
	   }
		
	   int i=m.c.UpdateAttendance(this.Date_ID2.getText(),this.tempTeacher.getTeacher_email(),check);
		if(i==0)
		{
			success.setText("Attendance Doesn't Exists");
		}
		if(i==1) {
			success.setText("Updated");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void AttendanceMenu(ActionEvent e) throws ClassNotFoundException, SQLException, IOException
	{
		
			this.Display_att.setVisible(true);
	 		this.update_att.setVisible(true);
	 		this.Mark_att.setVisible(true);
	 		this.Date_ID2.setVisible(true);
	 		this.ListCheckBox.setVisible(true);
	 		this.classroomItems.setVisible(false);
	 		//this.Dateid.setVisible(true);
		/*root=FXMLLoader.load(getClass().getResource("MarkAttendance.fxml"));
		appStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		appStage.setScene(scene);
		appStage.show();*/	
		
		
	}

     public void DisplayAttendance() throws ClassNotFoundException, SQLException
     
     {
    	
    	 this.Date_ID2.getText();
    	 m=new Main();
 		Data d1=Data.getInstance();
 		this.tempTeacher=d1.getT();
    	 Attendance ac=m.c.getAttendance(this.Date_ID2.getText());
    	 ArrayList<AttendanceItem>array=m.c.getAttendanceItem();
    	 ArrayList<Student>st=m.c.getStudents();
    	 this.checkBoxes.clear();
    	 if(ac!=null)
    		 
    	 {
    		 System.out.println("Displaying Attendance");
    		 
    		 for(int i=0; i<array.size(); i++)
    		 {
    			 System.out.println("Displaying Attendance1");
    			 
    			 System.out.println(ac.getAttendanceId()+" = "+array.get(i).getdate_id());
    			 if(ac.getAttendanceId()==array.get(i).getdate_id())
    			 {
    				 System.out.println("size: "+st.size());
    				for(int j=0; j<st.size(); j++)
    				{
    					CheckBox check=new CheckBox(st.get(j).getStudentName());
    					
    					System.out.println(st.get(j).getStudentName());
    					/*s=s+"           ";*/
    					if(array.get(j).getpresence()==1)
    					{
    						
    						check.setIndeterminate(true);
    						//s=s+"present";
    					}
    					else
    					{
    						
    						check.setIndeterminate(false);
    						//s=s+"absent";
    					}
    					this.checkBoxes.add(check);
    					
    				
    					
    				}
    				break;
    			 }
    		 }
    		 
    		 
    	     this.ListCheckBox.getItems().clear();
    		 this.ListCheckBox.getItems().addAll(checkBoxes);
    		 ListCheckBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    		 
    		 
    	 }
    		
    	 else
    	 {
    		 System.out.println("Invalid Date");
    	 }
    		 
    	 
    	 
    	 
    	 
    	 
     }
     
     
     public void DisplayStudentAttendanceMenu()
     {
    	 this.ListCheckBox.getItems().clear();
    	 this.ListCheckBox.setVisible(true);
    	 this.classroomItems.setVisible(false);
    	 
     }
     
     

     
     @FXML
     public void DisplayStudentAttendance() throws ClassNotFoundException, SQLException
     {

    	 System.out.println("Displayying students");
    	 
    	 this.Date_ID2.getText();
    	 m=new Main();
    	 Data d1=Data.getInstance();
    	 Student s=d1.getS();
    	// CheckBox check;
    	 Attendance ac=m.c.getAttendance(this.Date_ID2.getText());
    	 ArrayList<AttendanceItem> att=m.c.getAttendanceItem();
    	 for(int i=0; i<att.size(); i++)
    	 {
    		 if(att.get(i).getstd_id()==s.getStudent_id())
    		 {
    			 CheckBox check=new CheckBox(s.getStudentName());
    			 if((att.get(i).getpresence()==1))
    			 {
    				 check.setIndeterminate(true);
    			 }
    			 else
    			 {
    				 check.setIndeterminate(false);
    			 }
    			 
    			 this.ListCheckBox.getItems().clear();
    	    	 this.ListCheckBox.getItems().add(check);
    			 break;
    		 }
    	 }
    	 
    	
    	 
    	 
    	 
    	 
     }
	
	///////////////////////////////////////////////////////////////////////////////////
	//--------------------------------------------------------------------------------
	/////////////////////////////////////////////////////////////////////////////////
	
	
	//-----------------------------------------------------
	//-----------------Add Assessment Item-----------------
	//-----------------------------------------------------
	
	//
	
	
	
	
	
	
	
	//Change theme-------------------------------------
	
	public void themeSwitchDark(ActionEvent e) {
		
		Theme t1=Theme.getInstance();
		t1.setTheme();

		setMyTheme(t1);
		
	}

	public void setMyTheme(Theme t1) {
		
		
		classfeed.setStyle(t1.getFeedBG());
		bannerpane.setStyle(t1.getAllBG());
		descriptionPane.setStyle(t1.getAllBG());
		listpane.setStyle(t1.getAllBG());
		OptionsPane.setStyle(t1.getAllBG());
		
	}
	//------------------------------------------------
	
	
	
	
	//-----------------------------------------------------------------
	
	public void initData(String t_name,String email) {
		
		teacherName.setText(t_name);
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*Main q = null;
		Data d2=Data.getInstance();
		tempTeacher=d2.getT();

		try {
			q=new Main();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(q!=null&&adminName!=null) {
			//System.out.println("This is admin "+m.c.getAdmin().getTeacher_name());
			adminName.setText("Welcome Back "+q.c.getAdmin().getTeacher_name()+"!");
			
		}
		int k=1;
		if(classroomItems!=null&&k==1) {
			
			
			//Data d1=Data.getInstance();
			//tempTeacher=d1.getT();
			if(tempTeacher!=null) {
				if(OptionsPane!=null) {
					
					Theme t2=Theme.getInstance();
					setMyTheme(t2);
					
				}
				addAnnoun.setVisible(true);
				addAssess.setVisible(true);
				imgSrc=new Image(getClass().getResource("assets\\TeacherLogo.png").toExternalForm());
				accIcon.setImage(imgSrc);
				teacherName.setText("Welcome "+tempTeacher.getTeacher_name()+"!");
				//System.out.println(d1.getTname());
				
				//System.out.println(Data.Tmail+"ahjhdjh");
				classroomItems.getItems().add("Nothing at this moment");
				courseName.setText("Course Name: "+q.c.getCname());
			}
		}*/
		Main q = null;
		
		Data d2=Data.getInstance();
		tempTeacher=d2.getT();
		try {
			q=new Main();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(q!=null&&adminName!=null) {
			//System.out.println("This is admin "+m.c.getAdmin().getTeacher_name());
			adminName.setText("Welcome Back "+q.c.getAdmin().getTeacher_name()+"!");
			
		}
		int k=1;
		if(classroomItems!=null&&k==1) {
			
			
			Data d1=Data.getInstance();
			tempTeacher=d1.getT();
			if(tempTeacher!=null) {
				addAnnoun.setVisible(true);
				addAssess.setVisible(true);
				this.Display_att.setVisible(false);
		 		this.update_att.setVisible(false);
		 		this.Mark_att.setVisible(false);
		 		this.Date_ID2.setVisible(false);
			 	this.StudentAttendance.setVisible(false);
			 	this.Display_att1.setVisible(false);
		 		//this.Date_display.setVisible(false);
		 		//this.Dateid.setVisible(false);
		 		this.ListCheckBox.setVisible(false);
				imgSrc=new Image(getClass().getResource("assets\\TeacherLogo.png").toExternalForm());
				accIcon.setImage(imgSrc);
				teacherName.setText("Welcome "+tempTeacher.getTeacher_name()+"!");
				//System.out.println(d1.getTname());
				
				//System.out.println(Data.Tmail+"ahjhdjh");
				classroomItems.getItems().add("Nothing at this moment");
				courseName.setText("Course Name: "+q.c.getCname());
			}
		}
		
		
		
		
		if(classroomItems!=null&&k==1) {
			classroomItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
	
				@Override
				public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
					// TODO Auto-generated method stub
					
					item = classroomItems.getSelectionModel().getSelectedItem();
					itemDescription.setDisable(false);
					itemDescription.setText(item);
					
					for(int i=0;i<AssessmentCatalog.getObj().size();i++) {
						if(item!=null) {
							if(item.equals(AssessmentCatalog.getObj().get(i).getText())) {
								
								atemp=AssessmentCatalog.getObj().get(i);
								
							}
						}
								
					}//for
					if(atemp!=null) {
						success.setText("Due Date: "+atemp.get_Due_Date());
					}
				}
			
			});
		}
		
		/*
		
		
		
		/////////////////////////////////////////////
		if(classroomItems!=null&&k==1) {
			
			
			Data d1=Data.getInstance();
			if(d1.getS()!=null) {
				if(OptionsPane!=null) {
					
					Theme t2=Theme.getInstance();
					setMyTheme(t2);
					
				}
				addAnnoun.setVisible(false);
				addAssess.setVisible(false);
				imgSrc=new Image(getClass().getResource("assets\\student_logo.png").toExternalForm());
				accIcon.setImage(imgSrc);
				tempStd=d1.getS();
				teacherName.setText("Welcome "+tempStd.getStudentName()+"!");
				//System.out.println(d1.getTname());
				
				//System.out.println(Data.Tmail+"ahjhdjh");
				classroomItems.getItems().add("Nothing at this moment");
				courseName.setText("Course Name: "+q.c.getCname());
			}	
			
		}
		
		
		
		
		
		if(classroomItems!=null) {
			try {
				m=new Main();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Student>students=m.c.getStudents();
			for(int i=0; i<students.size(); i++)
			{
				CheckBox check=new CheckBox(students.get(i).getStudentName());
				checkBoxes.add(check);
	
			}
			ListCheckBox.getItems().addAll(checkBoxes);
			ListCheckBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		/////////////
		
		}*/
		
	if(classroomItems!=null&&k==1) {
			
			
			Data d1=Data.getInstance();
			if(d1.getS()!=null) {
				this.Display_att.setVisible(false);
	 	 		this.update_att.setVisible(false);
	 	 		this.Mark_att.setVisible(false);
	 	 		this.Date_ID2.setVisible(true);
	 	 		this.attendence.setVisible(false);
	 		 	this.StudentAttendance.setVisible(true);
	 		    this.Display_att1.setVisible(true);
	 	 	//	this.Dateid.setVisible(false);
				addAnnoun.setVisible(false);
				addAssess.setVisible(false);
				this.ListCheckBox.setVisible(false);
				imgSrc=new Image(getClass().getResource("assets\\student_logo.png").toExternalForm());
				accIcon.setImage(imgSrc);
				tempStd=d1.getS();
				
				teacherName.setText("Welcome "+tempStd.getStudentName()+"!");
				//System.out.println(d1.getTname());
				
				//System.out.println(Data.Tmail+"ahjhdjh");
				classroomItems.getItems().add("Nothing at this moment");
				courseName.setText("Course Name: "+q.c.getCname());
			}
			
			
		}
		
		
		
		
		
		if(classroomItems!=null && this.Date_ID2!=null) {
			try {
				m=new Main();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Student>students=m.c.getStudents();
			for(int i=0; i<students.size(); i++)
			{
				System.out.println(students.get(i).getStudentName());
				CheckBox check=new CheckBox(students.get(i).getStudentName());
				checkBoxes.add(check);
	
			}
			
			
			
			ListCheckBox.getItems().addAll(checkBoxes);
			ListCheckBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		/////////////
		
		}
	
		
		
		
	
	
		
		
		
	}
	
	
	
	
	

}
