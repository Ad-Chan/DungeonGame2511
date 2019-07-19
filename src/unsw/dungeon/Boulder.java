package unsw.dungeon;

// Boulder Class (to be modified)
public class Boulder extends Entity {
	
	public Boulder(int x, int y) {
		super(x, y);
	}
	
	//Change position of boulder
	public void checkPlayerPos(int playerX, int playerY) {
		//Left
		if (playerX > this.getX() && playerY == this.getY()) {
			moveBoulder(this.getX()-1, this.getY());
		}
		
		//Right
		if (playerX < this.getX() && playerY == this.getY()) {
			moveBoulder(this.getX()+1, this.getY());			
		}		
		
		//Up
		if (playerX == this.getX() && playerY > this.getY()) {
			moveBoulder(this.getX(), this.getY()-1);			
		}		
		
		//Down
		if (playerX == this.getX() && playerY < this.getY()) {
			moveBoulder(this.getX(), this.getY()+1);			
		}
	}
	
	public void moveBoulder(int x, int y) {
		y().set(y);
		x().set(x);
	}
}
