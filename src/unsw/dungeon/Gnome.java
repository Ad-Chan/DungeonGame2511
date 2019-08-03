package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class Gnome extends Enemy{

	public Gnome(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		Timer timer = new Timer();
		timer.schedule(new moveTimer(),0,2000);
	}

	public void fireProjectiles(Player player) {
		WandProjectile shot1 = new WandProjectile(this.getX()+1, this.getY(), 1, 0, player);
		WandProjectile shot2 = new WandProjectile(this.getX()-1, this.getY(), -1, 0, player);
		WandProjectile shot3 = new WandProjectile(this.getX(), this.getY()+1, 0, +1, player);
		WandProjectile shot4 = new WandProjectile(this.getX(), this.getY()-1, 0, -1, player);
		player.createEntities(shot1);
		player.createEntities(shot2);
		player.createEntities(shot3);
		player.createEntities(shot4);
	}
	
	public void runFireProjectiles() {
		if (this.getHealth() == 1) {
			fireProjectiles(this.getDungeon().getPlayer());
		}
	}
	
	
    class moveTimer extends TimerTask {

    	@Override
    	public void run() {
    		Platform.runLater(new Runnable() {
  		       public void run() {
  		    		Gnome.this.moveEnemy(this); 
  		    		Gnome.this.runFireProjectiles();
  		      }
  		    });
 		
    	}
    	
    	
    }
}
