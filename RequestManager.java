import java.util.*;
import java.io.*;

public class RequestManager {

    private String request;

    public RequestManager(String request) {
	this.request = request;
    }

    private String requestURL() {
	if(request.contains("GET")){
	    String[] getRequest;
	    getRequest = request.split(" ");
	    return getRequest[1];
	} else {
	    return null;
	}
    }
	
    private String requestParams() {
	String url = requestURL();
	String[] params;
	if(url != null && url.contains("?")) {
	    params = url.split("\\?");
	    return params[1];
	} else {
	    return null;
	}
    }

    public String requestFile(){
	String url = requestURL();
	String[] file;
	if(url != null)
	    if(url.contains("?")){
		file = url.split("\\?");
		return "." + file[0];
	    } else {
		return "." + url;
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

    public String requestPosition() {
	String[] position;
	String params = requestParams();
	if(params != null) {
	    position = params.split(".");
	    return position[0];
	} else {
	    return null;
	}
    }
    
}
