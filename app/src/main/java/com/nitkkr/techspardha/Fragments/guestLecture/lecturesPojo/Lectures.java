package com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo;

public class Lectures {
    private String date;

    private String imageUrl;

    private String name;

    private String time;

    private String desc;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ClassPojo [date = " + date + ", imageUrl = " + imageUrl + ", name = " + name + ", time = " + time + ", desc = " + desc + "]";
    }

}
