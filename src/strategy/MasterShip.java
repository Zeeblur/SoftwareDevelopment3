package strategy;

import java.awt.Point;
import java.util.Random;

import factory.BattleShooter;
import factory.Ship;

public class MasterShip extends Ship
{
	// store the only instance of the ship
	private static MasterShip instance = null;
	protected ShipMode myMode;
	
	// private constructor for use in singleton pattern	
	private MasterShip()
	{
		// initialise descriptions
		this.description = "Master Ship";
		
		// choose defence mode by default
		ShipMode mode = new DefenceMode();
		
		// set ships operational mode
		setShipMode(mode);
		
		// set random starting location for ship
		Random rng = new Random();
		this.position = new Point(rng.nextInt(3) + 1, rng.nextInt(4));
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
	
	// copy constructor
	public MasterShip(MasterShip newShip)
	{
		this.description = newShip.description;
		this.position = newShip.position;
	}

	// return a deepcopy of a concrete implementation of "SHIP"
	@Override
	public Ship deepCopy()
	{	
		return new MasterShip(this);
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
