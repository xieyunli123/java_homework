package JavaGit5;

import java.io.File;

public class TreeObject extends AddCommand{
	private String type = "tree";
	private String path;
	private String key;	
	private File file;
	private String vrtype = "100644";
	private String temp;

    public TreeObject(String path) throws Exception {
    	this.file = new File(path);
    	this.path = path;
    }

    public String ComputeKey() throws Exception{
	for(File f : file.listFiles()){
		if(f.isFile()){
			temp = temp + "\n" + vrtype + " blob" + new BlobObject(f.getPath()).GetKey() + " " + f.getName();
		}
		else if(f.isDirectory()){
			temp = temp + "\n" + vrtype + " tree" + new TreeObject(f.getPath()).GetKey() + " " + f.getName();
		}
	}
	this.key = GetKey(temp);
	return key;
}

	public String GetKey() {
		return key;
	}


}