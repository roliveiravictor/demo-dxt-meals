package stonetree.com.meals.ingredients.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IngredientsResponse implements Serializable {

    @Expose
    @SerializedName("ingredients")
    private List<Ingredient> mealIngredients = new ArrayList<>();

    public List<Ingredient> getMealIngredients() {
        return mealIngredients;
    }

    public void setMealIngredients(List<Ingredient> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }
}
