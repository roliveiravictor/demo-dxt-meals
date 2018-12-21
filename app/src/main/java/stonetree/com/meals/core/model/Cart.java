package stonetree.com.meals.core.model;

import stonetree.com.meals.mealselection.model.Meal;

public class Cart {

    private Meal meal;

    private float price;

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public float getPrice() {
        for (float price : meal.getIngredients()) {
            this.price += price;
        }

        for (float price : meal.getCustomIngredients()) {
            this.price += price;
        }

        return this.price;
    }

    protected void setPrice(float price) {
        this.price = price;
    }

    public void refreshPrice() {
        setPrice(0);
    }
}
