package com.nitkkr.techspardha.Fragments.food.foodPojo;

public class FoodSponsors {
    private String imageUrl;

    private String link;

    private String name;

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
        return "ClassPojo [imageUrl = " + imageUrl + ", link = " + link + ", name = " + name + "]";
    }
}
