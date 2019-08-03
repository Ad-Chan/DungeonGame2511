package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;


// Lit Bomb Class (to be modified)
public class LitBomb extends Entity {
	
	private LitBombState state;
	private Player player;
	private boolean explode;
	public LitBomb(int x, int y, Player p) {
		super(x, y);
		this.state = new LitBomb1();
		bombTick();
		this.player = p;
		this.explode = false;
	}
	
	public void setState(LitBombState state) {
		this.state = state;
	}
	
	public void bombTick() {
		Timer timer = new Timer();
		timer.schedule(new bombTimer(),0,500);

	}
	
	public int checkStrategy() {
		return this.state.bombState();
	}
	
	public void updateBomb() {
		this.player.updateImage(this);
	}
    
	public void blowup() {
		if (this.explode == false) {
			this.player.blowup(this);
			this.explode = true;
		}
	}
	
	class bombTimer extends TimerTask {

    	@Override
    	public void run() {
    		Platform.runLater(new Runnable() {
    		       public void run() {
    		    		if (!(LitBomb.this.state.bombState() == 4)) {
    		    			LitBomb.this.state.next_stage(LitBomb.this);
    		    			updateBomb();
    		    		} else {
    		    			blowup();    		    			
    		    		}
    		      }
    		    });
    		}
    	}
}

