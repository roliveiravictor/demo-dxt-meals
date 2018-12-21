package stonetree.com.meals.core.provider;

import android.graphics.Bitmap;

import stonetree.com.meals.core.model.Error;

public interface IImageProvider {

    void onSuccess(final Bitmap bitmap);
    void onFailure(final Error response);

}
