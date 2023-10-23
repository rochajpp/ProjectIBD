package src.entities;

public class Car {
    private int id;
    private int idUser;
    private String model;
    private String brand;
    private int manufactureYear;
    private double value;

    public Car(int id, int idUser, String model, String brand, int manufactureYear, float value){
        this.id = id;
        this.idUser = idUser;
        this.model = model;
        this.brand = brand;
        this.manufactureYear = manufactureYear;
        this.value = value;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getIdUser(){
        return this.idUser;
    }
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    public String getModel(){
        return this.model;
    }
    public void setModel(String model){
        this.model = model;
    }

    public String getBrand(){
        return this.brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    public int getManufactureYear(){
        return this.manufactureYear;
    }
    public void setManufactureYear(int manufactureYear){
        this.manufactureYear = manufactureYear;
    }

    public double getValue(){
        return this.value;
    }
    public void setValue(double value){
        this.value = value;
    }
    

    @Override
    public String toString(){
        return "Car[id: " + this.id + ", idUser: " + this.idUser + ", model: " + this.model + ", brand: " + this.brand + ", manufactureYear: " + this.manufactureYear + ", value: " + this.value + "]";
    }
   
}
