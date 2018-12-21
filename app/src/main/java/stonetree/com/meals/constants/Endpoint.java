package stonetree.com.meals.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static stonetree.com.meals.constants.Endpoint.MEAL_INGREDIENTS;
import static stonetree.com.meals.constants.Endpoint.MEALS;

@StringDef({MEALS, MEAL_INGREDIENTS})
@Retention(RetentionPolicy.SOURCE)
public @interface Endpoint {

    String MEALS = "/api/lanche";
    String ALL_INGREDIENTS = "/api/ingrediente";
    String MEAL_INGREDIENTS = "/api/ingrediente/de/{" + Query.MEAL_ID + "}";

}
