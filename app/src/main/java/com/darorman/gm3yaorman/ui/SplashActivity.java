package com.darorman.gm3yaorman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.ui.admin_main.AdminActivity;
import com.darorman.gm3yaorman.ui.home.HomeActivity;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

public class SplashActivity extends AppCompatActivity {

    private SharedPrefsHelper prefsHelper;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        prefsHelper = new SharedPrefsHelper(this);
        //set time for splash from postdelayed method then start the new activity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (prefsHelper.getAdminID() != null)
                    intent = new Intent(SplashActivity.this , AdminActivity.class);
                else
                    intent = new Intent(SplashActivity.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
                finish();
            }
        }, 3000);//time for splash
    }
}
