package com.api.QuickResponse.Model;

import java.util.UUID;

public class ItemRegister {
    public String userName;
    public String fullName;
    public int age;
    public boolean gender;
    public String password;
    public String id;
    public UUID uuid = UUID.randomUUID();

    @Override
    public String toString() {
        return "Registered{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", password='" + password + '\'' +
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

    public boolean getGender() {
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
        return uuid.toString();
    }

    public void setId(String id) {
        this.id = uuid.toString();
    }

    public ItemRegister(String userName, String fullName, int age, boolean gender, String password, String id) {
        this.userName = userName;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.id = id;
    }
}
