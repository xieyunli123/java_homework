package 家庭作业02;
//  7.10 找出最小元素的下标
//  求出整数 数组中 最小元素的下标。 
// 提示用户输入十个数字
// 调用这个方法返回最小值，并显示最小值。

import java.util.*;

public class minelement_index {

	public static void main(String[] args) 
	{
		
		
		
			//输入十个数字，存入alist
			double[] alist = new double[10]; 
			for(int i=0;i<10;i++) {
				System.out.println("Enter number"+String.valueOf(i+1));
				Scanner input =new Scanner(System.in);
				alist[i] = input.nextDouble();
			}


		int indexofmin;
		indexofmin= indexOfSmallestElement(alist);
		System.out.println("最小元素的下标是"+indexofmin);
		//思路
		// 
		

	}


	public static int indexOfSmallestElement(double[] arr)
	{
		double min =arr[0]; //指针初始点在0 (请区分指针和 输出的结果）（结果是index i，而非比较的值/指针）
		int index = 0;
		
		for(int k=0; k<10; k++)
			//从1开始比，而不是0     
			
		{
			if (arr[k]<min)
			{
				min=arr[k];
				index =k;	
			}
		}
		return index;
	}
	//封装的函数：求最小值的
	
}
