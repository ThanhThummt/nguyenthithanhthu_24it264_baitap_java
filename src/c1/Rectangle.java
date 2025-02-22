package c1;

public class Rectangle {
       private float length;
       private float width;
       
	public Rectangle() {
		this.length = 1.0f;
		this.width =1.0f;
	}

	public Rectangle(float length, float width) {
		this.length = length;
		this.width = width;
	}
       
       public float getLength() {
    	   return length;
       }
       
       public void setLength(float length) {
    	   this.length=length;
       }
       
       public float getWidth() {
    	   return width;
       }
       
       public void setWidth(float width) {
    	   this.width=width;
       }
       
       public double getArea() {
    	   return width*length;
       }
       
       public double getPerimeter() {
    	   return (width+length)*2;
       }

	@Override
	public String toString() {
		return "Rectangle [length=" + length + ", width=" + width + "]";
	}
       public static void main(String[] args) {
	Rectangle r1 = new Rectangle(1.2f, 3.4f);
	System.out.println(r1);
	
	Rectangle r2 = new Rectangle();
	System.out.println(r2);
	
	r1.setLength(5.6f);
	r1.setWidth(7.8f);
	System.out.println(r1);
	 System.out.println("length is: " + r1.getLength());
     System.out.println("width is: " + r1.getWidth());
	
     
     System.out.printf("area is: %.2f%n ", r1.getArea());
     System.out.printf("perimeter is: %.2f%n ",r1.getPerimeter());
	}
       
}
