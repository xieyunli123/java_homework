package JavaGit5;

import java.io.File;
import java.io.FileInputStream;

public class BlobObject extends KeyValue{
	private static final long serialVersionUID = 7776932294900051410L;
	private String type = "blob";
	private String key;
	
	/**Construct a BlobObject, create a new key, and write content to keyFile */
   BlobObject(String path) throws Exception {
    	super(path);//构造对象
    	this.key =super.GetKey();
    } 
   public String GetKey() {
	   return key;
   }
}


