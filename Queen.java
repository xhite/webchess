public class Queen extends Bishop {

    public Queen(String color) {
	super(color, 3);
    }

    public Queen(String color, int x, int y) {
	super(color, x, y);
    }

    public String getName() {
	return "Queen";
    }

    public String getRep() {
	if (color == "white") 
	    return "D";
	else
	    return "d";
    }

    public void findNextPositions() {
	super.findNextPositions();
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
