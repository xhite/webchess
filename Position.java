public class Position {
    
    private int x;
    private int y;

    public Position(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }
    
    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setPosition(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public Position topLeft() {
	return new Position(x-1, y+1);
    }

    public  Position top() {
	return new Position(x, y+1);
    }

    public Position topRight() {
	return new Position(x+1, y+1);
    }

    public Position right() {
	return new Position(x+1, y);
    }

    public Position bottomRight() {
	return new Position(x+1, y-1);
    }

    public Position bottom() {
	return new Position(x, y-1);
    }

    public Position bottomLeft() {
	return new Position(x-1, y-1);
    }

    public Position left() {
	return new Position(x-1, y);
    }

    private Boolean valid(int index) {
	if (index >= 0 && index <8)
	    return true;
	return false;
    }
    
    public Boolean valid() {
	if (valid(x) && valid(y))
	    return true;
	return false;
    }

}
