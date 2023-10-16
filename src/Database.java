package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String url = "jdbc:mysql://localhost:3306/projectibd";
    private String user = "root";
    private String password = "database";


    public void registerUser(String name, String user, String password){
        try{
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            
            String query = "INSERT INTO user (name, user, password) VALUES ('" + name + "', '" + user + "', '" + password + "');"; 
            System.out.println(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
