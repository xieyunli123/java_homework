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

	public static void test1(String[] args) throws Exception {
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
		
		String github ="/Users/apple/Documents/txt 答疑解惑小笔记";
		String branch ="master_branch";
		
		String path1 = "/Users/apple/Documents/txt 答疑解惑小笔记/离散小组.rtf";
		String path2 ="/Users/apple/Documents/txt 答疑解惑小笔记/二叉搜索树序列.rtf";
		
		
		/** 测试keyValue的hash生成
		
		KeyValue test1 = new KeyValue(path1,github);
		KeyValue test2 = new KeyValue(path2,github);
	
		System.out.println(test1+"\n"+ test2);
		 */
		

		/** 测试CommitObejct的链表
		
		System.out.println("parent is " +t1.GetParent());
		
		System.out.println(t1.ReadObject().toString());
		
		//CommitObject t2= new CommitObject("/Users/apple/Documents/txt 答疑解惑小笔记/二叉搜索树序列.rtf",author_id,ip,message2,github, branch);
		//System.out.println("parent is " +t2.GetParent());
		
		 */
		
		
		/**测试是否重复*/
		
		CommitObject keyforCommit = new CommitObject(path1,author_id,ip,message,github, branch);
		System.out.println(keyforCommit.ReadObject().toString());
		//System.out.println(keyforCommit);
		CommitObject keyforCommit2 = new CommitObject(path2,author_id,ip,message,github, branch);
		//System.out.println(keyforCommit2);
		
		//\\BlobObject keyforCommit = new BlobObject(path1,github+File.separator+ branch+File.separator);	

		
		
		if(new File(github+File.separator+ branch+".dat").exists()) {
			
			LinkedList<CommitObject> commitList;
			File file = new File(github+File.separator+ branch+".dat"); 
			
			try (//create an input stream for file array.dat
				    ObjectInputStream input =
				    new ObjectInputStream (new FileInputStream(file));
				  ){
					
					LinkedList<CommitObject> allCommit= new LinkedList<CommitObject>();
					allCommit =(LinkedList<CommitObject>) input.readObject();
					commitList = allCommit;
				  }
			
			int numberOfCommits = commitList.size(); 
			System.out.println(commitList+"\n"+ numberOfCommits );

	
				boolean flag = commitList.toString().contains(keyforCommit2.GetKey());
				System.out.println(flag);
					 //System.out.println("no changes were made, already up to date");		
		
		}
		

		
		/**Test with WriteObject()
		KeyValue treea= new KeyValue("/Users/apple/Documents/txt 答疑解惑小笔记/# 面向对象: 画Usecase的疑问.rtf");
		System.out.println(treea);
		 * 
		 */
	
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

		
