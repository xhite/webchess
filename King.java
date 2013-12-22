public class King extends Piece {

    public King(String color) {
	super(color, 4);
    }

    public String getName() {
	return "King";
    }

    public String getRep() {
	if (color == "white") 
	    return "R";
	else
	    return "r";
    }

    public void findNextPositions() {
	addNextPosition(currentPosition.top());
	addNextPosition(currentPosition.bottom());
	addNextPosition(currentPosition.left());
	addNextPosition(currentPosition.right());
	addNextPosition(currentPosition.topLeft());
	addNextPosition(currentPosition.topRight());
	addNextPosition(currentPosition.bottomLeft());
	addNextPosition(currentPosition.bottomRight());
    }

}
