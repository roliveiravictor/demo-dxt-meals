package stonetree.com.meals.mealselection.presenter;

import android.graphics.Bitmap;

import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.core.provider.IImageProvider;
import stonetree.com.meals.core.provider.ImageDownloaderCallback;
import stonetree.com.meals.core.provider.ImageProvider;
import stonetree.com.meals.mealselection.model.Meal;
import stonetree.com.meals.mealselection.model.MealSelectionModel;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;
import stonetree.com.meals.mealselection.provider.IMealSelectionProvider;
import stonetree.com.meals.mealselection.provider.MealSelectionProvider;
import stonetree.com.meals.mealselection.view.IMealSelection;

public class MealSelectionPresenter implements IMealSelectionPresenter {

    private MealSelectionModel model = new MealSelectionModel();

    private IMealSelection view;

    public MealSelectionPresenter(IMealSelection view) {
        this.view = view;
    }

    @Override
    public void getMeals() {
        view.showLoading();
        new MealSelectionProvider(new IMealSelectionProvider() {
            @Override
            public void onSuccess(MealSelectionResponse response) {
                model.setMealSelectionResponse(response);
                view.showMeals(response);
            }

            @Override
            public void onFailure(Error response) {
                view.toast(response);
                view.hideLoading();
            }
        }).getMeals();
    }

    @Override
    public void getMealImage(final Meal meal, final ImageDownloaderCallback imageDownloaderCallback) {
        new ImageProvider(new IImageProvider() {
            @Override
            public void onSuccess(Bitmap response) {
                meal.setBitmap(response);
                imageDownloaderCallback.onDownloadedImage(meal);
            }

            @Override
            public void onFailure(Error response) {
            }
        }).getImage(meal.getImageUrl());
    }

    @Override
    public void onCreate() {

    }

    @Override
    public MealSelectionResponse getMealsResponse() {
        return model.getMealSelectionResponse();
    }

    @Override
    public void onResume() {

    }

}
