package com.nitkkr.techspardha.Fragments.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nitkkr.techspardha.R;

import com.nitkkr.techspardha.events.eventList.CategoryList;

import co.ceryle.fitgridview.FitGridAdapter;

public class GridViewMyAdapter extends FitGridAdapter {


    String cat[];


    private int[] drawables = {
            R.drawable.astronomy,R.drawable.design,R.drawable.informals,R.drawable.programming,
            R.drawable.m,R.drawable.onlineevents,R.drawable.papyrusvitae,R.drawable.quizzes,
            R.drawable.robotics ,R.drawable.deafultpic,R.drawable.deafultpic,R.drawable.deafultpic};

    public Context context;

    GridViewMyAdapter(Context context) {
        super(context, R.layout.grid_item);
        this.context = context;
    }

    @Override
    public void onBindView(final int position, View itemView) {
        Log.d("testing     ",position+"");

        ImageView iv = (ImageView) itemView.findViewById(R.id.grid_item_iv);
        if(position<12)
            iv.setImageResource(drawables[position]);

        cat = context.getResources().getStringArray(R.array.Categories);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, CategoryList.class);
                i.putExtra("eventList",cat[position]);
                context.startActivity(i);

            }
        });
    }
}
