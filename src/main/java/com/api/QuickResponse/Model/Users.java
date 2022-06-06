package com.api.QuickResponse.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("age")
    @Expose
    private int age;
    @SerializedName("gender")
    @Expose
    private boolean gender;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("accessToken")
    @Expose
    private String accessToken ;

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Users(String userName, String fullName, int age, boolean gender, String password, String id, String accessToken) {
        this.userName = userName;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.id = id;
        this.accessToken = accessToken;
    }
}
