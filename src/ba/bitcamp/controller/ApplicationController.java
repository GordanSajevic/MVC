package ba.bitcamp.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import ba.bitcamp.model.*;
import ba.bitcamp.view.*;

public class ApplicationController {
	
	public static void home(){
		//show GUI 'HOME' 
		ApplicationView.home();
	}
	
	public static void addContact(){
		ApplicationView.addContact();
	}

	public static void create(String name, String surname, String number){
		Contact newContact = new Contact(name, surname, number);
		if ( newContact.save() == true ){
			JOptionPane.showMessageDialog(null, "Contact saved successfully " + newContact.getName(), "Contact added", JOptionPane.INFORMATION_MESSAGE);//TODO redirect to contact info
			home();
		} else {
			JOptionPane.showMessageDialog(null, "There is an invalid input", "Error saving Contact", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		
		/*TODO connecting to database */
		try {
			Application.init();
		} catch (SQLException e) {
			System.err.println("Applicazion.int " + e.getMessage());
			System.exit(1);
		}
		
		Main.init();
		home();
		
	}

	public static void list()
	{
		ApplicationView.list(Contact.all());
	}
	
}