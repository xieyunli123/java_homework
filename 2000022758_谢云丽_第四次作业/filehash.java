package Hash;

import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.InputStream;  
import java.math.BigInteger;  
import java.security.MessageDigest; 
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class filehash {

/*利用缓存区读取hash摘要*/
    public static byte[] SHA1_Checksum(InputStream inputstream) throws Exception {
        
        //         1、建立缓冲区 2、使用摘要算法å
        byte[] buffer = new byte[1024];
        MessageDigest complete =MessageDigest.getInstance("SHA-1");


        int numread=0;
        do {
            //    1、inputstream读出到buffer之后，会返回字节数 2、根据buffer[0:numread]的内容更新hash值
            numread =inputstream.read(buffer);
            if (numread>0){
                complete.update(buffer,0, numread);
            }
            //    文件读取完毕
        } while(numread!=-1);
        
        inputstream.close();
        return complete.digest();
    }

/*利用文件路径打印hash值*/
// 文件 -> inputstream -> byte[]-> string
    public static void SHA1_Print(File file) {
        try{
            // 已有文件对象
            FileInputStream is =new FileInputStream(file);
            //    生成哈希值
            byte[] sha1= SHA1_Checksum(is);
            //    打印哈希值
            String result ="";
            for(int i=0; i< sha1.length; i++){
                result += Integer.toString(sha1[i] &0xFF, 16);
            }
            System.out.println(result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


//     path-> file-> getname()-> print
    public static void dfs (String path){
        //     创建文件、显示文件
        File dir = new File(path);
        File[] fs= dir.listFiles();
      
        //     深度遍历：两种情况
        for (int i=0; i<fs.length; i++){
            if (fs[i].isFile()){
                System.out.println("file:"+fs[i].getName());
                SHA1_Print(fs[i]);
                
            }
            if (fs[i].isDirectory()){               
                SHA1_Print(fs[i]);
                System.out.println("Directory:"+fs[i].getName());
                dfs(path+'/'+fs[i].getName());
             //*    此处同我预计的不一样，访问文件的时候，无法如同列表一样直接访问
            }
        }
    }

    public static void main (String[] args){
        try{
            dfs("/Users/apple/Javatest");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

	


