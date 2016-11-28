package skywars;

import gui.GameWindow;
import gui.PopUp;

import java.awt.*;

import javax.swing.JDialog;

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
					frame.setManager(myMan);
					
					PopUp dialog = new PopUp();	// create pop-up window for fight
					dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
					dialog.setVisible(false);   // don't show up at first
					myMan.registerPop(dialog);	// register as a listener
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	

}
