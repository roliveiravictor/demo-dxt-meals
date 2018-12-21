package stonetree.com.meals.ingredients.view;

import stonetree.com.meals.core.view.ICoreActivity;
import stonetree.com.meals.ingredients.model.IngredientsResponse;

public interface IIngredientsView extends ICoreActivity {

    void showIngredients(IngredientsResponse response);

}
