import java.util.*;
import java.io.*;

public class WebChess{

    public static void main(String[] args){
	try{
	    HTTPServer s = new HTTPServer();
	    while(true){
		s.loadConnexion();
	    }
	} catch (IOException ioe){
	    System.out.println("Server Socket Error: " + ioe.getMessage());
	} catch (Exception e){
	    System.out.println("Application Error: " + e.getMessage());
	}
	System.out.println("Server stopped");
    }

}
