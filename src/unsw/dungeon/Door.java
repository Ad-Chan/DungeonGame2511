package unsw.dungeon;

// Door Class (to be modified)
public class Door extends Entity{
	
	private int keycode;
	private DoorStrategy strategy;
	
	public Door(int x, int y, int keycode, String name) {
		super(x, y, name);
		this.setKeycode(keycode);
		this.strategy = new DoorClosed();
	}

	public int getKeycode() {
		return keycode;
	}

	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}

	public boolean unlockDoor(int keycode) {
		if (keycode == this.keycode && this.checkStrategy().equals("Locked")) {
			strategy.unlock(this);
			return true;
		}
		return false;
	}
	
	public void setState(DoorStrategy newState) {
		this.strategy = newState;
	}
	
	public String checkStrategy() {
		if (this.strategy instanceof DoorClosed) {
			return "Locked";
		} else if (this.strategy instanceof DoorOpen){
			return "Open";
		}
		return "Unknown";
	}
	
}
