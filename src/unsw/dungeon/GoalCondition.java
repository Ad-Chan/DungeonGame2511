package unsw.dungeon;

import java.util.ArrayList;

public interface GoalCondition {
	public String getEntityName();
	public boolean getGoalStatus();
	public void setGoalComplete();
	public void addSubGoal(Goal goal);
	public ArrayList<GoalCondition> getSubGoalsList();
}
