import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class ResponseManager {

    private String filename;
    File file;
    private BufferedReader br;
    private HTMLBuilder htmlBuilder;

    public ResponseManager(String filename) throws FileNotFoundException {
	this.filename = filename;
	file = new File(filename);
	if (fileExists())
	    br = new BufferedReader(new FileReader(filename));
    }
    
    public String read() throws IOException {
	if (fileExists())
	    return br.readLine();
	return null;
    }

    private Boolean fileExists() {
	if (file.exists() && !filename.equals("./"))
	    return true;
	return false;
    }

    public String date() {
	SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	return sdf.format(file.lastModified());
    }

    public void close() throws IOException {
	br.close();
    }

}
