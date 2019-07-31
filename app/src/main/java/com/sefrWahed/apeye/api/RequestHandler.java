package com.sefrWahed.apeye.api;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Response;


import static com.sefrWahed.apeye.api.Status.ERROR;
import static com.sefrWahed.apeye.api.Status.Failure;
import static com.sefrWahed.apeye.api.Status.SUCCESS;

/**
 * Created by Abdel-Rahman El-Shikh
 */
public abstract class RequestHandler<T> {
    private MutableLiveData<ApiResponse<T>> apiResponseLiveData;

    protected RequestHandler() {
        apiResponseLiveData = new MutableLiveData<>();
    }

    public abstract Call<T> makeRequest();

    public void doRequest() {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        makeRequest().enqueue(new ApiCallback<T>() {
            @Override
            void handleException(Exception t) {
                //Failure happened
                apiResponse.setApiException(t);
                apiResponse.setStatus(Failure);
                apiResponseLiveData.setValue(apiResponse);
            }

            @Override
            void handleError(Response<T> response) {
                //response isn't successful
                apiResponse.setApiError(RetrofitBuilder.convertErrors(response.errorBody()));
                apiResponse.setStatus(ERROR);
                apiResponseLiveData.setValue(apiResponse);
            }

            @Override
            void handlerResponseData(T body) {
                //response is successful
                apiResponse.setData(body);
                apiResponse.setStatus(SUCCESS);
                apiResponseLiveData.setValue(apiResponse);
            }
        });
    }

    public LiveData<ApiResponse<T>> getApiResponseLiveData() {
        return apiResponseLiveData;
    }
}
