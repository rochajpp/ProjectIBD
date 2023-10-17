package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import com.mysql.cj.xdevapi.Statement;

public class Database {

    private String url = "jdbc:mysql://localhost:3306/projectibd";
    private String user = "root";
    private String password = "database";


    public boolean registerUser(String name, String user, String password){

        user = user.toLowerCase();

        try{

            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);


            String queryCheck = "SELECT * FROM user WHERE user = '" + user + "'";

            PreparedStatement checkUser = connection.prepareStatement(queryCheck);

            ResultSet result = checkUser.executeQuery();

            if(result.next()){             
                return false;
            }


            String query = "INSERT INTO user (name, user, password) VALUES ('" + name + "', '" + user + "', '" + password + "');"; 
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            int update = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return true;

    }

    public boolean checkLogin(String user, String password){
        try{
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);


            String query = "SELECT * FROM user WHERE user = " + "'" + user + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();

            String passDb = result.getString("password");

            if(passDb != password || !result.next()){
                return false;
            }

            

            preparedStatement.close();
            connection.close();

           
            
        }catch(Exception e){
            System.err.println(e);
        }

        return true;
    }

}
