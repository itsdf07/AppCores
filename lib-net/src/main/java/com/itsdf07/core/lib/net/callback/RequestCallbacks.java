package com.itsdf07.core.lib.net.callback;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Description: 把自定义的接口回调绑定到Retrofit2上，可以实现精细内容的业务
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/12/20
 */

public class RequestCallbacks<T> implements Callback<T> {
    private static final String TAG = "JK_OKHTTP";
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {//Call已经被执行了
                if (SUCCESS != null) {
                    Log.i(TAG, "onResponse-> response is success:body=" + response.body());
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                Log.i(TAG, "onResponse-> response is unsuccess:code=" + response.code() + ",msg=" + response.message());
                ERROR.onError(response.code(), response.message());
            }
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.i(TAG, "onResponse-> response is failure:err=" + t.getMessage());
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
