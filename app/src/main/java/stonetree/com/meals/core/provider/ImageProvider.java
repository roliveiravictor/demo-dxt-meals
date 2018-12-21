package stonetree.com.meals.core.provider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;
import stonetree.com.meals.core.model.Error;

public class ImageProvider {

    private IImageProvider callback;

    public ImageProvider(IImageProvider callback) {
        this.callback = callback;
    }

    public void getImage(@Url String url) {
        final ICoreProvider provider = CoreProvider.getRetrofit().create(ICoreProvider.class);
        final Call<ResponseBody> call = provider.getMealImage(url);
        call.enqueue(getMealImageCallback());
    }

    public Callback<ResponseBody> getMealImageCallback() {
        return new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                byte[] bytes = new byte[0];
                try {
                    bytes = response.body().bytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                callback.onSuccess(bitmap);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                final Error error = new Error();
                error.setup(throwable);

                callback.onFailure(error);
            }
        };
    }

}
