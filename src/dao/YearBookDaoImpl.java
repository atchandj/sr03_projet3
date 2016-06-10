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
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        String query1 = "UPDATE Category "
        		+ "SET name = ? "
        		+ "WHERE name = ?;";
        String query2 = "UPDATE Address "
        		+ "SET street = ?, town = ?, postCode = ? "
        		+ "WHERE street = ? AND town = ? AND postCode = ?;";
        String query3 = "UPDATE Ad "
        		+ "SET name = ?, phone = ? "
        		+ "WHERE yearBook = ? AND name = ?;";
        String databaseErrorMessage = "Impossible de communiquer avec la base de données";
        try{
            connexion = daoFactory.getConnection();
            preparedStatement1 = (PreparedStatement) connexion.prepareStatement(query1);
            preparedStatement1.setString(1, newCategory);
            preparedStatement1.setString(2, oldCategory);
            int result1 = preparedStatement1.executeUpdate();
            
            preparedStatement2 = (PreparedStatement) connexion.prepareStatement(query2);
            preparedStatement2.setString(1, newStreet);
            preparedStatement2.setString(2, newTown);
            preparedStatement2.setString(3, newPostCode);            
            preparedStatement2.setString(4, oldStreet);
            preparedStatement2.setString(5, oldTown);
            preparedStatement2.setString(6, oldPostCode);            
            int result2 = preparedStatement2.executeUpdate();
            
            preparedStatement3 = (PreparedStatement) connexion.prepareStatement(query3);
            preparedStatement3.setString(1, newAdName);
            preparedStatement3.setString(2, newPhone);
            preparedStatement3.setInt(3, yearBook);
            preparedStatement3.setString(4, oldAdName);
            int result3 = preparedStatement3.executeUpdate();
            
            connexion.commit();
            if( (result1 == 0) && (result2 == 0) && (result3 == 0) ){
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