package com.sefrWahed.apeye.repositories;

import androidx.lifecycle.LiveData;

import com.sefrWahed.apeye.api.ApiResponse;
import com.sefrWahed.apeye.api.ApiService;
import com.sefrWahed.apeye.api.RequestHandler;
import com.sefrWahed.apeye.api.RetrofitBuilder;
import com.sefrWahed.apeye.models.PredictionResponse;

import retrofit2.Call;

/**
 * Created by Abdel-Rahman El-Shikh
 */
public class PredictionRepo {
    public static PredictionRepo predictionRepo;
    private ApiService apiService;
    public PredictionRepo() {apiService = RetrofitBuilder.createService(ApiService.class); }

    public static PredictionRepo getInstance(){
        if(predictionRepo == null)
            predictionRepo = new PredictionRepo();
        return predictionRepo;
    }

    public LiveData<ApiResponse<PredictionResponse>> getPrediciton(String imgUrl,String plantType){
        RequestHandler<PredictionResponse> requestHandler = new RequestHandler<PredictionResponse>() {
            @Override
            public Call<PredictionResponse> makeRequest() {
                return apiService.classify(imgUrl,plantType);
            }
        };
        requestHandler.doRequest();
        return requestHandler.getApiResponseLiveData();
    }

}
