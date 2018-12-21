package stonetree.com.meals.customize.presenter;

import stonetree.com.meals.core.provider.ImageDownloaderCallback;
import stonetree.com.meals.customize.model.CustomizeResponse;
import stonetree.com.meals.ingredients.model.Ingredient;

public interface ICustomizePresenter {

    void getAllIngredients();

    void getIngredientImage(final Ingredient ingredient, final ImageDownloaderCallback imageDownloaderCallback);

    CustomizeResponse getCustomizeResponse();

    void onCreate();

    void onResume();

    int countSingleIngredient(int ingredientId);
}
