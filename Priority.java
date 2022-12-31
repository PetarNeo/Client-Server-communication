package rs.ac.bg.etf.kdp.neo;

public interface Priority extends Comparable<Priority>{
	
	public static final long LOW = 0;
	public static final long HIGH = 100;
	
	public long getLongPriority();
	
	public void setLongPriority(long priority);
}
