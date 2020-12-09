package homework;

/* Key��Ϊ�ļ������ļ�������Ϊvalue

֧�����¹���
task1. ����value����洢����Ӷ�Ӧ��key-value

- �½�һ��file����д���ļ�����
- ��file����������Ϊhashֵ

task2. ����key�����ҵõ���Ӧ��valueֵ

- �������hash���ҵ��ļ������ļ����ݾ���value
- ��������ҵ��ļ����򷵻�null

 */



import java.io.*;
import java.security.MessageDigest;

public class KeyValue {
	private String hashpath;
	public String key;
	public String value;
	
	//�޲ι��췽��
	KeyValue(){};
	
	//�вι��췽����
	KeyValue(String path,String hashpath) throws Exception{
		this.hashpath = hashpath;
	}
	
	//����·������洢����Ӷ�Ӧ��key-value
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
	
	//����key�����ҵõ���Ӧ��valueֵ
	String getValue(String key) throws Exception{
		File file = new File(hashpath  +"//" + key + ".txt");
		
		//��������ڷ���null
		if(!file.exists()) 
			return null;
		
		//������ڣ�����������
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

	//����hashֵ
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