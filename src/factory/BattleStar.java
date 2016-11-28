package factory;

import java.awt.Point;

public class BattleStar extends Ship {
	
	// default constructor
	public BattleStar()
	{
		this.description = "Battle Star";
		this.position = new Point(0,0);
	}
	
	// copy constructor
	public BattleStar(BattleStar newShip)
	{
		this.description = newShip.description;
		this.position = newShip.position;
	}

	// return a deepcopy of a concrete implementation of "SHIP"
	@Override
	public Ship deepCopy()
	{	
		return new BattleStar(this);
	}
}
