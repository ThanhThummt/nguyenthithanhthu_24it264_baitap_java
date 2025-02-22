package c4_1;

public class Cricle {
private double radius;
private  String color;

public Cricle() {
	this.radius = 1.0;
	this.color = "red";
}
public Cricle(double radius) {
	this.radius = radius;
}
public Cricle(double radius, String color) {
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

public double getArea() {
	return Math.PI*Math.pow(radius, 2);
}
@Override
public String toString() {
	return "Cricle [radius=" + radius + ", color=" + color + "]";
}

}
