package JavaGit5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class BranchManager {
	private LinkedList<Branch> allBranch = new LinkedList<>();
	private String github;  // belong to which hub
	private int numOfbranch;
	private Branch head_ofBranch;
	
	BranchManager(String github) throws ClassNotFoundException, IOException{ 
		this.github = github;
		File file = new File(github+File.separator+"allBranch.dat"); 
		
		if(file.exists()) {
			allBranch =ReadBranch();
			numOfbranch = allBranch.size();
		}
		// this github has never established any branch
		else {
			newBranch("master");
			System.out.println(">> at master branch" );
			WriteBranch();
			head_ofBranch =allBranch.get(0);
		}
		
	}
	
	// switch to declared branch
	public void checkout(String branchName) throws ClassNotFoundException, IOException {
		allBranch =ReadBranch();
		for (Branch each: allBranch) {
			if (branchName.contentEquals(each.branchName)) {
				head_ofBranch = each;
				System.out.println(">> Switch to branch " + branchName);
			}
		}
		
	}
	//return latest established branch
	public String head() {
		return head_ofBranch.branchName;
	}
	
	//return latest established branch
	public String latest_head() {
		return allBranch.get(numOfbranch-1).branchName;
	}
	
	
	public void newBranch(String branchName) throws IOException {
		Branch newbranch = new Branch(branchName);
		newbranch.setGithub(this.github);
		allBranch.add(newbranch);
		numOfbranch++;
		WriteBranch();
		System.out.println(">> Branch" + branchName + "created!");
	}
	
	
	public static class Branch implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 4400223999596556589L;
		private String branchName;
		private String github;  // belong to which hub
		private String branchPath;

		Branch(String branchName){
			this.branchName = branchName;
		}
		
		public void setGithub(String github) {
			this.github =github;
			this.branchPath = github + File.separator + branchName+".dat";
		}
		
		@Override
		public String toString() {
			return new StringBuilder()
					  .append("\n"+"branch name："+branchName+"\n")
					   .append("	>branch path："+branchPath +"\n")
					   .append("	>github："+github+"\n")
					   .toString();	
			
		}
	}
	
	
	
	public  void WriteBranch() throws IOException {	
		File file = new File(github+File.separator+"allBranch.dat"); 
		file.createNewFile();
		 
		try ( //Create an output stream for file array.dat
				    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
				  ){				  
				   	output.writeObject(allBranch);
				  }
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Branch> ReadBranch() throws IOException, ClassNotFoundException{		
		File file = new File(github+File.separator+"allBranch.dat"); 
		try (//create an input stream for file array.dat
			    ObjectInputStream input =
			    new ObjectInputStream (new FileInputStream(file));
			  ){			
				LinkedList<Branch> readBranch= new LinkedList<Branch>();
				readBranch =(LinkedList<Branch>) input.readObject();
				return readBranch; 
			  }
		
	}

}
