package com.sefrWahed.apeye.ui.main.analysis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sefrWahed.apeye.R;
import com.sefrWahed.apeye.databinding.AnalysisFragmentBinding;

/**
 * Created by Abdel-Rahman Elshikh
 */
public class AnalysisFragment extends Fragment {
    AnalysisFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.analysis_fragment,container,false);
        return binding.getRoot();
    }
}
