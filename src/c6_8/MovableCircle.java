package c6_8;

public class MovableCircle implements Movable {
	private int radius;
	private MovablePoint center;

	public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius) {
		this.center = new MovablePoint(radius, radius, radius, radius);
		this.radius = radius;
	}

	@Override
	public String toString() {
		return center.toString() + "," + "radius=" + radius;
	}

	@Override
	public void moveUp() {
		center.moveUp();

	}

	@Override
	public void moveDown() {
		center.moveDown();
	}

	@Override
	public void moveLeft() {
		center.moveLeft();

	}

	@Override
	public void moveRight() {
		center.moveDown();

	}
}