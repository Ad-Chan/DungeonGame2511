package unsw.dungeon;

import java.util.ArrayList;

// Boulder Class (to be modified)
public class Boulder extends Entity {
	
	public Boulder(int x, int y) {
		super(x, y);
	}
		
	public void moveBoulder(int x, int y) {
		y().set(y);
		x().set(x);
	}
	
	@Override
	public boolean isObstacle(int x, int y, Player P) {
		if (this.getX() == x && this.getY() == y) {
			if (this.getPlayerX() > x && this.getPlayerY() == y) {
				moveBoulder(x-1, y);
			}
			if (this.getPlayerX() < x && this.getPlayerY() == y) {
				moveBoulder(x+1, y);
			}
			if (this.getPlayerX() == x && this.getPlayerY() > y) {
				moveBoulder(x, y-1);
			}
			if (this.getPlayerX() == x && this.getPlayerY() < y) {
				moveBoulder(x, y+1);
			}
			System.out.println(this.getPlayerX());
			System.out.println(this.getPlayerY());
			return false;
		} else {
			return false;
		}
	}
}
