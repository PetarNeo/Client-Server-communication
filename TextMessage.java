package rs.ac.bg.etf.kdp.neo;

public class TextMessage implements Message<String> {
	
	private static final long serialVersionUID = 1L;
	String body;
	
	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String getBody() {
		return body;
	}
	
}
