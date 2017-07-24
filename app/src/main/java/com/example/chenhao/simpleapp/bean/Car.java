package com.example.chenhao.simpleapp.bean;

/**
 * Created by IamMr on 2017/7/24.
 */

public class Car {
    public int Id;
    public int userId;
    public int carId;
    public String carName;
    public double balance ;

    public Car() {
    }

    public Car(int userID, int carId, String carName, double balance) {
        this.userId = userID;
        this.carId = carId;
        this.carName = carName;
        this.balance = balance;
    }

    public Car(int id, int userID, int carId, String carName, double balance) {
        Id = id;
        this.userId = userID;
        this.carId = carId;
        this.carName = carName;
        this.balance = balance;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        this.userId = userID;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Id=" + Id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", carName='" + carName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
