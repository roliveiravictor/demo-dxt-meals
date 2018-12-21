package stonetree.com.meals.customize.view;

import stonetree.com.meals.core.view.ICoreView;
import stonetree.com.meals.customize.model.CustomizeResponse;

public interface ICustomizeView extends ICoreView {

    void showAllIngredients(CustomizeResponse customizeResponse);

}
