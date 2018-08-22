package com.example.mikle.daymanager.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.presenter.MainActivityPresenter;
import com.example.mikle.daymanager.presenter.Presenter;
import com.example.mikle.daymanager.presenter.impl.PresenterFactory;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout container;
    private MainActivityPresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(R.layout.fragment_plan, PresenterFactory.getDayPlanPresenter());
                    return true;
                case R.id.navigation_dashboard:
                    changeFragment(R.layout.fragment_cash, PresenterFactory.getCashFlowPresenter());
                    return true;
                case R.id.navigation_notifications:
                    changeFragment(R.layout.fragment_stat, PresenterFactory.getStatisticsPresenter());
                    return true;
            }
            return false;
        }
    };

    @Override
    public Dialog onCreateDialog(int id) {
        if (id == MainActivityPresenter.DATE_DIALOG_ID) {
            return presenter.getDateDialog();
        } else {
            return super.onCreateDialog(id);
        }
    }


    private void changeFragment(int fragmentId, MainActivityPresenter presenter){
        this.presenter = presenter;
        presenter.setActivity(this);
        View view = getLayoutInflater().inflate(fragmentId, null);
        container.removeAllViews();
        container.addView(view);
        presenter.onInit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.relativeContainer);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        changeFragment(R.layout.fragment_plan, PresenterFactory.getDayPlanPresenter());
    }

}
