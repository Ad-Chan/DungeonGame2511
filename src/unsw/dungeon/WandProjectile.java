package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class WandProjectile extends Entity{

	private int moveX;
	private int moveY;
	private Player player;
	private boolean hit;
	public WandProjectile(int x, int y, int moveX, int moveY, Player player) {
		super(x, y);
		this.moveX = moveX;
		this.moveY = moveY;
		this.player = player;
		this.hit = false;
		Timer timer = new Timer();
		timer.schedule(new wandProjectileTimer(),0,250);

	}

	public void MoveProjectile() {
		if (hit == false) {
			y().set(getY() + moveY);
			x().set(getX() + moveX);
			if (player.findObstacles(getX(), getY()) == true) {
				player.bombDestroyEntities(getX(), getY());	
				this.hit = true;
				player.destroyEntities(this);
			}
		}
		
	}
	
	
	@Override
    public boolean playerWalksInto(Player player) {
		player.killPlayer();
		return false;
    }
	
	class wandProjectileTimer extends TimerTask {

    	@Override
    	public void run() {
    		Platform.runLater(new Runnable() {
    		       public void run() {
    		    	   MoveProjectile();
    		      }
    		    });
    		}
    	}
}
