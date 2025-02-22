package c4_5;

public class Circle extends Shape {
	private double radius;

	public Circle() {
		this.radius = 1.0;
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	public Circle(double radius, String color, boolean filled) {
		super(color, filled);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	public double getPerimeter() {
		return Math.PI * radius * 2;
	}

	@Override
	public String toString() {
		return "Cicrle [" + super.toString() + "radius=" + radius + "]";
	}
}
