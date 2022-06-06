package com.api.QuickResponse.Model;

public class ItemLogin {
    public String userName;
    public String password;
    public String accessToken;

    public ItemLogin(String userName, String password, String accessToken) {
        this.userName = userName;
        this.password = password;
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "ItemLogin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
