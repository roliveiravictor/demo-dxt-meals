package stonetree.com.meals.mealselection.provider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.core.provider.CoreProvider;
import stonetree.com.meals.core.provider.ICoreProvider;
import stonetree.com.meals.mealselection.model.Meal;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;

public class MealSelectionProvider {

    private IMealSelectionProvider callback;

    public MealSelectionProvider(IMealSelectionProvider callback) {
        this.callback = callback;
    }

    public void getMeals() {
        final ICoreProvider provider = CoreProvider.getRetrofit().create(ICoreProvider.class);
        final Call<List<Meal>> call = provider.getMeals();
        call.enqueue(getMealsCallback());
    }

    private Callback<List<Meal>> getMealsCallback() {
        return new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                final List<Meal> bodyResponse = response.body();
                final MealSelectionResponse mealSelectionResponse = new MealSelectionResponse();

                mealSelectionResponse.setMeals(bodyResponse);

                callback.onSuccess(mealSelectionResponse);
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable throwable) {
                final Error error = new Error();
                error.setup(throwable);

                callback.onFailure(error);
            }
        };
    }

}
