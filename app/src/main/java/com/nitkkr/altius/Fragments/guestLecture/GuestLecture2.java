package com.nitkkr.altius.Fragments.guestLecture;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nitkkr.altius.Fragments.guestLecture.lecturesPojo.LectureData;
import com.nitkkr.altius.Fragments.guestLecture.lecturesPojo.Lectures;
import com.nitkkr.altius.R;
import com.nitkkr.altius.retrofit.Interface;
import com.nitkkr.altius.retrofit.RetroClient;
import com.wang.avi.AVLoadingIndicatorView;

import org.qap.ctimelineview.TimelineRow;

import java.util.ArrayList;
import java.util.List;


public class GuestLecture2 extends Fragment {

    ArrayList<Lectures> lectures = new ArrayList<>();
    ListView myListView;
    LectureData lectureD;
    GuestAdapter adapter;

    RecyclerView recyclerView;

    private List<LectureData> ldata = new ArrayList<>();
    LinearLayout content;
    ArrayAdapter<TimelineRow> myAdapter;
    AVLoadingIndicatorView progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guest_lecture2, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.cat_recycler);
        progress = view.findViewById(R.id.sponsi_avi);

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

                        Toasty.error(getContext(),"No Internet", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onComplete() {
                        progress.setVisibility(View.GONE);

                        Log.i("size",String.valueOf(lectureD.getData().getLectures().length));
                        for (int i = 0; i < lectureD.getData().getLectures().length; i++) {
                            lectures.add(lectureD.getData().getLectures()[i]);
                        }

                        adapter = new GuestAdapter(lectures, getContext());

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });


    }


}
