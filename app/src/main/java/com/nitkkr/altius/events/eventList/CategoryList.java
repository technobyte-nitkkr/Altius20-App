package com.nitkkr.altius.events.eventList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nitkkr.altius.Database_Internal.DBManager;
import com.nitkkr.altius.retrofit.Interface;
import com.nitkkr.altius.events.categoryPojo.CategoryData;
import com.nitkkr.altius.events.categoryPojo.Data;
import com.nitkkr.altius.R;
import com.nitkkr.altius.retrofit.RetroClient;
import com.nitkkr.altius.root.RegisteredEvents.Registered;
import com.nitkkr.altius.root.db.userDataStore;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class CategoryList extends AppCompatActivity {

    private List<CategoryData> edata=new ArrayList<>();
    RecyclerView recyclerView;
    CategoryListAdapter adapter;
    Bundle bundle;
    String back;
    AVLoadingIndicatorView progress;
    private List<Registered> cdata=new ArrayList<>();
    private DBManager dbManager;
    userDataStore userData;
    String s="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s+="1";
        setContentView(R.layout.activity_category);

         recyclerView = (RecyclerView) findViewById(R.id.cat_recycler);
         progress = findViewById(R.id.category_avi);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("eventList"));

        try {
            back=getIntent().getExtras().getString("eventList");
            LoadJson(getIntent().getExtras().getString("eventList"));
        } catch (Exception e) {
            LoadJson(back);
            e.printStackTrace();
        }
        userData=userDataStore.getInstance(CategoryList.this);


    }

    public void LoadJson(final String keyword) {


        Interface service = RetroClient.getClient().create(Interface.class);


        service
                .getCatData(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryData categoryData) {
                        Log.i("Code", categoryData.getSuccess());

                        edata.add(categoryData);

                    }


                    @Override
                    public void onError(Throwable e) {
                        Toasty.error(getApplicationContext(),"No Internet", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onComplete() {

                        progress.setVisibility(View.GONE);
                        ArrayList<Data> eventd = new ArrayList<>();

                            for (int i = 0; i < edata.get(0).getData().getEvents().length; i++) {
                                eventd.add(edata.get(0).getData().getEvents()[i]);
                                Log.i("List Size", eventd.get(i).getEventName());

                            }
                            adapter = new CategoryListAdapter(eventd, getApplicationContext());
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                    }
                });


    }







}
