package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class Database {

    private String url = "jdbc:mysql://localhost:3306/projectibd";
    private String user = "root";
    private String password = "123456";


    public void registerUser(String name, String user, String password){

        try{
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
        
            String query = "INSERT INTO user (name, user, password) VALUES ('" + name + "', '" + user + "', '" + password + "');"; 

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            

            int update = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void checkLogin(String user, String password){
        try{
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);

            String query = "SELECT * FROM user WHERE user = " + user;

            
        }
    }

}
