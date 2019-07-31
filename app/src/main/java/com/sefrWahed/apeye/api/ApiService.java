package com.sefrWahed.apeye.api;

import com.sefrWahed.apeye.models.PredictionResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Abdel-Rahman El-Shikh
 */
public interface ApiService {
    @POST("API/")
    @FormUrlEncoded
    Call<PredictionResponse> classify(@Field("pathimage") String imgUrl,
                                      @Field("plant") String plantKind
                               );


//    @POST("analysisapi/")
//    @FormUrlEncoded
//    Call<ApiResultResponse> MakeAnalysis(@Field("plantname") String plant,
//                                         @Field("username") String userName,
//                                         @Field("date") String date);
}
