package stonetree.com.meals.core.provider;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import stonetree.com.meals.constants.Endpoint;
import stonetree.com.meals.constants.Query;
import stonetree.com.meals.ingredients.model.Ingredient;
import stonetree.com.meals.mealselection.model.Meal;

public interface ICoreProvider {

    @GET(Endpoint.MEALS)
    Call<List<Meal>> getMeals();

    @GET()
    Call<ResponseBody> getMealImage(@Url String url);

    @GET(Endpoint.MEAL_INGREDIENTS)
    Call<List<Ingredient>> getMealIngredients(@Path(Query.MEAL_ID) String mealId);

    @GET(Endpoint.ALL_INGREDIENTS)
    Call<List<Ingredient>> getAllIngredients();
}
