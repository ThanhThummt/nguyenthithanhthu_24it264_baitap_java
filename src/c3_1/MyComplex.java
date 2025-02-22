package c3_1;

public class MyComplex {
  private double real;
  private double imag;
  
  public MyComplex() {
		this.real = 0.0;
		this.imag = 0.0;
	}
public MyComplex(double real, double imag) {
	this.real = real;
	this.imag = imag;
}
public double getReal() {
	return real;
}
public void setReal(double real) {
	this.real = real;
}
public double getImag() {
	return imag;
}
public void setImag(double imag) {
	this.imag = imag;
}
  
public void setValue(double real,double imag) {
	this.real = real;
	this.imag = imag;
}
@Override
public String toString() {
 return real + (imag >= 0 ? "+" : "") + imag + "i";
}
public boolean isReal() {
    return imag == 0;
}

public boolean isImaginary() {
    return real == 0;
}
public double magnitude() {
    return Math.sqrt(real * real + imag * imag);
}
public boolean equals(double real, double imag) {
    return this.real == real && this.imag == imag;
}

public boolean equals(MyComplex another) {
    return this.real == another.real && this.imag == another.imag;
}
public MyComplex addInto(MyComplex right) {
	this.real+=right.real;
	this.imag+=right.imag;
	return this;
}
public MyComplex addNew(MyComplex right) {
	return new MyComplex(this.real+right.real, this.imag+right.imag);
}
public double argument() {
	return Math.atan2(real, imag);
}

public MyComplex subtract(MyComplex right) {
	this.real-= right.real;
	this.imag-= right.imag;
	return this;
}
public MyComplex subtractNew(MyComplex right) {
	return new MyComplex(this.real- right.real,this.imag-right.imag);
}

public MyComplex multiply(MyComplex right) {
    double newReal = this.real * right.real - this.imag * right.imag;
    double newImag = this.real * right.imag + this.imag * right.real;
    this.real = newReal;
    this.imag = newImag;
    return this;
}


public MyComplex divide(MyComplex right) {
    double denominator = right.real * right.real + right.imag * right.imag;
    double newReal = (this.real * right.real + this.imag * right.imag) / denominator;
    double newImag = (this.imag * right.real - this.real * right.imag) / denominator;
    this.real = newReal;
    this.imag = newImag;
    return this;
}
public MyComplex conjugate() {
	return new MyComplex(real, -imag);
}
}
