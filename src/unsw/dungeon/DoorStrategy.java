package unsw.dungeon;

public interface DoorStrategy {
	
	public void unlock(Door door);
	public boolean checkLock();
}
