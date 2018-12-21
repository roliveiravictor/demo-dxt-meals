package stonetree.com.meals.ingredients.view;

import stonetree.com.meals.core.view.ICoreView;
import stonetree.com.meals.ingredients.model.IngredientsResponse;

public interface IIngredientsView extends ICoreView {

    void showMealIngredients(IngredientsResponse response);

}
