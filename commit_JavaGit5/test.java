package JavaGit5;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String ip = null;
		String author_id = "xyl";
		String message = "xyl first Commit";
		String message2 = "xyl second Commit";

		try(final DatagramSocket socket = new DatagramSocket()){
			  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			   ip = socket.getLocalAddress().getHostAddress();} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
		//Scanner input = new Scanner(System.in);
		//String keyboardin =input.next();
	
		
		
		String github ="/Users/apple/Documents/txt 答疑解惑小笔记";
		String branch ="master_branch";
		
		//
		CommitObject t1 = new CommitObject("/Users/apple/Documents/txt 答疑解惑小笔记/离散小组.rtf",author_id,ip,message,github, branch);
		CommitObject t2= new CommitObject("/Users/apple/Documents/txt 答疑解惑小笔记/二叉搜索树序列.rtf",author_id,ip,message2,github, branch);
		//(ReadObject());
		//t1.GetParent();
		System.out.println(t1);
		System.out.println(t2.ReadObject().toString());
		

		

		//Test with WriteObject()
		//KeyValue treea= new KeyValue("/Users/apple/Documents/txt 答疑解惑小笔记/# 面向对象: 画Usecase的疑问.rtf");
		//System.out.println(treea);
	
	}
	
	
	
	
	
	
	
	
	
	
	/**try with static writeObject */

	public static void WriteObject(CommitObject ob) throws IOException {
		File file = new File("/Users/apple/Documents/txt 答疑解惑小笔记/commit3.dat");
		 try ( //Create an output stream for file array.dat
				    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file,true)); //继续添加
				  ){
				    //write arrays to the object output stream
				   	output.writeObject(ob);
				
				  }
	}
	
	public static String ReadObject() throws IOException, ClassNotFoundException{
		File file = new File("/Users/apple/Documents/txt 答疑解惑小笔记/commit3.dat");
		try (//create an input stream for file array.dat
			    ObjectInputStream input =
			    new ObjectInputStream (new FileInputStream(file));
			  ){
				CommitObject key =  (CommitObject) input.readObject();
				return key.toString();
			  }
	}
	
		
		
		
		
		//CommitObject tree2= new CommitObject
		
		//
		//System.out.println(tree3.GetParent());
		
	}

		
