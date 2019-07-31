package com.sefrWahed.apeye.api;

import android.util.Log;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abdel-Rahman El-Shikh
 */
public abstract class ApiCallback<T> implements Callback<T> {

    private static final String TAG = "ApiCallback";
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if(response.body() != null){
            handlerResponseData(response.body());
        }else{
            handleError(response);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call,@NonNull Throwable t) {
        if(t instanceof Exception){
            handleException((Exception) t);
        }else{
            Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
        }
    }

    abstract void handleException(Exception t);

    abstract void handleError(Response<T> response);

    abstract void handlerResponseData(T body);

}
