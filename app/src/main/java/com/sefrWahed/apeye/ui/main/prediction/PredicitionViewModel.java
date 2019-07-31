package com.sefrWahed.apeye.ui.main.prediction;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sefrWahed.apeye.api.ApiResponse;
import com.sefrWahed.apeye.models.PredictionResponse;
import com.sefrWahed.apeye.repositories.PredictionRepo;

/**
 * Created by Abdel-Rahman El-Shikh
 */
public class PredicitionViewModel extends ViewModel {
    private PredictionRepo predictionRepo;
    private LiveData<ApiResponse<PredictionResponse>> predicionData;

    public void init(String imgUrl,String plantType){
        predictionRepo = PredictionRepo.getInstance();
        predicionData =  predictionRepo.getPrediciton(imgUrl,plantType);
    }
    public LiveData<ApiResponse<PredictionResponse>> getPredicionData(){
        return predicionData;
    }
}
