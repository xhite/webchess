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

    private String searchImageRep(String name) {
	switch(name) {
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

    private String buildImageRep(Piece p) {
	String imageRep;
	    if (p != null) {
		imageRep = searchImageRep(p.getName());
	    } else {
		return "";
	    }
	return p.getColor() == "white" ? imageRep : imageRep.toLowerCase();
    }

    public void buildHTMLLine(int x, int y) {
	Boolean whiteSquare = cb.getSquare(x,y);
	Piece piece = cb.getPiece(x,y);
	sb.append("\t\t<td class=\"" + buildParity(whiteSquare) + "\" ><input type=\"image\" name=\"" + buildPlacement(x,y) + "\" src=\"pieces/"+ buildImageName(piece) + ".svg\" alt=\""
		  + buildImageRep(piece)
		  + "\" width=32 /></td>\n");
    }

    

    public void buildHTML() {
	int x,y;
	for (y = 7; y >= 0; y--) {
	    sb.append("\t<tr>\n\t\t<td class=\"border\">" + (y+1) + "</td>\n");
	    for (x = 0; x < 8; x++) {
	       buildHTMLLine(x,y);
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
