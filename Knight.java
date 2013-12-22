public class Knight extends Piece {

    public Knight(String color, int x) {
	super(color, x);
    }

    public Knight(String color, int x, int y) {
	super(color, x, y);
    }

    public String getName() {
	return "Knight";
    }

    public String getRep() {
	if (color == "white") 
	    return "C";
	else
	    return "c";
    }
	
    public void findNextPositions() {
	int x = currentPosition.getX();
	int y = currentPosition.getY();
	addNextPosition(x + 2, y + 1);
	addNextPosition(x + 2, y - 1);
	addNextPosition(x - 2, y + 1);
	addNextPosition(x - 2, y - 1);
	addNextPosition(x - 1, y + 2);
	addNextPosition(x - 1, y - 2);
	addNextPosition(x + 1, y + 2);
	addNextPosition(x + 1, y - 2);
    }

}
