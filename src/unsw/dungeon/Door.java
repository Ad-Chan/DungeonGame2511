package unsw.dungeon;

// Door Class (to be modified)
public class Door extends Entity{
	
	private int keycode;
	private DoorState state;
	
	public Door(int x, int y, int keycode) {
		super(x, y);
		this.setKeycode(keycode);
		this.state = new DoorClosed();
	}

	public int getKeycode() {
		return keycode;
	}

	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}

	public void unlockDoor(int keycode) {
		if (keycode == this.keycode) {
			state.unlock(this);
		}
	}
	
	public void setState(DoorState newState) {
		this.state = newState;
	}
	
}
