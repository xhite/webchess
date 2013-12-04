import java.util.*;
import java.io.*;

public class ResponseManager {

    private String filename;
    private BufferedReader br;

    public ResponseManager(String filename) throws FileNotFoundException {
	this.filename = filename;
	if(fileExists()) {
	    br = new BufferedReader(new FileReader(filename));   
	}
    }
    
    public String read() throws IOException{
	if (!fileExists()) 
	    return null;
	else
	    return br.readLine();
    }

    public void close() throws IOException{
	br.close();
    }

    private Boolean fileExists(){
	File f = new File(filename);
	if (f.exists() && !filename.equals("./"))
	    return true;
	return false;
    }
}
