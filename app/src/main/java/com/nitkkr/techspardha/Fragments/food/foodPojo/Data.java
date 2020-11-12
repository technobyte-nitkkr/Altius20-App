package com.nitkkr.techspardha.Fragments.food.foodPojo;

public class Data {
    private FoodSponsors[] foodSponsors;

    public FoodSponsors[] getFoodSponsors() {
        return foodSponsors;
    }

    public void setFoodSponsors(FoodSponsors[] foodSponsors) {
        this.foodSponsors = foodSponsors;
    }

    @Override
    public String toString() {
        return "ClassPojo [foodSponsors = " + foodSponsors + "]";
    }
}
