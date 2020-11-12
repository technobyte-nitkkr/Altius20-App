package com.nitkkr.techspardha.Fragments.food.foodPojo;

public class MyPojo {
    private Data data;

    private String success;

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

    @Override
    public String toString() {
        return "ClassPojo [data = " + data + ", success = " + success + "]";
    }
}


