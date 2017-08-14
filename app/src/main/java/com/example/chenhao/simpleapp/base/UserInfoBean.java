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

    public int isRole = -1;

    /**
     * Instantiates a new User info bean.
     */
    public UserInfoBean() {
    }

    /**
     * Instantiates a new User info bean.
     *
     * @param id          the id
     * @param mUserName   the m user name
     * @param mPassword   the m password
     * @param mName       the m name
     * @param mEmail      the m email
     * @param mPhone      the m phone
     * @param mRegistTime the m regist time
     * @param mRole       the m role
     */
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

    /**
     * Instantiates a new User info bean.
     *
     * @param mUserName   the m user name
     * @param mPassword   the m password
     * @param mName       the m name
     * @param mEmail      the m email
     * @param mPhone      the m phone
     * @param mRegistTime the m regist time
     * @param mRole       the m role
     */
    public UserInfoBean(String mUserName, String mPassword, String mName, String mEmail, String mPhone, String mRegistTime, int mRole) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mRegistTime = mRegistTime;
        this.mRole = mRole;
    }


    /**
     * Instantiates a new User info bean.
     *
     * @param id          the id
     * @param mUserName   the m user name
     * @param mPassword   the m password
     * @param mName       the m name
     * @param mEmail      the m email
     * @param mPhone      the m phone
     * @param mRegistTime the m regist time
     * @param mRole       the m role
     * @param mCarId      the m car id
     */
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
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return mUserName;
    }

    /**
     * Sets user name.
     *
     * @param mUserName the m user name
     */
    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return mPassword;
    }

    /**
     * Sets password.
     *
     * @param mPassword the m password
     */
    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return mName;
    }

    /**
     * Sets name.
     *
     * @param mName the m name
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * Sets email.
     *
     * @param mEmail the m email
     */
    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return mPhone;
    }

    /**
     * Sets phone.
     *
     * @param mPhone the m phone
     */
    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    /**
     * Gets regist time.
     *
     * @return the regist time
     */
    public String getRegistTime() {
        return mRegistTime;
    }

    /**
     * Sets regist time.
     *
     * @param mRegistTime the m regist time
     */
    public void setRegistTime(String mRegistTime) {
        this.mRegistTime = mRegistTime;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public int getRole() {
        return mRole;
    }

    /**
     * Sets role.
     *
     * @param mRole the m role
     */
    public void setRole(int mRole) {
        this.mRole = mRole;
    }

    /**
     * Gets car id.
     *
     * @return the car id
     */
    public String getCarId() {
        return mCarId;
    }

    /**
     * Sets car id.
     *
     * @param mCarId the m car id
     */
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
