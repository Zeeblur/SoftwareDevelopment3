package observer;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import skywars.GameState;

public class GridElement implements Observer
{
	// hold array indexes for this instance of grid element
	private int xCoord;
	private int yCoord;
	
	private boolean collision = false;
	
	private ArrayList<JLabel> shipLabels = new ArrayList<JLabel>();
	
	// encapsulating label for boxlayout
	private JLabel panelLayoutLabel;
	
	private ImageIcon backgroundImg = new ImageIcon("images/spacenew.jpg");
	private ImageIcon blackHole = new ImageIcon("images/bh.jpg");
	private ImageIcon masterShip = new ImageIcon("images/ship2.png");
	private ImageIcon shooter = new ImageIcon("images/evilShip1.png");
	private ImageIcon star = new ImageIcon("images/evilShip2.png");
	private ImageIcon cruiser = new ImageIcon("images/evilShip3.png");
	
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
		
		String gridIndex = loc[xCoord][yCoord];
		
		//ensure collision is false at start of turn
		collision = false;
		boolean master = false;
		boolean enemy = false;
		
		// clear curret ships
		for (JLabel ship : shipLabels)
		{
			panelLayoutLabel.remove(ship);
		}
		
		// see what's in my grid()
		if (gridIndex != null)
		{
			//JLabel label = null;
			// if contains mastership if contains etc
			if (gridIndex.contains("Ma"))
			{
				master = true;
				JLabel label = new JLabel(masterShip);
				shipLabels.add(label);
			    panelLayoutLabel.add(label);
			}

			
			if (gridIndex.contains("Sho"))
			{
				enemy = true;
				JLabel label = new JLabel(shooter);
				shipLabels.add(label);
			    panelLayoutLabel.add(label);
			}
			
			if (gridIndex.contains("Sta"))
			{
				enemy = true;
				JLabel label = new JLabel(star);
				shipLabels.add(label);
			    panelLayoutLabel.add(label);
			}
			
			if (gridIndex.contains("Cru"))
			{
				enemy = true;
				JLabel label = new JLabel(cruiser);
				shipLabels.add(label);
			    panelLayoutLabel.add(label);
			}
			

		}
		
		panelLayoutLabel.revalidate();
	    panelLayoutLabel.repaint();
	    
	    // collision detection if master and enemy are there
	    if (master && enemy){
	    	collision = true;
	    	System.out.println("FIGHT!");
	    }
	    
	}
	
	// getter for layered panel to add to game pane
	public JLabel getPanel()
	{
		return this.panelLayoutLabel;
	}
	
	public Boolean getCollision()
	{
		return this.collision;
	}

}
