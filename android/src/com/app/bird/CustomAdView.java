package com.app.bird;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class CustomAdView extends RelativeLayout {
    public CustomAdView(Context context) {
        super(context);
        initializeAdView();
    }

    AdView adView = null;
    RelativeLayout.LayoutParams adParams = null;

    public void initializeAdView() {
        setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        adParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        initAdView();
    }

    private void initAdView() {
        Log.d("MyAdView", "ad initialise");
        adView = new AdView(getContext());
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getContext().getString(R.string.admob_banner)); // Put in your secret key here
        requestAd();
        addView(adView);
    }

    public void requestAd() {
        if (adView == null) {
            initializeAdView();
        }
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void closeAd() {
        if (adView != null) {
            adView.pause();
            adView.destroy();
            adView = null;
            removeAllViews();
        }
    }

}
