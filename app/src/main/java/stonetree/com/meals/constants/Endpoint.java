package stonetree.com.meals.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static stonetree.com.meals.constants.Endpoint.MEALS;

@StringDef({MEALS})
@Retention(RetentionPolicy.SOURCE)
public @interface Endpoint {

    String MEALS = "/api/lanche";

}
