package com.api.QuickResponse.Model.ReturnRegisterStatus;

public class DataLoginStatus {
    public String userName;
    public String fullName;
    public int age;
    public boolean gender;
    public String id;
    public String accessToken;

    public DataLoginStatus(String userName, String fullName, int age, boolean gender, String id, String accessToken) {
        this.userName = userName;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "DataLoginStatus{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", id='" + id + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
