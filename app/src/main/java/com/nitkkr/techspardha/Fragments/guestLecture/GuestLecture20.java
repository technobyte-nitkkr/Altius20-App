package com.nitkkr.techspardha.Fragments.guestLecture;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.LectureData;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.Lectures;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GuestLecture20 extends Fragment {

//    private RecyclerView guestRecyclerView;
//    private RecyclerView.Adapter GuestAdapter;
//    private RecyclerView.LayoutManager GuestLayoutManager;
    ArrayList<GuestItem> guest_list = new ArrayList<>();;
    LectureData lectureD;
    ArrayList<Lectures> lectures = new ArrayList<>();
    ExpandingList expandingList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guest_lecture_2020, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandingList = (ExpandingList) view.findViewById(R.id.expanding_list_main);
        LoadJson();
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
                        lectures.clear();
                        for (int i = 0; i < lectureD.getData().getLectures().length; i++) {
                            lectures.add(lectureD.getData().getLectures()[i]);
                            Lectures lec = lectureD.getData().getLectures()[i];
                            guest_list.add(new GuestItem(lec.getImageUrl(), lec.getName(), lec.getDate(),lec.getTime(), lec.getLink()));
                        }
                          showList(lectures);
//                        GuestAdapter = new GuestAdapter20(guest_list);
//                        guestRecyclerView.setHasFixedSize(true);
//                        guestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                        guestRecyclerView.setAdapter(GuestAdapter);

                    }
                });
    }

    public void showList(final ArrayList<Lectures> gl){

        for(int i=0;i< gl.size();i++){
            final ExpandingItem item = expandingList.createNewItem(R.layout.gl_expandlist_layout);
            ((TextView)item.findViewById(R.id.person_name)).setText(gl.get(i).getName());
            Glide.with((ImageView)item.findViewById(R.id.person_pic))
                    .load(gl.get(i).getImageUrl())
                    .into((ImageView)item.findViewById(R.id.person_pic));

            item.createSubItem(0);
            final TextView tv = (TextView)item.getSubItemView(0).findViewById(R.id.person_des);
            tv.setText(gl.get(i).getDesc());
            final String s = gl.get(i).getDesc();
            item.setIndicatorColorRes(R.color.blue_btn_bg_color);
            item.setIndicatorIconRes(R.drawable.ic_guest);
            item.collapse();

            item.setStateChangedListener(new ExpandingItem.OnItemStateChanged() {
                @Override
                public void itemCollapseStateChanged(boolean expanded) {
                    Log.i("abhi", String.valueOf(item.isExpanded()));

                }
            });

        }

    }

}
