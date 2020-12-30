package JavaGit5;

import java.io.*;
import java.security.MessageDigest;

public class KeyValue {

	protected String path;  
	protected String key ;	
	protected String savepath ;
	
	/** */
	KeyValue(){
		
	}
	/** Construct KeyValue for target file path*/
    KeyValue(String path) throws Exception {
    	try (FileInputStream In = new FileInputStream(new File(path))){
    		FileSHA1Checksum(In);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	//save file content
    	savepath = path +File.separator+ "HashKey";
    	WriteKeyToFile(); 	
    }
    
    public String getKey() {
		return key;
    }
    public String getSavepath() {
    	return savepath;
    }
 
    
	/** Generate Key from InputStream */
	public void FileSHA1Checksum(InputStream is) throws Exception {
			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance("SHA-1");
	        int numRead = 0;
	        do {
	            numRead = is.read(buffer);
	            if (numRead > 0) {
	                complete.update(buffer, 0, numRead);
	            }
	        } while (numRead != -1);
	        is.close();
	        
	        // Print digest message 
	        String sha1 = "";
			for(int i=0;i<complete.digest().length;i++) {
				sha1 += Integer.toString(complete.digest()[i] & 0xFF, 16);
			}
			this.key = sha1;
	}
	
	/**Write file content to save path, with new file named after key */
	public void WriteKeyToFile() throws Exception{
	
		File keyfile = new File(savepath +File.separator+ key + ".txt");
		keyfile.createNewFile();
		
        FileInputStream input = new FileInputStream(path);
        FileOutputStream output = new FileOutputStream(keyfile);
        
        byte[] buffer = new byte[1024];
        int numRead = 0;
        do {
        	//Read from file content located target path
            numRead = input.read(buffer);
            //Write to keyfile
            if(numRead > 0){
                output.write(buffer,0,numRead);
            }
        }while(numRead!=-1);
        
        input.close();
        output.close();
    }
	
	public String GetValue(String key) throws Exception{
		File keyfile = new File(savepath + File.separator + key + ".txt");
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