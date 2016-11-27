package observer;

import skywars.GameState;

// the observer is updated by the observable object
public interface Observer
{
	public void update(GameState state);
}
