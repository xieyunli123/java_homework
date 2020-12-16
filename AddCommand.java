package JavaGit5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.LinkedList;


public class AddCommand {

	//1 为什么要用迭代器
		//把这个类找到
	private Collection<String> filepatterns;
	//private WorkingTreeIterator workingTreeIterator;
	
	private boolean update = false;
	
	protected String path = "D:\\A二学位课程\\Java\\作业";  //原文件存储路径
	
	protected String savepath = "D:\\A二学位课程\\Java\\作业\\hash";   //key-value文件存储路径


	/**
	 * Constructor for AddCommand
	 * @return 
	 * @throws Exception 
	 */
	
	public String add(String filename) throws Exception {
		File file = new File(path + File.separator + filename + ".txt");
		if(!file.exists()) 
			return null;
		else {
	        WriteToFile(path);	//将原文件存储到savepath下
	        FileInputStream input = new FileInputStream(file);
	        String filehash = FileSHA1Checksum(input);
	        System.out.println("Add successful!");
	        input.close();
	        return filehash;    //返回文件哈希值
		}		
	}
	
	/*
	public void addall() {
		
	}
	
    public static void dfs(String path) throws Exception {
        File dir = new File(path);
        File[] fs = dir.listFiles();
        for(int i = 0; i < fs.length; i++) {
            if(fs[i].isFile()) {
                System.out.println("file " + fs[i].getName());
                FileInputStream is = new FileInputStream(fs[i]);
                add(fs[i].getName());
                
                System.out.println(fs[i].getName()+'\n');
            }
            if(fs[i].isDirectory()) {
                System.out.println("directory " + fs[i].getName());

                dfs(path + File.separator + fs[i].getName());
            }
        }
    }
    */
	
	//定义hash值计算函数：实现文件或文件夹的hash值计算
		public String FileSHA1Checksum(InputStream is) throws Exception {
				byte[] buffer = new byte[1024];// 用于计算hash值的文件缓冲区
				MessageDigest complete = MessageDigest.getInstance("SHA-1");// 使用SHA1哈希/摘要算法
		        int numRead = 0;
		        do {
		            numRead = is.read(buffer);// 读出numRead字节到buffer中
		            if (numRead > 0) {
		                complete.update(buffer, 0, numRead);// 根据buffer[0:numRead]的内容更新hash值
		            }
		        } while (numRead != -1);// 文件已读完，退出循环
		        is.close();// 关闭输入流
		        return getSha1(complete.digest());// 返回SHA1哈希值
		    }


	    public String StringSHA1Checksum(String value) throws Exception{
	        MessageDigest complete = MessageDigest.getInstance("SHA-1");
	        complete.update(value.getBytes());
	        return getSha1(complete.digest());
	    }


		public String getSha1(byte[] temp) {
			String sha1 = "";
			for(int i=0;i<temp.length;i++) {
				sha1 += Integer.toString(temp[i] & 0xFF, 16);
			}
			return sha1;
		}


	    public String GetKey(InputStream file) throws Exception{
	    	return FileSHA1Checksum(file);
	    }


	    public String GetKey(String value) throws Exception{
	    	return StringSHA1Checksum(value);
	    }


		public String GetValue(String key) throws Exception{
			File file = new File(savepath + File.separator + key + ".txt");
			if(!file.exists()) 
				return null;
			else {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								new FileInputStream(file)));
		        StringBuffer value = new StringBuffer();
				String line; 
		        while((line = in.readLine())!=null) {
		        	value.append(line);
		        }
		        in.close();
		        return value.toString();    	        
		     }
		}


		public void WriteToFile(String path) throws Exception{
			String key = FileSHA1Checksum(new FileInputStream(path));
			File file = new File(savepath +File.separator+ key + ".txt");
			file.createNewFile();
	        FileInputStream input = new FileInputStream(path);
	        FileOutputStream output = new FileOutputStream(file);
	        byte[] buffer = new byte[1024];
	        int numRead = 0;
	        do {
	            numRead = input.read(buffer);
	            if(numRead > 0){
	                output.write(buffer,0,numRead);
	            }
	        }while(numRead!=-1);
	        input.close();
	        output.close();
	    };	


	public static void main(String[] Args) throws Exception {
		AddCommand ad = new AddCommand();
		String filename = "text1";
		ad.add(filename);
	}
}



