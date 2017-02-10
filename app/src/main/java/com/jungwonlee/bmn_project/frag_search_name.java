package com.jungwonlee.bmn_project;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jungwon Lee on 2017-02-06.
 */

public class frag_search_name extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_search_name, container, false);

        final Search activity = (Search) getActivity();
        final Button startButton = (Button) rootView.findViewById(R.id.button5);
        final EditText startText = (EditText) rootView.findViewById(R.id.search1) ;

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startText.length() == 0)
                    Toast.makeText(activity, "출발지를 입력하세요", Toast.LENGTH_LONG).show();
                else
                    activity.SearchStartPoint(startText.getText().toString());
            }
        });













        return  rootView;
    }
}
