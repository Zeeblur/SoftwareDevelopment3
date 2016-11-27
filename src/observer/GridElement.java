package observer;

import java.awt.*;

import javax.swing.*;

import skywars.GameState;

public class GridElement implements Observer
{
	// hold array indexes for this instance of grid element
	private int xCoord;
	private int yCoord;
	
	private JLabel label;
	private JLabel label1;
	
	// encapsulating label for boxlayout
	private JLabel panelLayoutLabel;
	
	private ImageIcon backgroundImg = new ImageIcon("images/spacenew.jpg");
	private ImageIcon blackHole = new ImageIcon("images/bh.jpg");
	private ImageIcon masterShip = new ImageIcon("images/ship2.png");
	private ImageIcon shipTile = new ImageIcon("images/evilShip1.png");
	//private ImageIcon shipTile = new ImageIcon("images/ship2.png");
	
	public GridElement (int row, int i, int xDim, int yDim)
	{	
		// store grid's index
		this.xCoord = row;
		this.yCoord = i;
		
		// add background labels
		if(row == 0 && i == 0)
		{
			panelLayoutLabel = new JLabel(this.blackHole);
		}
		else
		{
			panelLayoutLabel = new JLabel(this.backgroundImg);
		}
		
	    this.panelLayoutLabel.setLayout(new BoxLayout(this.panelLayoutLabel, BoxLayout.X_AXIS));

	}

	// called by observable to all listeners
	@Override
	public void update(GameState state)
	{
		// what's in my grid?
		// if new then update
		
		// get locations from gamestate
		String[][] loc = state.getLocations();
		
		
		// see what's in my grid()
		if (loc[xCoord][yCoord] != null)
		{
			System.out.println("Something is here!!! "+ xCoord + yCoord);
		    label1 = new JLabel(masterShip);
		    panelLayoutLabel.add(label1);
		}
	}
	
	// getter for layered panel to add to game pane
	public JLabel getPanel()
	{
		return this.panelLayoutLabel;
	}

}
