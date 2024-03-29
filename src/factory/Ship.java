package factory;

import java.awt.Point;
import java.util.*;

public abstract class Ship
{
	protected Point position;
	protected String description;
	private boolean valid = false;
	
	// default super constructor
	public Ship(){}
	
	// deepCopy of ship must be implemented 
	public abstract Ship deepCopy();
	
	// move to a random neighbouring square (avoiding top left)
	public void Move()
	{	
		Point newPosition = null;
		
		// keep searching for valid move until found
		while (!valid)
		{
			newPosition = generatePoint();
			
			// black hole check
			if (newPosition.x == 0 && newPosition.y == 0)
			{
				// if invalid continue and choose new point
				continue;
			}
			
			// can't be off grid
			if (newPosition.x > 3 || newPosition.y > 3 || newPosition.x < 0 || newPosition.y < 0)
				continue;
			
			// can't move to same square
			if (newPosition.x == position.x && newPosition.y == position.y)
				continue;
			
			valid = true;
		}
		
		// set new position for ship
		position = newPosition;
		System.out.println(this.description + " is moving to " + position.x + "," + position.y);
			
	};
	
	public Point generatePoint()
	{
		Random rng = new Random();
		
		// (max - min  ) + 1 + min for random within a range
		int newX = rng.nextInt(((position.x+1) - (position.x-1)) + 1) + (position.x-1);
		int newY = rng.nextInt(((position.y+1) - (position.y-1)) + 1) + (position.y-1);
		
		return new Point(newX, newY);
	}
	
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
