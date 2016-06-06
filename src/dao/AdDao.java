package dao;

public interface AdDao {
    void dropCategory(String category) throws DaoException;
	void updateCategory(String oldCategoryName, String newCategoryName) throws DaoException;
	void addCategory(String categoryName) throws DaoException;
}
