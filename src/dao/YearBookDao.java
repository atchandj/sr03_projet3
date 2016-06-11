package dao;

/* The interface of the class witch interacts with the database 
 * to add, modify or delete some elements directly inked to a year book.
 * */
public interface YearBookDao {
	public void dropAd(int yearBook, String adName) throws DaoException;
	public String getAdsNames(int yearBook) throws DaoException;
	public String getYearBook(int yearBook) throws DaoException;
	public String getAd(int yearBook, String adName) throws DaoException;
	public void addAd(int yearBook, String adName, String phone, String street, String town, String postCode, String category) throws DaoException;
	public void modifyAd(int yearBook, String oldAdName, String oldStreet, String oldTown, String oldPostCode, String oldCategory, String newAdName, String newPhone, String newStreet, String newTown, String newPostCode, String newCategory) throws DaoException;
	public String getAdsByAddress(int yearBook, String street, String town, String postCode) throws DaoException;
}
