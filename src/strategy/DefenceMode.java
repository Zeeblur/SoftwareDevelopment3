package strategy;

import skywars.GameState;

import java.awt.Point;
import java.util.*;

import factory.Ship;

public class DefenceMode implements ShipMode {

	@Override
	public GameState react(GameState gs) {
		// TODO Auto-generated method stub
		System.out.println("Defennnnd");
		
		// get player position.
		Point playerPos = gs.getPlayer().getPosition();
		
		// for every enemy in list. if in same square add to list
		
		ArrayList<Ship> totalEnemies = new ArrayList<Ship>();
		
		ArrayList<Ship> collidingEn = new ArrayList<Ship>();
		
		for (Ship s : gs.getEnemies())
		{
			// if in same square check, if not add to total
			if (s.getPosition() == playerPos)
			{
				collidingEn.add(s);
			}
			else
			{
				// add unchanged enemy
				totalEnemies.add(s);
			}
			
		}
		
		// defensive can withstand 2
		if (collidingEn.size() > 2)
		{
			System.out.println("I SHOULD DIE");
		}
		
		return new GameState(gs.getPlayer(), totalEnemies);
	}

}
