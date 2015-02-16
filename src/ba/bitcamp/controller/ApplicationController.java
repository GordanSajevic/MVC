package ba.bitcamp.controller;

import java.sql.SQLException;
import ba.bitcamp.model.*;
import ba.bitcamp.model.Application;
import ba.bitcamp.view.ApplicationView;
import ba.bitcamp.view.MainView;

public class ApplicationController {
	
	public static void main(String[] args) 
	{
		MainView.init();
		try {
			Application.init();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		home();
	}
	
	public static void home()
	{
		Contact c = Contact.find(1);
		System.out.println(c.getName() + " " + c.getSurname() + " " + c.getNumber());
		ApplicationView.home();
	}

}
