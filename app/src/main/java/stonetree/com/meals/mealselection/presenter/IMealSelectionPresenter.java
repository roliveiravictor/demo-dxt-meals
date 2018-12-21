package stonetree.com.meals.mealselection.presenter;

import stonetree.com.meals.mealselection.model.Meal;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;
import stonetree.com.meals.core.provider.ImageDownloaderCallback;

interface IMealSelectionPresenter {

    void getMeals();

    void getMealImage(Meal meal, ImageDownloaderCallback imageDownloaderCallback);

    void onCreate();

    MealSelectionResponse getMealsResponse();

    void onResume();

}
