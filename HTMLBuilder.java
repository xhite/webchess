import java.util.*;
import java.io.*;

public class HTMLBuilder {

    private StringBuilder sb;
    private ChessBoard cb;

    public HTMLBuilder(ChessBoard cb) {
	this.cb = cb;
	sb = new StringBuilder();
	sb.append("<html>\n");
	sb.append("<head>\n");
	sb.append("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\" />\n");
	sb.append("<link rel=\"stylesheet\" href=\"stylesheet.css\" type=\"text/css\" />\n");
	sb.append("<title>\n");
	sb.append("\tÉchecs en ligne\n");
	sb.append("</title>\n");
	sb.append("</head>\n");
	sb.append("<body>\n");
	sb.append("<form>\n");
	sb.append("<table align=center>\n");
	buildBorder();
    }

    public void buildBorder() {
	sb.append("\t<tr>\n");
	sb.append("\t\t<td class=\"corner\"></td>\n");
	int x;
	for (x = 0; x < 8; x++) 
	    sb.append("\t\t<td class=\"border\">" + (char)(x+65) + "</td>\n");
	sb.append("\t\t<td class=\"corner\"></td>\n");
	sb.append("\t</tr>\n");
    } 

    private String buildPlacement(Position pos) {
	return (char)(pos.getX()+65) + String.valueOf(pos.getY()+1);
    }

    private String buildImageType(Piece p, String square) {
	if (cb.getTurn() == p.getColor()) {
	    String placement;
	    if (square.contains("Select"))
		placement = "to" + buildPlacement(p.getPosition());
	    else
		placement = buildPlacement(p.getPosition());
	    return "input type=\"image\" name=\""
		+ placement
		+ "\"";
	} else {
	    return "img";
	}
    }

    public String buildImageName(Piece p){
	return p.getColor() + p.getName();
    }

    private void buildEmptySquare(String square, Position pos) {
	sb.append("\t\t<td class=\"" + square + "\">");
	if (square.contains("Select")) {
	    sb.append("<input type=\"image\" name=\""
		      + "to" + buildPlacement(pos)
		      + "\" src=\"pieces/blank.svg\" alt=\" \" width=32 /></td>\n");
	} else
	    sb.append("<img src=\"pieces/blank.svg\" alt=\" \" width=32 /></td>\n");
    }

    private void buildPiece(Piece p, String square) {
	sb.append("\t\t<td class=\""
		  + square
		  + "\"><"
		  + buildImageType(p, square)
		  + " src=\"pieces/"
		  + buildImageName(p)
		  + ".svg\" alt=\""
		  + p.getRep()
		  + "\" width=32 /></td>\n");
    }
    
    public void buildHTMLLine(int x, int y) {
	Piece p = cb.getPiece(x, y);
	String square = cb.getSquare(x, y);
	if (p == null) {
	    buildEmptySquare(square, new Position(x,y));
	}
	else {
	    buildPiece(p, square);
	}
    }

    public void buildHTML() {
	int x, y;
	for (y = 7; y >= 0; y--) {
	    sb.append("\t<tr>\n\t\t<td class=\"border\">" + (y+1) + "</td>\n");
	    for (x = 0; x < 8; x++) {
		buildHTMLLine(x, y);
	    }
	    sb.append("\t\t<td class=\"border\">" + (y+1) + "</td>\n\t</tr>\n");
	}
	buildBorder();
	sb.append("</table>\n");
	sb.append("</form>\n");
	sb.append("</body>\n");
	sb.append("</html>\n");
    }

    public void build(String filename) throws IOException {
       	buildHTML();
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
	out.print(sb.toString());
	out.close();
    }

}
