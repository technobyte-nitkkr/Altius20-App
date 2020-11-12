package com.nitkkr.techspardha.Fragments.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitkkr.techspardha.Database_Internal.DBManager;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.root.RegisteredEvents.Registered;
import com.nitkkr.techspardha.root.RootActivity;
import com.nitkkr.techspardha.root.UserLogin;
import com.nitkkr.techspardha.root.db.userDataStore;

import java.util.ArrayList;
import java.util.List;

import co.ceryle.fitgridview.FitGridView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentEventCategory extends Fragment {

    FitGridView gridView;
    private List<Registered> edata=new ArrayList<>();
    private DBManager dbManager;
    userDataStore userData;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_home,container,false);

        gridView = (FitGridView) view.findViewById(R.id.gridView);
        gridView.setFitGridAdapter(new GridViewMyAdapter(getContext()));
        userData=userDataStore.getInstance(getContext());
        changeSize(3, 3);


        return view;
    }

    public void onClick(View v) {
        showAlert();
    }



    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        FitGridView gridView = new FitGridView(getContext());
        gridView.setNumColumns(3);
        gridView.setNumRows(4);
        gridView.setFitGridAdapter(new GridViewMyAdapter(getContext()));
        builder.setView(gridView);

        builder.show();
    }


    private void changeSize(int r, int c) {
        gridView.setNumRows(r);
        gridView.setNumColumns(c);
        gridView.update();
    }







}
