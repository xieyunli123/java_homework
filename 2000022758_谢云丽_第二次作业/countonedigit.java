package 家庭作业02;
// 生成0到9之间，100个随机整数， 显示每一个数出现的次数

public class countonedigit {
	public static void main(String args[])
	{
		
		int[] mylist= new int[10]; //生成统计数面板
		
		for(int i=0; i<100; i++)
		{
			int k= (int) (Math.random() * 10);  //随机生成  //提问：书上没有对右边处理
			mylist[k]++; //恰好索引和数值一致，统计出现次数
			 
		}//生成随机数
		for (int i=0; i<10; i++)
		{
			System.out.println("随机生成数"+i+" 出现次数："+mylist[i]);
		}
		
		
		
	}
	
	
}
