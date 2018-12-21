package stonetree.com.meals.mealselection.provider;

import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;

public interface IMealSelectionProvider {

    void onSuccess(final MealSelectionResponse response);
    void onFailure(final Error response);

}
