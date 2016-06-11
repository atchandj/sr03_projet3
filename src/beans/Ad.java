package beans;

/* This bean represents an ad */
public class Ad {
	private int yearBook;
	private String name;
	private String phone;
	private Category category;
	private Address address;
	
	public Ad(int yearBook, String name, String phone, Category category, Address address) {
		this.yearBook = yearBook;
		this.name = name;
		this.phone = phone;
		this.category = category;
		this.address = address;
	}
	
	public Ad(int yearBook, String name) {
		this.yearBook = yearBook;
		this.name = name;
	}

	public Ad(int yearBook, String name, String phone) {
		this.yearBook = yearBook;
		this.name = name;
		this.phone = phone;
		this.category = null;
		this.address = null;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
