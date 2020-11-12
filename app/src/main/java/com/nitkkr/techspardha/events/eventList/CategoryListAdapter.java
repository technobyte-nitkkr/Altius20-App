package com.nitkkr.techspardha.events.eventList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitkkr.techspardha.events.eventDetail.EventInDetail;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    ArrayList<Data> list;
    Context context;
    public CategoryListAdapter(ArrayList<Data> list, Context context){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_cat, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Data myListData = list.get(position);

        holder.name.setText(myListData.getEventName());


        Glide.with(context).load(myListData.getBanner()).placeholder(R.drawable.new_logo).into(holder.img);
        holder.img.setScaleType(ImageView.ScaleType.FIT_XY);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), EventInDetail.class);
                intent.putExtra("Obj",myListData);
                view.getContext().startActivity(intent);

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

            this.name = (TextView) itemView.findViewById(R.id.txtCoin);
            this.img=(ImageView) itemView.findViewById(R.id.image);


        }
    }
}

