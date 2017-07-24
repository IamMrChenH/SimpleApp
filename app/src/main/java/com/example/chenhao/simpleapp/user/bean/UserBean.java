package com.example.chenhao.simpleapp.user.bean;

/**
 * Created by chenhao on 17/7/15.
 */

public class UserBean {

    private int id = 0;
    private String name = " ";
    private float money = 0;
    private int status = 0;
    private int speed = 0;


    public UserBean() {

    }

    public UserBean(int id, String name, float money, int status, int speed) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.status = status;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
