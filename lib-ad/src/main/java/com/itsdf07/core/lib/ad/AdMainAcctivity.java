package com.itsdf07.core.lib.ad;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itsdf07.core.lib.ad.csj.TTAdManagerHolder;
import com.itsdf07.core.lib.alog.ALog;
import com.qq.e.comm.managers.GDTADManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/3/23
 */
public class AdMainAcctivity extends AppCompatActivity implements View.OnClickListener {
    private final String YLH_POSID_1 = "2041160548181992";//  半屏插屏横版（仅图片）-1
    private final String YLH_POSID_2 = "5051360598887846";// 半屏插屏横版（仅图片）-2
    private final String YLH_POSID_3 = "4081462558381939";//  半屏插屏横版（仅图片）-3
    private final String YLH_POSID_4 = "9081661518595100";//  半屏插屏横版（仅图片）-4
    private final String YLH_POSID_5 = "4031369599997350";//  半屏插屏横版（仅图片）-5
    private final String YLH_POSID_6 = "8021665549691311";//  半屏插屏横版（仅图片）-6
    FrameLayout mAdContainer;

    Spinner sdks;
    TextView sdkVersion;

    EditText inputAdId;
    Button btnSplash;
    Button btnFlow;
    Button btnInterstitial;

    AdPaltformFactory adPaltformFactory;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ad);
        inputAdId = findViewById(R.id.input_adid);
        btnSplash = findViewById(R.id.btn_splash);
        btnFlow = findViewById(R.id.btn_flow);
        btnInterstitial = findViewById(R.id.btn_interstitial);
        btnSplash.setOnClickListener(this);
        btnFlow.setOnClickListener(this);
        btnInterstitial.setOnClickListener(this);

        mAdContainer = findViewById(R.id.ad_container);

        sdks = findViewById(R.id.sp_sdk);
        sdks.setSelection(0);
        sdkVersion = findViewById(R.id.sdk_version);
        sdkVersion.setText(sdkVersionYLH());
        sdks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sdkName = "";
                switch (position) {
                    case 0: //优亮汇
                        sdkName = "优亮汇";
                        sdkVersion.setText(sdkVersionYLH());
                        btnSplash.setEnabled(false);
                        btnFlow.setEnabled(false);
                        btnInterstitial.setEnabled(true);
                        break;
                    case 1: //穿山甲
                        sdkName = "穿山甲";
                        sdkVersion.setText(sdkVersionCSJ());
                        btnSplash.setEnabled(true);
                        btnFlow.setEnabled(true);
                        btnInterstitial.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //权限校验,android O 以后,Android 10 以前推荐APP提前申请好权限再加载广告
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 29) {
            checkAndRequestPermission();
        }

        adPaltformFactory = new AdPaltformFactory();
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }


        // 缺少权限，进行申请
        if (lackedPermission.size() > 0) {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }


    private void initADGDT() {
        ALog.i("initADGDT ...");
        GDTADManager.getInstance().initWith(this, "1111567150");
    }

    private String sdkVersionYLH() {
        return "4.330.1200";
    }

    private String sdkVersionCSJ() {
        return "3.4.5.4";
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String adId = inputAdId.getText().toString();
        if (id == R.id.btn_splash) {
            if (sdks.getSelectedItemPosition() == 0) {

            } else if (sdks.getSelectedItemPosition() == 1) {
//                AdAdvance adCsj = adPaltformFactory.createAdPlatform(1 << 1);
//                adCsj.loadSplashAdNativeExpress(this, mAdContainer, TextUtils.isEmpty(adId) ? "887450728" : adId);
//                startActivity(new Intent(this, SplashActivity.class));
            }
        } else if (id == R.id.btn_flow) {
            if (sdks.getSelectedItemPosition() == 0) {

            } else if (sdks.getSelectedItemPosition() == 1) {
                AdAdvance adCsj = adPaltformFactory.createAdPlatform(1 << 1);
                adCsj.loadFlowAdNativeExpress(this, mAdContainer, TextUtils.isEmpty(adId) ? "945912187" : adId);
            }
        } else if (id == R.id.btn_interstitial) {
            if (sdks.getSelectedItemPosition() == 0) {
                AdAdvance adYlh = adPaltformFactory.createAdPlatform(1 << 0);
                adYlh.loadInterstitialNativeExpress(this, TextUtils.isEmpty(adId) ? "6051369783915899" : adId);
            } else if (sdks.getSelectedItemPosition() == 1) {
                AdAdvance adCsj = adPaltformFactory.createAdPlatform(1 << 1);
                adCsj.loadInterstitialNativeExpress(this, TextUtils.isEmpty(adId) ? "945896865" : adId);
            }
        }
    }


    /**
     * 随机生成数值
     * [3,6]
     *
     * @return
     */
    public int makeRandomMin(int min, int max) {
        Random random = new Random();
        int time2StartAd = random.nextInt(max - min + 1) + min;
        return time2StartAd;
    }
}
