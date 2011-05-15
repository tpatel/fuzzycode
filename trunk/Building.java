package fuzzycode;

public class Building {

	private Integer type;
	protected Boolean friend;
	
	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}
	
	public Building(Integer type) {
		this.setType(type);
	}	

	public Boolean isFriend() {
		return friend;
	}

	public void setFriend(Boolean isAmi) {
		this.friend = isAmi;
	}
	
}

