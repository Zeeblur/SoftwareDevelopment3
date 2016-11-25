package command;

import java.util.ArrayList;

// class to hold and run movement commands
public class FleetCommander
{
	// store commands to list
	private ArrayList<Command> theCommands = new ArrayList<Command>();
	
	// method to add a command to the list
	public void addCommand(Command aCommand)
	{
		this.theCommands.add(aCommand);
	}
	
	// execute all the commands in the list
	public void execute()
	{
		for (Command command : this.theCommands)
			command.execute();
	}
}
