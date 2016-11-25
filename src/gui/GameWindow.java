package gui;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameWindow extends JFrame {

	private JPanel contentPane;
	private JLayeredPane[][] grid = new JLayeredPane[4][4]; // create 2D array of buttons for 4x4 grid
	
	private int xDim = 640;
	private int yDim = 480;

	
	private ImageIcon spaceTile = new ImageIcon("images/space.png");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		// add tool bar and game grid to split pane.
		JToolBar controlPane = new JToolBar();
		controlPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		controlPane.setFloatable(false); // don't allow user to move toolbar
		
		// grid panel for buttons 4 by 4 layout
		JPanel gamePane = new JPanel();
		gamePane.setLayout(new GridLayout(4, 4, 0, 0));
		
		// create split pane 
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, gamePane, controlPane)
		{
			// override divider location so it cannot be moved by user
			private final int location = 350;
		    {
		        setDividerLocation( location );
		    }
		    @Override
		    public int getDividerLocation() {
		        return location ;
		    }
		    @Override
		    public int getLastDividerLocation() {
		        return location ;
		    }
		
		};
		
		splitPane.setResizeWeight(1.0); 		// ensure toolbar stays the same size and the grid resizes
		contentPane.add(splitPane);				// show split pane in window
		

	
		JButton undoBtn = new JButton("Undo");
		controlPane.add(undoBtn);		

		// Add buttons to game grid
		int row = 0;
		// for each row, add 4 buttons and ensure they are disabled.
		while (row < 4)
		{
			for (int i = 0; i < 4; ++i)
			{
				// create a new button and add to layered pane
				JButton newButton = new JButton();
				newButton.setEnabled(true);
				newButton.setOpaque(false);
				
				// new layered panel to be stored in 2d array as a grid. 
				// images can be added as layers.
				JLayeredPane panel = new JLayeredPane();
				newButton.setBounds(0, 0, xDim, yDim);
				newButton.setVerticalAlignment(JButton.TOP);
				newButton.setHorizontalAlignment(JButton.CENTER);

				panel.add(newButton, 0);
								
				JLabel label = new JLabel(spaceTile);
				label.setVerticalAlignment(JLabel.CENTER);
		        label.setHorizontalAlignment(JLabel.CENTER);
		        label.setBounds(0,0, xDim, yDim);
				panel.add(label, 0);
				
				// add whole component to game pane
				gamePane.add(panel);
				grid[row][i] = panel;

			}
			row++;
		}
		
	}

}
