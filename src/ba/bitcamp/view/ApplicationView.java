package ba.bitcamp.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ba.bitcamp.controller.ApplicationController;
import ba.bitcamp.model.*;

/**
 * Class creates main GUI for our application.
 * This class extends Main class(because of method replaceContent in Main class)
 * @author gordansajevic
 *
 */

public class ApplicationView extends Main {

	/**
	 *  Method creates pane, buttons and label for main frame.
	 *  After that, we put panel on main frame(with method replaceContent from main class)
	 */

	public static void home() {
		JPanel content = new JPanel();
		JLabel greeting = new JLabel("Welcome to BitBook");

		Font grretingFont = new Font("SansSerif", Font.BOLD, 30);
		greeting.setFont(grretingFont);

		content.add(greeting);

		JButton showContact = new JButton("Show Contacts"); // anonymous action listener
		showContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.list();
			}
		});
		JButton addContact = new JButton("Add Contact");
		addContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.addContact();
			}
		});

		content.add(addContact);
		content.add(showContact);

		Main.replaceContent(content);

	}

	/**
	 * Method creates panel for adding new contacts to phonebook.
	 * Again we use replaceContent from Main class
	 * In action listeners we call methods from 
	 * ApplicationController class to create new contact
	 * and to get back to the main panel.
	 */

	public static void addContact() {

		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		JLabel nameL = new JLabel("Name    ");
		final JTextField nameF = new JTextField(25);
		content.add(nameL);
		content.add(nameF);

		JLabel surnameL = new JLabel("Surname");
		final JTextField surnameF = new JTextField(25);
		content.add(surnameL);
		content.add(surnameF);

		JLabel numberL = new JLabel("Number ");
		final JTextField numberF = new JTextField(25);
		content.add(numberL);
		content.add(numberF);

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cName = nameF.getText();
				String cSurname = surnameF.getText();
				String cNumber = numberF.getText();
				ApplicationController.create(cName, cSurname, cNumber);
			}
		});
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.home();
			}
		});

		JPanel buttons = new JPanel();
		buttons.add(save);
		buttons.add(back);		
		content.add(buttons);
		replaceContent(content);

	}

	/**
	 * Class creates panel for list of all contacts in phonebook.
	 * Again we use replaceContent from Main class.
	 * In action listeners we use methods from ApplicationController
	 * to add new contact and get back to the main panel.
	 * @param all
	 */

	public static void list(Contact[] all)
	{
		int buttonHeight = 50;
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(ApplicationView.windowWidth-70, all.length*(buttonHeight+20)));
		
		if(all.length < 1)
		{
			JLabel message = new JLabel("Welcome to BitBook");
			Font greetingFont = new Font("SansSerif", Font.BOLD, 30);
			message.setFont(greetingFont);

			panel.add(message);
		}
		
		for (int i=0; i<all.length; i++)
		{
			JButton current = new JButton(all[i].getDisplayName());
			current.setName(Integer.toString(all[i].getId()));
			current.setPreferredSize(new Dimension(ApplicationView.windowWidth-75, buttonHeight));
			current.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//TODO redirect to contact
					JButton clicked = (JButton) e.getSource();
					int id = Integer.parseInt(clicked.getName())-1;
					System.out.println("User: " + all[id].getId());
					ApplicationController.show(id);
				}
			});
			panel.add(current);
		}
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.addContact();
				
			}
		});
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.home();
				
			}
		});
		panel.add(addButton);
		panel.add(backButton);
		JScrollPane sp = new JScrollPane(panel);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		Main.replaceContent(sp);
		
	}
	
	/**
	 * Class creates panel for with all informations about
	 * selected user from list.
	 * @param current
	 */
	
	public static void show(Contact current) {
		JPanel content = new JPanel();
		Font dataFont = new Font("SansSerif", Font.BOLD, 30);

		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		JLabel nameL = new JLabel("Name: ");
		JLabel name = new JLabel(current.getName());
		name.setFont(dataFont);
		content.add(nameL);
		content.add(name);

		JLabel surnameL = new JLabel("Surname: ");
		JLabel surname = new JLabel(current.getSurname());
		surname.setFont(dataFont);
		content.add(surnameL);
		content.add(surname);

		JLabel numberL = new JLabel("Number: ");
		JLabel number = new JLabel(current.getNumber());
		number.setFont(dataFont);
		content.add(numberL);
		content.add(number);

		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.update(current.getId());
			}

		});

		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.list();
			}

		});

		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null,
						"Are you sure?", "Delete?", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					ApplicationController.delete(current.getId());
				} else {
					return;
				}
			}
		});

		JPanel buttons = new JPanel();
		buttons.add(back);
		buttons.add(update);
		buttons.add(delete);
		content.add(buttons);
		replaceContent(content);

	}

	/**
	 * Class creates panel for updating selected contact from list
	 * @param current
	 */
	
	public static void updateContact(Contact current) {

		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		JLabel nameL = new JLabel("Name: ");
		JTextField nameF = new JTextField(current.getName(), 25);
		content.add(nameL);
		content.add(nameF);

		JLabel surnameL = new JLabel("Surname: ");
		JTextField surnameF = new JTextField(current.getSurname(), 25);
		content.add(surnameL);
		content.add(surnameF);

		JLabel numberL = new JLabel("Number: ");
		JTextField numberF = new JTextField(current.getNumber(), 25);
		content.add(numberL);
		content.add(numberF);

		JButton submit = new JButton("Save Update");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * get the data from the input and send it to the create method
				 */
				String cName = nameF.getText();
				String cSurname = surnameF.getText();
				String cNumber = numberF.getText();
				current.setName(cName);
				current.setSurname(cSurname);
				current.setNumber(cNumber);
				ApplicationController.update(current);
			}

		});

		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.show(current.getId());
			}

		});

		JPanel buttons = new JPanel();
		buttons.add(submit);
		buttons.add(back);
		content.add(buttons);
		replaceContent(content);
	}

	
}
