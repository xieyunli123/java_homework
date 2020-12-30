package JavaGit5;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import JavaGit.CommitAll;


public class CommitCommand extends Git{
	private File file;
	private String author;

	private String committer;

	private String message;

	private String parent;

	private boolean all;


	private String commitKey;
	private String commitValue;
	
	
	public CommitCommand(String path, String author, String committer, String message) {
		this.file = new File(path);
		this.author = author;
		this.committer = committer;
		this.message = message;

	}

	//鏍规嵁鎸囧畾璺緞锛屾眰鐨刪ashkey
	public String getkey() throws Exception{
		AddCommand index = new AddCommand();
		try {
			return index.add(this.file.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	//灏咰ommit淇℃伅姹俬ashKey
	public void commitCommandKey() throws Exception {
		String commitINFO = "";
		if (commitsAll.isEmpty()){
			commitValue =  getkey()+"\n" + author + "\n" + committer + "\n" + message;
		}
		else{
			commitValue =  getkey()+"\n" + author + "\n" + committer + "\n" + message+ "\n"+ HEAD;
		}
		AddCommand commitindex = new AddCommand();
		commitKey = commitindex.GetKey(commitValue);
		commitsAll.add(commitKey); //灏嗚绠楀緱鍒扮殑key瀛樺叆linkedList涔嬩腑
		String HEAD = commitKey; //甯屾湜鏄釜鍏叡鍙橀噺锛岃�屼笉鏄眬閮ㄥ彉閲忋��
	}
	

	//璋冪敤鍛戒护
	public void call() throws Exception{
		commitCommandKey();	
		System.out.println("Commit Successfully submitted, Committer:"+committer);
	}
	
}




	