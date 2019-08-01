package unsw.dungeon;

public class Wall extends Entity implements PlayerPosObserver{
	
    public Wall(int x, int y) {
        super(x, y);
    }
	
	@Override
	public boolean isObstacle(int x, int y, Player p) {
		if (this.getX() == x && this.getY() == y) {
			return true;
		} else {
			return false;
		}
	}
}