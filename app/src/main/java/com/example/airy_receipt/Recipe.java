package com.example.airy_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.List;

public class Recipe {

    @SerializedName("creator")
    @Expose
    private String creator;

    @SerializedName("review")
    @Expose
    private String review;

    @SerializedName("rating")
    @Expose
    private Double rating;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("ingredients")
    @Expose
    private List<String> ingredients;

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Comparator<Recipe> ByRating = new Comparator<Recipe>() {
        @Override
        public int compare(Recipe o1, Recipe o2) {
            return - Double.valueOf(o1.rating).compareTo(Double.valueOf(o2.rating));
        }
    };
}
