package ba.bitcamp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Contact extends Application{
	
	private int id;
	private String name;
	private String surname;
	private String number;
	
	private final static String tableName = "Contacts";
	
	public Contact(int id, String name, String surname, String number)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}
	
	public Contact(String name, String surname, String number)
	{
		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}
	
	public Contact()
	{
		this.id = -1;
		this.name = "Unknown";
		this.surname = "Unknown";
		this.number = "";
	}

	public static Contact find(int id)
	{
		ResultSet res = Application.find(id, tableName);
		try {
			int cId = res.getInt("id");
			String cName = res.getString("Name");
			String cSurname = res.getString("Surname");
			String cNumber = res.getString("Number");
			return new Contact(cId, cName, cSurname, cNumber);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		if (name.length()<1)
		{
			System.err.println("Name cannot be empty field!");
		}
		else
		{
			this.name = name;
		}
	}

	public String getSurname() 
	{
		return surname;
	}

	public void setSurname(String surname) 
	{
		if (surname.length()<1)
		{
			System.err.println("Surname cannot be empty field!");
		}
		else
		{
			this.surname = surname;
		}
	}

	public String getNumber() 
	{
		return number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

}
