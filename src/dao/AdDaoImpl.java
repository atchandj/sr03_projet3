package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


/* The implementation of the class witch interacts with the database 
 * to add, modify or delete some elements linked to an year book.
 * */
public class AdDaoImpl implements AdDao {
	private DaoFactory daoFactory;
	
    public AdDaoImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void dropCategory(String category) throws DaoException{
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("DELETE FROM Category WHERE name = ?;");
            preparedStatement.setString(1, category.toLowerCase(Locale.FRENCH));
            int result = preparedStatement.executeUpdate();
            connexion.commit();
            if(result == 0){
            	throw new DaoException("Catégorie à supprimer inconnue.");
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
	public void updateCategory(String oldCategoryName, String newCategoryName) throws DaoException{
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("UPDATE Category SET name=? WHERE name =?;");
            preparedStatement.setString(1, newCategoryName.toLowerCase(Locale.FRENCH));
            preparedStatement.setString(2, oldCategoryName.toLowerCase(Locale.FRENCH));
            int result = preparedStatement.executeUpdate();
            connexion.commit();
            if(result == 0){
            	throw new DaoException("Catégorie à modifier inconnue.");
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
    public void addCategory(String categoryName) throws DaoException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null; 
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("INSERT INTO Category(name) VALUES(?);");
            preparedStatement.setString(1, categoryName.toLowerCase(Locale.FRENCH));
            int result = preparedStatement.executeUpdate();
            connexion.commit();
            if(result == 0){
            	throw new DaoException("Catégorie impossible à ajouter.");
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
	public String getCategoriesNames(int yearBook) throws DaoException{
    	ArrayList<String> categoriesNames = new ArrayList<String>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        String categoryName = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("SELECT DISTINCT C.name AS name "
            		+ "FROM Category C, Ad A "
            		+ "WHERE C.id = A.category AND A.yearBook = ?;");
            preparedStatement.setInt(1, yearBook);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
            	categoryName = result.getString("name");             
            	categoriesNames.add(categoryName.toLowerCase(Locale.FRENCH));
            }
            jsonInString = mapper.writeValueAsString(categoriesNames);
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
	public String getTownsNames(int yearBook) throws DaoException{
    	ArrayList<String> townsNames = new ArrayList<String>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        String townName = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("SELECT DISTINCT Addr.town AS town "
            		+ "FROM Address Addr, Ad A "
            		+ "WHERE Addr.id = A.address AND A.yearBook = ?;");
            preparedStatement.setInt(1, yearBook);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
            	townName = result.getString("town");             
            	townsNames.add(townName.toLowerCase(Locale.FRENCH));
            }
            jsonInString = mapper.writeValueAsString(townsNames);
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
	public String getStreetsNames(int yearBook) throws DaoException{
    	ArrayList<String> streetsNames = new ArrayList<String>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        String streetName = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("SELECT DISTINCT Addr.street AS street "
            		+ "FROM Address Addr, Ad A "
            		+ "WHERE Addr.id = A.address AND A.yearBook = ?;");
            preparedStatement.setInt(1, yearBook);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
            	streetName = result.getString("street");             
            	streetsNames.add(streetName.toLowerCase(Locale.FRENCH));
            }
            jsonInString = mapper.writeValueAsString(streetsNames);
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
	public String getPostCodes(int yearBook) throws DaoException{
    	ArrayList<String> postCodes = new ArrayList<String>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        String postCode = null;
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("SELECT DISTINCT Addr.postCode AS postCode "
            		+ "FROM Address Addr, Ad A "
            		+ "WHERE Addr.id = A.address AND A.yearBook = ?;");
            preparedStatement.setInt(1, yearBook);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
            	postCode = result.getString("postCode");             
            	postCodes.add(postCode);
            }
            jsonInString = mapper.writeValueAsString(postCodes);
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
