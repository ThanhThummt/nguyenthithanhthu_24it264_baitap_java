package c2_8;

public class MyCircle {
  private MyPoint center ;
  private int radius ;
  public MyCircle() {
      this.center = new MyPoint(0, 0);
      this.radius = 1;
  }

public MyCircle(int x, int y, int radius) {
	this.center =new MyPoint(x,y);
	this.radius = radius;
}
public MyCircle(MyPoint center, int radius) {
	this.center = center;
	this.radius = radius;
}
public MyPoint getCenter() {
	return center;
}
public void setCenter(MyPoint center) {
	this.center = center;
}
public int getRadius() {
	return radius;
}
public void setRadius(int radius) {
	this.radius = radius;
}
  public int[] getCenterXY() {
	  return center.getXY();
  }
  public void setCenterXY(int x, int y) {
	  center.setXY(x,y);
  }

@Override
public String toString() {
	return "MyCircle [radius=" + radius + ",center=" + center.toString() + "]";
}
  public double getArea() {
	  return Math.PI*Math.pow(radius, 2);
  }
  
  public double getCircumference() {
	  return 2*Math.PI*radius;
  }
  
  public double distance(MyCircle another) {
	  return  center.distance(another.getCenter());
  }
}
