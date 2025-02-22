package c1;

public class Circle { 

	   private double radius;
	   private String color;
	   
	
	   public Circle() {  
	      radius = 1.0;
	      color = "red";
	   }
	   
	  
	   public Circle(double r) {  
	      radius = r;
	   }
	   
	  
	   public double getRadius() {
	     return radius; 
	   }
	   
	
	   public double getArea() {
	      return radius*radius*Math.PI;
	   }
	   
	   public static void main(String[] args) {
	        Circle c1 = new Circle();  
	        Circle c2 = new Circle(5.0);

	        System.out.println("Circle 1: Radius = " + c1.getRadius() + ", Area = " + c1.getArea());
	        System.out.println("Circle 2: Radius = " + c2.getRadius() + ", Area = " + c2.getArea());
	    }
	}
