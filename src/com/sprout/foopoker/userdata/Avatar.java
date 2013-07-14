package com.sprout.foopoker.userdata;

public class Avatar {

	public int id;
	public String name;
	public String path;
	
	private final int AVATAR_COUNT = 3;
	private final String PATH = "pics/avatar/";
	private final String PNG_EXT = ".png";
	
	/**
	 * 
	 * @param id if less than 0, avatar will be random
	 */
	public Avatar(int id) {
		if (id > AVATAR_COUNT)
			throw new IllegalArgumentException(id + " is not a valid avatar id");
		this.id = id;
		
		initAvatarInfo();
	}
	
	private void initAvatarInfo() {
		switch(id) {
		case 1:
			name = "Spiderman";
			break;
		case 2:
			name = "Smiley";
			break;
		case 3:
			name = "Poker Face";
			break;
		default:
			// choose a random avatar
			id = (int) (Math.random() * 10000);
			id = (int) Math.ceil(id);
			id = id % AVATAR_COUNT + 1;
			initAvatarInfo();
		}
		path = PATH + id + PNG_EXT;
	}
	
	@Override
	public String toString() {
		return id + ", " + name + ", " + path;
	}
	public static void main(String[] args) {
		Avatar avatar = new Avatar(1);
		System.out.println(avatar);
		avatar = new Avatar(0);
		System.out.println(avatar);

	}

}
