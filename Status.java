package rs.ac.bg.etf.kdp.neo;

public class Status {
	
	public static final int OK = 0;
	public static final int NOK = 1;
	
	private int status;
	
	public Status() {
		this(OK);
	}
	
	public Status(int status) {
		this.status = status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	
}
