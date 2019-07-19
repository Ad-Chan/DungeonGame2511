package unsw.dungeon;

import java.util.ArrayList;

// Boulder Class (to be modified)
public class Boulder extends Entity {
	
	public Boulder(int x, int y) {
		super(x, y);
	}
	
	//Change position of boulder
	public void checkPlayerPos(int playerX, int playerY, ArrayList<String> surrounding) {
		//Left
		if (playerX > this.getX() && playerY == this.getY() && !checkSurrounding(surrounding, "Left")) {
			moveBoulder(this.getX()-1, this.getY());
		}
		
		//Right
		if (playerX < this.getX() && playerY == this.getY() && !checkSurrounding(surrounding, "Right")) {
			moveBoulder(this.getX()+1, this.getY());			
		}		
		
		//Up
		if (playerX == this.getX() && playerY > this.getY() && !checkSurrounding(surrounding, "Up")) {
			moveBoulder(this.getX(), this.getY()-1);			
		}		
		
		//Down
		if (playerX == this.getX() && playerY < this.getY() && !checkSurrounding(surrounding, "Down")) {
			moveBoulder(this.getX(), this.getY()+1);			
		}
	}
	
	private boolean checkSurrounding(ArrayList<String> surrounding, String direction) {
		for (String i:surrounding) {
			if (direction.equals(i)) {
				return true;
			}
		}
		return false;
	}
	
	public void moveBoulder(int x, int y) {
		y().set(y);
		x().set(x);
	}
}
