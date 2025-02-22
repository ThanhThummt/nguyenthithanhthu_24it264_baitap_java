package c2_5;

public class Account {
private int id;
private Customer customer;
private double balance=0.0;

public Account(int id, Customer customer) {
	this.id = id;
	this.customer = customer;
}
public Account(int id, Customer customer, double balance) {
	this.id = id;
	this.customer = customer;
	this.balance = balance;
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
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
@Override
public String toString() {
	return customer.toString() +" balance=$"+String.format("%.2f", balance);
}
 public Account deposit(double amount) {
	 this.balance += amount;
     return this;
 }
 public Account withdraw (double amount) {
	if(balance>=amount) {
		balance-=amount;
	}else {
		System.out.println("Amount withdraw exceeds the current balance!");
	}
	return this;
 }
 

}
