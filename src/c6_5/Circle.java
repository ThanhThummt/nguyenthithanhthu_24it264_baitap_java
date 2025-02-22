package c6_5;

public class Circle implements GeometricObject{
 protected double radius;

public Circle(double radius) {
	super();
	this.radius = radius;
}

@Override
public String toString() {
	return "Circle [radius=" + radius + "]";
}

@Override
public double getPerimeter() {
	// TODO Auto-generated method stub
	return Math.PI*radius*radius;
}

@Override
public double getArea() {
	// TODO Auto-generated method stub
	return 2*Math.PI*radius;
}
 
}
