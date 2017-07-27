package com.example.chenhao.simpleapp.user.bean;

/**
 * Created by chenhao on 17/7/15.
 * 用户简单的一个信息类
 */
public class UserBean {

    private int id = 0;
    private String name = " ";
    private float money = 0;
    private int status = 0;
    private int speed = 0;


    /**
     * Instantiates a new User bean.
     */
    public UserBean() {

    }

    /**
     * Instantiates a new User bean.
     *
     * @param id     the id
     * @param name   the name
     * @param money  the money
     * @param status the status
     * @param speed  the speed
     */
    public UserBean(int id, String name, float money, int status, int speed) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.status = status;
        this.speed = speed;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public float getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(float money) {
        this.money = money;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
