package com.app.bird.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.app.bird.R;

public class AboutFlightlessBirdActivity extends Activity {


    private Button communicateWithUs;
    private Button rateApp;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_flightless_bird);
        init();
        communicateWithUs.setOnClickListener(l -> {
            String subject = "Communicate With Flightless Bird";
            Intent feedBackIntent = new Intent();
            feedBackIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            feedBackIntent.setData(Uri.parse(""));
            startActivity(feedBackIntent);
        });
        rateApp.setOnClickListener(l -> {
            Intent feedBackIntent = new Intent(Intent.ACTION_VIEW);
            feedBackIntent.setData(Uri.parse(""));
            startActivity(feedBackIntent);
        });
        back.setOnClickListener(l -> {
            finish();
        });

    }

    private void screenConfigurations() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
        );
    }

    public void init() {
        communicateWithUs = findViewById(R.id.communicateWithUs);
        rateApp = findViewById(R.id.rateUs);
        back = findViewById(R.id.back);
    }

}
