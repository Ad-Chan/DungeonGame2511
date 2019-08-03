package unsw.dungeon;

public interface LitBombState {
	public void next_stage(LitBomb bomb);
	public int bombState();
}


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

class Explode implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {
		//EXPLODE STATE
	}

	@Override
	public int bombState() {
		return 4;
	}
	
}