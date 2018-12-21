package stonetree.com.meals.customize.provider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.core.provider.CoreProvider;
import stonetree.com.meals.core.provider.ICoreProvider;
import stonetree.com.meals.customize.model.CustomizeResponse;
import stonetree.com.meals.ingredients.model.Ingredient;

public class CustomizeProvider {

    private ICustomizeProvider callback;

    public CustomizeProvider(ICustomizeProvider callback) {
        this.callback = callback;
    }

    public void getAllIngredients() {
        final ICoreProvider provider = CoreProvider.getRetrofit().create(ICoreProvider.class);

        final Call<List<Ingredient>> call = provider.getAllIngredients();
        call.enqueue(getAllIngredientsCallback());
    }

    private Callback<List<Ingredient>> getAllIngredientsCallback() {
        return new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                final List<Ingredient> bodyResponse = response.body();
                final CustomizeResponse customizeResponse = new CustomizeResponse();

                customizeResponse.setAllIngredients(bodyResponse);

                callback.onSuccess(customizeResponse);
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
