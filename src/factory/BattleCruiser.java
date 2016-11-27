package factory;

public class BattleCruiser extends Ship
{
	public BattleCruiser()
	{
		this.description = "Battle Cruiser";
	}
	
	// copy constructor
	public BattleCruiser(BattleCruiser newShip)
	{
		this.description = newShip.description;
		this.position = newShip.position;
	}

	// return a deepcopy of a concrete implementation of "SHIP"
	@Override
	public Ship deepCopy()
	{	
		return new BattleCruiser(this);
	}
}
