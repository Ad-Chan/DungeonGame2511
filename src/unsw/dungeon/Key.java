package unsw.dungeon;

// Key Class (to be modified)
public class Key extends Collectable {
	
	private int keycode;
	
	public Key(int x, int y, int id, int keycode, String name) {
		super(x, y, id, name);
		this.setKeycode(keycode);
	}

	public int getKeycode() {
		return keycode;
	}

	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}
	
}
