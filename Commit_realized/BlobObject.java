package JavaGit5;

import java.io.File;
import java.io.FileInputStream;

public class BlobObject extends AddCommand{
	private String type = "blob";
	private String path;
	private String key;	
	private File file;
	private String vrtype = "100644";

    public BlobObject(String path) throws Exception {
    	this.file = new File(path);
        this.key = GetKey(new FileInputStream(file));
        this.path = path;
    } 

    public void write() throws Exception {
        WriteToFile(path);
    }

	public String GetKey() {
		return key;
	}

}