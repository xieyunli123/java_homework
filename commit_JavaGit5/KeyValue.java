package JavaGit5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
public class KeyValue implements Serializable {

	private static final long serialVersionUID = -4615912312187301435L;
	private String path;  
	private String key ;	
	
	//protected String branchPath ;
	private String branchPath;
	private String versionPath;
	private File file;

	/** Construct KeyValue for target file path*/
	KeyValue(){};
    KeyValue(String path,String branchPath) throws Exception {
    	this.path =path;
    	File file = new File(path);
    	this.file =file;
    	this.branchPath=branchPath;
    	
    	
    	
    	
    	
    	/** Generate Key from InputStream */
    	FileInputStream is = new FileInputStream(file);
    	byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("SHA-1");
        int numRead = 0;
        do {
            numRead = is.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        // Print digest message 
        String sha1 = "";
		for(int i=0;i<complete.digest().length;i++) {
			sha1 += Integer.toString(complete.digest()[i] & 0xFF, 16);
		}
		this.key = sha1;
		is.close();
		
		/**Write file content to save path, with new file named after key */
	 	FileInputStream In = new FileInputStream(file);
	 		this.versionPath =branchPath+ key + ".txt"; 
            File keyfile = new File(versionPath);
            keyfile.createNewFile();
            FileOutputStream output = new FileOutputStream(keyfile);
            
            byte[] buffer1 = new byte[1024];
            int numRead1 = 0;
            do {
            	//Read from file content located target path
                numRead1 = In.read(buffer1);
                //Write to keyfile
                if(numRead1 > 0){
                    output.write(buffer1,0,numRead1);
                }
            }while(numRead1!=-1);
            In.close();
            output.close();    
    }
    
    public String GetKey() {
		return key;
    }
    public String GetversionPath() {
    	return versionPath;
    }

	public String GetValue(String key) throws Exception{
		File keyfile = new File(versionPath);
		if(!keyfile.exists()) 
			return null;
		else {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(keyfile)));
	        StringBuffer value = new StringBuffer();
			String line; 
	        while((line = in.readLine())!=null) {
	        	value.append(line);
	        }
	        in.close();
	        return value.toString();    	        
	     }
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				  .append("key："+key+"\n")
				   .append("path："+path +"\n")
				 
				   .toString();	
	}
	
	

}




/** 
public String StringSHA1Checksum(String value) throws Exception{
    MessageDigest complete = MessageDigest.getInstance("SHA-1");
    complete.update(value.getBytes());
    return getSha1(complete.digest());
}

  public String GetKey(String value) throws Exception{
	return StringSHA1Checksum(value);
}
*/