package 家庭作业02;

import java.util.Scanner;

public class Reverseint {

	public static void main(String args[]) {
		// TODO Auto-generated method stub
		//我有两个想法： 一个是用数字除法，一个是用string的stack再转int
		System.out.println("Enter an integer number:");
		Scanner input =new Scanner (System.in);
		int number =input.nextInt();
		
		String str ="";
		while (number != 0)
		{
			str += number %10;
			number /=10;
			if (number == 0)
			{
				System.out.println(str);
			}
		}
		
		

	}

}
