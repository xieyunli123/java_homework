package 第三次作业;
/* Course类可克隆
 * 重写清单10-6中的Course类，增加一个Clone方法，执行Students域上的深度复制。
 */
/*两个问题：
 * 1⃣Cloneable接口的报错
 * 2⃣深度克隆 
 * 3⃣元组的修改与赋值
 */

import java.util.Scanner;
//增加一个clone方法，在students域上实现深度复制
//page442
/*object中的clone方法将原始对象的每个数据域复制给目标对象。
 * 比如date类，那么复制的是引用。这个叫做shallow copy，而不是深复制。
*/



public class Clone_Course{
	public static void main(String[] args) {		
		//创建测试对象
		Course course1= new Course("英语课");		
		System.out.println("please enter the student name,divided by comma:");
		try (Scanner input = new Scanner(System.in)) {
			String str = input.next().toString();
			String[] arr  = str.split(",");			
			for(int j = 0; j<arr.length;j++) {
				course1.addStudents(arr[j]);
			}//完成数组的输入	
		}
		
		//执行
		//Course course2 =new Course("英语课");不存在缺省的构造方法
		Course course2 = course1.clone();	
		
		for (int i=0; i<course1.getnumberOfStudents();i++) {
				System.out.println( course2.getStudents()[i] );
		}
		
	}
}

/* 构造课程类*/
	class Course{
		private String courseName;
		private String[] students=new String[100];
		private int numberOfStudents;
		
		public Course(String courseName) {
			this.courseName= courseName;
		}//输入课程名字
		
		public void addStudents(String student) {
			students[numberOfStudents]=student;
			numberOfStudents++;		
		}//新增学生
		
		public String[] getStudents() {
			return students; 
		}// 返回学生名单
		
		public String getCourseName() {
			return courseName;
		}	
		public int getnumberOfStudents() {
			return numberOfStudents;
		}
		//@Override
		public Course clone() {
			// perform a shallow copy
			//Course courseClone =(Course)super.clone();
			// deep copy on whenBuitl
			Course courseClone=new Course(getCourseName());
			for (int i=0;i<students.length;i++) {
				courseClone.students[i]=students[i];
			}
			courseClone.numberOfStudents=this.numberOfStudents;			
			return courseClone;
		}
	}

/* 继承可克隆接口方法	
 * : 克隆接口出现错误，并不明了为何报错
 * 
 * public interface Cloneable{
	super.clone();	
}
class Course_clonebale extends Course implements Cloneable {
	
	
	public Course_clonebale(String courseName) {
		super(courseName);
	}
	@Override
	public Object clone(){
		return super.clone(); 
	}
}
*/

