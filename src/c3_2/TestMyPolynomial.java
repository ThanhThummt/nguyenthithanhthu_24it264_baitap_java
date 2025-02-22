package c3_2;

public class TestMyPolynomial {
	  public static void main(String[] args) {
	        MyPolynomial p1 = new MyPolynomial(1.1, 2.2, 3.3);
	        MyPolynomial p2 = new MyPolynomial(1.1, 2.2, 3.3, 4.4, 5.5);

	        System.out.println("Polynomial p1: " + p1);
	        System.out.println("Polynomial p2: " + p2);

	        System.out.println("p1 evaluated at x=2: " + p1.evaluate(2));

	        MyPolynomial sum = p1.add(p2);
	        System.out.println("p1 + p2: " + sum);

	        MyPolynomial product = p1.multiply(p2);
	        System.out.println("p1 * p2: " + product);
	    }
	}

