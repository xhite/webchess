import java.util.*;
import java.io.*;

public class HTMLBuilder {

    private StringBuilder sb;
    private ChessBoard cb;

    public HTMLBuilder(ChessBoard cb) {
	this.cb = cb;
	sb = new StringBuilder();
	sb.append("<html>");
	sb.append("<head>");
	sb.append("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\" />");
	sb.append("<html>");
	sb.append("<link rel=\"stylesheet\" href=\"stylesheet.css\" type=\"text/css\" />");
	sb.append("<title>");
	sb.append("Échecs en ligne");
	sb.append("</title>");
	sb.append("</head>");
	sb.append("<body>");
	sb.append("<form>");
	sb.append("<table align=center>");
	buildBorder();
    }

    public void buildBorder() {
	sb.append("<tr>");
	sb.append("<td class=\"corner\"></td>");
	int x;
	for (x = 0; x < 8; x++) 
	    sb.append("<td class=\"border\">" + (char)(x+65) + "</td>");
	sb.append("<td class=\"corner\"></td>");
	sb.append("<tr>");
    } 

    public String buildImageName(Piece p){
	if (p != null) {
	    String name = p.getName();
	    return p.getColor() + Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	return "blank";
    }

    private String buildParity(Boolean b) {
	return b? "even" : "odd";
    }

    private String buildPlacement(int x, int y) {
	y++;
	return (char)(x+65) + String.valueOf(y);
    }

    private String buildImageRep(Piece p) {
	if (p != null) {
	    switch(p.getName()) {
	    case "pawn":
		return "P";
	    case "rook":
		return "T";
	    case "knight":
		return "C";
	    case "bishop":
		return "F";
	    case "queen":
		return "D";
	    case "king":
		return "R";
	    default:
		return " ";
	    }
	}
	return " ";
    }

    public void buildHTMLLine(int x, int y) {
	Boolean whiteSquare = cb.getSquare(x,y);
	Piece piece = cb.getPiece(x,y);
	sb.append("<td class=\"" + buildParity(whiteSquare) + "\" ><input type=\"image\" name=\"" + buildPlacement(x,y) + "\" src=\"pieces/"+ buildImageName(piece) + ".svg\" alt=\""
		  + buildImageRep(piece)
		  + "\" width=32 /></td>");
    }

    

    public void buildHTML() {
	int x,y;
	for (y = 7; y >= 0; y--) {
	    sb.append("<tr><td class=\"border\">" + (y+1) + "</td>");
	    for (x = 0; x < 8; x++) {
	       buildHTMLLine(x,y);
	    }
	    sb.append("</td><td class=\"border\">" + (y+1) + "<tr>");
	}
	buildBorder();
	sb.append("</table>");
	sb.append("</form>");
	sb.append("</body>");
	sb.append("</html>");
    }

    public void build(String filename) throws IOException {
	buildHTML();
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
	out.print(sb.toString());
	out.close();
    }


}
