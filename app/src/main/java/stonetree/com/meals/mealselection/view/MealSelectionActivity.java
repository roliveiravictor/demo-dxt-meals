package stonetree.com.meals.mealselection.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import stonetree.com.meals.R;
import stonetree.com.meals.constants.Constants;
import stonetree.com.meals.core.view.CoreActivity;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;
import stonetree.com.meals.mealselection.presenter.MealSelectionPresenter;
import stonetree.com.meals.mealselection.view.adapter.MealSelectionAdapter;
import stonetree.com.meals.utils.IntentStarterUtils;

public class MealSelectionActivity extends CoreActivity implements IMealSelection {

    private RecyclerView mealsRecycler;

    private Button customize;
    private Button checkout;

    private MealSelectionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

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
        customize = findViewById(R.id.customize);
        checkout = findViewById(R.id.checkout);
    }


    private void loadComponents() {
        final MealSelectionAdapter adapter = new MealSelectionAdapter(this, presenter, presenter.getMealsResponse());

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mealsRecycler.setLayoutManager(linearLayoutManager);
        mealsRecycler.setAdapter(adapter);
    }

    private void setListeners() {
        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void proceedToQuotasSelection(MealSelectionResponse response) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.MEALS_SELECTION_RESPONSE, response);

        IntentStarterUtils.goFromWithExtraBundleTo(this, MealSelectionActivity.class, bundle);
    }

    @Override
    public void showMeals(MealSelectionResponse response) {
        final MealSelectionAdapter adapter = (MealSelectionAdapter) mealsRecycler.getAdapter();
        adapter.update(response);

        hideLoading();
    }

}
