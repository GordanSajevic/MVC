package ba.bitcamp.view;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * Class creates main frame for our application
 * @author gordansajevic
 *
 */

public class Main {

	/**
	 * Method initializes main frame
	 */

	private static JFrame window = null;
	protected static int windowWidth = 400;
	protected static int windowHeight = 500;

	/**
	 * Method that initializes main frame
	 */
	
	public static void init() {
		window = new JFrame("BitBook");
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

	}
	
	/**
	 * Method that sets visibility of main frame
	 */
	
	protected static void setVisible() {
		window.validate();
		window.repaint();
		window.setVisible(true);
	}

	/**
	 * Method that sets panel for main frame
	 * @param panel
	 */
	
	protected static void replaceContent(Container panel) {
		window.setContentPane(panel);
		setVisible();
	}

}
