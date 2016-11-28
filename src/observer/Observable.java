package observer;

import gui.GameWindow;

public interface Observable
{
	public void registerListeners(GameWindow gui);
	public void notifyListeners();
}
