package com.nitkkr.techspardha.root;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.ListAdapter;

import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.root.db.userDataStore;
import com.nitkkr.techspardha.root.registerPojo.RegisterData;
import com.nitkkr.techspardha.root.userPojo.Udata;
import com.nitkkr.techspardha.root.userPojo.userInfo;
import com.reginald.editspinner.EditSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class DetailsDialogue {
    final List<userInfo> lst=new ArrayList<>();
    Boolean isOnboarded=false;

    public void showDialog(final Activity activity, final String email){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.user_detail);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        final EditText phone=dialog.findViewById(R.id.mobile);
        final EditText college=dialog.findViewById(R.id.college);
        final EditSpinner year = dialog.findViewById(R.id.year);
        final ImageView close =dialog.findViewById(R.id.dialouge_close);
        final RelativeLayout dismissKeyboard = dialog.findViewById(R.id.layout_year);
        year.setEnabled(false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_spinner_dropdown_item,
                dialog.getContext().getApplicationContext().getResources().getStringArray(R.array.years));
        year.setAdapter(adapter);


        Button register = (Button) dialog.findViewById(R.id.register);

        year.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(activity.getParent().getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

        dismissKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CLicked","Hide");
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(phone.getText().toString().isEmpty()){
                    phone.requestFocus();
                    phone.setError("Please enter mobile number");
                    return;
                }

                if(college.getText().toString().isEmpty()){
                    college.requestFocus();
                    college.setError("Please enter college");
                    return;
                }

                if(year.getText().toString().isEmpty()){
                    year.requestFocus();
                    year.setError("Please select year");
                    return;
                }

                Log.i("details",phone.getText().toString());



                Interface service = RetroClient.getClient().create(Interface.class);






                    service .registerUser(String.valueOf(phone.getText()),String.valueOf(college.getText()),String.valueOf(year.getText()),email)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<RegisterData>() {

                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(RegisterData udata) {

                                    lst.add(udata.getInformation());

                                }

                                @Override
                                public void onError(Throwable e) {

                                    Log.i("Code", e.getMessage());
                                    Toasty.error(activity,"Unable to Register.",Toast.LENGTH_LONG).show();

                                }

                                @Override
                                public void onComplete() {


                                    if (lst.get(0).getOnBoard().equals("true")){
                                        Log.i("OnCLean", lst.get(0).getOnBoard());
                                        userDataStore userData=userDataStore.getInstance(activity.getApplicationContext());
                                        userData.saveData(lst.get(0),"true");
                                        userData.changeState("true");
                                        isOnboarded=true;
                                    }
                                    dialog.cancel();
                                    Toasty.success(activity,"Successfully Registerd",Toast.LENGTH_LONG).show();

                                }
                            });
                }







        });

        dialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public boolean getResult(){
        return isOnboarded;
    }
}
