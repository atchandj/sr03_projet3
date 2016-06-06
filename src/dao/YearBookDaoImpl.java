package dao;

public class YearBookDaoImpl implements YearBookDao {
	private DaoFactory daoFactory;

    public YearBookDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
	}
}