package com.nitkkr.techspardha.drawers.developers.developersPojo;

public class DevelopersData {
    private Data data;

    private String success;

    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClassPojo [data = " + data + ", success = " + success + ", message = " + message + "]";
    }
}
