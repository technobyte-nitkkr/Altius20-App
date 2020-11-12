package com.nitkkr.techspardha.Fragments.guestLecture;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.vipulasri.timelineview.TimelineView;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.LectureData;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.Lectures;
import com.nitkkr.techspardha.Pojo.Data;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.root.db.userDataStore;
import com.nitkkr.techspardha.root.registerPojo.RegisterData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.TimeLineViewHolder> {

    ArrayList<Lectures> list;
    Context context;

    public GuestAdapter(ArrayList<Lectures> list, Context context){
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position) {
        final Lectures myListData = list.get(position);
        holder.date.setText(myListData.getDate());
        holder.name.setText(myListData.getName());
        holder.time.setText(myListData.getTime());

        Glide.with(holder.itemView.getContext())
                .load(myListData.getImageUrl())
                .placeholder(R.drawable.deafultpic)
                .into(holder.img);

        holder.mTimelineView.setElevation(5);
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(myListData.getName(),myListData.getDesc(),myListData.getImageUrl());
            }
        });

        char d=myListData.getDate().charAt(1);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        String formattedDate = df.format(c);

        if(formattedDate.charAt(1)>d){
            holder.mTimelineView.setEnabled(false);
        }






    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {
        public  TimelineView mTimelineView;
        ImageView img;
        TextView time,date;
        TextView name;
        LinearLayout click;


        public TimeLineViewHolder(@NonNull View itemView,int viewType) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imgg);
            time=(TextView) itemView.findViewById(R.id.timeg);
            date=(TextView) itemView.findViewById(R.id.dateg);
            click = itemView.findViewById(R.id.timeline_layout);


            name=(TextView) itemView.findViewById(R.id.nameguest);

            mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            mTimelineView.initLine(viewType);
        }
    }

    public void showDialog(String name,String Descrip,String url){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.gl_about);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        final ImageView image=(ImageView) dialog.findViewById(R.id.expandedImage);
        final TextView gname=(TextView) dialog.findViewById(R.id.name);
        final TextView desc=(TextView) dialog.findViewById(R.id.description);

        Glide.with(context)
                .load(url)
                .into(image);
        gname.setText(name);
        desc.setText(Descrip);



        dialog.show();
    }

}
