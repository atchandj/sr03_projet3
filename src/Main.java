import dao.AdDao;
import dao.DAOConfigurationException;
import dao.DaoException;
import dao.DaoFactory;
import dao.YearBookDao;

public class Main {
    private YearBookDao yearBookDao;
    private AdDao adDao;
    public Main(){
        DaoFactory daoFactory;
		try {
			daoFactory = DaoFactory.getInstance(); // We get an instance of the factory
			this.adDao = daoFactory.getAdDao(); // Here we get an implementation to communicate with the db
			this.yearBookDao = daoFactory.getYearBookDao(); // Here we get another instance to communicate with the db

		} catch (DAOConfigurationException e) {
			e.printStackTrace();
		}        
    }  
    
    /* This service allows the user to drop a category */
	public void dropCategory(String category) throws DaoException{
		try {
			this.adDao.dropCategory(category);
		} catch (DaoException e) {
			throw e;
		} 
	}

	/* This service allows the user to update the name of a category */
	public void updateCategory(String oldCategoryName, String newCategoryName) throws DaoException {
		try {
			this.adDao.updateCategory(oldCategoryName, newCategoryName);
		} catch (DaoException e) {
			throw e;
		} 
	}
	
	/* This service allows the user to add a category */
	public void addCategory(String categoryName) throws DaoException{
		try {
			this.adDao.addCategory(categoryName);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to drop an ad */
	public void dropAd(int yearBook, String adName) throws DaoException{
		try {
			this.yearBookDao.dropAd(yearBook, adName);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to get a JSON array with the ads names of a yearbook */
	public String getAdsNames(int yearBook) throws DaoException{
		try {
			return this.yearBookDao.getAdsNames(yearBook);
		} catch (DaoException e) {
			throw e;
		}		
	}
	
	/* This service allows the user to get a JSON string with all the data of a yearbook */
	public String getYearBook(int yearBook) throws DaoException{
		try {
			return this.yearBookDao.getYearBook(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to get a JSON object with the data of an ad */
	public String getAd(int yearBook, String adName) throws DaoException{
		try {
			return this.yearBookDao.getAd(yearBook, adName);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to add an ad*/
	public void addAd(int yearBook, String adName, String phone, String street, String town, String postCode, String category) throws DaoException{
		try {
			this.yearBookDao.addAd(yearBook, adName, phone, street, town, postCode, category);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to modify an ad */
	public void modifyAd(int yearBook, String oldAdName, String oldStreet, String oldTown, String oldPostCode, String oldCategory, String newAdName, String newPhone, String newStreet, String newTown, String newPostCode, String newCategory) throws DaoException{
		try {
			this.yearBookDao.modifyAd(yearBook, oldAdName, oldStreet, oldTown, oldPostCode, oldCategory, newAdName, newPhone, newStreet, newTown, newPostCode, newCategory);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to get a JSON array with the names of the categories of a yearbook */
	public String getCategoriesNames(int yearBook) throws DaoException{
		try {
			return this.adDao.getCategoriesNames(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to get a JSON array with the post codes linked to a yearbook */
	public String getPostCodes(int yearBook) throws DaoException{
		try {
			return this.adDao.getPostCodes(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to get a JSON array with the street names linked to a yearbook */
	public String getStreetsNames(int yearBook) throws DaoException{
		try {
			return this.adDao.getStreetsNames(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to get a JSON array with the towns names linked to a yearbook */
	public String getTownsNames(int yearBook) throws DaoException{
		try {
			return this.adDao.getTownsNames(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	/* This service allows the user to get a JSON array with the ads names based on an adress */
	public String getAdsByAddress(int yearBook, String street, String town, String postCode) throws DaoException{
		try {
			return this.yearBookDao.getAdsByAddress(yearBook, street, town, postCode);
		} catch (DaoException e) {
			throw e;
		}
	}
}
