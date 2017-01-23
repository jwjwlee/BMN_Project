package com.jungwonlee.bmn_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Init_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_);
    }
    public void onButtonNavi(View v){
         Intent intent  = new Intent(getApplicationContext(), navi_Activity.class);
         startActivity(intent);
    }
}
