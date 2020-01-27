package entities;

public class Action {
	enum Act {
		EAT, BREED, MOVE
	}

	Act nextAction;
	int x;
	int y;
}
