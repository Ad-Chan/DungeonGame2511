package unsw.dungeon;

import java.util.ArrayList;

public interface GoalCondition {
	public String getEntityName();
	public void addSubGoal(Goal goal);
	public ArrayList<GoalCondition> getSubGoalsList();
}
