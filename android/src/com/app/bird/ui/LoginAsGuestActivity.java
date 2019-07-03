package com.app.bird.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.app.bird.R;

public class LoginAsGuestActivity extends AppCompatActivity {


    Button loginAsGuest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
        );

        setContentView(R.layout.activity_login_with_guest);
        init();
        Button guestLogin = (Button) findViewById(R.id.guestLogin);

        guestLogin.setOnClickListener(l -> {
           startGuestLoginActivity();
        });

    }

    private void startGuestLoginActivity() {

    }

    public void init() {
        loginAsGuest = findViewById(R.id.guestLogin);
    }

}
