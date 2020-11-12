package com.nitkkr.techspardha.Fragments.food;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nitkkr.techspardha.Fragments.food.foodPojo.FoodSponsors;
import com.nitkkr.techspardha.Fragments.sponsership.SponsorshipAdapter;
import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.Paisa;
import com.nitkkr.techspardha.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodSponsors> list;

    Context context;

    public FoodAdapter(ArrayList<FoodSponsors> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_food, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        this.context=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        final FoodSponsors myListData = list.get(position);

        Log.d("Name",myListData.getName());

        holder.name.setText(myListData.getName());

        holder.img.setVisibility(View.VISIBLE);
        Glide.with(holder.itemView.getContext())
                .load(myListData.getImageUrl())
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri webpage = Uri.parse(myListData.getLink());
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    view.getContext().startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(view.getContext(), "No application can handle this request. Please install a web browser or check your URL.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView img;


        public ViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.food_name);
            this.img =itemView.findViewById(R.id.food_img);
        }
    }

}
