package stonetree.com.meals.customize.provider;

import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.customize.model.CustomizeResponse;

public interface ICustomizeProvider {

    void onSuccess(CustomizeResponse customizeResponse);
    void onFailure(Error error);

}
