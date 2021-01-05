package JavaGit5;

import java.io.BufferedReader;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class CommitObject implements Serializable{
	private static final long serialVersionUID = -5016890703844398449L;
	
	private String path;
	private transient File file;
	private KeyValue keyforCommit = null;
	private String versionPath;
	private String author;
	private String committer;
	private String message;
	/**Construct a CommitObject with four parameters 
	 * @param file, the target object
	 * @param author, annotates the userId who made the change
	 * @param commiter, the userId who submit the change
	 * @param message, the digest of this commit.
	 * */
	private String commitKey;
	private String commitValue;
	
	
	private String parent=null;
	private transient int index; 
	private static String head;
	
	
	private static String branchName;
	private static String Github;
	private  String branchPath ;
	
	private static LinkedList<CommitObject> commitList = new LinkedList<>();
	private int numberOfCommits;

	/** To Construct a CommitObject:
	 * create a new key for target path,
	 * global variable head for newest record of commit, commitList for 
	*/
	CommitObject(){
	}
	@SuppressWarnings("null")
	CommitObject(String path, String author, String committer, String message, String Github, String branchName) throws Exception {
		this.path = path;
		this.file = new File(path);
		this.author = author;
		this.committer = committer;
		this.message = message;	
		this.branchName = branchName;
		this.Github = Github;
		this.branchPath = Github +File.separator + branchName +File.separator;
		// check if source file exists
		if (!file.exists()){
		     System.out.println("Source file does not exist");
		     System.exit(1);
		     return;
		   }
		
		//check if branch had been established;
		File dir =new File(branchPath);
		if(!dir.exists()) {
			boolean flag = dir.mkdir();
			System.out.println("new branch:" +branchName+" created!" );
		}
		
		
		
		tempCommit();

		if(new File(Github+File.separator+ branchName+".dat").exists()) {
			
			/** check if parent exists in the file*/
			commitList = ReadObject();
			numberOfCommits = commitList.size(); 
			
			if(numberOfCommits>=1) {
				CommitObject parent = commitList.get(numberOfCommits-1); 
				this.parent = parent.GetKey(); 
		
			
				/**check if any changes were made compared with parent*/
				// parent. but without change -> out, and print warnings;	
				
				boolean flag = commitList.toString().contains(keyforCommit.GetKey());
				System.out.println(flag);
				
				if(flag) {
					 System.out.println("no changes were made, already up to date");
					
					 return;
				}
				else {
					createNewCommit() ;
				}				
			}
		}
		
		//no parent at all
		else {
		/**directly commit without comparison*/
			createNewCommit() ;	
		}

		
		
	
	}
	private void tempCommit() throws Exception {
		if (file.isFile()) {
			keyforCommit = new BlobObject(path,branchPath);
			this.commitKey = keyforCommit.GetKey();
			this.versionPath =keyforCommit.GetversionPath();
			//this.commitValue = keyforCommit.GetValue(commitKey);				
		}
			
		else if (file.isDirectory()) {
			keyforCommit = new TreeObject(path,branchPath);
			this.commitKey = keyforCommit.GetKey();
			this.versionPath =keyforCommit.GetversionPath();							
			//this.commitValue = keyforCommit.GetValue(commitKey);
			
		}
	}
	
		private void createNewCommit() throws Exception{
			//record global variables 			
			commitList.add(this);
			numberOfCommits++;
			index =numberOfCommits;
			head = this.commitKey;  //newest commit 
			
			WriteObject();				
			//Print commit;
			System.out.println(commitKey+"Committed at "+new java.util.Date());
			
		}
	
		
		public String GetKey() {
			return commitKey;
		}
		public String GetParent() {
			if (parent != null)
			return this.parent;
			else {
				System.out.println("There exists no parent");
				return null ;
			}
		}
		
		@Override
		public String toString() {
			String info = commitKey+" "+"commit No."+numberOfCommits+
					"\n source file: "+path+
					"\n version path: "+versionPath+
					"\n author: "+author+
					", committer:"+ committer + 
					" \n -message: " +message;
			//System.out.println(info);
			return info;
			
		}
		     
		
		public static  void WriteObject() throws IOException {
			
			File file = new File(Github + File.separator+branchName+".dat"); 
			file.createNewFile();
			try ( //Create an output stream for file array.dat
					    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file)); //继续添加
					  ){
					    //write arrays to the object output stream
					   	output.writeObject(commitList);
					   	//output.writeObject(numberOfCommits);
					
					  }
		}
		
		@SuppressWarnings("unchecked")
		public LinkedList<CommitObject> ReadObject() throws IOException, ClassNotFoundException{
		
			File file = new File(Github+File.separator+ branchName+".dat"); 
			
			try (//create an input stream for file array.dat
				    ObjectInputStream input =
				    new ObjectInputStream (new FileInputStream(file));
				  ){
					
					LinkedList<CommitObject> allCommit= new LinkedList<CommitObject>();
					allCommit =(LinkedList<CommitObject>) input.readObject();
					//String numberOfCommit = (String) input.readObject();
					return allCommit; //+ numberOfCommit;
				  }
			
		}
		
		
}

