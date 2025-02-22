package c2;

public class Invoice {
	 private int id;
	 private Customer customer;
	 private double amount;
	 
	public Invoice(int id, Customer customer, double amount) {
		this.id = id;
		this.customer = customer;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCustomerId() {
		return getCustomer().getId();
	}

	public String getCustomerName() {
		return getCustomer().getName();
	}
	public int getCustomerDiscount() {
		return getCustomer().getDiscount();
	}
	public double getAmountAfterDiscount() {
		return getAmount()*(1-getCustomer().getDiscount()/100);
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", customer=" + customer.toString() + ", amount=" + amount + "]";
	}
	 
	 
}
