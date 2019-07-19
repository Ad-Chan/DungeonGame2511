package unsw.dungeon;

// Key Class (to be modified)
public class Key extends Entity {
	
	private int keycode;
	
	public Key(int x, int y, int keycode) {
		super(x, y);
		this.setKeycode(keycode);
	}

	public int getKeycode() {
		return keycode;
	}

	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}
	
}
