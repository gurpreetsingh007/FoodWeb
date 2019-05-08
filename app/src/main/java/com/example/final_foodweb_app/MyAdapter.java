package com.example.final_foodweb_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private ArrayList<Bitmap> images;
//    private ArrayList<Double> distances;
    private Context mContext;
    String clicked_username;


    public MyAdapter(Context mContext,ArrayList<String> names, ArrayList<Bitmap> images
                    ,String username) {
        this.names = names;
        this.images = images;
        this.mContext = mContext;
        this.clicked_username = username;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view,names);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(images.get(position))
                .into(holder.image);
        holder.name.setText(names.get(position));

    }



    @Override
    public int getItemCount() {
        return names.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public CircleImageView image;
        RelativeLayout parentLayout;

        public MyViewHolder(View itemView, final ArrayList<String> names) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
//            distance = v.findViewById(R.id.distance);
            image = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), Foodlist.class);
                    Log.d("0","VIEW\n\n"+names.get(pos));
                    intent.putExtra("items",names.get(pos));
                    v.getContext().startActivity(intent);
                }
            });

        }
    }

}
