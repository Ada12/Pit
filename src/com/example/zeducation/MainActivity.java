package com.example.zeducation;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

@SuppressLint("ResourceAsColor") public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler x = new Handler();
        x.postDelayed(new lunchhandler(), 2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class lunchhandler implements Runnable{

        public void run() {
            startActivity(new Intent(getApplication(),LoginActivity.class));
            MainActivity.this.finish();
        }

        
    }
}
