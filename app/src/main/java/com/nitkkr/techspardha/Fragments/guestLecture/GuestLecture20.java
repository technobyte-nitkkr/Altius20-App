package com.nitkkr.techspardha.Fragments.guestLecture;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.LectureData;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.Lectures;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GuestLecture20 extends Fragment {

    private RecyclerView guestRecyclerView;
    private RecyclerView.Adapter GuestAdapter;
    private RecyclerView.LayoutManager GuestLayoutManager;
    ArrayList<GuestItem> guest_list;
    LectureData lectureD;
    ArrayList<Lectures> lectures = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guest_lecture_2020, container, false);
        guestRecyclerView = (RecyclerView) view.findViewById(R.id.guest_recycler);
        guest_list = new ArrayList<>();
        LoadJson();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void LoadJson() {

        Interface service = RetroClient.getClient().create(Interface.class);

        service
                .getLectureData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LectureData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LectureData lectureData) {

                        lectureD = lectureData;

                    }


                    @Override
                    public void onError(Throwable e) {

//                        Toasty.error(getContext(),"No Internet", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onComplete() {

                        Log.i("size",String.valueOf(lectureD.getData().getLectures().length));
                        for (int i = 0; i < lectureD.getData().getLectures().length; i++) {
                            lectures.add(lectureD.getData().getLectures()[i]);
                            Lectures lec = lectureD.getData().getLectures()[i];
                            guest_list.add(new GuestItem(lec.getImageUrl(), lec.getName(), lec.getDate(),lec.getTime(), lec.getLink()));
                        }

                        GuestAdapter = new GuestAdapter20(guest_list);
                        guestRecyclerView.setHasFixedSize(true);
                        guestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        guestRecyclerView.setAdapter(GuestAdapter);

                    }
                });
    }

}
