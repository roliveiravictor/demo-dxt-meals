package stonetree.com.meals.ingredients.presenter;

import android.graphics.Bitmap;

import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.core.provider.IImageProvider;
import stonetree.com.meals.core.provider.ImageDownloaderCallback;
import stonetree.com.meals.core.provider.ImageProvider;
import stonetree.com.meals.ingredients.model.Ingredient;
import stonetree.com.meals.ingredients.model.IngredientsModel;
import stonetree.com.meals.ingredients.model.IngredientsResponse;
import stonetree.com.meals.ingredients.provider.IIngredientsProvider;
import stonetree.com.meals.ingredients.provider.IngredientsProvider;
import stonetree.com.meals.ingredients.view.IIngredientsView;

public class IngredientsPresenter implements IIngredientsPresenter {

    private IngredientsModel model = new IngredientsModel();

    private IIngredientsView view;

    public IngredientsPresenter(IIngredientsView view) {
        this.view = view;
    }

    @Override
    public void getIngredients() {
        view.showLoading();
        new IngredientsProvider(new IIngredientsProvider() {
            @Override
            public void onSuccess(IngredientsResponse response) {
                model.setIngredientsResponse(response);
                view.showIngredients(response);
            }

            @Override
            public void onFailure(Error response) {
                view.toast(response);
                view.hideLoading();
            }
        }).getMeals();
    }

    @Override
    public void getIngredientImage(final Ingredient ingredient, final ImageDownloaderCallback imageDownloaderCallback) {
        new ImageProvider(new IImageProvider() {
            @Override
            public void onSuccess(Bitmap response) {
                ingredient.setBitmap(response);
                imageDownloaderCallback.onDownloadedImage(ingredient);
            }

            @Override
            public void onFailure(Error response) {
            }
        }).getImage(ingredient.getImageUrl());
    }

    @Override
    public void onCreate() {

    }

    @Override
    public IngredientsResponse getIngredientsResponse() {
        return model.getIngredientsResponse();
    }

    @Override
    public void onResume() {

    }

}
