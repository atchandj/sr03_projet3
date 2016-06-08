package dao;

public interface YearBookDao {
	public void dropAd(int yearBook, String adName) throws DaoException;
	public String getAdsNames(int yearBook) throws DaoException;
	public String getYearBook(int yearBook) throws DaoException;
	public String getAd(int yearBook, String adName) throws DaoException;
	public void addAd(int yearBook, String adName, String phone, String street, String town, String postCode, String category) throws DaoException;
}
