package stonetree.com.meals.mealselection.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meal implements Serializable {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("ingredients")
    private List<Integer> ingredients = new ArrayList<>();

    @Expose
    @SerializedName("image")
    private String imageUrl;

    @Expose
    @SerializedName("bitmap")
    private Bitmap bitmap;

    @Expose
    @SerializedName("custom")
    private List<Integer> customIngredients = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public List<Integer> getCustomIngredients() {
        return customIngredients;
    }

    public void setCustomIngredients(List<Integer> customIngredients) {
        this.customIngredients = customIngredients;
    }
}
