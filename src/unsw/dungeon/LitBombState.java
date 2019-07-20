package unsw.dungeon;

public interface LitBombState {
	public void next_stage(LitBomb bomb);
}


class LitBomb1 implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {
		bomb.setState(new LitBomb2());
	}
	
}

class LitBomb2 implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {
		bomb.setState(new LitBomb3());
	}
	
}

class LitBomb3 implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {
		bomb.setState(new Explode());
	}
	
}

class Explode implements LitBombState{

	@Override
	public void next_stage(LitBomb bomb) {
		//EXPLODE STATE
	}
	
}