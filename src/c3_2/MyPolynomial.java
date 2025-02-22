package c3_2;

public class MyPolynomial {
  private double[] coeffs;

public MyPolynomial(double... coeffs) {
	this.coeffs = coeffs;
}
  public int getDegree() {
	   return coeffs.length-1;
  }
  
  // Chuyển đổi đa thức thành chuỗi để hiển thị
  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = getDegree(); i >= 0; i--) {
          if (coeffs[i] != 0) {
              if (sb.length() > 0) {
                  sb.append(" + ");
              }
              sb.append(coeffs[i]);
              if (i > 0) {
                  sb.append("x");
                  if (i > 1) {
                      sb.append("^").append(i);
                  }
              }
          }
      }
      return sb.toString();
  }
  public double evaluate(double x){
	  double result=0;
	  for(int i=0; i< coeffs.length; i++) {
		  result+=coeffs[i]*Math.pow(x,i);
	  }
	  return result;
  }
  
  public MyPolynomial add(MyPolynomial right) {
      int maxDegree = Math.max(this.getDegree(), right.getDegree());
      double[] newCoeffs = new double[maxDegree + 1];

      for (int i = 0; i <= maxDegree; i++) {
          double coeff1 = (i <= this.getDegree()) ? this.coeffs[i] : 0;
          double coeff2 = (i <= right.getDegree()) ? right.coeffs[i] : 0;
          newCoeffs[i] = coeff1 + coeff2;
      }
      return new MyPolynomial(newCoeffs);
  }
  public MyPolynomial multiply(MyPolynomial right) {
      int newDegree = this.getDegree() + right.getDegree();
      double[] newCoeffs = new double[newDegree + 1];

      for (int i = 0; i <= this.getDegree(); i++) {
          for (int j = 0; j <= right.getDegree(); j++) {
              newCoeffs[i + j] += this.coeffs[i] * right.coeffs[j];
          }
      }
      return new MyPolynomial(newCoeffs);
  }
}
