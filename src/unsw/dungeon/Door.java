package unsw.dungeon;

// Door Class (to be modified)
public class Door extends Entity{
	
	private int keycode;
	private DoorStrategy strategy;
	
	public Door(int x, int y, int keycode) {
		super(x, y);
		this.setKeycode(keycode);
		this.strategy = new DoorClosed();
	}

	public int getKeycode() {
		return keycode;
	}

	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}

	public void unlockDoor(int keycode) {
		strategy.unlock(this);
	}
	
	public void setState(DoorStrategy newState) {
		this.strategy = newState;
	}
	
	
	@Override
	public boolean isObstacle(int x, int y, Player p) {
		for (Entity e: p.getInventory()) {
			if (e instanceof Key) {
				unlockDoor(((Key) e).getKeycode());
			}
		}			
		if (this.getX() == x && this.getY() == y && this.strategy.checkLock() == false){
			return false;
		} else {
			return true;
		}
	}
	
}
