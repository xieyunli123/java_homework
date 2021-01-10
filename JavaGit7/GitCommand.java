package JavaGit5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import JavaGit5.CommitManager.CommitObject;

public class GitCommand {
	
	public static void main(String[] args) throws Exception {
		String githubPath =null;
		String branchName =null;		
		Scanner input = new Scanner(System.in);
	
		//Step 1 login the username
				String author;
				System.out.println("Please login your userId: ");
				author = input.nextLine();
				
				//Step 1 register the committer
				String committer = "apple";
				committer = ipSocket();
				
				//Step2 open a directory
				System.out.println("Please init/ open a directory: "+"\n"+
						"	Usage:git cd <github path> ");
				String init = input.nextLine();
				githubPath =  sub_github( init);
				
				
				//step3  checkout latest branch (default) 
				BranchManager branchOfHub = new BranchManager(githubPath);
				branchName = branchOfHub.head(); 
				CommitManager commitOfBranch= new CommitManager (githubPath,branchName);		
				
				String[] split = null ; // to store the subString
				
				/** to manage all the commands*/
				StringBuilder endCommand =  new StringBuilder();
				endCommand.append("close").append("init");
				StringBuilder gitCommand =  new StringBuilder();
				gitCommand.append("log").append("commit").append("checkout").append("revert").append("branch");
				
				/** read in command line*/
				do{
					System.out.println("Please enter your command $:");
					
					String s = input.nextLine();
					split=s.split(" ");
					
					if (!split[0].matches("git") ) {
						System.out.println("Your command should start with git. ");
					}
					
					else if (gitCommand.indexOf(split[1],0) !=-1) {
						
						if (split[1].contentEquals("log")) {
							System.out.println("Commit history: "+"\n") ;				
							File history =new File(commitOfBranch.branchPath);
							if(history.exists())
								System.out.println(commitOfBranch.ReadObject(commitOfBranch.branchPath));
							else System.err.println("no commit history!");
						}
						
						if (split[1].contentEquals("commit")) {
							System.out.println("Commit: ") ;
							String message =null;
							String path =null;
							// find '-m' 
							int j =0;
							for (j=1;j<split.length; j++) {
								if(split[j].contentEquals("-m")) break;
							}
							
							if (j==0) {
								System.err.println("valid usage: git commit <filepath> -m <your message> !") ;
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
							}												
							commitOfBranch.newCommit(path, author,committer,message);
							}
						
						if (split[1].contentEquals("branch")) {						
							branchOfHub.newBranch(split[2]);
							branchName = branchOfHub.head(); 
							
							 commitOfBranch= new CommitManager (githubPath,branchName);				
						}
						if (split[1].contentEquals("checkout")) {							
							branchOfHub.checkout(split[2]);
							
						}
						
						if (split[1].contentEquals("revert")) {
							String revertKey = split[2];
							revert(commitOfBranch,revertKey);
						}
					}
					
					else System.out.println("no such command usage!  ");
					
				} while (true);
				//endCommand.indexOf(split[1],0 ) != -1
				//input.close();		
	
}	
	
	public static String ipSocket() throws UnknownHostException {
		String ip = null;
		try(final DatagramSocket socket = new DatagramSocket()){
			  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			   ip= socket.getLocalAddress().getHostAddress();
		
			  } catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ip;
	}

	public static String sub_github(String init) {	
		String github = init.substring(7);
		
		if (new File(github).exists() && new File(github).isDirectory()) { //check if source path exist
		System.out.println(github+
				" initialized...");
		 return github;
		 }
		else return "no such dirctory";
		
	}
	
	public static void revert(CommitManager commitOfBranch,String revertKey) throws ClassNotFoundException, IOException {
		LinkedList<CommitManager.CommitObject> commitlist = new LinkedList<CommitManager.CommitObject>();
		commitlist = commitOfBranch.ReadObject(commitOfBranch.branchPath);
		//get commit path by revertKey
		int i=0;
		for(i=0;i<commitlist.size();i++) {
			CommitManager.CommitObject c = new CommitManager.CommitObject();
			c = commitlist.get(i);
			if(c.GetKey().equals(revertKey)) {
				break;
			}
		}
		//revert
		int j=0;
		int commitsize = commitlist.size();
		for(j=i+1;j<commitsize;j++) {
			CommitManager.CommitObject c = new CommitManager.CommitObject();
			c = commitlist.get(j);

			String commitpath = c.GetversionPath();
			System.out.println(commitpath);
			File commitfile = new File(commitpath);
			if(commitfile.exists())
				commitfile.delete();
		}
		for(j=i+1;j<commitsize;j++) {
			commitlist.removeLast();
		}
		//write commitOfBranch in .dat file
		//commitOfBranch.WriteObject();
		File file = new File(commitOfBranch.branchPath); 
		  file.createNewFile();
		  try ( //Create an output stream for file array.dat
		        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file)); //继续添加
		      ){
		        //write arrays to the object output stream
		        output.writeObject(commitlist);
		        //output.writeObject(numberOfCommits);
	}

}
}
