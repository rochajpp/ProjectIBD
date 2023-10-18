package src.entities;

public class User {
    private int id;
    private String name;
    private String user;
    private String password;

    public User(int id, String name, String user, String password){
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
    }
    public User(){

    };

    public User(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
    public void serId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getUser(){
        return this.user;
    }
    public void setUser(String user){
        this.user = user;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return "User[id: " + this.id + ", name: " + this.name + ", user: " + this.user + ", password: " + this.password + "]";
    }
}
