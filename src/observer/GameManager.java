package observer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import command.Command;
import command.FleetCommander;
import command.MoveCommand;
import command.MoveCommandGenerator;
import skywars.GameState;
import strategy.MasterShip;
import factory.Ship;
import factory.ShipFactory;
import gui.GameWindow;
import gui.PopUp;
import strategy.*;

public class GameManager implements Observable
{
	// hold game states. update cells as listeners.
	private GameState currentState;
	
	private Stack<GameState> undoStack = new Stack<GameState>();
	
	
	// instance of factory
	private ShipFactory myFactory;
	
	// list of observers
	private ArrayList<Observer> cellObservers = new ArrayList<Observer>();
	private GridElement[][] gridcells;
	
	// constructor to create new master ship and get observers
	public GameManager(GameWindow gui)
	{
		myFactory = new ShipFactory();
		Ship master = myFactory.createMasterShip();
		
		registerListeners(gui);
		
		// create initial state
		currentState = new GameState(master, new ArrayList<Ship>());
		undoStack.push(currentState);
		notifyListeners();
	}
	
	public void nextTurn()
	{

		FleetCommander fComm = new FleetCommander();
		
		// move all ships
		
		// store all generated commands 
		MoveCommandGenerator[] movements = new MoveCommandGenerator[this.currentState.getEnemies().size()];
		for(int i = 0; i < this.currentState.getEnemies().size(); ++i)
		{
			// generate a command to move for a specific ship
			MoveCommandGenerator mcg = new MoveCommandGenerator(this.currentState.getEnemies().get(i), fComm);
			
			// store command
			movements[i] = mcg;
			
			// start thread
			movements[i].start();
		}
		
		
		// ensure threads are completed
		for (int i = 0; i < this.currentState.getEnemies().size(); i++)
		{
			try
			{
				movements[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Command ships to move
		fComm.execute();
		
		spawn(); // after movement roll for spawn of new ship
		
		Command movePlayer = new MoveCommand(this.currentState.getPlayer());
		movePlayer.execute();
		
		// create new gamestate and notify listeners(update gui)
		undoStack.push(currentState);
		currentState = new GameState(this.currentState.getPlayer(), this.currentState.getEnemies());
		notifyListeners();
		
		// check for collisions
		CollisionDetection();
	}
	
	public void spawn()
	{
		// 1 in three chance of spawn
		Random rng = new Random();
		//if (rng.nextInt(3) + 1 == 2)
		{
			Ship enemy = myFactory.createEnemy();
			this.currentState.getEnemies().add(enemy); // this enemy plus current enemies in sky
			System.out.println(enemy.getDescription() + " has entered the fray!");	
		}
	}
	
	public void CollisionDetection()
	{
		//System.out.println("MOVE");
		
		// see if something shares master square. if so check what mode master is in
		// death of ship
		for (GridElement[] cellArray : gridcells)
		{
			for (GridElement cell : cellArray)
			{
				if (cell.getCollision())
				{
					// cast player as master ship to resolve conflicts according to it's mode
					MasterShip m = (MasterShip) this.currentState.getPlayer();
					
					// set collision to be true
					this.currentState.setCollision(true);
					
					// notify the gui that a pop up is needed
					notifyListeners();

					// resolveCollisons
					GameState gs = m.resolveCollision(this.currentState);
					
					// update game
					currentState = gs;
					notifyListeners();
					
					return;
				}
			}
		}
		
	}
	
	public void undo()
	{		
		//GameState lastMove = undoStack.pop();
		currentState = undoStack.pop();//new GameState(undoStack.pop().getPlayer(), null);
		notifyListeners();
	}

	// register all the listeners for the GM
	@Override
	public void registerListeners(GameWindow gui)
	{
		// store the grid elements then add each in turn to the observer list
		gridcells = gui.getGrid();
		for (GridElement[] cellArray : gridcells)
		{
				
			for (GridElement cell : cellArray)
			{
				cellObservers.add(cell);
			}
		}
		
		// add the gui as an observer for amount of enemies 
		cellObservers.add(gui);
	}
	
	public void registerPop(PopUp dialog)
	{
		cellObservers.add(dialog);
	}


	// update function for the observers (gridcells and gui)
	@Override
	public void notifyListeners()
	{
		// update all observers with gamestate
		for (Observer l : cellObservers)
		{
			l.update(currentState);
		}	
	}
	
	public void changeShipMode(Boolean att)
	{
		MasterShip newPlayer = (MasterShip)this.currentState.getPlayer().deepCopy();
		
		System.out.println("Changed to Attack Mode");
		// if attack is true create attack mode etc		
		newPlayer.setShipMode( att ? new AttackMode() : new DefenceMode());
		
		// update current gamestate and notify changes. add new player and old enemies 
		currentState = new GameState(newPlayer, this.currentState.getEnemies());
		notifyListeners();
			
	}
		
	public int getEnemyCount()
	{
		return this.currentState.getEnemies().size();
	}


}
