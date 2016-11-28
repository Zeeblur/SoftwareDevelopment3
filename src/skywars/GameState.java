package skywars;

import java.util.*;

import observer.GridElement;
import factory.Ship;

// store all objects
public class GameState
{
	private Ship player;
	private ArrayList<Ship> enemies = new ArrayList<Ship>();
	
	private String[][] cellLocations = new String[4][4];
	
	public GameState(Ship master, ArrayList<Ship> en)
	{
		// deep copy of ships so changes elsewhere are not reflected 
		this.player = master.deepCopy();
		
		for (Ship enemy : en)
		{
			this.enemies.add(enemy.deepCopy());
		}	
		
		// initialise celllocations
		for (String[] stringArray : cellLocations)
		{
			for (String str : stringArray)
			{
				str = " ";
			}
		}
		
		// add data to celllocation
		populateLocations();

	}
	
	// retrieve location data from ships.
	public void populateLocations()
	{
		
		int x = player.getPosition().x;
		int y = player.getPosition().y;
		
		cellLocations[x][y] = cellLocations[x][y] + player.getDescription();
		
		if (enemies == null)
			return;
		
		for (Ship enemy : enemies)
		{
			x = enemy.getPosition().x;
			y = enemy.getPosition().y;
			
			cellLocations[x][y] += enemy.getDescription();
		}
	}
	
	public String[][] getLocations()
	{
		return this.cellLocations;
	}
	
	public Ship getPlayer()
	{
		return player;
	}
	
	public ArrayList<Ship> getEnemies()
	{
		return enemies;
	}
}
