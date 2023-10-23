package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import src.entities.*;

import java.util.List;
import java.util.ArrayList;
import com.mysql.cj.xdevapi.Statement;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Database {

    private String url = "jdbc:mysql://localhost:3306/projectibd";
    private String user = "root";
    private String password = "database";

    private byte[] scretKey = new byte[16];

    public String encryptPassword(String password) {
        try {
            SecretKey key = new SecretKeySpec(scretKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            String encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);
            return encryptedPassword;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptPassword(String encryptedPassword) {
        try {
            SecretKey key = new SecretKeySpec(scretKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedPassword = new String(decryptedBytes);
            return decryptedPassword;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public int registerUser(String name, String cpf, String user, String password){

        user = user.toLowerCase();

        try{

            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);


            String queryCheck = "SELECT * FROM user WHERE user = '" + user + "'";
            String queryCpf = "SELECT * FROM user WHERE cpf = '" + cpf + "'";

            PreparedStatement checkUser = connection.prepareStatement(queryCheck);
            PreparedStatement checkCpf = connection.prepareStatement(queryCpf);

            ResultSet result = checkUser.executeQuery();
            ResultSet result2 = checkCpf.executeQuery();

            if(result2.next()){
                return 0;
            }

            if(result.next() ){             
                return 1;
            }

            password = encryptPassword(password);


            String query = "INSERT INTO user (name, cpf, user, password) VALUES ('" + name + "', '" + cpf + "', '" + user + "', '" + password + "');"; 
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            int update = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
            return 8;
        }
        return 2;

    }

    public User checkLogin(String user, String password){
        try{
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);


            String query = "SELECT * FROM user WHERE user = " + "'" + user + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();
            User userCheck = new User();
            while(result.next()){
                String passDb = result.getString("password");
                passDb = decryptPassword(passDb);
                if(!passDb.equals(password)){
                    return new User();
                }
                userCheck = new User(result.getInt("id"), result.getString("name"), result.getString("user"), result.getString("password"));
            }
            preparedStatement.close();
            connection.close();

            return userCheck;
           
            
        }catch(Exception e){
            System.err.println(e);
            return new User(-1);
        }

        
    }

    public List<Car> getCarsByIdUser(int idUser){
        List<Car> cars = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            String query = "SELECT * FROM car WHERE idUser=" + idUser;

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                Car car = new Car(result.getInt("id"), result.getInt("idUser"), result.getString("model"), result.getString("brand"), result.getInt("manufactureYear"), result.getFloat("value"));
                cars.add(car);
            }

            return cars;

        }catch(Exception e){
            System.err.println(e);
            return cars;
        }
    }


    public boolean updateCar(int id, String model, String brand, int manufactureYear, double value){
        try{
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            
            String query = "UPDATE car SET model = '" + model + "', brand = '" + brand + "', manufactureYear = " + manufactureYear  + ", value = " + value + " WHERE id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int result = preparedStatement.executeUpdate();

            return true;
        }catch(Exception e){
            System.err.println(e);
            return false;
        }
    }


    public boolean removeCar(int id){
        try{
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);

            String query = "DELETE FROM car WHERE id = " + id;
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            int result = preparedStatement.executeUpdate();

            return true;
        }catch(Exception e){
            System.err.println(e);
            return false;
        }
    }


    public boolean addCar(int idUser, String brand, String model, int manufactureYear, double value){
        try{
            Connection  connection = DriverManager.getConnection(this.url, this.user, this.password);
            
            String query = "INSERT INTO car (idUser, brand, model, manufactureYear, value) VALUES (" + idUser + ", '" + brand + "', '" + model + "', " + manufactureYear + ", " + value + ")";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            int result = preparedStatement.executeUpdate();

            return true;
        }catch(Exception e){
            System.err.println(e);
            return false;
        }
    }
}
