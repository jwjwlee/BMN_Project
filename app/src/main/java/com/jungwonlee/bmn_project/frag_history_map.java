package com.jungwonlee.bmn_project;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 박민수 on 2017-02-06.
 */

public class frag_history_map extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_history_map , container , false);
        history activity = (history) getActivity();
        activity.ViewMap();
        return rootView;
    }
}
