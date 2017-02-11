package com.jungwonlee.bmn_project;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_search_name, container, false);
        final Search activity = (Search)getActivity();
        final Button startButton = (Button) rootView.findViewById(R.id.button5);
        final EditText startText = (EditText) rootView.findViewById(R.id.search1) ;

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(startText.getWindowToken(),0);
                if (startText.length() == 0)
                    Toast.makeText(activity, "출발지를 입력하세요", Toast.LENGTH_LONG).show();
                else
                    activity.SearchStartPoint(startText.getText().toString());
            }
        });

        Button DesButton = (Button) rootView.findViewById(R.id.button6);
        DesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s;
                EditText editText = (EditText)rootView.findViewById(R.id.search2);
                s = editText.getText().toString();
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                if(editText.length() == 0){
                    Toast.makeText(activity,"다시 입력해 주세요!",Toast.LENGTH_LONG).show();
                }
                else{
                    activity.DesSearch(s);
                }
            }
        });



        return  rootView;
    }
}
