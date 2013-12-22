public class ChessBoard {

    private Piece[][] pieces;
    private String[][] squares;
    private Boolean turn; //white if true
    private Position selected;

    public ChessBoard() {
	squares = new String[8][8];
	pieces = new Piece[8][8];
	turn = true;
	initializeBoard();
	initializePieces();
    }

    private Boolean odd(int i) {
	return (i & 1) != 0;
    }

    private String squareType(int i) {
	return odd(i) ? "even" : "odd";
    }

    public void initializeBoard(){
	int i,x,y;
	for (i = 0; i < 8; i++) {
	    x = i;
	    y = 0;
	    while (x>=0 && y<=7) {
	        squares[7-x][7-y] = squares[x][y] = squareType(i);
		x--;
		y++;
	    }
	}
    }
    
    public void initializePieces() {
	pieces[0][7] = new Rook("black", 0);
	pieces[7][7] = new Rook("black", 7);
	pieces[0][0] = new Rook("white", 0);
	pieces[7][0] = new Rook("white", 7);

	pieces[1][7] = new Knight("black", 1);
	pieces[6][7] = new Knight("black", 6);
	pieces[1][0] = new Knight("white", 1);
	pieces[6][0] = new Knight("white", 6);

	pieces[2][7] = new Bishop("black", 2);
	pieces[5][7] = new Bishop("black", 5);
	pieces[2][0] = new Bishop("white", 2);
	pieces[5][0] = new Bishop("white", 5);

	pieces[3][7] = new Queen("black");
	pieces[3][0] = new Queen("white");

	pieces[4][7] = new King("black");
	pieces[4][0] = new King("white");

	int i;
	for(i = 0; i < 8; i++){
	    pieces[i][1] = new Pawn("white", i);
	    pieces[i][6] = new Pawn("black", i);
	}
    }

    public Piece getPiece(int x, int y) {
	return pieces[x][y];
    }

    public String getSquare(int x, int y) {
	return squares[x][y];
    }
    
    public String getTurn() {
	return turn ? "white" : "black";
    }
    
    private Boolean piecesTurnAt(int x, int y) {
	Piece p = pieces[x][y];
	if (p != null)
	    return getTurn() == p.getColor();
	return false;
    }
    
    private void setNextSquare(Position pos) {
	if (pos.valid() && !piecesTurnAt(pos.getX(), pos.getY()))
	    squares[pos.getX()][pos.getY()] += "Select";
    }
    
    private void clearNextSquare(Position pos) {
	int x = pos.getX(), y = pos.getY();
	if (pos.valid() && !piecesTurnAt(x, y)) {
	    String square = squares[x][y];
	    squares[x][y] = square.contains("odd") ? "odd" : "even";
	}
    }

    private void setAllNextSquares(Piece p) {
	int i = 0;
	Position next = p.getNextPosition(i);
	while (next != null) {
	    setNextSquare(next);
	    next = p.getNextPosition(++i);
	}
    }

    private void clearAllNextSquares(Piece p) {
	int i = 0;
	Position next = p.getNextPosition(i);
	while (next != null) {
	    clearNextSquare(next);
	    next = p.getNextPosition(++i);
	}
    }

    public void setNextPositionsFrom(int x, int y) {
	Piece p = pieces[x][y];
	p.findNextPositions();
	selected = new Position(x, y);
	setAllNextSquares(p);
    }

    public void movePieceTo(int x, int y) {
	Position pos = new Position(x, y);
	Piece p = pieces[selected.getX()][selected.getY()];
	clearAllNextSquares(p);
	p.moveTo(pos);
	pieces[x][y] = p;
	pieces[selected.getX()][selected.getY()] = null;
	turn = turn ? false : true;
    }

}
