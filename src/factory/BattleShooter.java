package factory;

public class BattleShooter extends Ship
{
	public BattleShooter()
	{
		this.description = "Battle Shooter";
	}
	
	// copy constructor
	public BattleShooter(BattleShooter newShip)
	{
		this.description = newShip.description;
		this.position = newShip.position;
	}

	// return a deepcopy of a concrete implementation of "SHIP"
	@Override
	public Ship deepCopy()
	{	
		return new BattleShooter(this);
	}
}
