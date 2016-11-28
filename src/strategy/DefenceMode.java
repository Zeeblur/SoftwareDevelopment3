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
		Ship playerTemp = gs.getPlayer();
		Point playerPos = playerTemp.getPosition();
		
		// for every enemy in list. if in same square add to list
		
		ArrayList<Ship> totalEnemies = new ArrayList<Ship>();
		
		ArrayList<Ship> collidingEn = new ArrayList<Ship>();
		
		for (Ship s : gs.getEnemies())
		{
			// if in same square check, if not add to total
			if (s.getPosition().x == playerPos.x && s.getPosition().y == playerPos.y)
			{
				collidingEn.add(s);
			}
			else
			{
				// add unchanged enemy
				totalEnemies.add(s);
			}
			
		}
		
		// defensive can withstand 1
		if (collidingEn.size() > 1)
		{
			System.out.println("I SHOULD DIE");
			playerTemp = null;
		}
		
		return new GameState(playerTemp, totalEnemies);
	}

}
