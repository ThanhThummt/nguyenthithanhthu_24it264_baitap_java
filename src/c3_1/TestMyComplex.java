package c3_1;

public class TestMyComplex {
public static void main(String[] args) {
	MyComplex c1 = new MyComplex();
	System.out.println(c1);
	
	MyComplex c2 = new MyComplex(1,2);
	System.out.println(c2);
	c2.setReal(4);
	System.out.println(c2);
	c2.setReal(4);
	
	MyComplex c3 = new MyComplex(2, 3);
	System.out.println(c1.isReal());
	System.out.println(c3.isReal());
	System.out.println(c2.magnitude());
	System.out.println(c2.addInto(c2));
	System.out.println(c3.addNew(c3));
	System.out.println("-------------");
	
	 MyComplex c4 = new MyComplex(3, 4);
     MyComplex c5 = new MyComplex(1, -2);
     System.out.println(c4);
     System.out.println(c5);
     
     System.out.println(c4.addNew(c5));
     System.out.println(c4.multiply(c5));
     System.out.println(c4.argument());
     System.out.println(c4.divide(c5));
     System.out.println(c4.conjugate());
}
}

