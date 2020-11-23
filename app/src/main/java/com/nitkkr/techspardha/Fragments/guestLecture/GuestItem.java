package com.nitkkr.techspardha.Fragments.guestLecture;

public class GuestItem {
    private String guest_image,name,date,time,link;

    public GuestItem(String guest_image,String name,String date,String time,String link)
    {
        this.guest_image = guest_image;
        this.date = date;
        this.link = link;
        this.name = name;
        this.time = time;
    }
    public String getGuestImage(){
        return guest_image;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public String getLink(){
        return link;
    }
}
