package unsw.dungeon;

class LitBomb2 implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {
		bomb.setState(new LitBomb3());
	}

	@Override
	public int bombState() {
		return 2;
	}
	
}