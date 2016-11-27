package gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import observer.GameManager;
import observer.GridElement;

public class GameWindow extends JFrame {

	private JPanel contentPane;
	private GridElement[][] gridcell = new GridElement[4][4];
	
	private int xDim = 640;
	private int yDim = 480;
	
	private GameManager myMan;
	
	/**
	 * Create the frame.
	 */
	public GameWindow()
	{	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 640, 480);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		};
		
		splitPane.setResizeWeight(1.0); 		// ensure toolbar stays the same size and the grid resizes
		contentPane.add(splitPane);				// show split pane in window
		

	
		JButton undoBtn = new JButton("Undo");
		undoBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("click");
			}
		});
		
		controlPane.add(undoBtn);
		
		JButton moveBtn = new JButton("Move");
		moveBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("click moe");
				myMan.nextTurn();
			}
		});
		
		controlPane.add(moveBtn);

		// Add buttons to game grid
		int row = 0;
		// for each row, add 4 buttons and ensure they are disabled.
		while (row < 4)
		{
			for (int i = 0; i < 4; ++i)
			{
				// create a gridelement object at the right position
				GridElement cell = new GridElement(row, i, xDim, yDim);
				
				gamePane.add(cell.getPanel());
				gridcell[row][i] = cell;
			}
			row++; // increment to next row once 4 buttons have been added
		}
		
	}
	
	
	public GridElement[][] getGrid()
	{
		return this.gridcell;
	}
	
	
	// set reference to game man so can call when btn clicked
	public void setManager(GameManager man)
	{
		this.myMan = man;
	}
}
