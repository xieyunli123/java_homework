package homework;

/* Key作为文件名，文件内容作为value

支持以下功能
task1. 给定value，向存储中添加对应的key-value

- 新建一个file对象，写入文件内容
- 将file对象重命名为hash值

task2. 给定key，查找得到对应的value值

- 如果根据hash能找到文件，则文件内容就是value
- 如果不能找到文件，则返回null

 */



import java.io.*;
import java.security.MessageDigest;

public class KeyValue {
	private String hashpath;
	public String key;
	public String value;
	
	//无参构造方法
	KeyValue(){};
	
	//有参构造方法，
	KeyValue(String path,String hashpath) throws Exception{
		this.hashpath = hashpath;
	}
	
	//给定路径，向存储中添加对应的key-value
	String setKeyValue(String path) throws Exception{
		String key = getHash(path);
		File file = new File(hashpath +"//"+ key + ".txt");
		file.createNewFile();

		
		FileInputStream is = new FileInputStream(path);
        FileOutputStream os = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int numRead=0;
        while (numRead!=-1){
            numRead = is.read(buffer);
            if (numRead>0)
                os.write(buffer, 0 , numRead);
        }
        is.close();
        os.close();
		
		return key;
	}
	
	//给定key，查找得到对应的value值
	String getValue(String key) throws Exception{
		File file = new File(hashpath  +"//" + key + ".txt");
		
		//如果不存在返回null
		if(!file.exists()) 
			return null;
		
		//如果存在，返回其内容
		else {
			BufferedReader reader = null;
	        StringBuffer sbf = new StringBuffer();
	        reader = new BufferedReader(new FileReader(file));
	        String tempStr;
	        while((tempStr = reader.readLine())!=null) {
	        	sbf.append(tempStr);
	        }
	        reader.close();
	        return sbf.toString();    	        
	     }
	}

	//计算hash值
	public String getHash(String path) throws Exception{
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("SHA-1");
		File file = new File(path);
		FileInputStream is = new FileInputStream(file);
		int numRead = 0;
		do {
			numRead = is.read(buffer); 
	        if (numRead > 0) {  
	        	complete.update(buffer, 0, numRead);
	        	}
	        } while (numRead != -1); 
		is.close(); 
		
		byte[] sha1 = complete.digest();
		String result = " ";
		for(int i = 0; i < sha1.length; i++) {
			result += Integer.toString(sha1[i]&0xFF,16);
		}
		return result;
	}
	
}