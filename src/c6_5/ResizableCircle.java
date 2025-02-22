package c6_5;

public class ResizableCircle extends Circle implements Resizable{

	public ResizableCircle(double radius) {
		super(radius);
	}

	@Override
	public String toString() {
		return "ResizableCircle [" + super.toString() + "]";
	}
	
public void resize(int percent) {
	radius*=percent/100;
}
}
