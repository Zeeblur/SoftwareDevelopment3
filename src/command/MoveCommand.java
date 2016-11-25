package command;

import factory.Ship;

public class MoveCommand implements Command {

	private Ship theShip; // the ship to move
	
	// constructor assigns move command to specific ship
	public MoveCommand(Ship aShip)
	{
		this.theShip = aShip;
	}
	
	// move the ship
	@Override
	public void execute()
	{
		this.theShip.Move();
	}

}
