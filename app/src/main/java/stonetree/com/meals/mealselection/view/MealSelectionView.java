package stonetree.com.meals.mealselection.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import stonetree.com.meals.R;
import stonetree.com.meals.core.view.CoreView;
import stonetree.com.meals.ingredients.view.IngredientsView;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;
import stonetree.com.meals.mealselection.presenter.MealSelectionPresenter;
import stonetree.com.meals.mealselection.view.adapter.MealSelectionAdapter;
import stonetree.com.meals.utils.IntentStarterUtils;

public class MealSelectionView extends CoreView implements IMealSelectionView {

    private RecyclerView mealsRecycler;

    private MealSelectionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal_selection);

        presenter = new MealSelectionPresenter(this);

        findComponents();
        loadComponents();
        setListeners();

        presenter.onCreate();
        presenter.getMeals();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void findComponents() {
        super.findComponents();
        mealsRecycler = findViewById(R.id.meals);
    }


    private void loadComponents() {
        final MealSelectionAdapter adapter = new MealSelectionAdapter(this, presenter, presenter.getMealsResponse());

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mealsRecycler.setLayoutManager(linearLayoutManager);
        mealsRecycler.setAdapter(adapter);
    }

    private void setListeners() {

    }

    @Override
    public void proceedToIngredientsSelection() {
        IntentStarterUtils.goFromTo(this, IngredientsView.class);
    }

    @Override
    public void showMeals(MealSelectionResponse response) {
        final MealSelectionAdapter adapter = (MealSelectionAdapter) mealsRecycler.getAdapter();
        adapter.update(response);

        hideLoading();
    }

}
