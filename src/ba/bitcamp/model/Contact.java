package ba.bitcamp.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/*
* Class for contacts in phonebook
*/

public class Contact extends Application {

	private int id;
	private String name, surname, number;
	private final static String tableName = "contacts";
/*
* Constructor with no parameters
*/
	public Contact() {

		this.id = -1;
		this.name = "unknown";
		this.surname = "unknown";
		this.number = "";

	}
/*
* Constructor with three parameters.
*/	
	public Contact(int id, String name, String surname)
	{
		this.name = name;
		this.surname = surname;
		this.number = "";
		this.id = id;
	}
/*
* Constructor with three parameters.
* Default value for id is -1
*/
	public Contact(String name, String surname, String number) {

		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}
/*
* Constructor with all four parameters
*/
	public Contact(int id, String name, String surname, String number) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

/*
* Method that returns contact with given id.
* We call find method from Application class, and give it id and table name.
* Than we put all values in strings, so we use them to create contact
* and return that contact
*/
	public static Contact find(int id) {
		ResultSet res = Application.find(id, tableName);
		try {
			int cid = res.getInt("id");
			String cName = res.getString("name");
			String cSurname = res.getString("surname");
			String cNumber = res.getString("number");
			return new Contact(cid, cName, cSurname, cNumber);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
/*
* Method that sends values in save method in Application class
* and checks if save method is valid, and returns boolean value.
*/
	public boolean save(){
		 String values = String.format("(?, '%s', '%s', '%s')", this.name, this.surname, this.number);
		 return Application.save(tableName, values);
	}
/*
* Method that returns array with all contacts. First, we use all() method
* from Application class to put all contacts in ResultSet. Then, we put all
* contact in linked list. After that, we create array, and put all contacts in array.
*/	
	public static Contact[] all()
	{
		ResultSet rs = Application.all(tableName, "ID, name, surname");
		if (rs == null)
		{
			return new Contact[0];
		}
		LinkedList<Contact> cl = new LinkedList<Contact>();
		try {
			while(rs.next())
			{
				int id = rs.getInt("id");
				String cName = rs.getString("name");
				String cSurname = rs.getString("surname");
				cl.add(new Contact(id, cName, cSurname));
			}
			Contact[] all = new Contact[cl.size()];
			cl.toArray(all);
			return all;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return new Contact[0];
		}
	}
/*
* Getter for name and surname
*/
	public String getDisplayName()
	{
		return this.name + " " + this.surname;
	}
/*
* Method that creates array with all contacts
*/	
	public static void list()
	{
		Contact[] all = Contact.all();
	}
/*
* Getter for id
*/
	public int getId() {
		return id;
	}
/*
* Getter for name
*/
	public String getName() {
		return name;
	}
/*
* Setter for name
*/
	public void setName(String name) {
		this.name = name;
	}
/*
* Getter for surname
*/
	public String getSurname() {
		return surname;
	}
/*
* Setter for surname
*/
	public void setSurname(String surname) {
		this.surname = surname;
	}
/*
* Getter for number
*/
	public String getNumber() {
		return number;
	}
/*
* Setter for number
*/
	public void setNumber(String number) {
		this.number = number;
	}

}
