package JavaGit5;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Git  {
	/** The git repository this class is interacting with */
	private final String repo;
	private final boolean closeRepo;
	public String HEAD;
	public List<String> commitsAll = new LinkedList<>();//鐎涙ɑ鏂侀柧鎹愩��

	/*閺嬪嫰锟界姴鍤遍弫锟�*/
	public void git(String repo){
		this.repo=repo;
	}

	//1 娴犳挸绨遍惃鍕彠閸滃苯绱�
	public static Git open(File dir) throws IOException {
		return open(dir, FS.DETECTED);
	}

	public void close() {
		if (closeRepo)
			repo.close();
	}

//////////// 瀵拷婵鎼锋担婊堟６妫帮拷
	public CommitCommand commit(String path, String author, String committer, String message) {
		return new CommitCommand( path,  author,  committer,  message);
	}
}


