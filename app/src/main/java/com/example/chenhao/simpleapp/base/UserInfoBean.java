package com.example.chenhao.simpleapp.base;

/**
 * Created by IamMr on 2017/7/17.
 */

public class UserInfoBean {
    private int id;
    private String mUserName;
    private String mPassword;
    private String mName;
    private String mEmail;
    private String mPhone;
    private String mRegistTime;
    private int mRole;
    private String mCarId = null;

    public UserInfoBean() {
    }

    public UserInfoBean(int id, String mUserName, String mPassword, String mName, String mEmail, String mPhone, String mRegistTime, int mRole) {
        this.id = id;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mRegistTime = mRegistTime;
        this.mRole = mRole;
    }

    public UserInfoBean(String mUserName, String mPassword, String mName, String mEmail, String mPhone, String mRegistTime, int mRole) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mRegistTime = mRegistTime;
        this.mRole = mRole;
    }


    public UserInfoBean(int id, String mUserName, String mPassword, String mName, String mEmail, String mPhone, String mRegistTime, int mRole, String mCarId) {
        this.id = id;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mRegistTime = mRegistTime;
        this.mRole = mRole;
        this.mCarId = mCarId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getRegistTime() {
        return mRegistTime;
    }

    public void setRegistTime(String mRegistTime) {
        this.mRegistTime = mRegistTime;
    }

    public int getRole() {
        return mRole;
    }

    public void setRole(int mRole) {
        this.mRole = mRole;
    }

    public String getCarId() {
        return mCarId;
    }

    public void setCarId(String mCarId) {
        this.mCarId = mCarId;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "id=" + id +
                ", mUserName='" + mUserName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mName='" + mName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mRegistTime='" + mRegistTime + '\'' +
                ", mRole=" + mRole +
                '}';
    }
}
