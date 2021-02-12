package com.itsdf07.core.app.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itsdf07.core.app.R;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/12
 */
public class ADMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_main);
        findViewById(R.id.btn_ad_splash).setOnClickListener(v ->
                startActivity(new Intent(ADMainActivity.this, SplashActivity.class))
        );
        findViewById(R.id.btn_ad_unifiedinterstitial).setOnClickListener(v ->
                startActivity(new Intent(ADMainActivity.this, UnifiedInterstitialADActivity.class))
        );
    }
}
