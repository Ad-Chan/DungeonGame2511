package unsw.dungeon;

import java.util.ArrayList;

public class SubGoals implements GoalCondition {
	
	private String goalType;
	//private boolean complete;
	
	private ArrayList<GoalCondition> subGoalsList;
	
	public SubGoals(String goalType) {
		this.goalType = goalType;
		//this.complete = false;
		this.subGoalsList = new ArrayList<GoalCondition>();
	}
	
	public String getEntityName() {
		return this.goalType;
	}
	
	public boolean getGoalStatus() {
		if(checkSubGoalCompletion()) {
			return true;
		}
		return false;
	}
	
	private boolean checkSubGoalCompletion() {
		ArrayList<GoalCondition> subGoals = this.subGoalsList;
		switch(this.goalType) {
		case("AND"):
			if(subGoals.get(0).getGoalStatus() && subGoals.get(1).getGoalStatus()) {
				return true;
			}
			break;
		case("OR"):
			if(subGoals.get(0).getGoalStatus() || subGoals.get(1).getGoalStatus()) {
				return true;
			}
			break;
		
		}
		return false;
	}
	
	@Override
	public void addSubGoal(Goal goal) {
		subGoalsList.add(goal);
	}
	
	@Override
	public ArrayList<GoalCondition> getSubGoalsList() {
		return this.subGoalsList;
	}

	@Override
	public void setGoalComplete() {
		
	}
}
