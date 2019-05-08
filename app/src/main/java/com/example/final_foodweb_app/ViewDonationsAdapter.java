package com.example.final_foodweb_app;

import android.content.Context;
import android.graphics.Bitmap;
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

public class ViewDonationsAdapter extends RecyclerView.Adapter<ViewDonationsAdapter.MyViewHolder> {


    private ArrayList<String> food_names;
    private ArrayList<Bitmap> food_images;
    private ArrayList<String> food_quantities;
    private Context mContext;

    public ViewDonationsAdapter(Context mContext,ArrayList<String> names, ArrayList<Bitmap> images,
                           ArrayList<String> quantities) {
        this.food_names = names;
        this.food_images = images;
        this.mContext = mContext;
        this.food_quantities = quantities;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewdonationrecycler,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(food_images.get(position))
                .into(holder.image);
        holder.name.setText("Food name: "+food_names.get(position));
        holder.quantity.setText(("Quantity: "+food_quantities.get(position).toString()));
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
            name = v.findViewById(R.id.foodname);
            quantity = v.findViewById(R.id.foodquantity);
            image = v.findViewById(R.id.image);
            parentLayout = v.findViewById(R.id.donation_parent_layout);
        }
    }


}
