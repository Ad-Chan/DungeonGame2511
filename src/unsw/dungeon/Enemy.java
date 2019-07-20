package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

// Enemy Class (to be modified)
public class Enemy extends Entity {
	
	private int id;
	
	public Enemy(int x, int y, int id, String name) {
		super(x, y, name);
		this.id = id;
		Timer timer = new Timer();
		timer.schedule(new moveTimer(),0,5000);
	}
	
    public void moveUp() {
            y().set(getY() - 1);
    }

    public void moveDown() {
            y().set(getY() + 1);
    }

    public void moveLeft() {
            x().set(getX() - 1);
    }

    public void moveRight() {
            x().set(getX() + 1);
    }

    class moveTimer extends TimerTask {

    	@Override
    	public void run() {
    		Enemy.this.moveLeft();    		
    	}
    }
}