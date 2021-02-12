package com.itsdf07.core.app.ui.activity.ad;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itsdf07.core.app.R;
import com.itsdf07.core.lib.alog.ALog;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.ads.interstitial2.UnifiedInterstitialMediaListener;
import com.qq.e.comm.constants.AdPatternType;
import com.qq.e.comm.util.AdError;

import java.util.Locale;

import static com.itsdf07.core.app.ui.activity.ad.ADGloadConfig.VIDEO_DURATION_SETTING_MAX;
import static com.itsdf07.core.app.ui.activity.ad.ADGloadConfig.VIDEO_DURATION_SETTING_MIN;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/2/12
 */
public class UnifiedInterstitialADActivity extends AppCompatActivity implements UnifiedInterstitialADListener,
        View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        UnifiedInterstitialMediaListener,
        CompoundButton.OnCheckedChangeListener {
    private final String posId = "1011061282915030";
    private UnifiedInterstitialAD iad;
    private String currentPosId;

    private CheckBox btnNoOption;
    private CheckBox btnMute;
    private CheckBox btnDetailMute;
    private Spinner networkSpinner;

    private EditText posIdEdt;

    private Spinner spinner;
    private PosIdArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unified_interstitial_ad);
//        findViewById(R.id.loadIAD).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (iad != null) {
//                    iad.close();
//                    iad.destroy();
//                }
//                iad = new UnifiedInterstitialAD(UnifiedInterstitialADActivity.this, posId, UnifiedInterstitialADActivity.this);
//            }
//        });

        spinner = findViewById(R.id.id_spinner);
        arrayAdapter = new PosIdArrayAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.unified_interstitial));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        posIdEdt = findViewById(R.id.posId);
        posIdEdt.setText(posId);
        this.findViewById(R.id.loadIAD).setOnClickListener(this);
        this.findViewById(R.id.showIAD).setOnClickListener(this);
        this.findViewById(R.id.showIADAsPPW).setOnClickListener(this);
        this.findViewById(R.id.closeIAD).setOnClickListener(this);
        this.findViewById(R.id.isAdValid).setOnClickListener(this);
        btnNoOption = findViewById(R.id.cb_none_video_option);
        btnNoOption.setOnCheckedChangeListener(this);
        btnMute = findViewById(R.id.btn_mute);
        btnDetailMute = findViewById(R.id.btn_detail_mute);
        networkSpinner = findViewById(R.id.spinner_network);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iad != null) {
            iad.destroy();
        }
    }

    private UnifiedInterstitialAD getIAD() {
        if (this.iad != null) {
            iad.close();
            iad.destroy();
        }
        String posId = posIdEdt.getText().toString();
        if (!posId.equals(currentPosId) || iad == null) {
            iad = new UnifiedInterstitialAD(this, posId, this);
            currentPosId = posId;
        }
        return iad;
    }


    private void showAD() {
        if (iad != null && iad.isValid()) {
            iad.show();
        } else {
            Toast.makeText(this, "请加载广告后再进行展示 ！ ", Toast.LENGTH_LONG).show();
        }
    }

    private void showAsPopup() {
        if (iad != null && iad.isValid()) {
            iad.showAsPopupWindow();
        } else {
            Toast.makeText(this, "请加载广告后再进行展示 ！ ", Toast.LENGTH_LONG).show();
        }
    }

    private void close() {
        if (iad != null) {
            iad.close();
        } else {
            Toast.makeText(this, "广告尚未加载 ！ ", Toast.LENGTH_LONG).show();
        }
    }

    private void isAdValid() {
        if (iad == null) {
            Toast.makeText(this, "请加载广告后再进行校验 ！ ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "广告" + (iad.isValid() ? "有效" : "无效"), Toast.LENGTH_LONG).show();
        }
    }

    private void setVideoOption() {
        VideoOption.Builder builder = new VideoOption.Builder();
        VideoOption option = builder.build();
        if (!btnNoOption.isChecked()) {
            option = builder.setAutoPlayMuted(btnMute.isChecked())
                    .setAutoPlayPolicy(networkSpinner.getSelectedItemPosition())
                    .setDetailPageMuted(btnDetailMute.isChecked())
                    .build();
        }
        iad.setVideoOption(option);
        iad.setMinVideoDuration(getMinVideoDuration());
        iad.setMaxVideoDuration(getMaxVideoDuration());

    }

    private int getMinVideoDuration() {
        if (((CheckBox) findViewById(R.id.cbMinVideoDuration)).isChecked()) {
            try {
                int rst =
                        Integer.parseInt(((EditText) findViewById(R.id.etMinVideoDuration)).getText().toString());
                if (rst > 0) {
                    return rst;
                } else {
                    Toast.makeText(getApplicationContext(), "最小视频时长输入须大于0!", Toast.LENGTH_LONG).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "最小视频时长输入不是整数!", Toast.LENGTH_LONG).show();
            }
        }
        return 0;
    }

    private int getMaxVideoDuration() {
        if (((CheckBox) findViewById(R.id.cbMaxVideoDuration)).isChecked()) {
            try {
                int rst = Integer.parseInt(((EditText) findViewById(R.id.etMaxVideoDuration)).getText()
                        .toString());
                if (rst >= VIDEO_DURATION_SETTING_MIN && rst <= VIDEO_DURATION_SETTING_MAX) {
                    return rst;
                } else {
                    String msg = String.format("最大视频时长输入不在有效区间[%d,%d]内",
                            VIDEO_DURATION_SETTING_MIN, VIDEO_DURATION_SETTING_MAX);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "最大视频时长输入不是整数!", Toast.LENGTH_LONG).show();
            }
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadIAD:
                iad = getIAD();
                setVideoOption();
                iad.loadAD();
                break;
            case R.id.showIAD:
                showAD();
                break;
            case R.id.showIADAsPPW:
                showAsPopup();
                break;
            case R.id.closeIAD:
                close();
                break;
            case R.id.isAdValid:
                isAdValid();
                break;
            default:
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == btnNoOption) {
            btnMute.setEnabled(!isChecked);
            btnDetailMute.setEnabled(!isChecked);
            networkSpinner.setEnabled(!isChecked);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        arrayAdapter.setSelectedPos(position);
        posIdEdt.setText(getResources().getStringArray(R.array.unified_interstitial_value)[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //---------------------------------------------UnifiedInterstitialADListener

    /**
     * 插屏半屏广告加载完毕，此回调后才可以调用 show 方法
     */
    @Override
    public void onADReceive() {
        Toast.makeText(this, "广告加载成功 ！ ", Toast.LENGTH_LONG).show();
        // onADReceive之后才能调用getAdPatternType()
        if (iad.getAdPatternType() == AdPatternType.NATIVE_VIDEO) {
            iad.setMediaListener(this);
        }
        // onADReceive之后才可调用getECPM()
        ALog.iTag(ADGloadConfig.TAG_AD, "eCPMLevel:%s", iad.getECPMLevel());
        spinner.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showAsPopup();
                    }
                });
            }
        },500);

    }

    /**
     * 插屏半屏视频广告，视频素材下载完成
     */
    @Override
    public void onVideoCached() {
        // 视频素材加载完成，在此时调用iad.show()或iad.showAsPopupWindow()视频广告不会有进度条。
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoCached...");
    }

    /**
     * 广告加载失败，error 对象包含了错误码和错误信息
     *
     * @param adError
     */
    @Override
    public void onNoAD(AdError adError) {
        String msg = String.format(Locale.getDefault(), "onNoAD, error code: %d, error msg: %s",
                adError.getErrorCode(), adError.getErrorMsg());
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 插屏半屏广告展开时回调
     */
    @Override
    public void onADOpened() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onADOpened...");
    }

    /**
     * 插屏半屏广告曝光时回调
     */
    @Override
    public void onADExposure() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onADExposure...");
    }

    /**
     * 插屏半屏广告点击时回调
     */
    @Override
    public void onADClicked() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onADClicked:clickUrl=%s", (iad.getExt() != null ? iad.getExt().get("clickUrl") : ""));
    }

    /**
     * 插屏半屏广告点击离开应用时回调
     */
    @Override
    public void onADLeftApplication() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onADLeftApplication...");
    }

    /**
     * 插屏半屏广告关闭时回调
     */
    @Override
    public void onADClosed() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onADClosed...");
    }

    //------UnifiedInterstitialMediaListener

    @Override
    public void onVideoInit() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoInit...");
    }

    @Override
    public void onVideoLoading() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoLoading...");
    }

    @Override
    public void onVideoReady(long videoDuration) {
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoReady,duration=%s", videoDuration);
    }

    @Override
    public void onVideoStart() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoStart...");
    }

    @Override
    public void onVideoPause() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoPause...");
    }

    @Override
    public void onVideoComplete() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoComplete...");
    }

    @Override
    public void onVideoError(AdError adError) {
        String msg = String.format(Locale.getDefault(), "onVideoError, error code: %d, error msg: %s",
                adError.getErrorCode(), adError.getErrorMsg());
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onVideoPageOpen() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoPageOpen...");
    }

    @Override
    public void onVideoPageClose() {
        ALog.iTag(ADGloadConfig.TAG_AD, "onVideoPageClose...");
    }
}
