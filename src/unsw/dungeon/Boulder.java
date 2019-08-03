package unsw.dungeon;

public class Boulder extends Entity {
	
	public Boulder(int x, int y) {
		super(x, y);
	}
		
	public void moveBoulder(int x, int y) {
		y().set(y);
		x().set(x);
	}
	
	@Override
	public boolean isObstacle(int x, int y, Player p) {
		if (this.getX() == x && this.getY() == y) {
			if (this.getPlayerX()-1 == x && this.getPlayerY() == y) {
				if (p.findObstacles(x-1, y) != true) {
					moveBoulder(x-1, y);
					return false;
				}
				return true;
			}
			if (this.getPlayerX()+1 == x && this.getPlayerY() == y) {
				if (p.findObstacles(x+1, y) != true) {
					moveBoulder(x+1, y);
					return false;
				}
				return true;
			}
			if (this.getPlayerX() == x && this.getPlayerY()-1 == y) {
				if (p.findObstacles(x, y-1) != true) {
					moveBoulder(x, y-1);
					return false;
				}
				return true;
			}
			if (this.getPlayerX() == x && this.getPlayerY()+1 == y) {
				if (p.findObstacles(x, y+1) != true) {
					moveBoulder(x, y+1);
					return false;
				}
				return true;
			}
		}
		return true;
	}
}
