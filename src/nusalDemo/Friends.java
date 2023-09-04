package nusalDemo;

public abstract class Friends extends Clients{
	protected String brithday;

	public Friends() {
		super();
	}

	
	public Friends(String name, String email, String brithday) {
		super(name, email);
		this.brithday = brithday;
	}

	public String getBrithday() {
		return brithday.substring(5);
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}
	
	public abstract String getWish();
	
}
