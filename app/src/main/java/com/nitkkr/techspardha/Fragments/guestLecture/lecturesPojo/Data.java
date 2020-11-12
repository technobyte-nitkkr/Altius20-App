package com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo;

public class Data {
    private Lectures[] lectures;

    public Lectures[] getLectures() {
        return lectures;
    }

    public void setLectures(Lectures[] lectures) {
        this.lectures = lectures;
    }

    @Override
    public String toString() {
        return "ClassPojo [lectures = " + lectures + "]";
    }
}
