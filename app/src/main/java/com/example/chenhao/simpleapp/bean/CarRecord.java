package com.example.chenhao.simpleapp.bean;

/**
 * Created by IamMr on 2017/7/24.
 */

public class CarRecord {
    public int Id;
    public int carId;
    public double money;

    public String opType;
    public int userId;
    public String opTime;


    public CarRecord() {
    }

    public CarRecord(int carId, double money, String opType, int userId, String opTime) {
        this.carId = carId;
        this.money = money;
        this.opType = opType;
        this.userId = userId;
        this.opTime = opTime;
    }

    public CarRecord(int id, int carId, double money, String opType, int userId, String opTime) {
        Id = id;
        this.carId = carId;
        this.money = money;
        this.opType = opType;
        this.userId = userId;
        this.opTime = opTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

    @Override
    public String toString() {
        return "CarRecord{" +
                "Id=" + Id +
                ", carId=" + carId +
                ", money=" + money +
                ", opType='" + opType + '\'' +
                ", userId=" + userId +
                ", opTime='" + opTime + '\'' +
                '}';
    }
}
