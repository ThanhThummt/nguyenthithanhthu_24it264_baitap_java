package c5_2;

public class Circle {
	 private double radius;
	 private String color;
	 
	 public Circle() {
			this.radius =0.0;
			this.color = "red";
		}
	public Circle(double radius, String color) {
		this.radius = radius;
		this.color = color;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	 
	 
}
