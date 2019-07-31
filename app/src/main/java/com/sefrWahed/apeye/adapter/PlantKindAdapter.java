package com.sefrWahed.apeye.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sefrWahed.apeye.R;
import com.sefrWahed.apeye.databinding.PlantItemBinding;
import com.sefrWahed.apeye.models.Plant;

import java.util.List;

/**
 * Created by Abdel-Rahman El-Shikh
 */
public class PlantKindAdapter extends RecyclerView.Adapter<PlantKindAdapter.ViewHolder> {

    private List<Plant> plantKindsList;
    private int selectedPosition = -1;
    private Context context;


    public void setPlantKinds(List<Plant> plantKinds, Context context) {
        this.plantKindsList = plantKinds;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PlantItemBinding binding = PlantItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plant plant = plantKindsList.get(position);
        holder.binding.setPlant(plant);
        holder.binding.imagePlantkind.setImageDrawable(context.getDrawable(plant.getResoucseId()));
        holder.bind(holder.binding.cardPlant);
    }

    @Override
    public int getItemCount() {
        return plantKindsList == null ? 0 : plantKindsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PlantItemBinding binding;

        public ViewHolder(PlantItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(view -> {
                binding.cardPlant.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
                if (selectedPosition != getAdapterPosition()) {
                    notifyItemChanged(selectedPosition);
                    selectedPosition = getAdapterPosition();
                }
            });
        }

        void bind(CardView cardView) {
            if (selectedPosition == -1) {
                cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                if (selectedPosition == getAdapterPosition()) {
                    cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
                } else {
                    cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                }
            }
        }


    }
    public String getSelected() {
        if (selectedPosition != -1) {
            return plantKindsList.get(selectedPosition).getName();
        }
        return null;
    }
}
