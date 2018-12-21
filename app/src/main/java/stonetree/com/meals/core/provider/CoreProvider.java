package stonetree.com.meals.core.provider;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import stonetree.com.meals.constants.Constants;

public class CoreProvider {

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.LOCAL_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
