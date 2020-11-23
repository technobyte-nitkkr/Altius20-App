package com.nitkkr.techspardha.Fragments.guestLecture;

import android.graphics.Bitmap;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nitkkr.techspardha.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;

public class GuestAdapter20 extends RecyclerView.Adapter<GuestAdapter20.GuestViewHolder> {
    private ArrayList<GuestItem>GuestList;

    public GuestAdapter20(ArrayList<GuestItem> GuestList){
        this.GuestList = GuestList;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_lecture_card,parent,false);
        return new GuestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final GuestViewHolder holder, final int position) {

        GuestItem current = GuestList.get(position);

        Glide.with(holder.itemView)
                .load(current.getGuestImage())
                .into(holder.guest_image);
        holder.guest_name.setText(current.getName());
        holder.guest_date.setText(current.getDate());
        holder.guest_time.setText(current.getTime());
        holder.guest_link.setText(current.getLink());

        holder.arrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (holder.hiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(holder.cardView,
                            new AutoTransition());
                    holder.hiddenView.setVisibility(View.GONE);
                    holder.arrow.setBackgroundResource(R.drawable.down);
                    holder.about_guest.setVisibility(View.GONE);
                } else {
                    TransitionManager.beginDelayedTransition(holder.cardView,
                            new AutoTransition());
                    Transition transition = new Fade();
                    transition.setDuration(600);
//                    transition.addTarget(R.id.expand);
//                    TransitionManager.beginDelayedTransition(holder.hiddenView,transition);
                    holder.hiddenView.setVisibility(View.VISIBLE);
                    holder.arrow.setBackgroundResource(R.drawable.up);
                    holder.about_guest.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return GuestList.size();
    }

    public static class GuestViewHolder extends RecyclerView.ViewHolder{

        public ImageView guest_image;
        public TextView guest_name,guest_date,guest_time,guest_link,about_guest;
        private RelativeLayout cardView;
        private Button arrow;
        private RelativeLayout hiddenView;

        public GuestViewHolder(View itemView) {
            super(itemView);
            guest_image = itemView.findViewById(R.id.guest_image);
            guest_name = itemView.findViewById(R.id.guest_name);
            guest_date = itemView.findViewById(R.id.guest_date);
            guest_time = itemView.findViewById(R.id.guest_time);
            guest_link = itemView.findViewById(R.id.guest_link);
            cardView = itemView.findViewById(R.id.parent_guest);
            arrow = itemView.findViewById(R.id.more_button);
            hiddenView = itemView.findViewById(R.id.expand);
            about_guest = itemView.findViewById(R.id.about_guest);
        }
    }
}


