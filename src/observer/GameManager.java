package observer;

import java.util.ArrayList;

import skywars.GameState;
import factory.Ship;
import factory.ShipFactory;
import gui.GameWindow;

public class GameManager implements Observable
{
	// hold game states. update cells as listeners.
	private GameState currentState;
	
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
		
		// create inital state
		currentState = new GameState(master, null);
		notifyListeners();
	}
	
	public void run()
	{
		
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
