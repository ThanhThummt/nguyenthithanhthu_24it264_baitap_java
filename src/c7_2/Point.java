package c7_2;

public class Point {
private int x;
private int y;
public Point(int x, int y) {
	super();
	this.x = x;
	this.y = y;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
@Override
public String toString() {
	return "Point [x=" + x + ", y=" + y + "]";
}
public double distance(Point another) {
	double dx = this.x-another.x;
	double dy = this.y-another.y;
	return Math.sqrt(dx*dx+dy*dy);
}
}
