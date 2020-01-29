package entities;

public class Action {
	public enum Act {
		EAT, BREED, MOVE, DIE
	}

	public Entity entity;
	public Act nextAction;
	public int x;
	int oldX;
	public int y;
	int oldY;
}
