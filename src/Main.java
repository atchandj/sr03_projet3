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
    
	public void dropCategory(String category){
		try {
			this.adDao.dropCategory(category);
		} catch (DaoException e) {
			e.printStackTrace();
		} 
	}

	public void updateCategory(String oldCategoryName, String newCategoryName) {
		try {
			this.adDao.updateCategory(oldCategoryName, newCategoryName);
		} catch (DaoException e) {
			e.printStackTrace();
		} 
	}
	
	public void addCategory(String categoryName){
		System.out.println("Bonjour");
		try {
			System.out.println("yo !");
			this.adDao.addCategory(categoryName);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
}
