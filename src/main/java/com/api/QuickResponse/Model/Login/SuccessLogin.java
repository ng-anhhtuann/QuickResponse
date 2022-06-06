package com.api.QuickResponse.Model.Login;

public class SuccessLogin {
    public boolean success;
    public DataLoginStatus dataLoginStatus;

    @Override
    public String toString() {
        return "SuccessLogin{" +
                "success=" + success +
                ", dataLoginStatus=" + dataLoginStatus +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataLoginStatus getDataLoginStatus() {
        return dataLoginStatus;
    }

    public void setDataLoginStatus(DataLoginStatus dataLoginStatus) {
        this.dataLoginStatus = dataLoginStatus;
    }

    public SuccessLogin(boolean success, DataLoginStatus dataLoginStatus) {
        this.success = success;
        this.dataLoginStatus = dataLoginStatus;
    }
}
