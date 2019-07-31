package com.sefrWahed.apeye.ui.main.prediction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.sefrWahed.apeye.R;
import com.sefrWahed.apeye.adapter.PlantKindAdapter;
import com.sefrWahed.apeye.databinding.PredictionFragmentBinding;
import com.sefrWahed.apeye.models.Plant;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Abdel-Rahman El-shikh
 */
public class PredictionFragment extends Fragment {
    private static final String TAG = "PredictionFragment";

    private static final int CAMERA_PIC_REQ = 1;
    private static final int CAMERA_PERMISSION_REQ = 2;
    private PredictionFragmentBinding binding;
    private byte[] imageBytes;
    private StorageTask mUploadTask;
    private PlantKindAdapter adapter;
    private StorageReference mStorageRef;
    private PredicitionViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.prediction_fragment, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerPlantTypes.setLayoutManager(layoutManager);
        initPlants();
        binding.recyclerPlantTypes.setAdapter(adapter);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        binding.btnCapture.setOnClickListener(view -> capture());
        binding.btnCheck.setOnClickListener(view ->uploadFile());
        return binding.getRoot();
    }


    private void initPlants() {
        List<Plant> plantList = new ArrayList<>();
        Plant plant = new Plant("Apple", R.drawable.ic_apple);
        plantList.add(plant);
        plant = new Plant("Corn", R.drawable.ic_corn);
        plantList.add(plant);
        plant = new Plant("cherry", R.drawable.ic_cherries);
        plantList.add(plant);
        plant = new Plant("grape", R.drawable.ic_grapes);
        plantList.add(plant);
        plant = new Plant("peach", R.drawable.ic_peach);
        plantList.add(plant);
        plant = new Plant("pepper", R.drawable.ic_pepper);
        plantList.add(plant);
        plant = new Plant("potato", R.drawable.ic_potatoes);
        plantList.add(plant);
        plant = new Plant("strawberry", R.drawable.ic_strawberry);
        plantList.add(plant);
        plant = new Plant("tomato", R.drawable.ic_tomato);
        plantList.add(plant);

        adapter = new PlantKindAdapter();
        adapter.setPlantKinds(plantList, getContext());
    }

    private void capture() {
        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_PIC_REQ);
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSION_REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQ && resultCode == RESULT_OK) {
            assert data != null;
            Bitmap photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            binding.imgCheck.setImageBitmap(photo);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            assert photo != null;
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageBytes = baos.toByteArray();
        }
    }

    private void uploadFile() {
        if (mUploadTask != null && mUploadTask.isInProgress()) {
            Toast.makeText(getActivity(), "Upload in progress", Toast.LENGTH_SHORT).show();
        } else {
            uploadImage();
        }
    }

    private void uploadImage() {
        if (adapter.getSelected() == null) {
            showErrorDialog("Please pick the plant type first");
        } else {
            if (imageBytes != null) {
                binding.progressBar.setVisibility(View.VISIBLE);
                final StorageReference imageReference = mStorageRef.child("image" + ".jpeg");
                mUploadTask = imageReference.putBytes(imageBytes).
                        addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        predict(uri, adapter.getSelected());
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            } else {
                showErrorDialog("Please Capture image to check");
            }
        }
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage(message)
                .setCancelable(true)
                .setTitle("We are Sorry")
                .setIcon(R.drawable.ic_confused);
        AlertDialog alert = builder.create();
        alert.show();


    }
    private void predict(final Uri uri, final String plantType) {
        uriPredict(uri, plantType);
    }

    private void uriPredict(Uri uri, String plantType) {
        binding.progressBar.setVisibility(View.VISIBLE);
        mViewModel = ViewModelProviders.of(this).get(PredicitionViewModel.class);
        mViewModel.init(uri.toString(),plantType);
        mViewModel.getPredicionData().observe(this,prediction -> {
            switch (prediction.getStatus()){
                case SUCCESS:
                    controller().navigate(PredictionFragmentDirections.actionPredictionFragmentToPredictionDetails(prediction.getData()));
                    Toast.makeText(getContext(), prediction.getData().getDiseasename(), Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Log.e(TAG, "uriPredict: Error-> "+prediction.getApiError());
                    break;
                case Failure:
                    Log.e(TAG, "uriPredict: Failure ->"+prediction.getApiException().getLocalizedMessage() );
                    break;
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }

    private NavController controller() {
        return NavHostFragment.findNavController(this);
    }


}
