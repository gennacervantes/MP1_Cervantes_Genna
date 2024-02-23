/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author giogen
 */
public class DBManager {

    private String mySQLHost = "localhost"; //localhost
    private String mySQLPort = "3306";
    private Connection conn = null;
    private HashMap<String, PreparedStatement> preStmts = new HashMap<String, PreparedStatement>();

    public DBManager(String username, String password) {
        
        // establishing a connection
        try {
            String url = String.format("jdbc:mysql://%s:%s", mySQLHost, mySQLPort);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Completed connection.");

        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        } catch (Exception e) {
            System.err.println(String.format("Fatal Error: %s", e.getMessage()));
        }

        // prepared statements
        try {
            String getUsersSQL = "select * from logindb.users order by email;";
            PreparedStatement getUsers = conn.prepareStatement(getUsersSQL);
            preStmts.put("getUsers", getUsers);
            

            String loginSQL = "select UserRole from logindb.users where email = ? and password = ?;";
            PreparedStatement login = conn.prepareStatement(loginSQL);
            preStmts.put("login", login);

            
            String addUserSQL = "insert into logindb.users values(?, ?, ?);";
            PreparedStatement addUser = conn.prepareStatement(addUserSQL);
            preStmts.put("addUser", addUser);
            
            String editUserSQL = "update logindb.users set password = ?, userRole = ? where email = ?";
            PreparedStatement editUser = conn.prepareStatement(editUserSQL);
            preStmts.put("editUser", editUser);
            
            String deleteUserSQL = "delete from logindb.users where email = ?";
            PreparedStatement deleteUser = conn.prepareStatement(deleteUserSQL);
            preStmts.put("deleteUser", deleteUser);
            
        } catch (SQLException sqlException) {
            System.err.println("SQLException: " + sqlException.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

    }

    public ArrayList<User> printRecords() {
        
        PreparedStatement getUsers = preStmts.get("getUsers");
        ArrayList<User> retrievedUsers = new ArrayList<>();

        try {
            ResultSet rs = getUsers.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("Email"), rs.getString("Password"), rs.getString("UserRole"));
                retrievedUsers.add(user);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQLException: " + sqlException.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        try {
            getUsers.clearParameters();
        } catch (SQLException sqlException) {
            System.err.println("SQLException: " + sqlException);
        }

        return retrievedUsers;
    }
    
    public String login(String username, String password){
        
        PreparedStatement login = preStmts.get("login");
        String role = null;
        
        try{
            login.setString(1, username);
            login.setString(2, password);
            
            ResultSet rs = login.executeQuery();
            if (rs.next()){
                role = rs.getString("UserRole");
            }
        } catch(SQLException sqlException){
            System.err.println("SQLException: " + sqlException.getMessage());
        } catch(Exception e){
            System.err.println("Exception: " + e.getMessage());
        }
        
        try {
            login.clearParameters();
        } catch (SQLException sqlException) {
            System.err.println("SQLException: " + sqlException);
        }
        
        return role;
    }

    public int addRecord(String email, String password, String role){
        
        PreparedStatement addUser = preStmts.get("addUser");
        int success = 0;
        
        try{
            addUser.setString(1, email);
            addUser.setString(2, password);
            addUser.setString(3, role);
            
            success = addUser.executeUpdate();
            
        }catch(SQLException sqlException){
            System.err.println("SQLException: " + sqlException.getMessage());
        }catch(Exception e){
            System.err.println("SQLException: " + e.getMessage());
        }
        
        try {
            addUser.clearParameters();
        } catch (SQLException sqlException) {
            System.err.println("SQLException: " + sqlException);
        }
        
        return success;
    }
    
    public int editRecord(String password, String role, String email){
        
        PreparedStatement editUser = preStmts.get("editUser");
        int success = 0;
        try{
            editUser.setString(1, password);
            editUser.setString(2, role);
            editUser.setString(3, email);
            
            success = editUser.executeUpdate();
           
        }catch(SQLException sqlException){
            System.err.println("SQLException:" + sqlException.getMessage());
        }catch(Exception e){
            System.err.println("Exception: " + e.getMessage());
        }
        
        try {
            editUser.clearParameters();
        } catch (SQLException sqlException) {
            System.err.println("SQLException: " + sqlException);
        }
        
        return success;
    }
    
    public int deleteRecord(String email){

        PreparedStatement deleteUser = preStmts.get("deleteUser");
        int success = 0;
        
        try{
            deleteUser.setString(1, email);
            
            success = deleteUser.executeUpdate();
        }catch(SQLException sqlException){
            
        }catch(Exception e){
            
        }
        
        try {
            deleteUser.clearParameters();
        } catch (SQLException sqlException) {
            System.err.println("SQLException: " + sqlException);
        }
        
        return success;
    }
        

    public boolean close() {
        if (conn == null) {
            return true;
        }
        try {
            conn.close();
            conn = null;
            System.out.println("Connection closed.");
            return true;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        } catch (Exception e) {
            System.err.println(String.format("Fatal Error: %s", e.getMessage()));
        }
        return false;
    }
}
