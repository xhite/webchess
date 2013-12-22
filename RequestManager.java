import java.util.*;
import java.io.*;

public class RequestManager {

    private String request;

    public RequestManager(String request) {
	this.request = request;
    }

    private String requestedURL() {
	if(request.contains("GET")){
	    String[] getRequest;
	    getRequest = request.split(" ");
	    return getRequest[1];
	} else {
	    return null;
	}
    }

    private String requestedParams() {
	String url = requestedURL();
	String[] params;
	if (url != null && url.contains("?")) {
	    params = url.split("\\?");
	    return params[1];
	} else {
	    return null;
	}
    }

    private String requestedPosition() {
	String[] position;
	String params = requestedParams();
	if(params != null) {
	    position = params.split("\\.");
	    return position[0];
	} else {
	    return null;
	}
    }

    public String requestedFile(){
	String url = requestedURL();	
	String[] file;
	if(url != null) {
	    if (url.contains("?")) {
		file = url.split("\\?");
		return "." + file[0];
	    } else {
		return "." + url;
	    }
	} else {
	    return null;
	}
    }
    
    public String requestedType() {
	if (request.contains("image/png")) {
	    return "image/svg+xml";
	} else if (request.contains("text/html")) {
	    return "text/html";
	} else if (request.contains("text/css")) {
	    return "text/css";
	} else {
	    return null;
	}
    }

    private void applyNextPositions(ChessBoard board) {
	String position = requestedPosition();
	if (position != null && !position.contains("to")) {
	    int x = (int) (position.charAt(0) - 65);
	    int y = Integer.parseInt("" + position.charAt(1));
	    board.setNextPositionsFrom(x, y-1);
	}
    }

    private void applyMove(ChessBoard board) {
	String position = requestedPosition();
	if (position != null && position.contains("to")) {
	    int x = (int) (position.charAt(2) - 65);
	    int y = Integer.parseInt("" + position.charAt(3));
	    board.movePieceTo(x, y-1);
	}
    }

    public void applyRequestedParams(ChessBoard board) throws IOException {
	applyNextPositions(board);
	applyMove(board);
	HTMLBuilder builder = new HTMLBuilder(board);
	builder.build("index.html");
    }
    
}
