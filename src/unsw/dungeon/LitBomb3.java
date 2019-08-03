package unsw.dungeon;


class LitBomb3 implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {
		bomb.setState(new Explode());
	}

	@Override
	public int bombState() {
		return 3;
	}
	
}