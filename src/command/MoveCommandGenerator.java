package command;

import factory.Ship;

// class threaded to generate move commands for ships and give them to the fleet commander
public class MoveCommandGenerator extends Thread
{
	private Ship myShip;			    // assigned ship
	private FleetCommander myCommander; // instance of fleet commander who will execute movement commands from list
	
	// assigns the ship to move and the commander to execute the command
	public MoveCommandGenerator(Ship theShip, FleetCommander fComm)
	{
		this.myShip = theShip;
		this.myCommander = fComm;
	}
	
	// overrides run method for thread (creates a command then adds it to the fcommander's list
	@Override
	public void run()
	{
		Command movement = new MoveCommand(myShip);
		this.myCommander.addCommand(movement);
	}
}
