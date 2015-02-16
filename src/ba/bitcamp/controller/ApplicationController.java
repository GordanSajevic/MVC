package ba.bitcamp.controller;

import ba.bitcamp.view.ApplicationView;
import ba.bitcamp.view.MainView;

public class ApplicationController {
	
	public static void main(String[] args) 
	{
		MainView.init();
		home();
	}
	
	public static void home()
	{
		ApplicationView.home();
	}

}
