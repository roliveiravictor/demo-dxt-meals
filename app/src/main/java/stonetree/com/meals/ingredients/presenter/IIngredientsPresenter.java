package stonetree.com.meals.ingredients.presenter;

import stonetree.com.meals.core.provider.ImageDownloaderCallback;
import stonetree.com.meals.ingredients.model.Ingredient;
import stonetree.com.meals.ingredients.model.IngredientsResponse;

public interface IIngredientsPresenter {

    void onCreate();

    void onResume();

    void getIngredients();

    void getIngredientImage(Ingredient ingredient, ImageDownloaderCallback callback);

    IngredientsResponse getIngredientsResponse();

}
