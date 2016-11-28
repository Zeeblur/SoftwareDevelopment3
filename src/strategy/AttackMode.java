package strategy;

import java.awt.Point;
import java.util.ArrayList;

import factory.Ship;
import skywars.GameState;

public class AttackMode implements ShipMode {

	@Override
	public GameState react(GameState gs) {
		// TODO Auto-generated method stub
		System.out.println("ATTAAACK");
		
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
		
		// colliding enemies are not passed to the new gameState (killed) if there is less than 2.
		if (collidingEn.size() > 2)
		{
			System.out.println("I SHOULD DIE NOW");
			playerTemp = null;
		}
		
		return new GameState(playerTemp, totalEnemies);
	}

}
