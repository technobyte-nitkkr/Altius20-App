package com.nitkkr.techspardha.root;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nitkkr.techspardha.Database_Internal.DBManager;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.CategoryData;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.events.eventList.CategoryListAdapter;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.root.RegisteredEvents.Registered;
import com.nitkkr.techspardha.root.db.userDataStore;
import com.nitkkr.techspardha.root.userPojo.Udata;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class UserLogin extends AppCompatActivity {

    int RC_SIGN_IN = 0;
    LinearLayout signInButton;
    Button signin;
    GoogleSignInClient mGoogleSignInClient;
    AVLoadingIndicatorView progress;
    private List<Registered> edata=new ArrayList<>();
    private DBManager dbManager;



    String serverClientId = "14707849805-mrniiqiii2i7qufric66dv5jrt71k79i.apps.googleusercontent.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        signInButton = findViewById(R.id.sign_in_button);
        progress = findViewById(R.id.login_avi);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestEmail()
                .requestIdToken(serverClientId)
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;

                }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            LoadJson(account.getIdToken());
            startActivity(new Intent(UserLogin.this, RootActivity.class));
            finish();


        } catch (ApiException e) {

            progress.setVisibility(View.GONE);
            Log.d("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(UserLogin.this, "Failed"+e.getStatusCode(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            LoadJson(account.getIdToken());
            startActivity(new Intent(UserLogin.this, RootActivity.class));
            finish();
        }

        super.onStart();
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
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
                        progress.setVisibility(View.GONE);
                        Log.i("Code", e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        progress.setVisibility(View.GONE);

                        Log.i("succs",lst.get(0).getInformation().getName() );
                        userDataStore userData=userDataStore.getInstance(UserLogin.this);
                        userData.saveData(lst.get(0).getInformation(),lst.get(0).getOnBoard());

                        Log.i("object",userData.getState());
                        if(userData.getData().getOnBoard().equals("true")){
                            LoadEvents(userData.getData().getEmail());

                        }




                    }
                });


    }
    public void LoadEvents(final String keyword) {



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

                    }

                    @Override
                    public void onComplete() {

                        ArrayList<Data> eventd = new ArrayList<>();
                        dbManager = new DBManager(UserLogin.this);
                        dbManager.open();

                        for(int i=0;i<edata.get(0).getData().getEvents().length;i++) {
                            Log.i("data", edata.get(0).getData().getEvents()[i].getEventName());
                            eventd.add(edata.get(0).getData().getEvents()[i]);
                            addtoDatabase(edata.get(0).getData().getEvents()[i]);
                        }



                    }
                });


    }
    public void addtoDatabase(Data data){

        dbManager.insert(data.getEventName(),data.getEventCategory(),data.getBanner());

    }


}