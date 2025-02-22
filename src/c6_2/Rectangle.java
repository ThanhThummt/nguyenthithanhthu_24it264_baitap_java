package c6_2;

public class Rectangle {
public double width;
public double heigth;

public Rectangle(double width, double heigth) {
	this.width = width;
	this.heigth = heigth;
}
@Override
public String toString() {
	return "Rectangle [width=" + width + ", heigth=" + heigth + "]";
}

public double getArea() {
	return width*heigth;
} 
public double getPerimeter() {
	return (heigth+width)*2;
}
}
