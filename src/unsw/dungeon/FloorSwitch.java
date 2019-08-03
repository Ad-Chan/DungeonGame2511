package unsw.dungeon;


public class FloorSwitch extends Entity {
	
	private boolean state;
	public FloorSwitch(int x, int y) {
		super(x, y);
		this.state = false;
	}
	
	public void updateSwitch(Boolean b) {
		state = b;
		System.out.println("update");
	}
	
	public boolean getState() {
		return this.state;
	}
	
	@Override
	public boolean objective() {
		if (this.state == false) {
			return true;
		} else if (this.state == true){
			return false;
		} else {
			return true;
		}
	}
}
