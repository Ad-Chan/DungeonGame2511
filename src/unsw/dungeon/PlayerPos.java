package unsw.dungeon;

public interface PlayerPos {
	void attachObserver(PlayerPosObserver p);	
	void detachObserver(PlayerPosObserver p);
	void notifyObservers();
}
