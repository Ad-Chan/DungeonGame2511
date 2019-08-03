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
		//System.out.println(this);
		System.out.println(state);
	}
	
	@Override
	public void checkObjective() {
		
	}
	
	/*@Override
    public boolean playerWalksInto(Player player) {
		ArrayList<Entity> findBoulder = player.findEntity(this.getX(), this.getY());
		for (Entity e: findBoulder) {
			if (e instanceof Boulder) {
				this.state = true;
				System.out.println("true");
			}		
		
		}
		return false;
	}*/
}
