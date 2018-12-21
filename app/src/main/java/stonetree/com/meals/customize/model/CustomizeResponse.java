package stonetree.com.meals.customize.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import stonetree.com.meals.ingredients.model.Ingredient;

public class CustomizeResponse implements Serializable {

    @Expose
    @SerializedName("ingredients")
    private List<Ingredient> allIngredients = new ArrayList<>();

    public List<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(List<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }
}
