package com.api.QuickResponse.Model;

import com.api.QuickResponse.Model.Manufacturing.EncodeBase64ToString;

public class Users {
    public String userName;
    public String fullName;
    public int age;
    public boolean gender;
    public String password;
    public String id;
    public String accessToken;

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
        this.accessToken = EncodeBase64ToString.base64Encode(this.userName);
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
