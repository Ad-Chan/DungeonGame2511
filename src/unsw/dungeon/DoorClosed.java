package unsw.dungeon;

public class DoorClosed implements DoorState{

	@Override
	public void unlock(Door door) {
		door.setState(new DoorOpen());
	}

}
