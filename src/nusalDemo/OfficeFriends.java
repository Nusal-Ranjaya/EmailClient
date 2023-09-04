package nusalDemo;

public class OfficeFriends extends Friends {
	
	private String designation;

	public OfficeFriends() {
		super();
	}

	public OfficeFriends(String name, String email, String brithday, String designation) {
		super(name, email, brithday);
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String getWish() {
		// TODO Auto-generated method stub
		return "Wish you a Happy Birthday";
	}

}
