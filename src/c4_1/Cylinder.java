package c4_1;

public class Cylinder extends Cricle {
private double height;

public Cylinder() {
	this.height = 1.0;
}
public Cylinder(double radius,double height) {
	super(radius);
	this.height = height;
}
public Cylinder(double height) {
	super();
	this.height = height;
}
	 public double getHeight() {
    return height; 
 }
	public void setHeight(double height) {
		this.height = height;
	}
	 public double getVolume() {
	      return getArea()*height; 
	   }
	
	@Override
	public String toString() {     
	   return "Cylinder: subclass of " + super.toString()  // gọi phương thức toString() của Circle
	          + " height=" + height;
	}

}