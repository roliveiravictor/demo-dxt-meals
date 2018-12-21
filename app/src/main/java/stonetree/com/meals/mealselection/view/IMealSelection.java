package stonetree.com.meals.mealselection.view;

import stonetree.com.meals.core.view.ICoreActivity;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;

public interface IMealSelection extends ICoreActivity {

    void showMeals(MealSelectionResponse response);

}
