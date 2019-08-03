package unsw.dungeon;

public interface LitBombState {
	public void next_stage(LitBomb bomb);
	public int bombState();
}