package stonetree.com.meals.core.view;

import android.content.Context;

import stonetree.com.meals.core.model.Error;

public interface ICoreView {

    void hideLoading();
    void showLoading();
    void toast(Error error);

    Context getContext();

    Object loadBundle(String bundleName);
}
