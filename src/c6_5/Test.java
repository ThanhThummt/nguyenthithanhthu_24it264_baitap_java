package c6_5;

public class Test {
public static void main(String[] args) {
	
	        GeometricObject c = new Circle(1);
	        System.out.println("Circle: " + c);
	        System.out.println("Perimeter: " + c.getPerimeter());
	        System.out.println("Area: " + c.getArea());
	        System.out.println();

	      
	        Circle r = new ResizableCircle(2);
	        System.out.println("ResizableCircle (before resizing): " + r);
	        System.out.println("Perimeter: " + r.getPerimeter());
	        System.out.println("Area: " + r.getArea());
	        System.out.println();

	      
	        if (r instanceof Resizable) {
	            Resizable resizable = (Resizable) r; // Ép kiểu để sử dụng phương thức resize
	            resizable.resize(150); // Tăng kích thước lên 150%
	            System.out.println("ResizableCircle (after resizing 150%): " + r);
	            System.out.println("Perimeter: " + r.getPerimeter());
	            System.out.println("Area: " + r.getArea());
	        }
	    
}
}
