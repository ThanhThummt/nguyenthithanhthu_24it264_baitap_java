package c4_4;

public class MovalePoint extends Point{
   private float xSpeed;
   private float ySpeed;
   
  public MovalePoint() {
		this.xSpeed = 0.0f;
		this.ySpeed =  0.0f;
	}
public MovalePoint(float xSpeed, float ySpeed) {
	this.xSpeed = xSpeed;
	this.ySpeed = ySpeed;
}
public MovalePoint(float x, float y,float xSpeed, float ySpeed) {
	super(x,y);
	this.xSpeed = xSpeed;
	this.ySpeed = ySpeed;
}
public float getxSpeed() {
	return xSpeed;
}
public void setxSpeed(float xSpeed) {
	this.xSpeed = xSpeed;
}
public float getySpeed() {
	return ySpeed;
}
public void setySpeed(float ySpeed) {
	this.ySpeed = ySpeed;
}
@Override
public String toString() {
	return super.toString()+","+"speed=("+xSpeed+" "+ySpeed+")";
}
 public MovalePoint move() {
	 x+=xSpeed;
	 y+=ySpeed;
	 return this;
 }
   
}
