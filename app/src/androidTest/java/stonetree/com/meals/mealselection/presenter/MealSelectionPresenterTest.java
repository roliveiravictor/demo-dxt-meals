package stonetree.com.meals.mealselection.presenter;


import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import stonetree.com.meals.core.CoreProviderTest;
import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;
import stonetree.com.meals.mealselection.view.IMealSelectionView;
import stonetree.com.meals.mealselection.view.MealSelectionView;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MealSelectionPresenterTest extends CoreProviderTest {

    @Rule
    public ActivityTestRule<MealSelectionView> mActivityRule
            = new ActivityTestRule<>(MealSelectionView.class);

    private MealSelectionPresenter presenter = new MealSelectionPresenter(new IMealSelectionView() {
        @Override
        public void showMeals(MealSelectionResponse response) {
            executeCoreSuccess(response);
        }

        @Override
        public void proceedToIngredientsSelection() {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void showLoading() {

        }

        @Override
        public void toast(Error error) {
            executeCoreFailure(error);
        }

        @Override
        public Context getContext() {
            return mActivityRule.getActivity().getContext();
        }

        @Override
        public Object loadBundle(String bundleName) {
            return new Object();
        }
    });

    @Before
    public void setup() {

    }

    @Test
    public void testGetMealsProvider() {
        presenter.getMeals();
        waitProviderResponse();
    }

}
