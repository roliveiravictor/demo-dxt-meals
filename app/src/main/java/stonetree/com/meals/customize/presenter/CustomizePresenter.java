package stonetree.com.meals.customize.presenter;

import android.graphics.Bitmap;

import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.core.provider.IImageProvider;
import stonetree.com.meals.core.provider.ImageDownloaderCallback;
import stonetree.com.meals.core.provider.ImageProvider;
import stonetree.com.meals.customize.model.CustomizeModel;
import stonetree.com.meals.customize.model.CustomizeResponse;
import stonetree.com.meals.customize.provider.CustomizeProvider;
import stonetree.com.meals.customize.provider.ICustomizeProvider;
import stonetree.com.meals.customize.view.ICustomizeView;
import stonetree.com.meals.ingredients.model.Ingredient;
import stonetree.com.meals.core.model.Session;

public class CustomizePresenter implements ICustomizePresenter {

    private CustomizeModel model = new CustomizeModel();

    private ICustomizeView view;

    public CustomizePresenter(ICustomizeView view) {
        this.view = view;
    }

    @Override
    public void getAllIngredients() {
        view.showLoading();
        new CustomizeProvider(new ICustomizeProvider() {
            @Override
            public void onSuccess(CustomizeResponse response) {
                model.setCustomizeResponse(response);
                view.showAllIngredients(response);
            }

            @Override
            public void onFailure(Error response) {
                view.toast(response);
                view.hideLoading();
            }
        }).getAllIngredients();
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
    public CustomizeResponse getCustomizeResponse() {
        return model.getCustomizeResponse();
    }

    @Override
    public void onResume() {

    }

    @Override
    public int countSingleIngredient(int ingredientId) {
        int counter = 0;

        for (int id : Session.getInstance().getCart().getMeal().getCustomIngredients()) {
            if (id == ingredientId)
                counter++;
        }

        return counter;
    }

}
