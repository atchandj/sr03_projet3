package beans;

import java.util.ArrayList;

public class YearBook {
	private int id;
	private String name;
	ArrayList<Ad> ads;
	
	public YearBook(int id, String name, ArrayList<Ad> ads) {
		this.id = id;
		this.name = name;
		this.ads = ads;
	}

	public YearBook(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public YearBook(int id) {
		this.id = id;
	}
	
	public ArrayList<Ad> getAds() {
		return ads;
	}

	public void setAds(ArrayList<Ad> ads) {
		this.ads = ads;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
