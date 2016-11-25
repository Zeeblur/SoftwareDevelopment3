package observer;

public interface Observable
{
	public void registerListner();
	public void unregisterListner();
	public void update();
}
