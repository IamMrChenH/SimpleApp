package com.example.chenhao.simpleapp.bean;

/**
 * Created by IamMr on 2017/7/24.
 * 小车操作记录的 实体类
 */
public class CarRecord {
    /**
     * The Id.
     */
    public int Id;
    /**
     * The Car id.
     */
    public int carId;
    /**
     * The Money.
     */
    public double money;

    /**
     * The Op type.
     */
    public String opType;
    /**
     * The User id.
     */
    public int userId;
    /**
     * The Op time.
     */
    public String opTime;


    /**
     * Instantiates a new Car record.
     */
    public CarRecord() {
    }

    /**
     * Instantiates a new Car record.
     *
     * @param carId  the car id
     * @param money  the money
     * @param opType the op type
     * @param userId the user id
     * @param opTime the op time
     */
    public CarRecord(int carId, double money, String opType, int userId, String opTime) {
        this.carId = carId;
        this.money = money;
        this.opType = opType;
        this.userId = userId;
        this.opTime = opTime;
    }

    /**
     * Instantiates a new Car record.
     *
     * @param id     the id
     * @param carId  the car id
     * @param money  the money
     * @param opType the op type
     * @param userId the user id
     * @param opTime the op time
     */
    public CarRecord(int id, int carId, double money, String opType, int userId, String opTime) {
        Id = id;
        this.carId = carId;
        this.money = money;
        this.opType = opType;
        this.userId = userId;
        this.opTime = opTime;
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
     * Gets money.
     *
     * @return the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Gets op type.
     *
     * @return the op type
     */
    public String getOpType() {
        return opType;
    }

    /**
     * Sets op type.
     *
     * @param opType the op type
     */
    public void setOpType(String opType) {
        this.opType = opType;
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
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets op time.
     *
     * @return the op time
     */
    public String getOpTime() {
        return opTime;
    }

    /**
     * Sets op time.
     *
     * @param opTime the op time
     */
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
