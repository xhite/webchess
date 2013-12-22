public class Bishop extends Piece{

    public Bishop(String color, int x) {
	super(color, x);
    }

    public Bishop(String color, int x, int y) {
	super(color, x, y);
    }
   
    public String getName() {
	return "Bishop";
    }

    public String getRep() {
	if (color == "white") 
	    return "F";
	else
	    return "f";
    }

    public void findNextPositions() {
	Position next = currentPosition.topLeft();
	while(next.valid()) {
	    addNextPosition(next);
	    next = next.topLeft();
	}
	next = currentPosition.topRight();
	while(next.valid()) {
	    addNextPosition(next);
	    next = next.topRight();
	}
	next = currentPosition.bottomRight();
	while(next.valid()) {
	    addNextPosition(next);
	    next = next.bottomRight();
	}	
	next = currentPosition.bottomLeft();
	while(next.valid()) {
	    addNextPosition(next);
	    next = next.bottomLeft();
	}
    }

}
