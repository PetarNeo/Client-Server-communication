package rs.ac.bg.etf.kdp.neo;

public interface MessageBox<T> {
	
	public void put(Message<T> m, Priority priority,long timeToLive);
	
	public Message<T> get(long timeToWait,Status status);
	
}
