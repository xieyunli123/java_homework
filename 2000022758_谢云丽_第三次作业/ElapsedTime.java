package 第三次作业;

public class ElapsedTime {

	public static void main(String[] args) {
		java.util.Date[] dateArray =new java.util.Date[8];
		long etime=10000;
		for(int i=0; i<8; i++) {
			
			dateArray[i]= new java.util.Date();
			dateArray[i].setTime(etime);
			System.out.println(dateArray[i].toString());
			etime=etime*10;
		}		
	}
}

