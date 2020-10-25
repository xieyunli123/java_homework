package 家庭作业02;

import java.util.Scanner;

public class sumof_diagno {
	public static void main(String[] args) 
	{
		
		// 求矩阵主对角线元素的和
		// 输入矩阵 matrix 
				
		System.out.println("Enter a 4-by-4 matrix row by row:");
		Scanner input = new Scanner(System.in);
		double[][] alist = new double[4][4];//建立矩阵
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++)
				alist[i][j] = input.nextDouble();
			} 
		
		//打印输出
		double sum = sumMajorDiagonal(alist); //设置结果变量
		System.out.print("Sum of the elements in the major diagonal is "+sum);
		
		}
		
		public static double sumMajorDiagonal(double[][] alist) 
		{
			double sum=0; //初始化结果
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) 
				{
					if(i == j) 
						sum += alist[i][j];
					} //累计求和 **
				}
				return sum;	
		}
}
