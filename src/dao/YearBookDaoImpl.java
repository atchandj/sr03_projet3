package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Ad;
import beans.Address;
import beans.Category;
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
        String jsonInString = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("SELECT A.yearBook AS yearBook, A.name AS adName "
            		+ "FROM Ad A "
            		+ "LEFT OUTER JOIN Address Ad ON  A.address = Ad.id "
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
    
    @Override
	public String getYearBook(int yearBook) throws DaoException{
    	YearBook yearBookBean = new YearBook(yearBook);
    	ArrayList<Ad> ads = new ArrayList<Ad>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ObjectMapper mapper = new ObjectMapper();        
        String jsonInString = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("SELECT A.yearBook AS yearBook, A.name AS adName, A.phone AS phone, Ad.id AS addressId, Ad.street AS street, Ad.town AS town, Ad.postCode AS postCode, C.id AS categoryId, C.name AS category "
            		+ "FROM Ad A "
            		+ "LEFT OUTER JOIN Address Ad ON  A.address = Ad.id "
            		+ "LEFT OUTER JOIN Category C ON A.category = C.id "
            		+ "WHERE A.yearBook = ? "
            		+ "ORDER BY A.name;");
            preparedStatement.setInt(1, yearBook);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
            	String adName = result.getString("adName");
            	String phone = result.getString("phone");
            	int addressId = result.getInt("addressId");
            	String street = result.getString("street");
            	String town = result.getString("town");
            	String postCode = result.getString("postCode");
            	int categoryId = result.getInt("categoryId");
            	String category = result.getString("category");    	
            	
            	Ad tmpAd = new Ad(yearBook, adName, phone); 
            	Category tmpCategory = null;
            	Address tmpAdress = null;
            	if(street != null){
            		tmpAdress = new Address(addressId, street, town, postCode);
            		tmpAd.setAddress(tmpAdress);
            	}else{
            		tmpAd.setAddress(null);
            	}
            	if(category != null){
            		tmpCategory = new Category(categoryId, category);
            		tmpAd.setCategory(tmpCategory);
            	}else{
            		tmpAd.setCategory(null);
            	}
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
    
    @Override
	public String getAd(int yearBook, String adName) throws DaoException{
    	Ad ad = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ObjectMapper mapper = new ObjectMapper();        
        String jsonInString = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("SELECT A.yearBook AS yearBook, A.name AS adName, A.phone AS phone, Ad.id AS addressId, Ad.street AS street, Ad.town AS town, Ad.postCode AS postCode, C.id AS categoryId, C.name AS category "
            		+ "FROM Ad A "
            		+ "LEFT OUTER JOIN Address Ad ON  A.address = Ad.id "
            		+ "LEFT OUTER JOIN Category C ON A.category = C.id "
            		+ "WHERE A.yearBook = ? AND A.name = ?;");
            preparedStatement.setInt(1, yearBook);
            preparedStatement.setString(2, adName);
            ResultSet result = preparedStatement.executeQuery();
            boolean isResult = result.next();
            if(isResult){
            	String phone = result.getString("phone");
            	int addressId = result.getInt("addressId");
            	String street = result.getString("street");
            	String town = result.getString("town");
            	String postCode = result.getString("postCode");
            	int categoryId = result.getInt("categoryId");
            	String category = result.getString("category");    	
            	ad = new Ad(yearBook, adName, phone);            	
            	Category tmpCategory = null;
            	Address tmpAdress = null;
            	if(street != null){
            		tmpAdress = new Address(addressId, street, town, postCode);
            		ad.setAddress(tmpAdress);
            	}else{
            		ad.setAddress(null);
            	}
            	if(category != null){
            		tmpCategory = new Category(categoryId, category);
            		ad.setCategory(tmpCategory);
            	}else{
            		ad.setCategory(null);
            	}
        	}
            jsonInString = mapper.writeValueAsString(ad);
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
    
    @Override
    public void addAd(int yearBook, String adName, String phone, String street, String town, String postCode, String category) throws DaoException {  
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String query = "CALL addAd(?, ?, ?, ?, ?, ?, ?);";
        String databaseErrorMessage = "Impossible de communiquer avec la base de données";
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement(query);
            preparedStatement.setInt(1, yearBook);
            preparedStatement.setString(2, adName);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, street);
            preparedStatement.setString(5, town);
            preparedStatement.setString(6, postCode);
            preparedStatement.setString(7, category);
            int result = preparedStatement.executeUpdate();
            connexion.commit();
            if(result == 0){
            	throw new DaoException("Rien n'a été mis à jour.");
            }
        } catch (SQLException e) {
            throw new DaoException(databaseErrorMessage + ": " + e.getMessage());
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException(databaseErrorMessage + ": " + e.getMessage());
            }
        }
    }
    
    @Override
    public void modifyAd(int yearBook, String oldAdName, String oldStreet, String oldTown, String oldPostCode, String oldCategory, String newAdName, String newPhone, String newStreet, String newTown, String newPostCode, String newCategory) throws DaoException {  
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String query = "CALL modifyAd(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        String databaseErrorMessage = "Impossible de communiquer avec la base de données";
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement(query);
            preparedStatement.setInt(1, yearBook);
            preparedStatement.setString(2, oldAdName);
            preparedStatement.setString(3, oldStreet);
            preparedStatement.setString(4, oldTown);
            preparedStatement.setString(5, oldPostCode);
            preparedStatement.setString(6, oldCategory);
            preparedStatement.setString(7, newAdName);
            preparedStatement.setString(8, newPhone);
            preparedStatement.setString(9, newStreet);
            preparedStatement.setString(10, newTown);
            preparedStatement.setString(11, newPostCode);
            preparedStatement.setString(12, newCategory);
            int result = preparedStatement.executeUpdate();
            connexion.commit();
            if(result == 0){
            	throw new DaoException("Rien n'a été mis à jour.");
            }
        } catch (SQLException e) {
            throw new DaoException(databaseErrorMessage + ": " + e.getMessage());
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException(databaseErrorMessage + ": " + e.getMessage());
            }
        }
    }
}