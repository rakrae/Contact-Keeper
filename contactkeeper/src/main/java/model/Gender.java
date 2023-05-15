package model;

public enum Gender {
	
	MALE("Male"),
	FEMALE("Female");

	private final String caption;
	
	
	Gender(String caption) {
		this.caption = caption;
	}
	
	
	@Override
	public String toString() {
		
		return caption;
	}
	
}
