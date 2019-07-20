package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;


// Lit Bomb Class (to be modified)
public class LitBomb extends Entity {
	
	private LitBombState state;
	public LitBomb(int x, int y, String name) {
		super(x, y, name);
		this.state = new LitBomb1();
		bombTick();
	}
	
	public void setState(LitBombState state) {
		this.state = state;
	}
	
	public void bombTick() {
		Timer timer = new Timer();
		timer.schedule(new bombTimer(),0,500);	
	}
	
	public String checkState() {
		if (this.state instanceof LitBomb1) {
			return "LitBomb1";
		} else if (this.state instanceof LitBomb2) {
			return "LitBomb2";
		} else if (this.state instanceof LitBomb3) {
			return "LitBomb3";
		} else if (this.state instanceof Explode) {
			return "Explode";
		} 
		return "Unknown";
	}
    
	class bombTimer extends TimerTask {

    	@Override
    	public void run() {
    		if (!(LitBomb.this.state instanceof Explode)) {
    			LitBomb.this.state.next_stage(LitBomb.this);
    		}
    	}
    }
}

