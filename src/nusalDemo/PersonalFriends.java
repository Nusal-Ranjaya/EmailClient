package nusalDemo;

public class PersonalFriends extends Friends{
	
	private String nickName;

	public PersonalFriends() {
		super();
	}


	public PersonalFriends(String name, String email, String brithday, String nickName) {
		super(name, email, brithday);
		this.nickName = nickName;
		// TODO Auto-generated constructor stub
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	@Override
	public String getWish() {
		// TODO Auto-generated method stub
		return "Hugs and love on your Birthday";
	}

}
