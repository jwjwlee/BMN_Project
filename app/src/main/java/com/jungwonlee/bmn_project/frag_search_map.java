package com.jungwonlee.bmn_project;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.skp.Tmap.TMapView;

/**
 * Created by Jungwon Lee on 2017-02-06.
 */

public class frag_search_map extends Fragment{

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_search_map, container, false);

        //현재 위치로 초기화 해서 맵 보여주기.
        final Search activity = (Search) getActivity();
        final RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.search_map_view);
        TMapView tmapview = activity.ViewMap();
        relativeLayout.addView(tmapview, 0);

        //현재위치로 화면을 옮기기
        ImageButton cur_search_button = (ImageButton) rootView.findViewById(R.id.imageButton);
        cur_search_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   activity.CurLoc();
                }
            });

        return  rootView;
    }
}

