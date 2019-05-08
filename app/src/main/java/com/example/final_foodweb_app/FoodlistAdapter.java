package com.example.final_foodweb_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodlistAdapter extends RecyclerView.Adapter<FoodlistAdapter.MyViewHolder> {

    private ArrayList<String> food_names;
    private ArrayList<String> food_images;
    private ArrayList<String> food_quantities;
    private Context mContext;

    public FoodlistAdapter(Context mContext,ArrayList<String> names, ArrayList<String> images,
                     ArrayList<String> quantities) {
        this.food_names = names;
        this.food_images = images;
        this.mContext = mContext;
        this.food_quantities = quantities;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_list_recycler,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view,food_names,food_quantities);
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

        public MyViewHolder(View v, final ArrayList<String> names, final ArrayList<String> quantities) {
            super(v);
            name = v.findViewById(R.id.food_name);
            quantity = v.findViewById(R.id.food_quantity);
            image = v.findViewById(R.id.food_image);
            parentLayout = v.findViewById(R.id.food_parent_layout);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
//                    Intent intent = new Intent(v.getContext(), Foodlist.class);
////                    Log.d("0","VIEW\n\n"+names.get(pos));
//                    String name = names.get(pos);
//                    String quantity = quantities.get(pos);
//
//                    intent.putExtra("name",name);
//                    intent.putExtra("quantity",quantity);
//
//                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
