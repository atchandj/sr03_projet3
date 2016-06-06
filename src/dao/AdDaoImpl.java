package dao;

import java.sql.SQLException;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

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
            preparedStatement.setString(1, category);
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
            preparedStatement.setString(1, newCategoryName);
            preparedStatement.setString(2, oldCategoryName);
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
    	System.out.println("hi");
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        System.out.println("hi");
        System.out.println(categoryName);
        if(!Pattern.matches("^[a-zA-Z]+$", categoryName)){
        	throw new DaoException("Veuillez saisir des données cohérentes.");
        }  
        try{
            connexion = daoFactory.getConnection();
            preparedStatement = (PreparedStatement) connexion.prepareStatement("INSERT INTO Category(name) VALUES(?);");
            preparedStatement.setString(1, categoryName);
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
}
