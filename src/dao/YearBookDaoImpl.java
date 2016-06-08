package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Ad;
import beans.YearBook;

public class YearBookDaoImpl implements YearBookDao {
	private DaoFactory daoFactory;

    public YearBookDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
	}
    
    @Override
    public void dropAd(int yearBook, String adName) throws DaoException{
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("DELETE FROM Ad WHERE yearBook=? AND name=?;");
            preparedStatement.setInt(1, yearBook);
            preparedStatement.setString(2, adName);
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
    
    @Override
	public String getAdsNames(int yearBook) throws DaoException{
    	YearBook yearBookBean = new YearBook(yearBook);
    	ArrayList<Ad> ads = new ArrayList<Ad>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ObjectMapper mapper = new ObjectMapper();
        // Object to JSON in String
        String jsonInString = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("SELECT A.yearBook AS yearBook, A.name AS adName "
            		+ "FROM Ad A "
            		+ "LEFT OUTER JOIN Adress Ad ON  A.adress = Ad.id "
            		+ "LEFT OUTER JOIN Category C ON A.category = C.id "
            		+ "WHERE A.yearBook = ? "
            		+ "ORDER BY A.name;");
            preparedStatement.setInt(1, yearBook);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
            	String adName = result.getString("adName");

            	Ad tmpAd = new Ad(yearBook, adName);                
            	ads.add(tmpAd);
            }
            yearBookBean.setAds(ads);
            jsonInString = mapper.writeValueAsString(yearBookBean);
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données " + e.getMessage());
        } catch (JsonProcessingException e) {
        	throw new DaoException("Impossible de communiquer avec la base de données " + e.getMessage());
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
        return jsonInString;
    }
}