package com.codeberry.settingsService;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.codeberry.settingsinterfacemanager.ISettinsgInterface;
import com.codeberry.settingsinterfacemanager.SettingsLineItem;

import java.util.List;
import java.util.Random;

public class SettingsService extends Service {
    private SystemSettingsManager mSystemSettingsManager;

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mSystemSettingsManager = SystemSettingsManager.getInstance(getApplicationContext());
        //Toast.makeText(getApplicationContext(), "suhail oncreate- vehicle model is " + mVehicleModel, Toast.LENGTH_LONG).show();
        Log.w("Suh", "Service OnCrete Called");
    }

    /**
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.w("Suh", "Service onBind Called");
        return settingInterface;
    }

    /**
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.w("Suh", "Service onStartCommand Called");
        startServiceAsForegroundService();
        return START_STICKY;

    }

    /**
     *
     */
    private final ISettinsgInterface.Stub settingInterface = new ISettinsgInterface.Stub() {
        @Override
        public List<SettingsLineItem> getAllSettings() throws RemoteException {
            return mSystemSettingsManager.getAllSettings();
        }

        @Override
        public void setStatus(int kind, int value) throws RemoteException {

        }

        @Override
        public int getStatus(int kind, int value) throws RemoteException {
            return 0;
        }

    };

    /**
     *
     */
    private void startServiceAsForegroundService() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.w("Suh", "Snotfication channel");
            String NOTIFICATION_CHANNEL_ID = "com.codeberry.settingsService";
            String channelName = "My Background Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(chan);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            Notification notification = notificationBuilder.setOngoing(true)
                    .setContentTitle("App is running in background")
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();
            startForeground(2, notification);
        }
    }
}