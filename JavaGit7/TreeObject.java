package JavaGit5;

import java.io.File;

public class TreeObject extends KeyValue{

	private static final long serialVersionUID = 1040787535000858419L;
	private String type = "tree";
	private String path;
	private String key;	
	private File file;
	
	
	private String vrtype = "100644";
	private String temp;

	
	TreeObject (String path) throws Exception{
    	this.file = new File(path);
    	this.path = path;
    	
    	for(File f : file.listFiles()){
			if(f.isFile()){
				temp = temp + "\n" + vrtype + " blob" + new BlobObject(f.getPath()).GetKey() + " " + f.getName();
			}
			else if(f.isDirectory()){
				temp = temp + "\n" + vrtype + " tree" + new BlobObject(f.getPath()).GetKey() + " " + f.getName();
			}
    	}
    	BlobObject newkey = new BlobObject(temp);
		this.key = newkey.GetKey();
    
    	
    }

	public String GetKey() {
		return key;
	}


}