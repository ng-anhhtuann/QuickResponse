package com.api.QuickResponse.Model.ReturnRegisterStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorLogin {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("success")
    @Expose
    private Boolean success;

    @Override
    public String toString() {
        return "ErrorLogin{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", success=" + success +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ErrorLogin(String message, Integer errorCode, Boolean success) {
        this.message = message;
        this.errorCode = errorCode;
        this.success = success;
    }
}
