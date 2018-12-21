package stonetree.com.meals.checkout.view;

import android.os.Bundle;
import android.widget.TextView;

import stonetree.com.meals.R;
import stonetree.com.meals.core.view.CoreView;
import stonetree.com.meals.core.model.Session;

public class CheckoutView extends CoreView implements ICheckoutView {

    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkout);

        findComponents();
        loadComponents();
        setListeners();
        showTotal();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public void findComponents() {
        super.findComponents();
        total = findViewById(R.id.total);
    }


    private void loadComponents() {

    }

    private void setListeners() {
    }

    @Override
    public void showTotal() {
        final float price = Session.getInstance().getCart().getPrice();
        total.setText(String.valueOf(price));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Session.getInstance().getCart().refreshPrice();
    }

}
