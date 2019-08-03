package unsw.dungeon;


// Floor Switch Class (to be modified)
public class FloorSwitch extends Entity {
	
	private boolean state;
	public FloorSwitch(int x, int y) {
		super(x, y);
		this.state = false;
	}
	
	public void updateSwitch(Boolean b) {
		state = b;
	}
	
	public boolean getState() {
		return this.state;
	}
	
	@Override
	public boolean objective() {
		if (this.state == false) {
			return false;
		} else if (this.state == true){
			return true;
		} else {
			return false;
		}
	}
}
