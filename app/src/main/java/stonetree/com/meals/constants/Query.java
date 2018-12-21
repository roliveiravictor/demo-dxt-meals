package stonetree.com.meals.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static stonetree.com.meals.constants.Query.MEAL_ID;

@StringDef({MEAL_ID})
@Retention(RetentionPolicy.SOURCE)
public @interface Query {

    String MEAL_ID = Constants.QUERY_SELECTOR + "meal_id";

}
