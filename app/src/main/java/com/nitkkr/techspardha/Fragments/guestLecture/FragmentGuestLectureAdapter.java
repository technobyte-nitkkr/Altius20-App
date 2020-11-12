package com.nitkkr.techspardha.Fragments.guestLecture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.Lectures;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
//import com.yarolegovich.discretescrollview.R;
//
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class FragmentGuestLectureAdapter extends RecyclerView.Adapter<FragmentGuestLectureAdapter.ViewHolder> {
    List<Lectures> lst;
    TextView name,date,time,desc;
    List<ImageView> img;
    DiscreteScrollView scrollView;
    int selectedPosition=-1;
    int initial=-1;
    private AdapterView.OnItemClickListener onItemClickListener;





    public FragmentGuestLectureAdapter(DiscreteScrollView scrollView, List<Lectures> lst, TextView name, TextView date, TextView time, TextView desc) {
        this.lst=lst; this.name=name;this.date=date;this.time=time;this.desc=desc;this.scrollView=scrollView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_shop_card, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        Glide.with(holder.itemView.getContext())
                .load(lst.get(position).getImageUrl())
                .into(holder.image);
        holder.image.setPadding(10,10,10,10);

        if(selectedPosition==position||initial==-1){
            holder.itemView.setBackgroundColor(Color.parseColor("#179FEB"));
            initial=0;
        }
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition=position;
                selectedPosition=selectedPosition%5;
                Log.d("position",selectedPosition+"");
                notifyDataSetChanged();
                Log.d("position",""+position);

                name.setText(lst.get(position).getName());
                date.setText(lst.get(position).getDate());
                time.setText(lst.get(position).getTime());
                desc.setText(lst.get(position).getDesc());
            }
        });




    }



    @Override
    public int getItemCount() {
        return lst.size();
    }






    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
//            if(image!=null)
//                img.add(image);
        }
    }

}