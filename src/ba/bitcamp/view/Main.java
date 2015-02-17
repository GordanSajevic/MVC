package ba.bitcamp.view;

import java.awt.Container;
import javax.swing.JFrame;

public class Main {

/*
* Class creates main frame for our application
*/

	private static JFrame window = null;
	protected static int windowWidth = 400;
	protected static int windowHeight = 500;
/*
* Method initializes main frame
*/
	public static void init() {
		window = new JFrame("BitBook");
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

	}
/*
* Method sets visibility of main frame
*/
	protected static void setVisible() {
		window.validate();
		window.repaint();
		window.setVisible(true);
	}
/*
* Method sets panel for main frame
*/
	protected static void replaceContent(Container panel) {
		window.setContentPane(panel);
		setVisible();
	}

}
