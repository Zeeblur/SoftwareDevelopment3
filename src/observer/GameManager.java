package observer;

import java.util.ArrayList;
import java.util.Stack;

import command.Command;
import command.MoveCommand;
import skywars.GameState;
import factory.Ship;
import factory.ShipFactory;
import gui.GameWindow;

public class GameManager implements Observable
{
	// hold game states. update cells as listeners.
	private GameState currentState;
	
	private Stack<GameState> undoStack = new Stack<GameState>();
	private Stack<GameState> redoStack = new Stack<GameState>();
	
	
	// instance of factory
	private ShipFactory myFactory;
	
	// list of observers
	private GridElement[][] cellObservers;
	
	// constructor to create new master ship and get observers
	public GameManager(GameWindow gui)
	{
		myFactory = new ShipFactory();
		Ship master = myFactory.createMasterShip();
		
		registerListeners(gui.getGrid());
		
		// create initial state
		currentState = new GameState(master, null);
		undoStack.push(currentState);
		notifyListeners();
	}
	
	public void nextTurn()
	{
		// move all ships
		// check for collisions
		System.out.println("MOVE");
		Command movePlayer = new MoveCommand(this.currentState.getPlayer());
		movePlayer.execute();
		
		// create new gamestate and notify listeners(update gui)
		undoStack.push(currentState);
		currentState = new GameState(this.currentState.getPlayer(), null);
		notifyListeners();
	}
	
	public void undo()
	{		
		redoStack.push(currentState);
		currentState = undoStack.pop();
		notifyListeners();
	}

	@Override
	public void registerListeners(GridElement[][] cells)
	{
		cellObservers = cells;
	}


	@Override
	public void notifyListeners()
	{
		// update all observers with gamestate
		for (GridElement[] cellArray : cellObservers)
		{
			for (GridElement cell : cellArray)
			{
				cell.update(currentState);
			}
		}
	}


}
