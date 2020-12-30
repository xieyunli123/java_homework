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
	private String branchPath;
	
	TreeObject (String path, String branchPath) throws Exception{
    	this.file = new File(path);
    	this.path = path;
    	this.branchPath =branchPath;
    	
    	for(File f : file.listFiles()){
			if(f.isFile()){
				temp = temp + "\n" + vrtype + " blob" + new BlobObject(f.getPath(),branchPath).GetKey() + " " + f.getName();
			}
			else if(f.isDirectory()){
				temp = temp + "\n" + vrtype + " tree" + new BlobObject(f.getPath(),branchPath).GetKey() + " " + f.getName();
			}
    	}
    	BlobObject newkey = new BlobObject(temp,branchPath);
		this.key = newkey.GetKey();
    
    	
    }

	public String GetKey() {
		return key;
	}
	public String getPath() {
		return path;
	}


}