package com.nitkkr.techspardha.Fragments.sponsership;

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

import com.bumptech.glide.Glide;
import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.Paisa;
import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.Sponsors;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.events.eventDetail.EventInDetail;
import com.nitkkr.techspardha.events.eventList.CategoryListAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SponsorshipAdapter extends RecyclerView.Adapter<SponsorshipAdapter.ViewHolder> {

    ArrayList<Paisa> list;

    Context context;




    public SponsorshipAdapter(ArrayList<Paisa> list){
        this.list=list;
    }


    @NonNull
    @Override
    public SponsorshipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_sponsi, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        this.context=parent.getContext();
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Paisa myListData = list.get(position);

        holder.name.setText(myListData.getSponsorSection());


       for(int i=0;i<myListData.getSponsors().length;i=i+1) {
           Log.i("string", myListData.getSponsors()[i].getImageUrl() + "     " + myListData.getSponsorSection());

           if (myListData.getSponsors()[i].getImageUrl() != null) {
               Log.i("string", myListData.getSponsors()[0].getImageUrl() + "     " + i);
               holder.img[i].setVisibility(View.VISIBLE);
               Glide.with(holder.itemView.getContext())
                       .load(myListData.getSponsors()[i].getImageUrl())
                       .into(holder.img[i]);


               holder.img[i].setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       try {
                           Uri webpage = Uri.parse(myListData.getSponsors()[0].getTargetUrl());
                           Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                           view.getContext().startActivity(myIntent);
                       } catch (ActivityNotFoundException e) {
                           Toast.makeText(view.getContext(), "No application can handle this request. Please install a web browser or check your URL.", Toast.LENGTH_LONG).show();
                           e.printStackTrace();
                       }

                   }
               });
           }
       }
    }








    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView []img=new ImageView[4];


        public ViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.titlesponsi);
            this.img[0]=(ImageView) itemView.findViewById(R.id.imgspon);
            this.img[1]=(ImageView) itemView.findViewById(R.id.img2spon);
            this.img[2]=(ImageView) itemView.findViewById(R.id.img3spon);
            this.img[3]=(ImageView) itemView.findViewById(R.id.img4spon);
        }
    }
}


