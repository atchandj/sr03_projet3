package dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class YearBookDaoImpl implements YearBookDao {
	private DaoFactory daoFactory;

    public YearBookDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
	}
    
    @Override
    public void dropAd(String adName) throws DaoException{
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("DELETE FROM Ad WHERE yearBook=1 AND name=?;");
            preparedStatement.setString(1, adName);
            int result = preparedStatement.executeUpdate();
            connexion.commit();
            if(result == 0){
            	throw new DaoException("Annonce à supprimer inconnue.");
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
    }
}