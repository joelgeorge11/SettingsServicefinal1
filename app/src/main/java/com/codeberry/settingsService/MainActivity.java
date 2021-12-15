package com.codeberry.settingsService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //registerReceiver(new AutoStart(), new IntentFilter("com.codeberry.settingsService.AutoStart.ACTION_CUSTOM"));

        Intent tmpIntent = new Intent(getApplicationContext(), SettingsService.class);
        Log.w("Autostart <", "started");
        startService(tmpIntent);
        //finish();
    }
}