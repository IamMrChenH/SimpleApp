package com.example.chenhao.simpleapp.bean;

/**
 * Created by IamMr on 2017/7/24.
 * 小车的简单属性的类
 */
public class Car {
    /**
     * The Id.
     */
    public int Id;
    /**
     * The User id.
     */
    public int userId;
    /**
     * The Car id.
     */
    public int carId;
    /**
     * The Car name.
     */
    public String carName;
    /**
     * The Balance.
     */
    public double balance ;

    /**
     * Instantiates a new Car.
     */
    public Car() {
    }

    /**
     * Instantiates a new Car.
     *
     * @param userID  the user id
     * @param carId   the car id
     * @param carName the car name
     * @param balance the balance
     */
    public Car(int userID, int carId, String carName, double balance) {
        this.userId = userID;
        this.carId = carId;
        this.carName = carName;
        this.balance = balance;
    }

    /**
     * Instantiates a new Car.
     *
     * @param id      the id
     * @param userID  the user id
     * @param carId   the car id
     * @param carName the car name
     * @param balance the balance
     */
    public Car(int id, int userID, int carId, String carName, double balance) {
        Id = id;
        this.userId = userID;
        this.carId = carId;
        this.carName = carName;
        this.balance = balance;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserId(int userID) {
        this.userId = userID;
    }

    /**
     * Gets car id.
     *
     * @return the car id
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Sets car id.
     *
     * @param carId the car id
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * Gets car name.
     *
     * @return the car name
     */
    public String getCarName() {
        return carName;
    }

    /**
     * Sets car name.
     *
     * @param carName the car name
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
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
