package com.sefrWahed.apeye.ui.main.community;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sefrWahed.apeye.R;
import com.sefrWahed.apeye.databinding.CommunityFragmentBinding;

/**
 * Created by Abdel-Rahman Elshikh
 */
public class CommunityFragment extends Fragment {
    private static final String TAG = "CommunityFragment";
    CommunityFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.community_fragment , container,false);
        binding.txtDummyCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onCreateView: Community" );
            }
        });

        return binding.getRoot();
    }
}
