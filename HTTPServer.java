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
/** 
* représente le nombre de connexion
*/
//private static Integer LAST_ID;

    public HTTPServer() throws NumberFormatException, IOException, NoSuchElementException{
	System.out.println("Starting server...");
	serverSocket = new ServerSocket(12345); // On injecte le port sur lequel le serveur va écouter
	//LAST_ID=0;
	System.out.println("HTTP Server ready!");
    }
    public void loadConnexion() throws IOException{

	start();
	
	//this.tc = new ThreadConnexion(this.connexion,LAST_ID++); 
	//this.tc.start();//on lance le Thread de connexion

	System.out.println("receiving data\n");

	String request;
	String[] getRequest;
	while((request = in.readLine()) != null){
	    System.out.println(request);
	    if(request.contains("GET")){
		getRequest = request.split(" ");
		sendFile(getRequest[1]);
	    }else{
		if(request.isEmpty()){
		    break;
		}
	    }
	}
	System.out.println("sending data\n");

	sendHEAD();
	
	close();
    }
    private void start() throws IOException{
	clientSocket = serverSocket.accept();
	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }
    private void sendHEAD() throws IOException{
	out.write("HTTP/1.0 200 OK\r\n");
	out.write("Content-Type: text/html\r\n");
	out.write("\r\n");
	out.write("<title>hello</title>");
    }

    private void close() throws IOException{
	out.close();
	in.close();
	clientSocket.close();
    }
    private void sendFile(String filename){
    }
}
