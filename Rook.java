public class Rook extends Piece{

    public Rook(String color, int x, int y) {
	super(color, x, y);
    }

    public Rook(String color, int x) {
	super(color, x);
    }

    public String getName() {
	return "Rook";
    }

    public String getRep() {
	if (color == "white") 
	    return "T";
	else
	    return "t";
    }

    public void findNextPositions() {
	Position next = currentPosition.top();
	while(next.valid()) {
	    addNextPosition(next);
	    next = next.top();
	}
	next = currentPosition.right();
	while(next.valid()) {
	    addNextPosition(next);
	    next = next.right();
	}
	next = currentPosition.bottom();
	while(next.valid()) {
	    addNextPosition(next);
	    next = next.bottom();
	}	
	next = currentPosition.left();
	while(next.valid()) {
	    addNextPosition(next);
	    next = next.left();
	}
    }

}
