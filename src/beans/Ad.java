package beans;

import java.util.ArrayList;


public class Ad {
	private int yearBook;
	private String name;
	private String phone;
	private ArrayList<Category> categories;
	private ArrayList<Adress> addresses;
	
	public Ad(int yearBook, String name, String phone, ArrayList<Category> category, ArrayList<Adress> addresses) {
		this.yearBook = yearBook;
		this.name = name;
		this.phone = phone;
		this.categories = category;
		this.addresses = addresses;
	}

	public int getYearBook() {
		return yearBook;
	}

	public void setYearBook(int yearBook) {
		this.yearBook = yearBook;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public ArrayList<Adress> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<Adress> addresses) {
		this.addresses = addresses;
	}


}
