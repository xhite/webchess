import java.util.*;
import java.io.*;

public class RequestManager {

    private String request;

    public RequestManager(String request){
	this.request = request;
    }
    
    public String requestURL(){
	String[] getRequest, url;
	if(request.contains("GET")){
	    getRequest = request.split(" ");
	    if(getRequest[1].contains("?")){
		url = getRequest[1].split("\\?");
		return "." + url[0];
	    } else {
		return "." + getRequest[1];
	    }
	} else {
	    return null;
	}
    }

    
    public String requestType() {
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
    
}
