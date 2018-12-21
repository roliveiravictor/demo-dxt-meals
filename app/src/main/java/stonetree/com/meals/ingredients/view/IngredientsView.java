package stonetree.com.meals.ingredients.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import stonetree.com.meals.R;
import stonetree.com.meals.checkout.view.CheckoutView;
import stonetree.com.meals.core.view.CoreView;
import stonetree.com.meals.customize.view.CustomizeView;
import stonetree.com.meals.ingredients.model.IngredientsResponse;
import stonetree.com.meals.ingredients.presenter.IngredientsPresenter;
import stonetree.com.meals.ingredients.view.adapter.IngredientsAdapter;
import stonetree.com.meals.utils.IntentStarterUtils;

public class IngredientsView extends CoreView implements IIngredientsView {

    private RecyclerView ingredientsRecycler;

    private Button customize;
    private Button checkout;

    private IngredientsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients);

        presenter = new IngredientsPresenter(this);

        findComponents();
        loadComponents();
        setListeners();

        presenter.onCreate();
        presenter.getMealIngredients();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void findComponents() {
        super.findComponents();
        ingredientsRecycler = findViewById(R.id.ingredients);
        customize = findViewById(R.id.customize);
        checkout = findViewById(R.id.checkout);
    }


    private void loadComponents() {
        final IngredientsAdapter adapter = new IngredientsAdapter(this, presenter, presenter.getIngredientsResponse());

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        ingredientsRecycler.setLayoutManager(linearLayoutManager);
        ingredientsRecycler.setAdapter(adapter);
    }

    private void setListeners() {
        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentStarterUtils.goFromTo(IngredientsView.this, CustomizeView.class);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentStarterUtils.goFromTo(IngredientsView.this, CheckoutView.class);
            }
        });
    }

    @Override
    public void showMealIngredients(IngredientsResponse response) {
        final IngredientsAdapter adapter = (IngredientsAdapter) ingredientsRecycler.getAdapter();
        adapter.update(response);

        hideLoading();
    }

}
