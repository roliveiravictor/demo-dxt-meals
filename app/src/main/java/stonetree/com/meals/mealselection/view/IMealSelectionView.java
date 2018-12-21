package stonetree.com.meals.mealselection.view;

import stonetree.com.meals.core.view.ICoreView;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;

public interface IMealSelectionView extends ICoreView {

    void showMeals(MealSelectionResponse response);

    void proceedToIngredientsSelection();

}
