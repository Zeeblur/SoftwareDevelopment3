package factory;

import java.awt.Point;

public abstract class Ship
{
	protected Point position;
	protected String description;
	
	public void Move(){	};
	
	// returns position of ship
	public Point getPosition()
	{
		return this.position;
	};
	
	// getter for description of ship
	public String getDescription()
	{
		return this.description;
	}
}
