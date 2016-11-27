package skywars;

import gui.GameWindow;

import java.awt.EventQueue;

import observer.GameManager;

public class GameDemo
{
	private static GameManager myMan;
	
	public static void main(String[] args)
	{		
		// Launch application (show gui)
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow(); // create gui window
					frame.setVisible(true);				 // ensure it's visible
					frame.setResizable(false);			 // ensure it's fullscreen
					myMan = new GameManager(frame);		 // create game manager and give it reference to gui
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

		
	}
	
	

}
