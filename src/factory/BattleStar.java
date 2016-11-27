package factory;

public class BattleStar extends Ship {
	
	// default constructor
	public BattleStar()
	{
		this.description = "BattleStar";
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
