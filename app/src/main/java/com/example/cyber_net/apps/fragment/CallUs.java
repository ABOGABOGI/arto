package com.example.cyber_net.apps.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyber_net.apps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallUs extends Fragment {


    public CallUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_us, container, false);
    }

}
