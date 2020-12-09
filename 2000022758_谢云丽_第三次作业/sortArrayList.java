package 第三次作业;


import java.util.ArrayList;
import java.util.Scanner;

/* 出现过的错误： 
 * 1. 没有import
 * 2. 时刻新变量要声明类型
 * 3. **sort显示错误 为何不能使用
 * 
 * 
 */

public class sortArrayList {
	public static void main(String[] args) {
	ArrayList<Integer> list= new ArrayList<Integer>();
		/*用户输入数组*/
	System.out.println("请输入几个数并用逗号隔开:");
	Scanner input=	 new Scanner(System.in);
	String str = input.next().toString();
	input.close();
	String[] arr  = str.split(",");
	/*构造测试对象*/
	int[] b = new int[arr.length];// 生成一个与字符串同长的的数组b
	for(int j = 0; j<b.length;j++) {
		b[j] = Integer.parseInt(arr[j]);
		list.add(b[j]);
	}//完成数组的输入
	/* 方法2 如果是字符串数组，可以用asList(arr); */
	sort(list);
	System.out.println(list);
	
	input.close();	
	}
	
	public static void sort(ArrayList<Integer> list) {
		/*进行排序*/
		for (int i=0; i<list.size();i++) {
			for (int j=i+1; j<list.size();j++){
				Integer temp;
				if (list.get(i).compareTo(list.get(j))>0) {
					//不符合顺序
					temp=list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}			
			}
		}
		
	}

}

