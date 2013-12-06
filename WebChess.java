import java.util.*;
import java.io.*;

public class WebChess{

    public static void main(String[] args){
	try {
	    HTMLBuilder htmlBuilder = new HTMLBuilder(new ChessBoard());
	    htmlBuilder.build("test.html");
	    HTTPServer s;
	    if (args.length == 0) {
		s = new HTTPServer();
	    } else {
		s = new HTTPServer(args[0]);
	    }
	    while(true) {
		s.loadConnection();
	    }
	} catch (IOException ioe) {
	    System.out.println("Server Socket Error: " + ioe.getMessage());
	} catch (Exception e) {
	    System.out.println("Application Error: " + e.getMessage());
	}
	System.out.println("Server stopped");
    }

}
