package homework;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class folderhash {
	public static void dfs_hash (String path,String hashpath)throws Exception{
	    //     创建文件、显示文件
	    File dir = new File(path);
	    File[] fs= dir.listFiles();
	    String[] subhash = new String[100];
	    
	  
	    //     深度遍历：两种情况
	    for (int i=0; i<fs.length; i++){
	        if (fs[i].isFile()){
	            System.out.println("file:"+fs[i].getName());
	            String s1=fs[i].getName();
	            KeyValue s= new KeyValue(path+'/'+fs[i].getName(),hashpath);
	            String skey= s.setKeyValue(path+'/'+fs[i].getName()); //生成的key
	            subhash[i] = skey;
	        }
	        if (fs[i].isDirectory()){                     	
	            dfs_hash(path+'/'+fs[i].getName(), hashpath);
	        
	        }
	    }
	    
	    //将subhash中的字符串，写入txt之中，求得文件夹的hash
	    File file = new File(hashpath +'/'+dir.getName() + ".txt");
		file.createNewFile();
	    FileOutputStream os = new FileOutputStream(file);
	    for (int j=0; j<subhash.length;j++) {
	    	os.write(subhash[j].getBytes());
	    }
	    
	}
}
