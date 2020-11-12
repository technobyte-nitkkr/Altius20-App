package com.nitkkr.techspardha.drawers.TeamTech;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.vipulasri.timelineview.TimelineView;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.Lectures;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.drawers.TeamTech.pojo.Contacts;
import com.nitkkr.techspardha.drawers.TeamTech.pojo.tech1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TimeLineViewHolder> {

    ArrayList<Contacts> list;
    Activity context;


    public TeamAdapter(ArrayList<Contacts> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.team_item, null);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position) {
        final Contacts myListData = list.get(list.size()-1-position);


        holder.name.setText(myListData.getSection());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showDialog(myListData);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {
        public TimelineView mTimelineView;
        TextView name;
        LinearLayout linearLayout;


        public TimeLineViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameguest);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.team_lay);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            mTimelineView.initLine(viewType);
        }
    }

    private void showDialog(Contacts contacts) {

        final Dialog dialog;
        dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.team_tech);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        final TextView title=(TextView)dialog.findViewById(R.id.sname);

        final LinearLayout ll5=(LinearLayout)dialog.findViewById(R.id.p5);
        final LinearLayout ll6=(LinearLayout)dialog.findViewById(R.id.p6);
        final LinearLayout ll1_2=(LinearLayout)dialog.findViewById(R.id.team_p1_2);
        final LinearLayout ll2=(LinearLayout)dialog.findViewById(R.id.team_p2);

        final ImageView img1 = (ImageView) dialog.findViewById(R.id.img1);
        final TextView nme1 = (TextView) dialog.findViewById(R.id.name1);
        final TextView pst1 = (TextView) dialog.findViewById(R.id.post1);

        final ImageView img2 = (ImageView) dialog.findViewById(R.id.img2);
        final TextView nme2 = (TextView) dialog.findViewById(R.id.name2);
        final TextView pst2 = (TextView) dialog.findViewById(R.id.post2);

        final ImageView img3 = (ImageView) dialog.findViewById(R.id.img3);
        final TextView nme3 = (TextView) dialog.findViewById(R.id.name3);
        final TextView pst3 = (TextView) dialog.findViewById(R.id.post3);

        final ImageView img4 = (ImageView) dialog.findViewById(R.id.img4);
        final TextView nme4 = (TextView) dialog.findViewById(R.id.name4);
        final TextView pst4= (TextView) dialog.findViewById(R.id.post4);

        final ImageView img5 = (ImageView) dialog.findViewById(R.id.img5);
        final TextView nme5 = (TextView) dialog.findViewById(R.id.name5);
        final TextView pst5 = (TextView) dialog.findViewById(R.id.post5);

        final ImageView img6 = (ImageView) dialog.findViewById(R.id.img6);
        final TextView nme6 = (TextView) dialog.findViewById(R.id.name6);
        final TextView pst6 = (TextView) dialog.findViewById(R.id.post6);


        TextView[] txtview={nme1,nme2,nme3,nme4,nme5,nme6};
        ImageView[] imageViews={img1,img2,img3,img4,img5,img6};
        TextView[] posts={pst1,pst2,pst3,pst4,pst5,pst6};

        title.setText(contacts.getSection());

        if(contacts.getPeople().length==1){
            ll1_2.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
            img1.setLayoutParams(new LinearLayout.LayoutParams(400,400));
        }

        for(int i=0;i<contacts.getPeople().length;i++) {

            if (i == 4) {
                ll5.setVisibility(View.VISIBLE);
                txtview[i].setText(contacts.getPeople()[i].getName());
                Log.i("Pic",contacts.getPeople()[i].getImageUrl());
                posts[i].setText(contacts.getPeople()[i].getPost());
                Glide.with(context)
                        .load(contacts.getPeople()[i].getImageUrl())
                        .centerCrop()
                        .placeholder(R.drawable.deafultpic).into(imageViews[i]);

            } else if(i==5) {
                ll6.setVisibility(View.VISIBLE);
                txtview[i].setText(contacts.getPeople()[i].getName());
                posts[i].setText(contacts.getPeople()[i].getPost());
                Glide.with(context)
                        .load(contacts.getPeople()[i].getImageUrl())
                        .centerCrop()
                        .placeholder(R.drawable.deafultpic).into(imageViews[i]);
            }else{
                txtview[i].setText(contacts.getPeople()[i].getName());
                posts[i].setText(contacts.getPeople()[i].getPost());
                Log.d("Team_TechSpardha",contacts.getPeople()[i].getName()+"      "+contacts.getPeople()[i].getPost());
                Glide.with(context)
                        .load(contacts.getPeople()[i].getImageUrl())
                        .centerCrop()
                        .placeholder(R.drawable.deafultpic).into(imageViews[i]);

            }
        }
        dialog.show();
    }
}