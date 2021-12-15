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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.DISPLAY_BRIGHTNESS_HEADLIGHT_OFF;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.DISPLAY_BRIGHTNESS_HEADLIGHT_OFF_STRING;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.DISPLAY_BRIGHTNESS_HEADLIGHT_ON;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.DISPLAY_BRIGHTNESS_HEADLIGHT_ON_STRING;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.DISPLAY_MODE_MANUAL_STRING;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.FUEL_SAVER_DISPLAY_IN_CLUSTER;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.FUEL_SAVER_IN_DISPLAY_STRING;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.INITIAL_VALUE_ZERO;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.THEME_MODE_MANUAL;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.TOUCH_SCREEN_BEEP;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.TOUCH_SCREEN_BEEP_STRING;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.VEHICLE_MODEL_M1;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.VEHICLE_MODEL_M1_VAL;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.VEHICLE_MODEl;

public class SettingsFormatter {

    private DbHelper mDbHelper;
    private HashMap<Integer,Integer> mSettingsValueMap;

    public SettingsFormatter(Context context) {
        mDbHelper = new DbHelper(context);
        mDbHelper.initializeDefaultData();
        mSettingsValueMap = SettingsPropertyValueMap.getSettingsPropertyValueMap();
        getVehicleModel();
        mDbHelper.getAndUpdateSettingsValue();
    }

    /**
     *
     * @return
     */
    public String getVehicleModel() {
        File sdcard = Environment.getExternalStoragePublicDirectory("text");
        File dir = new File(sdcard.getAbsolutePath());
        String vehicleModel = new String();
        if (dir.exists()) {
            File file = new File(dir, "vehiclemodel.txt");
            FileOutputStream os = null;
            vehicleModel = "";
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    vehicleModel = vehicleModel + line;
                }
                br.close();
            } catch (IOException e) {
                Log.w("suh", e.toString());
                //You'll need to add proper error handling here
            }
        }
        if (null != vehicleModel) {
            int actualValue = 0;
            if (vehicleModel.toString().equalsIgnoreCase(VEHICLE_MODEL_M1)) {
                actualValue = 0;
            } else {
               actualValue = 1;
            }
            if (null == mSettingsValueMap) {
                mSettingsValueMap = SettingsPropertyValueMap.getSettingsPropertyValueMap();
            }
            mSettingsValueMap.put(VEHICLE_MODEl, actualValue);
            mDbHelper.update(VEHICLE_MODEl, actualValue);
        }
        Log.w("suh", "vehicle model is " + vehicleModel);
        return vehicleModel;
    }

    public List<SettingsLineItem> getAllSettings() {
        if (null == mSettingsValueMap) {
            mSettingsValueMap = SettingsPropertyValueMap.getSettingsPropertyValueMap();
        }
        int vehicleModel = mSettingsValueMap.get(VEHICLE_MODEl);
        List<SettingsLineItem> settingsLineItemList = new ArrayList<>();
        SettingsLineItem touchScreenBeep = new SettingsLineItem(TOUCH_SCREEN_BEEP, mSettingsValueMap.get(TOUCH_SCREEN_BEEP), TOUCH_SCREEN_BEEP_STRING);
        SettingsLineItem fuelSaverDisplay = new SettingsLineItem(FUEL_SAVER_DISPLAY_IN_CLUSTER, mSettingsValueMap.get(TOUCH_SCREEN_BEEP), FUEL_SAVER_IN_DISPLAY_STRING);
        SettingsLineItem displayModeManual = new SettingsLineItem(THEME_MODE_MANUAL, mSettingsValueMap.get(THEME_MODE_MANUAL), DISPLAY_MODE_MANUAL_STRING);
        SettingsLineItem headlightOn = new SettingsLineItem(DISPLAY_BRIGHTNESS_HEADLIGHT_ON, mSettingsValueMap.get(DISPLAY_BRIGHTNESS_HEADLIGHT_ON), DISPLAY_BRIGHTNESS_HEADLIGHT_ON_STRING);
        SettingsLineItem headlightOff = new SettingsLineItem(DISPLAY_BRIGHTNESS_HEADLIGHT_OFF, mSettingsValueMap.get(DISPLAY_BRIGHTNESS_HEADLIGHT_OFF), DISPLAY_BRIGHTNESS_HEADLIGHT_OFF_STRING);

        settingsLineItemList.add(touchScreenBeep);
        if (vehicleModel == VEHICLE_MODEL_M1_VAL) {
            settingsLineItemList.add(fuelSaverDisplay);
        }
        settingsLineItemList.add(displayModeManual);
        settingsLineItemList.add(headlightOn);
        settingsLineItemList.add(headlightOff);
        return settingsLineItemList;
    }
}
