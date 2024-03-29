package stonetree.com.meals.ingredients.provider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.core.provider.CoreProvider;
import stonetree.com.meals.core.provider.ICoreProvider;
import stonetree.com.meals.ingredients.model.Ingredient;
import stonetree.com.meals.ingredients.model.IngredientsResponse;
import stonetree.com.meals.core.model.Session;

public class IngredientsProvider {

    private IIngredientsProvider callback;

    public IngredientsProvider(IIngredientsProvider callback) {
        this.callback = callback;
    }

    public void getMeals() {
        final ICoreProvider provider = CoreProvider.getRetrofit().create(ICoreProvider.class);

        final String mealId = Session.getInstance().getCart().getMeal().getId();

        final Call<List<Ingredient>> call = provider.getMealIngredients(mealId);
        call.enqueue(getMealIngredientsCallback());
    }

    private Callback<List<Ingredient>> getMealIngredientsCallback() {
        return new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                final List<Ingredient> bodyResponse = response.body();
                final IngredientsResponse ingredientsResponse = new IngredientsResponse();

                ingredientsResponse.setMealIngredients(bodyResponse);

                callback.onSuccess(ingredientsResponse);
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable throwable) {
                final Error error = new Error();
                error.setup(throwable);

                callback.onFailure(error);
            }
        };
    }

}
