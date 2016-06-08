package dao;

public interface YearBookDao {
	public void dropAd(int yearBook, String adName) throws DaoException;
	public String getAdsNames(int yearBook) throws DaoException;
}
