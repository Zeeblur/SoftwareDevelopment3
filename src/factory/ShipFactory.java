package factory;

import java.util.Random;

import strategy.MasterShip;

public class ShipFactory
{
	public Ship createMasterShip()
	{
		// returns instance of mastership
		Ship master = MasterShip.getInstance();
		return master;
	}
	
	
	// create a randomly generated enemy
	public Ship createEnemy()
	{
		Ship newShip = null;
		
		Random rng = new Random();
		int value = rng.nextInt(2);
		
		switch (value)
		{
			case 0:
				newShip = new BattleShooter();
				break;
			case 1:
				newShip = new BattleCruiser();
				break;
			case 2:
				newShip = new BattleStar();
				break;
		}
		
		
		return newShip;
	}
}
