package com.nitkkr.techspardha.drawers.developers.developersPojo;

public class Information {
    private String year;

    private String imageUrl;

    private String link;

    private String name;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [year = " + year + ", imageUrl = " + imageUrl + ", link = " + link + ", name = " + name + "]";
    }
}
