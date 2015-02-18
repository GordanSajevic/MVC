package ba.bitcamp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ba.bitcamp.model.Application;
import ba.bitcamp.model.Contact;

public class ModelTest {
	
	private Connection db;
	
	private static void testFind()
	{
		Contact c = Contact.find(1);
		if (!c.getName().equals("Bob") || !c.getSurname().equals("Bobs") || !c.getNumber().equals("1234565"))
		{
			System.err.println("Contact not equal");
		}
		c = Contact.find(4);
		if(c != null)
		{
			System.err.println("Found non existing contact");
		}
	}
	
	@SuppressWarnings("unused")
	private static void testAll()
	{
		Contact[] all = Contact.all();
		if(all.length != 3)
		{
			System.err.println("Length does not match");
		}
		if (!all[0].getName().equals("Bob") || !all[0].getSurname().equals("Bobs"))
		{
			System.err.println("Contact not equal");
		}
		if (!all[0].getName().equals("Jeff") || !all[0].getSurname().equals("Jefferson"))
		{
			System.err.println("Contact not equal");
		}
		if (!all[2].getName().equals("Jane") || !all[2].getSurname().equals("Janice"))
		{
			System.err.println("Contact not equal");
		}
	}
	
	public static void main(String[] args) {
		try {
			Application.init("BitBookTest");
		} catch (SQLException e) {
			System.err.println("Test failed: no connection");
			System.exit(1);
		}
		new Contact(1, "Bob", "Bobs", "1234565").save();
		new Contact(1, "Jeff", "Jefferson", "3214565").save();
		new Contact(1, "Jane", "Janice", "3461565").save();
		
		
		System.out.println("Testing find: ");
		testFind();
		System.out.println("Testing all: ");
		testAll();
		System.out.println("Done");
		

	}

}
