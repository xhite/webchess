import java.util.*;
import java.io.*;

public abstract class Piece {

    protected String color;
    protected Position currentPosition;
    protected List<Position> nextPositions;

    protected Piece(String color) {
   	this.color = color;
	nextPositions = new ArrayList<Position>();
    }

    protected Piece(String color, int x, int y) {
	this(color);
	currentPosition = new Position(x, y);
    }

    protected Piece(String color, int x) {
	this(color);
	initializePosition(x);
    }

    protected void initializePosition(int x) {
	int y;
	if (color == "white") 
	    y = 0;
	else
	    y = 7;
	currentPosition = new Position(x, y);
    }

    public String getColor() {
	return color;
    }

    public Position getPosition() {
	return currentPosition;
    }

    public Position getNextPosition(int index) {
	if (index < nextPositions.size())
	    return nextPositions.get(index);
	else
	    return null;
    }

    protected void addNextPosition(Position p) {
	nextPositions.add(p);
    }
    
    protected void addNextPosition(int x, int y) {
	Position pos = new Position(x, y);
	if (pos.valid())
	    addNextPosition(pos);
    }

    public void moveTo(Position pos) {
	currentPosition = pos;
	nextPositions.clear();
    }

    public abstract void findNextPositions();
    
    public abstract String getName();

    public abstract String getRep();

}
