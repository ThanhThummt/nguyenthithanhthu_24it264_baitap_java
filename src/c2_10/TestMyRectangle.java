package c2_10;

public class TestMyRectangle {
    public static void main(String[] args) {
        MyRectangle r1 = new MyRectangle(1, 5, 4, 1);
        System.out.println(r1);
        System.out.println("Width: " + r1.getWidth());
        System.out.println("Height: " + r1.getHeight());
        System.out.println("Area: " + r1.getArea());
        System.out.println("Perimeter: " + r1.getPerimeter());

        MyPoint p1 = new MyPoint(2, 6);
        MyPoint p2 = new MyPoint(5, 2);
        MyRectangle r2 = new MyRectangle(p1, p2);
        System.out.println(r2);
        System.out.println("Width: " + r2.getWidth());
        System.out.println("Height: " + r2.getHeight());
        System.out.println("Area: " + r2.getArea());
        System.out.println("Perimeter: " + r2.getPerimeter());
        
        
    }
}
