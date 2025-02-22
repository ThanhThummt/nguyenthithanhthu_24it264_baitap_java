package c7_1;

import java.util.Date;

public class Test {
public static void main(String[] args) {
	Customer c= new Customer("David");
	System.out.println(c);
	c.setMember(true);
	c.setMemberType("Gold");
	System.out.println(c);
	System.out.println();
	
	Visit v = new Visit(c,new  Date() );
	v.setProductExpense(50);
	v.setServiceExpense(100);
	System.out.println(v);
	System.out.println(v.getTotal());
	
	
}
}
