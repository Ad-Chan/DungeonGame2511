package unsw.dungeon;

class Explode implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {

	}

	@Override
	public int bombState() {
		return 4;
	}
	
}