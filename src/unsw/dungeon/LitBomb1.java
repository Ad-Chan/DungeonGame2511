package unsw.dungeon;

class LitBomb1 implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {
		bomb.setState(new LitBomb2());
	}

	@Override
	public int bombState() {
		return 1;
	}
	
}