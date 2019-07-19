package unsw.dungeon;

// Door Class (to be modified)
public class Door extends Entity implements DoorState{
	
	private int keycode;
	private DoorState state;
	
	public Door(int x, int y, int keycode) {
		super(x, y);
		this.setKeycode(keycode);
	}

	public int getKeycode() {
		return keycode;
	}

	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}

	@Override
	public void unlock() {
		state.unlock();
	}	
	
}
