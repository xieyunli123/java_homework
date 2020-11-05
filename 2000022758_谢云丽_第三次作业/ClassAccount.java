package 第三次作业;


public class ClassAccount  {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account1 = new Account(1122,20000);
		account1.fill_annualInterestRate(4.5);
		account1.withdraw(2500);
		account1.deposit(3000);
		System.out.println("The account balance is "+account1.get_balance()+"$");
		System.out.println("The monthly interest is "+account1.getMonthlyInterest()+"$");
		System.out.println("The account is established on "+account1.get_dateCreated());

	}
}

class Account{
	private int id=0;
	private double balance=0;
	private double annualInterestRate=0;
	java.util.Date dateCreated;
	//构造
	Account(){
	}
	Account(int id, double balance){
		this.id=id;
		this.balance=balance;
		dateCreated= new java.util.Date();
	}
	
	//访问
	public int get_id() {
		return id;
	}
	public double get_balance() {
		return balance;
	}
	public double get_annualInterestRate() {
		return annualInterestRate;
	}
	public java.util.Date get_dateCreated(){
		return dateCreated;
	}	
	
	//修改
	public void fill_id(int id) {
		this.id=id;
	}
	public void fill_balance(double balance) {
		this.balance=balance;
	}
	public void fill_annualInterestRate(double ir) {
		annualInterestRate=ir;
	}
	
	//月利息
	public double getMonthlyInterestRate() {
		return annualInterestRate/100/12;
	}
	
	public double getMonthlyInterest() {
		double monthlyInterest= getMonthlyInterestRate() *get_balance();
		return monthlyInterest;
	}
	
	//取款
	public void withdraw(double withdrawal) {
		fill_balance(get_balance()-withdrawal);
		System.out.println("you have withdrawn "+withdrawal+"$");
	}
	public void deposit(double deposits) {
		fill_balance(get_balance()+deposits);
		System.out.println("you have deposited "+deposits+"$");
	}	
}


