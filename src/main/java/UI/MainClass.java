package UI;

import DataBase.hibernate;

public class MainClass 
{

	public MainClass() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//Man.launch(args);
		hibernate obj = new hibernate();
		MainUI.main(args);
	}
	

}
