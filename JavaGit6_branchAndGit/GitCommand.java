package JavaGit5;

import java.io.File;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GitCommand {
	
	public static void main(String[] args) throws Exception {
		// basic info required
		String githubPath =null;
		String branchName =null;
		
		
		
		
		Scanner input = new Scanner(System.in);
		githubPath ="/Users/apple/Documents/txt 答疑解惑小笔记";
		branchName = "kjkdhah";
		BranchManager branch = new BranchManager(githubPath);
		branch.newBranch(branchName);
		
		
	
		//Step 1 login the username
				String author;
				System.out.println("Please login your userId: ");
				author = input.nextLine();
				
				//Step 1 register the committer
				String committer = null;
				try(final DatagramSocket socket = new DatagramSocket()){
					  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
					  String ip = socket.getLocalAddress().getHostAddress();
					  committer = ip;
					  } catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//Step2 open a directory
				
				System.out.println("Please init/ open a directory: "+"\n"+
						"	Usage:git cd <github path> ");
				String init = input.nextLine();
				String github = init.substring(7);
				System.out.println(github+
						" initialized...");
				if (new File(github).exists()) githubPath = github;
				
				
				
				
				String[] split = null ; // to store the subString
				
				/** to manage all the commands*/
				StringBuilder endCommand =  new StringBuilder();
				endCommand.append("close").append("init").append("checkout");
				StringBuilder gitCommand =  new StringBuilder();
				gitCommand.append("log").append("commit");
				
				/** read in command line*/
				do{
					System.out.println("Please enter your command($):");
					
					String s = input.nextLine();
					split=s.split(" ");
					
					if (!split[0].matches("git") ) {
						System.out.println("Your command should start with git. ");
					}
					
					else if (gitCommand.indexOf(split[1],0) !=-1) {
						
						if (split[1].contentEquals("log")) {
							System.out.println("Commit history: ") ;
						
						// readObject(), we need branchname and github path
						}
						
						if (split[1].contentEquals("commit")) {
							System.out.println("Commit: ") ;
							String path=null;
							String message = null;
							
							// find '-m' 
							int j =0;
							for (j=1;j<split.length; j++) {
								if(split[j].contentEquals("-m")) break;
							}
							
							if (j==0) {
								System.out.println("valid usage: git commit <filepath> -m <your message> !") ;
							}
							else {
							//restore the message
							ArrayList<String> m =new ArrayList<>() ;
							for (int i =j+1; i<split.length ; i++) {
								m.add(split[i]);
								message = String.join(" ",m);
							}
							
							// restore the path 
							ArrayList<String> p =new ArrayList<>() ;
							for (int i =2; i<j ; i++) {
								p.add(split[i]);
								path = String.join(" ",p);
							}
							
							
							CommitObject commit 
							= new CommitObject(path, author,committer, 
									message, githubPath,branchName);
							//System.out.println(commit);
							}
					
						}
						
						if (split[1].contentEquals("checkout")) {
							//System.out.println("Commit: ") ;
						
						
						}
						
					}
					
					else System.out.println("wrong command usage!  ");
					
				} while (endCommand.indexOf(split[1],0 ) != -1);
				input.close();
		


		
		
		
	}
	
	
	
	//System.out.println(endCommand.indexOf("c",1));  // test for indexof return
			//System.out.println(Arrays.toString(split)); // test for String split
			
}
