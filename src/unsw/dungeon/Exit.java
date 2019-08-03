package unsw.dungeon;

public class Exit extends Entity {

	private boolean found;
    public Exit(int x, int y) {
        super(x, y);
        this.found = false;
    }   
    
    @Override
    public boolean playerWalksInto(Player player) {
    	this.found = true;
    	return false;
    }
    
	@Override
	public boolean objective() {
		if (this.found == false) {
			return true;
		} else if (this.found == true){
			return false;
		} else {
			return false;
		}
	}
}