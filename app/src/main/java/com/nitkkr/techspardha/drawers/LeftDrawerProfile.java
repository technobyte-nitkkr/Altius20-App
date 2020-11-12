package com.nitkkr.techspardha.drawers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.root.DetailsDialogue;
import com.nitkkr.techspardha.root.db.userDataStore;
import com.nitkkr.techspardha.root.userPojo.Udata;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class LeftDrawerProfile extends AppCompatActivity {

    TextView nameTV;
    TextView emailTV;
    TextView phone;
    TextView college;
    TextView branch;
    TextView year;
    ImageView photoIV;
    userDataStore userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile");
        userData=userDataStore.getInstance(this);
        nameTV = findViewById(R.id.name);
        emailTV = findViewById(R.id.email);
        photoIV = findViewById(R.id.pic);
        phone=findViewById(R.id.phone);
        college=findViewById(R.id.college);
        year=findViewById(R.id.year);

        if(userData.getState().equals("false")){
            DetailsDialogue detailsDialogue=new DetailsDialogue();
            detailsDialogue.showDialog(LeftDrawerProfile.this,userData.getData().getEmail());

            Log.i("dial","dklsmd");
            LoadJson(userData.getData().getEmail());
        }else{
            setData(userData);
        }

        Log.i("getEmail",userData.getData().getOnBoard());

    }

    public void LoadJson(final String keyword) {

        final List<Udata> lst=new ArrayList<>();


        Interface service = RetroClient.getClient().create(Interface.class);


        service
                .getData(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Udata>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Udata udata) {

                        lst.add(udata);


                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.i("Code", e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                        Log.i("userD",lst.get(0).getInformation().getOnBoard() );
                        userDataStore user=userDataStore.getInstance(LeftDrawerProfile.this);
                        user.saveData(lst.get(0).getInformation(),lst.get(0).getOnBoard());
                        setData(user);


                    }
                });


    }

    public void setData(userDataStore Data){
        if(Data.getData().getName()!=NULL){
            nameTV.setText(Data.getData().getName());
        }
        if(Data.getData().getEmail()!=NULL){
            emailTV.setText(Data.getData().getEmail());
        }
        if(Data.getData().getPhone()!=NULL){
            phone.setText(Data.getData().getPhone());
        }
        if(Data.getData().getCollege()!=NULL){
            college.setText(Data.getData().getCollege());
        }
        if(Data.getData().getYear()!=NULL){
            year.setText(Data.getData().getYear());
        }
        if (Data.getData().getPicture()!=NULL){
            Glide.with(this)
                    .load(Data.getData().getPicture())
                    .into(photoIV);
        }


    }


}