public class ChessBoard {

    
    private Piece[][] pieces;
    private Boolean[][] squares;
    private Boolean turn; //true if white

    public ChessBoard() {
	squares = new Boolean[8][8];
	pieces = new Piece[8][8];
	turn = true;
	initializeBoard();
	initializePieces();
    }

    private Boolean odd(int i) {
	return (i & 1) != 0;
    }

    public void initializeBoard(){
	int i,x,y;
	for (i = 0; i < 8; i++) {
	    x = i;
	    y = 0;
	    while (x>=0 && y<=7) {
	        squares[7-x][7-y] = squares[x][y] = odd(i);
		x--;
		y++;
	    }
	}
    }
    
    public void initializePieces() {
	pieces[0][7] = new Piece("rook","black");
	pieces[7][7] = new Piece("rook","black");
	pieces[0][0] = new Piece("rook","white");
	pieces[7][0] = new Piece("rook","white");

	pieces[1][7] = new Piece("knight","black");
	pieces[6][7] = new Piece("knight","black");
	pieces[1][0] = new Piece("knight","white");
	pieces[6][0] = new Piece("knight","white");

	pieces[2][7] = new Piece("bishop","black");
	pieces[5][7] = new Piece("bishop","black");
	pieces[2][0] = new Piece("bishop","white");
	pieces[5][0] = new Piece("bishop","white");

	pieces[3][7] = new Piece("queen","black");
	pieces[3][0] = new Piece("queen","white");

	pieces[4][7] = new Piece("king","black");
	pieces[4][0] = new Piece("king","white");

	int i;
	for(i = 0; i < 8; i++){
	    pieces[i][1] = new Piece("pawn","white");
	    pieces[i][6] = new Piece("pawn","black");
	}
    }

    public Piece getPiece(int x, int y) {
	return pieces[x][y];
    }

    public Boolean getSquare(int x, int y) {
	return squares[x][y];
    }
    
    public String getTurn() {
	if (turn)
	    return "white";
	return "black";
    }
 
}
