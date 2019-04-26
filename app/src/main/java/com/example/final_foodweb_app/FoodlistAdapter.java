package com.example.final_foodweb_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodlistAdapter extends RecyclerView.Adapter<FoodlistAdapter.MyViewHolder> {

    private ArrayList<String> food_names;
    private ArrayList<String> food_images;
    private ArrayList<Double> food_quantities;
    private Context mContext;

    public FoodlistAdapter(Context mContext,ArrayList<String> names, ArrayList<String> images,
                     ArrayList<Double> quantities) {
        this.food_names = names;
        this.food_images = images;
        this.mContext = mContext;
        this.food_quantities = quantities;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_list_recycler,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(food_images.get(position))
                .into(holder.image);
        holder.name.setText("Name: "+food_names.get(position));
        holder.quantity.setText(("Quantity: "+food_quantities.get(position).toString())+" lbs left");

    }

    @Override
    public int getItemCount() {
        return food_names.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,quantity;
        public CircleImageView image;
        RelativeLayout parentLayout;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.food_name);
            quantity = v.findViewById(R.id.food_quantity);
            image = v.findViewById(R.id.food_image);
            parentLayout = v.findViewById(R.id.food_parent_layout);
        }
    }
}