package rs.ac.bg.etf.kdp.neo;

import java.io.*;

public interface Message<T> extends Serializable{
	
	public void setId(long id);
	
	public long getId();

	public void setBody(T body);
	
	public T getBody();
}
