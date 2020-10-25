package 家庭作业02;

import java.util.Random;
import java.util.Scanner;

public class creatermatrix {

	public static void main(String args[])
	{
		// TODO Auto-generated method stub
		
		// 直接创建整型变量的矩阵，无法打印。需要用循环
		
		System.out.println("Enter dimension n:");
		Scanner input =new Scanner (System.in);
		int n = input.nextInt();
		printMatrix(n);
	}
	
		// 经常使用：输入int 
		//要注意输入输出的类型
		

		public static void printMatrix(int n)
		{
			int[][] matrix;
			matrix = new int[n][n];
			int i,j =0; //双层指针初始化
			for (i=0; i<n; i++)
			{
				for(j=0; j<n; j++)
				{
					Random random = new Random();
					int k =random.nextInt(2);  //随机生成
					
					matrix[i][j]=k;  
					System.out.print(k+" "); //行打印
					
					}
				System.out.println(); //行结束，换行
			}
			
		}

}
