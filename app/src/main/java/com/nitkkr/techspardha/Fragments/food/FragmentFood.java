package com.nitkkr.techspardha.Fragments.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitkkr.techspardha.Fragments.food.foodPojo.FoodSponsors;
import com.nitkkr.techspardha.Fragments.food.foodPojo.MyPojo;
import com.nitkkr.techspardha.Fragments.sponsership.SponsorshipAdapter;
import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.Paisa;
import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.SponsorshipData;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentFood extends Fragment {
    RecyclerView recyclerView;
    FoodAdapter adapter;
    AVLoadingIndicatorView progress;
    TextView food;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_food,container,false);
        progress = view.findViewById(R.id.food_progress);
        loadJson();
        recyclerView = view.findViewById(R.id.food_recyclerView);
        food=view.findViewById(R.id.myevents_text);
        return view;
    }

    public void loadJson(){

        final List<MyPojo> sData=new ArrayList<>();


        Interface service = RetroClient.getClient().create(Interface.class);


        service
                .getFoodSponser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyPojo>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyPojo myPojo) {

                        sData.add(myPojo);

                    }


                    @Override
                    public void onError(Throwable e) {
                        progress.setVisibility(View.GONE);
                        food.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onComplete() {

                        progress.setVisibility(View.GONE);
                        ArrayList<FoodSponsors> foodSponsors=new ArrayList<>();


                        for(int i=0;i<sData.get(0).getData().getFoodSponsors().length;i++){
                            foodSponsors.add(sData.get(0).getData().getFoodSponsors()[i]);
                        }


                        adapter = new FoodAdapter(foodSponsors);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}
