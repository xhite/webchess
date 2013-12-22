import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class HTTPServer { 

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private ResponseManager resm;
    private RequestManager reqm;
    ChessBoard board;

    public HTTPServer() throws Exception{
	this(12345);
    }

    public HTTPServer(String port) throws Exception{
	this(Integer.parseInt(port));
    }

    public HTTPServer(int port) throws NumberFormatException, IOException, NoSuchElementException{
	System.out.println("Starting server using port: "+ port);

	serverSocket = new ServerSocket(port);

	System.out.println("HTTP Server ready!");

	board = new ChessBoard();
    }

    public void loadConnection() throws IOException{

	start();
	
	System.out.println("receiving data");
	
	String request = readRequest();
	  
	System.out.println(request);
	  
	reqm = new RequestManager(request);	
	
	resm = new ResponseManager(reqm.requestedFile());

	sendHEAD();
	
	System.out.println("sending data");
     
	sendResponse();

	System.out.println("data sent");
	
	close();
    }
    
    private void start() throws IOException{
	clientSocket = serverSocket.accept();
	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    private String readRequest() throws IOException{
	String line;
	StringBuilder sb = new StringBuilder();	
	while((line = in.readLine()) != null){
	    sb.append(line);
	    sb.append("\n");
	    if(line.isEmpty()){
		break;
	    }
	}
	return sb.toString();
    }
    
    private void sendHEAD() throws IOException{
	if (reqm.requestedType() != null){
	    out.write("HTTP/1.1 200 OK\r\n");
	    out.write("Content-Type: " + reqm.requestedType() + "\r\n");
	    out.write("Last-Modified: " + resm.date() + "\r\n");
	    out.write("\r\n");
	}
    }

    private void sendResponse() throws IOException {
	reqm.applyRequestedParams(board);
	String line = resm.read();
	if (line == null)
	    out.write("file does not exist");
	else {
	    while(line != null){
		out.write(line);
		out.write("\n");
		line = resm.read();
	    }
	    resm.close();
	}
    }
		
    private void close() throws IOException{
	out.close();
	in.close();
	clientSocket.close();
    }
  
}
