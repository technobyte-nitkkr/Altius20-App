package com.nitkkr.techspardha.events.eventDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.ListAdapter;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nitkkr.techspardha.Database_Internal.DBManager;
import com.nitkkr.techspardha.Database_Internal.DatabaseHelper;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.events.categoryPojo.EventCategory;
import com.nitkkr.techspardha.root.DetailsDialogue;
import com.nitkkr.techspardha.root.RootActivity;
import com.nitkkr.techspardha.root.UserLogin;
import com.nitkkr.techspardha.root.db.userDataStore;
import com.nitkkr.techspardha.root.registerPojo.EventRegister;
import com.nitkkr.techspardha.root.registerPojo.RegisterData;
import com.reginald.editspinner.EditSpinner;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class EventInDetail extends AppCompatActivity {

    private Adapter adapter;
    TextView rus, erules, time, evenue, edate, c1, c2;
    LinearLayout call1, call2;
    Button register;
    userDataStore userData;
    String ename,cate,ban;
    String onBoarded = "false";
    ImageView img;
    ArrayList<String> cordinatorName = new ArrayList<>();
    ArrayList<String> cordinatorNumber = new ArrayList<>();
    private List<EventRegister> edata = new ArrayList<>();
    AVLoadingIndicatorView progress;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_in_detail);
        getSupportActionBar().hide();

        img=(ImageView)findViewById(R.id.expandedImage);
        rus = (TextView) findViewById(R.id.description);
        erules = (TextView) findViewById(R.id.rules);
        time = (TextView) findViewById(R.id.time_event);
        evenue = (TextView) findViewById(R.id.venue);
        edate = (TextView) findViewById(R.id.date);
        c1 = (TextView) findViewById(R.id.name1);
        c2 = (TextView) findViewById(R.id.name2);
        call1 =  findViewById(R.id.phone1);
        call2 = findViewById(R.id.phone2);
        progress = findViewById(R.id.eventindetail_avi);



        Intent intent = getIntent();
        final Data cust = (Data) intent.getSerializableExtra("Obj");


        String desc = cust.getDescription();
        String etime = cust.getEndTime();
        String stime = cust.getStartTime();
        cate=cust.getEventCategory();
        ban=cust.getBanner();


        for (int i = 0; i < cust.getCoordinators().length; i++) {
            cordinatorName.add(cust.getCoordinators()[i].getCoordinator_name());
            cordinatorNumber.add(cust.getCoordinators()[i].getCoordinator_number());
        }

        c1.setText(cordinatorName.get(0));
        if (cordinatorNumber.size() > 1) {
            c2.setText(cordinatorName.get(1));
        }


        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"+91" + cordinatorNumber.get(0)));

//                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
                startActivity(intent);
            }
        });
        if (cordinatorNumber.size() > 1) {
            call2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"+91" + cordinatorNumber.get(1)));
//                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        return;
//                    }
                    startActivity(intent);
                }
            });
        }




        ename=cust.getEventName();

        String rules = "";

        for (int i = 0; cust.getRules() != null && i < cust.getRules().length; i++) {
            rules = rules + cust.getRules()[i] + "\n";
        }
        erules.setText(rules);

        rus.setText(desc);


        edate.setText(getDate(stime));
        time.setText(getDate(etime));
        evenue.setText(cust.getVenue());

        Glide.with(getApplicationContext())
                .load(cust.getBanner())
                .placeholder(R.drawable.new_logo).into(img);
        img.setScaleType(ImageView.ScaleType.FIT_XY);








        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cust.getEventName());
        collapsingToolbar.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);

        register = (Button) findViewById(R.id.register);

        dbManager = new DBManager(this);
        dbManager.open();

        userData=userDataStore.getInstance(EventInDetail.this);
        onBoarded=userData.getData().getOnBoard();

        Log.i("use",userData.getData().getOnBoard());

        long time= System.currentTimeMillis();
        long endtime=Long.parseLong(cust.getEndTime());

        if(time>endtime){
            register.setText("Event Ended");
            register.setBackground(getDrawable(R.drawable.redbutton));
            register.setClickable(false);

        }else if ((dbManager.ifNumberExists(cust.getEventName()))) {
           setRegisterButton();
        } else {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onBoarded.equals("true")){
                        progress.setVisibility(View.VISIBLE);
                        LoadJson(userData.getData().getEmail(),cust.getEventName(),cust.getEventCategory());

                    }else{
                        Toasty.info(getApplicationContext(),"Please Enter Your Details first",Toast.LENGTH_LONG).show();
                        DetailsDialogue detailsDialogue=new DetailsDialogue();
                        detailsDialogue.showDialog(EventInDetail.this,userData.getData().getEmail());
                        if(detailsDialogue.getResult()){
                        setRegisterButton();
                    }
                }

                }
            });

        }


    }



    public void LoadJson(final String email,final String eventName,final  String Category) {

        Interface service = RetroClient.getClient().create(Interface.class);




            Log.i("json","called");
            service
                    .eventregister(email,eventName,Category)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<EventRegister>() {

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(EventRegister udata) {

                           edata.add(udata);

                        }

                        @Override
                        public void onError(Throwable e) {

                            Log.i("jsone", e.getMessage());

                        }

                        @Override
                        public void onComplete() {
                            progress.setVisibility(View.GONE);
                            Log.i("status",edata.get(0).getSuccess());
                        if(edata.get(0).getSuccess().equals("true")){
                            Toasty.success(getApplicationContext(), edata.get(0).getStatus(), Toast.LENGTH_SHORT, true).show();
                            setRegisterButton();
                        }else{
                            Toasty.error(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT, true).show();

                        }
                    }
        });

    }

    public void setRegisterButton(){
        register.setText("Registered for "+ename+"!");
        register.setBackground(getDrawable(R.drawable.greenbutton));
        register.setClickable(false);
        dbManager.insert(ename,cate,ban);
    }
    public String getDate(String ms){
        Long ls=Long.parseLong(ms);
        Date date=new  Date(ls);
        SimpleDateFormat dateformat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        return dateformat.format(date);
    }

}
