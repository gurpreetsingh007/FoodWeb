package com.example.final_foodweb_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.BreakIterator;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private ArrayList<String> names;
    private ArrayList<String> images;
    private ArrayList<Double> distances;
    private Context mContext;


    public MyAdapter(Context mContext,ArrayList<String> names, ArrayList<String> images,
                     ArrayList<Double> distances) {
        this.names = names;
        this.images = images;
        this.mContext = mContext;
        this.distances = distances;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(images.get(position))
                .into(holder.image);
        holder.name.setText(names.get(position));
        holder.distance.setText((distances.get(position).toString())+" miles away");
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Foodlist.class);
                intent.putExtra("items","passing");
                v.getContext().startActivity(intent);
                Toast.makeText(mContext,names.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return names.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,distance;
        public CircleImageView image;
        RelativeLayout parentLayout;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            distance = v.findViewById(R.id.distance);
            image = v.findViewById(R.id.image);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }

}
