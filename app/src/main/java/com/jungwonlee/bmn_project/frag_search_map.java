package com.jungwonlee.bmn_project;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Jungwon Lee on 2017-02-06.
 */

public class frag_search_map extends Fragment{

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_search_map, container, false);
        //맵 보여주기.
        final Search activity = (Search) getActivity();
        activity.ViewMap();
        ImageButton cur_search_button = (ImageButton) rootView.findViewById(R.id.imageButton);

        //현재위치 받아오기.
        cur_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.CurLoc();
            }
        });

        return  rootView;
    }
}

