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
			daoFactory = DaoFactory.getInstance();
			this.adDao = daoFactory.getAdDao();
			this.yearBookDao = daoFactory.getYearBookDao();

		} catch (DAOConfigurationException e) {
			e.printStackTrace();
		}        
    }  
    
	public void dropCategory(String category) throws DaoException{
		try {
			this.adDao.dropCategory(category);
		} catch (DaoException e) {
			throw e;
		} 
	}

	public void updateCategory(String oldCategoryName, String newCategoryName) throws DaoException {
		try {
			this.adDao.updateCategory(oldCategoryName, newCategoryName);
		} catch (DaoException e) {
			throw e;
		} 
	}
	
	public void addCategory(String categoryName) throws DaoException{
		try {
			this.adDao.addCategory(categoryName);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public void dropAd(int yearBook, String adName) throws DaoException{
		try {
			this.yearBookDao.dropAd(yearBook, adName);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public String getAdsNames(int yearBook) throws DaoException{
		try {
			return this.yearBookDao.getAdsNames(yearBook);
		} catch (DaoException e) {
			throw e;
		}		
	}
	
	public String getYearBook(int yearBook) throws DaoException{
		try {
			return this.yearBookDao.getYearBook(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public String getAd(int yearBook, String adName) throws DaoException{
		try {
			return this.yearBookDao.getAd(yearBook, adName);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public void addAd(int yearBook, String adName, String phone, String street, String town, String postCode, String category) throws DaoException{
		try {
			this.yearBookDao.addAd(yearBook, adName, phone, street, town, postCode, category);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public void modifyAd(int yearBook, String oldAdName, String oldStreet, String oldTown, String oldPostCode, String oldCategory, String newAdName, String newPhone, String newStreet, String newTown, String newPostCode, String newCategory) throws DaoException{
		try {
			this.yearBookDao.modifyAd(yearBook, oldAdName, oldStreet, oldTown, oldPostCode, oldCategory, newAdName, newPhone, newStreet, newTown, newPostCode, newCategory);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public String getCategoriesNames(int yearBook) throws DaoException{
		try {
			return this.adDao.getCategoriesNames(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public String getPostCodes(int yearBook) throws DaoException{
		try {
			return this.adDao.getPostCodes(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public String getStreetsNames(int yearBook) throws DaoException{
		try {
			return this.adDao.getStreetsNames(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
	
	public String getTownsNames(int yearBook) throws DaoException{
		try {
			return this.adDao.getTownsNames(yearBook);
		} catch (DaoException e) {
			throw e;
		}
	}
}
