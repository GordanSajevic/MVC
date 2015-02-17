package ba.bitcamp.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Contact extends Application {

	private int id;
	private String name, surname, number;
	private final static String tableName = "contacts";

	public Contact() {

		this.id = -1;
		this.name = "unknown";
		this.surname = "unknown";
		this.number = "";

	}
	
	public Contact(int id, String name, String surname)
	{
		this.name = name;
		this.surname = surname;
		this.number = "";
		this.id = id;
	}

	public Contact(String name, String surname, String number) {

		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	public Contact(int id, String name, String surname, String number) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

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
	
	public boolean save(){
		 String values = String.format("(?, '%s', '%s', '%s')", this.name, this.surname, this.number);
		 return Application.save(tableName, values);
	}
	
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
	
	public String getDisplayName()
	{
		return this.name + " " + this.surname;
	}
	
	public static void list()
	{
		Contact[] all = Contact.all();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}