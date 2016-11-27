package observer;

public interface Observable
{
	public void registerListeners(GridElement[][] cells);
	public void notifyListeners();
}
