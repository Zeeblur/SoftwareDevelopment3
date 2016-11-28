package gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import observer.GameManager;
import observer.GridElement;
import observer.Observer;
import skywars.GameState;

public class GameWindow extends JFrame implements Observer {

	private JPanel contentPane;
	private JToolBar controlPane;
	private GridElement[][] gridcell = new GridElement[4][4];
	
	private int xDim = 640;
	private int yDim = 480;
	
	private JLabel enemyCounter;
	
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
		controlPane = new JToolBar();
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
		
		InitialiseBtns();
		
		// add radio btns for user to select which mode the ship is in		
		InitialiseRB();

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
		
		// zero to start with 
		enemyCounter = new JLabel ("Enemies: 0");
		controlPane.add(enemyCounter);
	}
	
	// method to intialise the buttons for the gui with their handlers
	public void InitialiseBtns()
	{
		// create undo btn
		JButton undoBtn = new JButton("Undo");
		
		// add event listener
		undoBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("click");
				myMan.undo();
			}
		});
		
		// add undo button to toolbar
		controlPane.add(undoBtn);
		
		// Create Move button
		JButton moveBtn = new JButton("Move");
		moveBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("click moe");
				myMan.nextTurn();
			}
		});
		
		controlPane.add(moveBtn); // add to toolbar
	}
	
	// Method to init Radio buttons for strategy selection
	public void InitialiseRB()
	{
		// create new button group to group switches together (Auto handles unchecking when the other is checked)
		ButtonGroup bg1 = new ButtonGroup( );

		// defence button switches to defence mode, is selected by default
		JRadioButton defendRBtn = new JRadioButton ("Defence Mode", true);
		
		// add event handler to tell GM which ship mode
		defendRBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Changed to Defensive Position");
				// false as (not attacking)
				myMan.changeShipMode(false);
			}
		});		
		
		bg1.add(defendRBtn); // add to btn group
		
		// Attack button switches to attack mode, is unchecked by default
		JRadioButton attackRBtn = new JRadioButton ("Attack Mode");
		
		attackRBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// true as attacking
				myMan.changeShipMode(true);
			}
		});
		
		bg1.add(attackRBtn); // add to btn group
		
		
		// add btns to toolbar 
		controlPane.add(defendRBtn);
		controlPane.add(attackRBtn);
	}

	// getter for grid array
	public GridElement[][] getGrid()
	{
		return this.gridcell;
	}
	
	// set reference to game man so can call when btn clicked
	public void setManager(GameManager man)
	{
		this.myMan = man;
	}

	// update from gm of the new gamestate
	@Override
	public void update(GameState state)
	{
		enemyCounter.setText("Enemies: " + state.getEnemies().size());
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOH");
	}
}
