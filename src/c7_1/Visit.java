package c7_1;

import java.util.Date;

public class Visit {
	private Customer customer;
	private Date date;
	private double serviceExpense;
	private double productExpense;
	
	public Visit(Customer customer, Date date) {
		super();
		this.customer = customer;
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public double getServiceExpense() {
		return serviceExpense;
	}

	public void setServiceExpense(double serviceExpense) {
		this.serviceExpense = serviceExpense;
	}

	public double getProductExpense() {
		return productExpense;
	}

	public void setProductExpense(double productExpense) {
		this.productExpense = productExpense;
	}

	public double getTotal() {
	    if (customer == null) return serviceExpense + productExpense;
	    double serviceDiscount = serviceExpense * DiscountRate.getServiceDiscountRate(customer.getMemberType());
	    double productDiscount = productExpense * DiscountRate.getProductDiscountRate();
	    return (serviceExpense - serviceDiscount) + (productExpense - productDiscount);
	}

	@Override
	public String toString() {
		return "Visit [customer=" + customer + ", date=" + date + ", serviceExpense=" + serviceExpense
				+ ", productExpense=" + productExpense + "]";
	}
	
	
}
