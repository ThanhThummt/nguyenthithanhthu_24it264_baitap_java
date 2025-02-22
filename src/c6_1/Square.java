package c6_1;

public class Square extends Rectangle {
		public Square() {
			super();
		}
		public Square(double side) {
			super(side,side);
		}
		public Square(double side, String color, boolean filled) {
	        super(side, side, color, filled);
	    }
		  public double getSide() {
		        return width;  // Vì width = length
		    }

		    public void setSide(double side) {
		        this.width = side;
		        this.length = side;
		    }
		    public void setWidth(double side) {
		        setSide(side);
		    }

		    @Override
		    public void setLength(double side) {
		        setSide(side);
		    }
		    @Override
		    public String toString() {
		        return "Square[" + super.toString() + "]";
		    }
	}


