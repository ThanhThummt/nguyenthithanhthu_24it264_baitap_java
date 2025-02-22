package c5_2;

public class Cylinder {
	private Circle base;
	private double heigth;

	public Cylinder(Circle base, double heigth) {
		this.base = base;
		this.heigth = heigth;
	}

	public Cylinder() {
		base = new Circle();
		heigth = 1.0;
	}

	public Circle getBase() {
		return base;
	}

	public void setBase(Circle base) {
		this.base = base;
	}

	public double getHeigth() {
		return heigth;
	}

	public void setHeigth(double heigth) {
		this.heigth = heigth;
	}

}
