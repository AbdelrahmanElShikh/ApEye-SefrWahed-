package com.sefrWahed.apeye.ui.main.predictionDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sefrWahed.apeye.R;
import com.sefrWahed.apeye.databinding.PredictionDetailsBinding;
import com.sefrWahed.apeye.models.PredictionResponse;
import com.squareup.picasso.Picasso;


/**
 * Created by Abdel-Rahman El-Shikh
 */
public class PredictionDetailsFragment extends Fragment {
    private PredictionDetailsBinding binding;
    private PredictionResponse prediction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.prediction_details,container,false);
        assert getArguments() != null;
        prediction = PredictionDetailsFragmentArgs.fromBundle(getArguments()).getPrediction();
        Picasso.get().load(prediction.getUrl()).placeholder(R.drawable.ic_camera).
                error(R.drawable.ic_confused)
                .into(binding.imgPredictedCrop);
        binding.setPrediction(prediction);
        return binding.getRoot();
    }
}
