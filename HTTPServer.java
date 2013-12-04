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
/** 
* représente le nombre de connexion
*/
//private static Integer LAST_ID;

    public HTTPServer() throws Exception{
	this(12345);
    }

    public HTTPServer(String port) throws Exception{
	this(Integer.parseInt(port));
    }

    public HTTPServer(int port) throws NumberFormatException, IOException, NoSuchElementException{
	System.out.println("Starting server using port: "+ port);
	serverSocket = new ServerSocket(port); // On injecte le port sur lequel le serveur va écouter
	//LAST_ID=0;
	System.out.println("HTTP Server ready!");
    }
    public void loadConnection() throws IOException{

	start();
	
	//this.tc = new ThreadConnexion(this.connexion,LAST_ID++); 
	//this.tc.start();//on lance le Thread de connexion

	System.out.println("receiving data\n");
	
	String request = readRequest();
	  
	System.out.println(request);
	  
	System.out.println("sending data\n");

	reqm = new RequestManager(request);	
	
	sendHEAD(reqm.requestType());
	System.out.println("sending data\n");

	resm = new ResponseManager(reqm.requestURL());
     
	sendResponse();
	
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
    
    private void sendHEAD(String type) throws IOException{
	if (type != null){
	    out.write("HTTP/1.0 200 OK\r\n");
	    out.write("Content-Type: " + type + "\r\n");
	    out.write("\r\n");
	}
    }

    private void sendResponse() throws IOException {
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
