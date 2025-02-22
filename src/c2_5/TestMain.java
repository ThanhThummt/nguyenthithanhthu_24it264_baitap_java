package c2_5;

public class TestMain {
	public static void main(String[] args) {
		Customer c1 = new Customer(1001, "Nguyen van A", 'm');
		System.out.println(c1);
	
		Account a1 = new Account(003, c1,500);
		System.out.println(a1);
		
		a1.deposit(100);
		System.out.println(a1);
		
		 a1.withdraw(200);
	        System.out.println(a1);
	        
	        a1.withdraw(700);  
	        System.out.println(a1);
	}
}
