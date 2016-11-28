package strategy;

import skywars.GameState;

public interface ShipMode {
	public GameState react(GameState gs);
}
