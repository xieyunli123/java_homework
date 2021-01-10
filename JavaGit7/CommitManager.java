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

public class CommitManager{
	private LinkedList<CommitObject> commitList = new LinkedList<>();
	private int numberOfCommits;
	
	private  String branchName;
	private  String Github;
	String branchPath ;
	
	

	private int index; 
	private String head;
	
	CommitManager(){};
	CommitManager(String Github,String branchName) throws ClassNotFoundException, IOException{
		this.Github = Github;
		this.branchName = branchName;
		this.branchPath = Github+File.separator+ branchName+".dat";
		
		//Check if branchDir had been created;
		File dir =new File(Github+File.separator+branchName+File.separator);
		if(!dir.exists()) {
			dir.mkdir();
			System.out.println("new branch dir:" +branchName+" created!" );
		}
			
		//Read in the branchfile.dat when branch.dat file exists
		if (!isEmpty()) {
			this.commitList = ReadObject(branchPath);		
			numberOfCommits = commitList.size(); 
		}
	
	}
	
public void newCommit(String path, String author, String committer, String message) throws Exception {
			
			CommitObject temp = new CommitObject(path,author,committer,message);
			
			/**check if any changes were made compared with parent*/
			if(numberOfCommits>=1) {//check if parent exists 
				String parent = commitList.peekLast().commitKey;
				temp.setParent(parent);
			
				for (CommitObject each:commitList) {
					if ( (each.equals(temp)) ) { //check changes 
						System.err.println("no changes were made, already up to date");
						return;
					}
				}
				
			}	
			//write version file onto disk
			temp.writeVersionFile(Github+File.separator+branchName+File.separator);
			temp.toString();
			
			//add to current branch
			commitList.add(temp);
			
			//record global variables 			
			numberOfCommits++;
			index =numberOfCommits;
			head = temp.commitKey;  //newest commit 
		
			//write branch file onto disk
			WriteObject();				
			//Print commit;
			System.out.println(temp.commitKey+"   Committed at "+new java.util.Date());
			
}

public LinkedList<String> iterator() {
	LinkedList<String> keys = new LinkedList<String>();
	for (CommitObject each:commitList) {
		keys.add(each.GetKey());
	}
	return keys;
}

	@SuppressWarnings("unchecked")
	public LinkedList<CommitObject> ReadObject(String branchpath) throws IOException, ClassNotFoundException{
		File file = new File(branchPath); 
		try (//create an input stream for file array.dat
			    ObjectInputStream input =
			    new ObjectInputStream (new FileInputStream(file));
			  ){				
				LinkedList<CommitObject> allCommit= new LinkedList<CommitObject>();
				allCommit =(LinkedList<CommitObject>) input.readObject();
				
				return allCommit; 
			  }		
	}
	
	public boolean isEmpty() {
		if(!new File(branchPath).exists())return true;
		if (commitList.isEmpty()) return true;
		else return false;
	}
	
	
	public void WriteObject() throws IOException {
		
		File file = new File(branchPath); 
		file.createNewFile();
		try ( //Create an output stream for file array.dat
				    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file)); //继续添加
				  ){
				    //write arrays to the object output stream
				   	output.writeObject(commitList);
				   	//output.writeObject(numberOfCommits);
				
				  }
	}
	
	
	
	/**Construct a CommitObject with four parameters 
	 * @param file, the target object
	 * @param author, annotates the userId who made the change
	 * @param commiter, the userId who submit the change
	 * @param message, the digest of this commit.
	 * */
public 	static class CommitObject implements Serializable, Comparable<CommitObject>{
	private static final long serialVersionUID = -5016890703844398449L;	
	private String path;
	private File file;
	
	private String versionPath;
	private String author;
	private String committer;
	private String message;
	
	private String commitKey;
	private String parent;

	CommitObject(){
	}

	@SuppressWarnings("null")
	CommitObject(String path, String author, String committer, String message) throws Exception {
		this.path = path;
		this.file = new File(path);
		this.author = author;
		this.committer = committer;
		this.message = message;	
		// check if source file exists
			if (!file.exists()){
			     System.out.println("Source file does not exist");
			     return;
			   }	
		//temp key
		KeyValue keyforCommit;
			if (file.isFile()) {
				keyforCommit = new BlobObject(path);
				this.commitKey = keyforCommit.GetKey();
							
			}		
			else if (file.isDirectory()) {
				keyforCommit = new TreeObject(path);
				this.commitKey = keyforCommit.GetKey();
			}		
	}	 
	
	public String GetKey() {
		return commitKey;
	}
	public String GetversionPath(){ return versionPath;}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String GetParent() {
		if (parent != null)
		return this.parent;
		else {
			System.out.println("There exists no parent");
			return null ;
		}
	}
	
	/**Write file content to save path, with new file named after key 
     * @throws IOException */
	public void writeVersionFile(String branchPath) throws IOException {
		this.versionPath =branchPath+ commitKey + ".txt"; 
		
		FileInputStream In = new FileInputStream(file);
	    File keyfile = new File(versionPath);
	    keyfile.createNewFile();
	    FileOutputStream output = new FileOutputStream(keyfile);
	    
	    byte[] buffer1 = new byte[1024];
	    int numRead1 = 0;
	    do {
	    	//Read from file content located target path
	        numRead1 = In.read(buffer1);
	        //Write to keyfile
	        if(numRead1 > 0){
	            output.write(buffer1,0,numRead1);
	        }
	    }while(numRead1!=-1);
	    In.close();
	    output.close();    
	}
		
		
	public boolean equals(CommitObject that) {
		if (that == null) return false;
		return that.GetKey().contains(this.GetKey()); 	
	}
	
	@Override 
	public int compareTo(CommitObject that) {
		if (that.GetKey().contentEquals(this.GetKey())) return 1;
		else return -1;		
	}  
		
	@Override
	public String toString() {
		String info = "\n "+commitKey+" "+
				"\n source file: "+path+
				"\n version path: "+versionPath+
				"\n author: "+author+
				", committer:"+ committer + 
				" \n -message: " +message+"\n ";
		//System.out.println(info);
		return info;
		
	}
	}
}

