package ba.bitcamp.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import ba.bitcamp.model.*;
import ba.bitcamp.view.*;

/**
 * Controller class that connects classes from model package 
 * with classes from view package
 * @author gordansajevic
 *
 */

public class ApplicationController {

	/**
	 *  Method that calls home() method from ApplicationView class.
	 * This method enables main GUI for out frame
	 */
	
	public static void home(){
		//show GUI 'HOME' 
		ApplicationView.home();
	}

	/**
	 * Method that calls addContact() method from ApplicationView class.
	 * This method creates panel for adding new contacts
	 */
	
	public static void addContact(){
		ApplicationView.addContact();
	}
	
	/**
	 * Method that creates new contact for adding in phonebook.
	 * We use JOptionPane for confirmation that adding was successfull,
	 * or warning if adding failed. We call save() method from Contact class
	 * @param name
	 * @param surname
	 * @param number
	 */
	
	public static void create(String name, String surname, String number){
		Contact newContact = new Contact(name, surname, number);
		if ( newContact.save() == true ){
			JOptionPane.showMessageDialog(null, "Contact saved successfully " + newContact.getName(), "Contact added", JOptionPane.INFORMATION_MESSAGE);//TODO redirect to contact info
			show(newContact.getId());
		} else {
			JOptionPane.showMessageDialog(null, "There is an invalid input", "Error saving Contact", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 *  Method initializes main frame for our application and connection with database
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		/*TODO connecting to database */
		try {
			Application.init("BitBook");
		} catch (SQLException e) {
			System.err.println("Applicazion.int " + e.getMessage());
			System.exit(1);
		}
		
		Main.init();
		home();
		
	}

	/**
	 *  Method that calls list() method from ApplicationView class
	 *  and sends it array with all contacts
	 */
	
	public static void list()
	{
		ApplicationView.list(Contact.all());
	}
	
	public static void show(int id)
	{
		Contact c = Contact.find(id);
		ApplicationView.show(c.getName(), c.getSurname(), c.getNumber());
	}
	
}
