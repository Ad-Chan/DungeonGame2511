package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class Projectile extends Entity{

	private int moveX;
	private int moveY;
	private Player player;
	private boolean hit;
	public Projectile(int x, int y, int moveX, int moveY, Player player) {
		super(x, y);
		this.moveX = moveX;
		this.moveY = moveY;
		this.player = player;
		this.hit = false;
		Timer timer = new Timer();
		timer.schedule(new ProjectileTimer(),0,250);
	}

	public void MoveProjectile() {
		if (hit == false) {
			y().set(getY() + moveY);
			x().set(getX() + moveX);
			if (player.findObstacles(getX(), getY()) == true) {
				player.projectileDestroyEntities(getX(), getY());	
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
	
	class ProjectileTimer extends TimerTask {

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
