package stonetree.com.meals.mealselection.view;

import stonetree.com.meals.core.view.ICoreActivity;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;

public interface IMealSelectionView extends ICoreActivity {

    void showMeals(MealSelectionResponse response);

    void proceedToIngredientsSelection();

}
