package com.codeberry.settingsService;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.codeberry.settingsinterfacemanager.SettingsLineItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SystemSettingsManager {
    private static SystemSettingsManager sSystemSettingsManager;
    private SettingsFormatter mSetingsFormatter;

    private SystemSettingsManager(Context context) {
        mSetingsFormatter = new SettingsFormatter(context);
    }

    /**
     * brief Get instance of the SystemSettingsManager
     *
     * @return
     */
    public static SystemSettingsManager getInstance(Context context) {
        if (null == sSystemSettingsManager) {
            sSystemSettingsManager = new SystemSettingsManager(context);
        }
        return sSystemSettingsManager;
    }

    /**
     * Get vehicle model
     */
    public String getVehicleModel() {
        String vehicleModel = mSetingsFormatter.getVehicleModel();
        return vehicleModel;
    }

    public List<SettingsLineItem> getAllSettings() {
        return mSetingsFormatter.getAllSettings();
    }
}
