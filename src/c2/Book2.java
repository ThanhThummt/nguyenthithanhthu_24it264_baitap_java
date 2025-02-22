package c2;

import java.util.Arrays;

public class Book2 {
	 private String name;
	 private Author2[] authors;
	 private double price;
	 private int qty=0;
	 
	public Book2(String name, Author2[] authors, double price) {
		this.name = name;
		this.authors = authors;
		this.price = price;
	}
	public Book2(String name, Author2[] authors, double price, int qty) {
		this.name = name;
		this.authors = authors;
		this.price = price;
		this.qty = qty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Author2[] getAuthors() {
		return authors;
	}
	public void setAuthors(Author2[] authors) {
		this.authors = authors;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	 public String getAuthorNames() {
		 StringBuilder names = new StringBuilder();
		 for(int i=0;i<authors.length;i++) {
			 names.append(authors[i].getName());
			 if(i<authors.length-1) {
				 names.append(", ");
			 }
		 }
		 return names.toString();
	 }
	@Override
	public String toString() {
		return "Book2 [name=" + name + ", authors=" + Arrays.toString(authors) + ", price=" + price + ", qty=" + qty
				+ "]";
	}
	 
	 
	 
}