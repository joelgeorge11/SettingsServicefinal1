package com.codeberry.settingsService;

import java.util.HashMap;

import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.DISPLAY_BRIGHTNESS_HEADLIGHT_OFF;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.DISPLAY_BRIGHTNESS_HEADLIGHT_ON;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.FUEL_SAVER_DISPLAY_IN_CLUSTER;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.INITIAL_VALUE_THREE;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.INITIAL_VALUE_ZERO;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.THEME_MODE_MANUAL;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.TOUCH_SCREEN_BEEP;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.VEHICLE_MODEl;

public class SettingsPropertyValueMap {

    static HashMap<Integer,Integer> sSettingsMap;

    private SettingsPropertyValueMap() {
        initSettingsValieMap();
    }

    private HashMap<Integer,Integer> initSettingsValieMap() {
        sSettingsMap = new HashMap<>();
        sSettingsMap.put(VEHICLE_MODEl, INITIAL_VALUE_ZERO);
        sSettingsMap.put(TOUCH_SCREEN_BEEP, INITIAL_VALUE_ZERO);
        sSettingsMap.put(FUEL_SAVER_DISPLAY_IN_CLUSTER, INITIAL_VALUE_ZERO);
        sSettingsMap.put(THEME_MODE_MANUAL, INITIAL_VALUE_ZERO);
        sSettingsMap.put(DISPLAY_BRIGHTNESS_HEADLIGHT_OFF, INITIAL_VALUE_THREE);
        sSettingsMap.put(DISPLAY_BRIGHTNESS_HEADLIGHT_ON, INITIAL_VALUE_THREE);

        return sSettingsMap;
    }

    public static HashMap getSettingsPropertyValueMap() {
        if (null == sSettingsMap) {
            new SettingsPropertyValueMap();
            return sSettingsMap;
        } else {
            return sSettingsMap;
        }
    }
}
