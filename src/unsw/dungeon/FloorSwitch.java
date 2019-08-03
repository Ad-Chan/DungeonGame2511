package unsw.dungeon;


// Floor Switch Class (to be modified)
public class FloorSwitch extends ObjectiveEntity {
	
	private boolean state;
	public FloorSwitch(int x, int y, int id) {
		super(x, y, id);
		this.state = false;
	}
	
	public void updateSwitch(Boolean b) {
		state = b;
	}
	
	@Override
	public void checkObjective() {
		
	}
	
}
