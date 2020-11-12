package com.nitkkr.techspardha.drawers.TeamTech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.view.View;

import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.LectureData;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.drawers.TeamTech.pojo.Contacts;
import com.nitkkr.techspardha.drawers.TeamTech.pojo.tech1;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.sql.Array;
import java.util.ArrayList;

public class Team_Techsparsha extends AppCompatActivity {

    ArrayList<tech1> list=new ArrayList<>();
    TeamAdapter adapter;

    RecyclerView recyclerView;
    AVLoadingIndicatorView progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team__techsparsha);
        recyclerView = (RecyclerView) findViewById(R.id.cat_recycler);
        progress = findViewById(R.id.team_progress);
        getSupportActionBar().setTitle("Team Techspardha");

        Loadjson();

    }

    @Override
    public void onResume(){
        super.onResume();

        Loadjson();
    }


    private void Loadjson() {

        Interface service = RetroClient.getClient().create(Interface.class);


        service
                .getTeam()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<tech1>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(tech1 lectureData) {

                        list.add(lectureData);

                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        progress.setVisibility(View.GONE);

                        ArrayList<Contacts> lst=new ArrayList<>();

                        for(int i=0;i<list.get(0).getData().getContacts().length;i++){
                            lst.add(list.get(0).getData().getContacts()[i]);
                        }
                        adapter = new TeamAdapter(lst,Team_Techsparsha.this);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                });
    }


}
