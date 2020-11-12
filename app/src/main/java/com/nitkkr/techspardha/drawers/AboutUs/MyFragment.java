package com.nitkkr.techspardha.drawers.AboutUs;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitkkr.techspardha.R;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    int[] mResources = {
            R.drawable.intro_1,
            R.drawable.intro_2,
            R.drawable.intro_3,
            R.drawable.intro_4,
            R.drawable.intro_5,
    };


    public static Fragment newInstance(AboutUs context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);
        return Fragment.instantiate(context, MyFragment.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        LinearLayout l = (LinearLayout)
                inflater.inflate(R.layout.mf, container, false);

        int pos = this.getArguments().getInt("pos");
//        TextView tv = (TextView) l.findViewById(R.id.text);
//        tv.setText("Position = " + pos);

        ImageView intro = l.findViewById(R.id.aboutus_content);
        intro.setImageResource(mResources[pos]);

        MyLinearLayout root = (MyLinearLayout) l.findViewById(R.id.root);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return l;
    }
}