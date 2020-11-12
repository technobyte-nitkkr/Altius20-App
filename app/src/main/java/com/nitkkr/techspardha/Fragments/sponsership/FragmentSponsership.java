package com.nitkkr.techspardha.Fragments.sponsership;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.Paisa;
import com.nitkkr.techspardha.Fragments.sponsership.sponsorshipPojo.SponsorshipData;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.CategoryData;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.events.eventList.CategoryListAdapter;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class FragmentSponsership extends Fragment {
    RecyclerView recyclerView;
    SponsorshipAdapter adapter;
    AVLoadingIndicatorView progress;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_sponsership,container,false);
        progress = view.findViewById(R.id.sponsi_avi);
        loadJson();
        recyclerView=(RecyclerView)view.findViewById(R.id.spon_recycler);
        return view;
    }

    public void loadJson(){

         final List<SponsorshipData> sData=new ArrayList<>();


        Interface service = RetroClient.getClient().create(Interface.class);


        service
                .getSponsorship()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SponsorshipData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SponsorshipData sponsorshipData) {

                       sData.add(sponsorshipData);

                    }


                    @Override
                    public void onError(Throwable e) {
                        Toasty.error(getContext(),"No Internet", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onComplete() {

                        progress.setVisibility(View.GONE);
                        ArrayList<Paisa> paisa=new ArrayList<>();
                        for(int i=0;i<sData.get(0).getData().getPaisa().length;i++){
                            paisa.add(sData.get(0).getData().getPaisa()[i]);
                        }
                        adapter = new SponsorshipAdapter(paisa);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                });

    }
}
