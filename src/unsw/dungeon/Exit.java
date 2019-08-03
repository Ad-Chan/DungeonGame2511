package unsw.dungeon;

public class Exit extends Entity {

    public Exit(int x, int y) {
        super(x, y);
    }   
    
    @Override
    public boolean playerWalksInto(Player player) {
    	if (player.getDungeon().checkGoalCompletion() == true) {
    		//Complete level
    	} else {

    	}
    	return false;
    }
}