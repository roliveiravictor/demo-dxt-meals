package stonetree.com.meals.core.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import stonetree.com.meals.R;
import stonetree.com.meals.core.model.Error;
import stonetree.com.meals.utils.ToastUtils;

public class CoreActivity extends AppCompatActivity implements ICoreActivity {

    private SpinKitView spinKitView;

    private LinearLayout rootLayout;

    public void findComponents() {
        spinKitView = findViewById(R.id.spin_kit);
        rootLayout = findViewById(R.id.rootLayout);
    }

    @Override
    public void showLoading() {
        rootLayout.setVisibility(View.GONE);
        spinKitView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rootLayout.setVisibility(View.VISIBLE);
        spinKitView.setVisibility(View.GONE);
    }

    @Override
    public void toast(Error error) {
        ToastUtils.show(this, error.getToastMessage(), Toast.LENGTH_LONG);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public Object loadBundle(String bundleName) {
        final Bundle bundle = this.getIntent().getExtras();
        if (bundle != null)
            return bundle.getSerializable(bundleName);
        else
            return bundle;
    }

}
