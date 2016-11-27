package skywars;

import java.util.*;

import observer.GridElement;
import factory.Ship;

// store all objects
public class GameState
{
	private Ship player;
	private ArrayList<Ship> enemies;
	
	private String[][] cellLocations = new String[4][4];
	
	public GameState(Ship master, ArrayList<Ship> en)
	{
		// reference to players 
		this.player = master;
		this.enemies = en;
		
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
}