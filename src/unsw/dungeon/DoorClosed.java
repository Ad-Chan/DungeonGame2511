package unsw.dungeon;

public class DoorClosed implements DoorStrategy{

	@Override
	public void unlock(Door door) {
		door.setState(new DoorOpen());
	}

	@Override
	public boolean checkLock() {
		return true;
	}

}
