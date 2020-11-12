package com.nitkkr.techspardha.root.RegisteredEvents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import com.nitkkr.techspardha.Database_Internal.DBManager;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.events.eventList.CategoryListAdapter;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class Registered_events extends AppCompatActivity {

    Intent intent;
    private List<Registered> edata=new ArrayList<>();
    RecyclerView recyclerView;
    CategoryListAdapter adapter;
    private DBManager dbManager;
    AVLoadingIndicatorView progress;
    TextView text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeres_events);

        getSupportActionBar().setTitle("My Events");

        recyclerView = (RecyclerView) findViewById(R.id.r_recycler);
        progress = findViewById(R.id.myevents_avi);
        text = findViewById(R.id.myevents_text);

        intent = getIntent();
        dbManager = new DBManager(this);
        dbManager.open();
        LoadJson(intent.getStringExtra("email"));
    }

    public void LoadJson(final String keyword) {

        Log.i("List Size",keyword);


        Interface service = RetroClient.getClient().create(Interface.class);


        service
                .getRegisteredEvents(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registered>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registered categoryData) {

                        Log.i("Code", categoryData.getSuccess());
                        edata.add(categoryData);

                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("Events","Not Found");
                        progress.setVisibility(View.GONE);
                        text.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {
                        progress.setVisibility(View.GONE);

                        ArrayList<Data> eventd = new ArrayList<>();
                        for(int i=0;i<edata.get(0).getData().getEvents().length;i++) {
                            Log.i("data", edata.get(0).getData().getEvents()[i].getEventName());
                            eventd.add(edata.get(0).getData().getEvents()[i]);
                            addtoDatabase(edata.get(0).getData().getEvents()[i]);
                        }

                        adapter = new CategoryListAdapter(eventd,getApplicationContext());

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });


    }
    public void addtoDatabase(Data data) {
        if (!dbManager.ifNumberExists(data.getEventName())) {
            dbManager.insert(data.getEventName(), data.getEventCategory(), data.getBanner());
        }
    }
}
