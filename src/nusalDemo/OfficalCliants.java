package nusalDemo;

public class OfficalCliants extends Clients{
	private String designation;

	public OfficalCliants() {
		super();
	}

	public OfficalCliants(String name, String email, String designation) {
		super(name, email);
		this.designation = designation;
		
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
