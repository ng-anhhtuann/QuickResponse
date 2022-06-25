package com.api.QuickResponse.Model.Register;

public class SuccessRegister {
    public boolean success;
    public int errorCode;
    public DataRegisterStatus info;

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "SuccessRegister{" +
                "success=" + success +
                ", errorCode=" + errorCode +
                ", info=" + info +
                '}';
    }

    public SuccessRegister(boolean success, int errorCode, DataRegisterStatus info) {
        this.success = success;
        this.errorCode = errorCode;
        this.info = info;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public DataRegisterStatus getInfo() {
        return info;
    }

    public void setInfo(DataRegisterStatus info) {
        this.info = info;
    }
}
