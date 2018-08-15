package com.example.mikle.daymanager.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.entity.dto.LoginDto;
import com.example.mikle.daymanager.presenter.AuthActivityPresenter;
import com.example.mikle.daymanager.presenter.impl.LoginPresenter;
import com.example.mikle.daymanager.presenter.impl.PresenterFactory;

import java.util.ArrayList;
import java.util.List;

public class AuthActivity extends AppCompatActivity {

    private AuthActivityPresenter presenter = PresenterFactory.getLoginPresenter();{
        presenter.setActivity(this);
    }
    private static String TAG = "AuthActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initViews();
        Log.d(TAG, "onCreate: ");
    }


    private void initViews() {
        Button loginB = findViewById(R.id.loginB);
        Button registrationB = findViewById(R.id.registrationB);

        loginB.setOnClickListener(presenter.getLoginBListener());
        registrationB.setOnClickListener(presenter.getRegistrationBListener());
    }

    public LoginDto getETData(){
        LoginDto data = new LoginDto();
        EditText mailET = findViewById(R.id.mailET);
        EditText passwordET = findViewById(R.id.passwordET);
        EditText hostET = findViewById(R.id.hostET);
        data.setMail(mailET.getText().toString());
        data.setPassword(passwordET.getText().toString());
        data.setHost(hostET.getText().toString());
        return data;
    }

//    private void setFragment(int fragment_login, LoginPresenter loginPresenter) {
//        if (presenter!=null){
//            presenter.setActivity(null);
//        }
//        presenter = loginPresenter;
//        presenter.setActivity(this);
//        View view = getLayoutInflater().inflate(fragment_login, null);
//        container.removeAllViews();
//        container.addView(view);
//        initViews();
//    }
}
