package ba.bitcamp.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicationView extends MainView{
	
	protected static JPanel panel = null;
	private static JLabel label = null;
	private static JButton addButton = null;
	private static JButton showButton = null;

	public static void home()
	{
		panel = new JPanel();
		label = new JLabel("Welcome to BitBook");
		Font labelFont = new Font("SansSerif", Font.BOLD, 30);
		label.setFont(labelFont);
		addButton = new JButton("Add Contact");
		showButton = new JButton("Show Contacts");
		panel.add(label);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(addButton);
		showButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(showButton);
		MainView.replaceContent(panel);
	}

}
