package beans;

public class Address {
	private int id;
	private String street;
	private String town;
	private String postCode;
	
	public Address(int id, String street, String town, String postCode) {
		this.id = id;
		this.street = street;
		this.town = town;
		this.postCode = postCode;
	}
	
	public Address(String street, String town, String postCode) {
		this.street = street;
		this.town = town;
		this.postCode = postCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
}
