package com.nitkkr.techspardha.drawers.developers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitkkr.techspardha.Fragments.guestLecture.FragmentGuestLectureAdapter;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.LectureData;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.Lectures;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.drawers.developers.developersPojo.DevelopersData;
import com.nitkkr.techspardha.drawers.developers.developersPojo.Information;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.wang.avi.AVLoadingIndicatorView;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.List;

import co.ceryle.fitgridview.FitGridView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Developers extends AppCompatActivity {

    private List<DevelopersData> ldata = new ArrayList<>();
    List<Information> lst = new ArrayList<>();
    DevelopersData DeveloperD;
    TextView name0, name1, name2, name3, name4, name5;
    ImageView i0, i1, i2, i3, i4, i5;
    FitGridView gridView;
    AVLoadingIndicatorView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        getSupportActionBar().setTitle("Developers");
        name0 = findViewById(R.id.dev_txt0);
        name1 = findViewById(R.id.dev_txt1);
        name2 = findViewById(R.id.dev_txt2);
        name3 = findViewById(R.id.dev_txt3);
        name4 = findViewById(R.id.dev_txt4);
        name5 = findViewById(R.id.dev_txt5);
        progress = findViewById(R.id.dev_avi);

        i0 = findViewById(R.id.dev_img0);
        i1 = findViewById(R.id.dev_img1);
        i2 = findViewById(R.id.dev_img2);
        i3 = findViewById(R.id.dev_img3);
        i4 = findViewById(R.id.dev_img4);
        i5 = findViewById(R.id.dev_img5);

        LoadJson();

    }

    public void LoadJson() {


        Interface service = RetroClient.getClient().create(Interface.class);



        service
                .getDevelopersData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DevelopersData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DevelopersData developersData) {

                        DeveloperD = developersData;

                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        progress.setVisibility(View.GONE);

                        i0.setVisibility(View.VISIBLE);
                        i1.setVisibility(View.VISIBLE);
                        i2.setVisibility(View.VISIBLE);
                        i3.setVisibility(View.VISIBLE);
                        i4.setVisibility(View.VISIBLE);
                        i5.setVisibility(View.VISIBLE);



                        Glide.with(getApplicationContext()).load(DeveloperD.getData().getInformation()[0].getImageUrl()).placeholder(R.drawable.dev_icon).into(i0);
                        Glide.with(getApplicationContext()).load(DeveloperD.getData().getInformation()[1].getImageUrl()).placeholder(R.drawable.dev_icon).into(i1);
                        Glide.with(getApplicationContext()).load(DeveloperD.getData().getInformation()[2].getImageUrl()).placeholder(R.drawable.dev_icon).into(i2);
                        Glide.with(getApplicationContext()).load(DeveloperD.getData().getInformation()[3].getImageUrl()).placeholder(R.drawable.dev_icon).into(i3);
                        Glide.with(getApplicationContext()).load(DeveloperD.getData().getInformation()[4].getImageUrl()).placeholder(R.drawable.dev_icon).into(i4);
                        Glide.with(getApplicationContext()).load(DeveloperD.getData().getInformation()[5].getImageUrl()).placeholder(R.drawable.dev_icon).into(i5);

                        name0.setText(DeveloperD.getData().getInformation()[0].getName());
                        name1.setText(DeveloperD.getData().getInformation()[1].getName());
                        name2.setText(DeveloperD.getData().getInformation()[2].getName());
                        name3.setText(DeveloperD.getData().getInformation()[3].getName());
                        name4.setText(DeveloperD.getData().getInformation()[4].getName());
                        name5.setText(DeveloperD.getData().getInformation()[5].getName());

                        i0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(DeveloperD.getData().getInformation()[0].getLink()));
                                startActivity(browse);
                            }
                        });
                        i1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(DeveloperD.getData().getInformation()[1].getLink()));
                                startActivity(browse);
                            }
                        });
                        i2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(DeveloperD.getData().getInformation()[2].getLink()));
                                startActivity(browse);
                            }
                        });
                        i3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(DeveloperD.getData().getInformation()[3].getLink()));
                                startActivity(browse);
                            }
                        });
                        i4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(DeveloperD.getData().getInformation()[4].getLink()));
                                startActivity(browse);
                            }
                        });
                        i5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(DeveloperD.getData().getInformation()[5].getLink()));
                                startActivity(browse);
                            }
                        });


                    }

                });


    }
}
