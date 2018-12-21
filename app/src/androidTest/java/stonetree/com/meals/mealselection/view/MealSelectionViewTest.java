package stonetree.com.meals.mealselection.view;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import stonetree.com.meals.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MealSelectionViewTest {

    @Rule
    public ActivityTestRule<MealSelectionView> mActivityRule
            = new ActivityTestRule<>(MealSelectionView.class);

    @Before
    public void setup() {

    }

    @Test
    public void testMealSelectionRecycler() {
        onView(withId(R.id.meals)).check(matches((isDisplayed())));
    }

}
