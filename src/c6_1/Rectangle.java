package c6_1;

	
public class Rectangle extends Shape  {
		protected double width;
		protected double length;
		
		public Rectangle() {
			super();
			this.width = 1.0;
			this.length = 1.0;
		}
		public Rectangle(double width, double lenght) {
			super();
			this.width = width;
			this.length = lenght;
		}
		 public Rectangle(double width, double length, String color, boolean filled) {
		        super(color, filled);
		        this.width = width;
		        this.length = length;
		    }
		public double getWidth() {
			return width;
		}
		public void setWidth(double width) {
			this.width = width;
		}
		public double getLength() {
			return length;
		}
		public void setLength(double lenght) {
			this.length = lenght;
		}
		public double getArea() {
			return width*length;
		}
		public double getPerimeter() {
			return (width+length)*2;
			}
		@Override
		public String toString() {
			return "Rentangle [" + super.toString() +"width="+width + ", lenght=" + length +  "]";
		}
		
	}


