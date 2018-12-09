package com.example.rohit.andriodalarmclock_surfaceview;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.rohit.andriodalarmclock_surfaceview.MySurfaceView;
import com.example.rohit.andriodalarmclock_surfaceview.R;
import com.rtugeek.android.colorseekbar.ColorSeekBar;


public class MainActivity extends Activity {
    MySurfaceView mySurfaceView=null;
    SurfaceView surfaceView1;
    SharedPreferences sp = null;
    Button startAlarmBtn,setColorBtn;
    ColorSeekBar seekBar;
    String hrHand,minHand,secHand,bodyClock;
    int hr,min,sec,body;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(getSharedPreferences("myPref", MODE_PRIVATE) != null)    {
            sp = getSharedPreferences("myPref", MODE_PRIVATE);
        }
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceView(this, 300);

       hrHand= sp.getString("hrHand", "4598972");
        Log.d("Debugg","Call Alarm 1" + hrHand);

        minHand= sp.getString("minHand", "4598972");
        Log.d("Debugg","Call Alarm 2");

        secHand= sp.getString("secHand", "4598972");
        Log.d("Debugg","Call Alarm 3");

        bodyClock= sp.getString("bodyClock", "4598972");

        Log.d("Debugg","Lnght" + hrHand.length());
        if(flag == true) {
            mySurfaceView.setSurfaceViewCOlor(hrHand, 1);
            mySurfaceView.setSurfaceViewCOlor(minHand, 2);
            mySurfaceView.setSurfaceViewCOlor(secHand, 3);
            mySurfaceView.setSurfaceViewCOlor(bodyClock, 4);
        }
        else{

            mySurfaceView.setSurfaceViewCOlor("4598972", 5);
            mySurfaceView.setSurfaceViewCOlor("4598972", 5);
            mySurfaceView.setSurfaceViewCOlor("4598972", 5);
            mySurfaceView.setSurfaceViewCOlor("4598972", 5);
        }
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.surfaceView);
        linearLayout.addView(mySurfaceView);

        final SharedPreferences.Editor editor = getSharedPreferences("myPref", MODE_PRIVATE).edit();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debugg","Call Alarm Activity");
                Intent intent = new Intent(MainActivity.this,
                        AlarmActivity.class);
                startActivity(intent);
            }
        });
        seekBar = (ColorSeekBar) findViewById(R.id.colorSlider);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        seekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i, int i1, int i2) {
                if (hr == 1) {
                    mySurfaceView.setSurfaceViewCOlor(String.valueOf(seekBar.getColor()),1);
                    editor.putString("hrHand", String.valueOf(seekBar.getColor()));
                }
                else if (min == 1) {
                    mySurfaceView.setSurfaceViewCOlor(String.valueOf(seekBar.getColor()),2);
                    editor.putString("minHand", String.valueOf(seekBar.getColor()));
                }
                else if(sec == 1) {
                    mySurfaceView.setSurfaceViewCOlor(String.valueOf(seekBar.getColor()),3);
                    editor.putString("secHand", String.valueOf(seekBar.getColor()));
                }
                else if(body == 1) {
                    mySurfaceView.setSurfaceViewCOlor(String.valueOf(seekBar.getColor()), 4);
                    editor.putString("bodyClock", String.valueOf(seekBar.getColor()));
                }
                editor.commit();
                }




        });




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rhr = (RadioButton) findViewById(checkedId);
                Log.d("Debugg", " Radio checked : " + checkedId);
                if(checkedId == 2131230821){
                    hr =1;
                    min = 0;
                    sec = 0;
                    body = 0;
                    flag=true;
                    Log.d("Debugg", "Hour Radio checked : " + checkedId);
                    seekBar.setColorBarPosition(Integer.parseInt(hrHand));
                }
                if(checkedId == 2131230822){
                    hr = 0;
                    min = 1;
                    sec =0;
                    body = 0;
                    flag=true;
                    seekBar.setColorBarPosition(Integer.parseInt(minHand));
                    Log.d("Debugg", "min Radio checked : " + checkedId);

                }
                if(checkedId == 2131230823){
                    hr = 0;
                    min = 0;
                    sec =1;
                    body = 0;
                    flag=true;
                    Log.d("Debugg", "sec Radio checked : " + checkedId);
                    seekBar.setColorBarPosition(Integer.parseInt(secHand));
                }
                if(checkedId == 2131230766){
                    hr = 0;
                    min = 0;
                    sec =0;
                    body =1;
                    flag=true;
                    Log.d("Debugg", "body Radio checked : " + checkedId);
                    seekBar.setColorBarPosition(Integer.parseInt(bodyClock));
                }
                else{
                    flag = false;
                }


                Log.d("Debugg", "Hour Radio Button");

            };

        });


    }

    protected void onResume(){
        super.onResume();
        mySurfaceView.onResumeMySurfaceView();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mySurfaceView.onPauseMySurfaceView();
    }





}
