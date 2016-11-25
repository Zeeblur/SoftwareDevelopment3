package strategy;

import factory.Ship;

public class MasterShip extends Ship
{
	// store the only instance of the ship
	private static MasterShip instance = null;
	protected ShipMode myMode;
	
	// private constructor for use in singleton pattern	
	private MasterShip()
	{
		// choose defence mode by default
		ShipMode mode = new DefenceMode();
		
		// set ships operational mode
		setShipMode(mode);
	}

	// singleton pattern for master ship
	public static synchronized MasterShip getInstance()
	{
		if(instance == null)
		{
			instance = new MasterShip();
		}
		return instance;
	}

	// getter and setter for ship mode
	public ShipMode getShipMode()
	{
		return this.myMode;
	}
	
	public void setShipMode(ShipMode newShipMode)
	{
		this.myMode = newShipMode;
	}
}
