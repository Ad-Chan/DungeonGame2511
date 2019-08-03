package unsw.dungeon;


// Floor Switch Class (to be modified)
public class FloorSwitch extends Entity {
	
	private boolean state;
	private int id;
	public FloorSwitch(int x, int y, int id) {
		super(x, y);
		this.state = false;
		this.id = id;
	}
	
	public void updateSwitch(Boolean b) {
		state = b;
	}
	
	
}
