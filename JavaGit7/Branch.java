package JavaGit5;

import java.io.File;
import java.io.Serializable;

public class Branch  implements Serializable {
		private static final long serialVersionUID = 4400223999596556589L;
		String branchName;
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

