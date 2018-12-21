package stonetree.com.meals.ingredients.provider;

import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.ingredients.model.IngredientsResponse;

public interface IIngredientsProvider {

    void onSuccess(final IngredientsResponse response);
    void onFailure(final Error response);

}
