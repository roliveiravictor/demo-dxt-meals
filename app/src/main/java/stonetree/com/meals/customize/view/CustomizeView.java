package stonetree.com.meals.customize.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import stonetree.com.meals.R;
import stonetree.com.meals.checkout.view.CheckoutView;
import stonetree.com.meals.core.model.Session;
import stonetree.com.meals.core.view.CoreView;
import stonetree.com.meals.customize.model.CustomizeResponse;
import stonetree.com.meals.customize.presenter.CustomizePresenter;
import stonetree.com.meals.customize.view.adapter.CustomizeAdapter;
import stonetree.com.meals.utils.IntentStarterUtils;

public class CustomizeView extends CoreView implements ICustomizeView {

    private RecyclerView customizeRecycler;

    private Button checkout;

    private CustomizePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customize);

        presenter = new CustomizePresenter(this);

        findComponents();
        loadComponents();
        setListeners();

        presenter.onCreate();
        presenter.getAllIngredients();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void findComponents() {
        super.findComponents();
        customizeRecycler = findViewById(R.id.ingredients);
        checkout = findViewById(R.id.checkout);
    }


    private void loadComponents() {
        final CustomizeAdapter adapter = new CustomizeAdapter(this, presenter, presenter.getCustomizeResponse());

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        customizeRecycler.setLayoutManager(linearLayoutManager);
        customizeRecycler.setAdapter(adapter);
    }

    private void setListeners() {
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentStarterUtils.goFromTo(CustomizeView.this, CheckoutView.class);
            }
        });
    }

    @Override
    public void showAllIngredients(CustomizeResponse response) {
        final CustomizeAdapter adapter = (CustomizeAdapter) customizeRecycler.getAdapter();
        adapter.update(response);

        hideLoading();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Session.getInstance().purge();
    }
}
