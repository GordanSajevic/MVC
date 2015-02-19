package ba.bitcamp.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Class for contacts in phonebook
 * @author gordansajevic
 *
 */

public class Contact extends Application {

	private int id;
	private String name, surname, number;
	private final static String tableName = "contacts";

	/**
	 * Constructor with no parameters
	 */
	
	public Contact() {

		this.id = -1;
		this.name = "unknown";
		this.surname = "unknown";
		this.number = "";

	}

	/**
	 * Constructor with three parameters.
	 * @param id
	 * @param name
	 * @param surname
	 */
	
	public Contact(int id, String name, String surname)
	{
		this.name = name;
		this.surname = surname;
		this.number = "";
		this.id = id;
	}
	
	/**
	 * Constructor with three parameters.
	 * Default value for id is -1
	 * @param name
	 * @param surname
	 * @param number
	 */
	
	public Contact(String name, String surname, String number) {

		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	/**
	 * Constructor with all four parameters
	 * @param id
	 * @param name
	 * @param surname
	 * @param number
	 */
	
	public Contact(int id, String name, String surname, String number) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	/**
	 * Method that returns contact with given id.
	 * We call find method from Application class, and give it id and table name.
	 * Than we put all values in strings, so we use them to create contact
	 * and return that contact
	 * @param id
	 * @return Contact
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

	/**
	 * Method that sends values in save method in Application class
	 * and checks if save method is valid, and returns boolean value.
	 * @return boolean
	 */
	
	public boolean save(){
		 String values = String.format("(?, '%s', '%s', '%s')", this.name, this.surname, this.number);
		 int id = Application.save(tableName, values);
		 if(id==-1)
		 {
			 return false;
		 }
		 else
		 {
			 this.id = id;
			 return true;
		 }
	}

	/**
	 * Method that returns array with all contacts. First, we use all() method
	 * from Application class to put all contacts in ResultSet. Then, we put all
	 * contact in linked list. After that, we create array, and put all contacts in array.
	 * @return Contact[]
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

	/**
	 * Method for updating contact. We call update method from Application class
	 */
	
	public void update(){
		String sql = String.format("name = '%s', surname = '%s', number = '%s'", this.name, this.surname, this.number);
		Application.update(tableName, this.id, sql);
	}
	
	/**
	 * Method for deleting contact. We use delete method from Application class
	 * @param id
	 */
	
	public static void delete(int id){
		Application.delete(tableName, id);
	}
	
	/**
	 * Getter for name and surname
	 * @return name + surname
	 */
	
	public String getDisplayName()
	{
		return this.name + " " + this.surname;
	}

	/**
	 * Method that creates array with all contacts
	 */
	
	@SuppressWarnings("unused")
	public static void list()
	{
		Contact[] all = Contact.all();
	}

	/**
	 * Getter for id
	 * @return id
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Getter for name
	 * @return name
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * @param name
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for surname
	 * @return surname
	 */
	
	public String getSurname() {
		return surname;
	}

	/**
	 * Setter for surname
	 * @param surname
	 */
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Getter for number
	 * @return number
	 */
	
	public String getNumber() {
		return number;
	}
	
	/**
	 * Setter for number
	 * @param number
	 */
	
	public void setNumber(String number) {
		this.number = number;
	}

}
