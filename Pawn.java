public class Pawn extends Piece {

    public Pawn(String color, int x) {
	super(color);
	initializePosition(x);
    }

    public Pawn(String color, int x, int y) {
	super(color, x, y);
    }

    protected void initializePosition(int x) {
	int y;
	if (color == "white") 
	    y = 1;
	else
	    y = 6;
	currentPosition = new Position(x, y);
    }

    public String getName() {
	return "Pawn";
    }

    public String getRep() {
	if (color == "white") 
	    return "P";
	else
	    return "p";
    }
	
    public void findNextPositions() {
	Position next;
	if (color == "white") {
	    next = currentPosition.top();
	    if (currentPosition.getY() == 1)
		addNextPosition(next.top());
	} else {
	    next = currentPosition.bottom();
	    if (currentPosition.getY() == 6)
		addNextPosition(next.bottom());
	}
	addNextPosition(next);
    }

}
