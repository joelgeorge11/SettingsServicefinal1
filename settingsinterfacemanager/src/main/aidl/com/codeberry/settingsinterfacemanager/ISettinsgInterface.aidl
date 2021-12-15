// ISettinsgInterface.aidl
package com.codeberry.settingsinterfacemanager;

import com.codeberry.settingsinterfacemanager.SettingsLineItem;

// Declare any non-default types here with import statements

 interface ISettinsgInterface {

    List<SettingsLineItem> getAllSettings();
    void setStatus(int kind, int value);
    int getStatus(int kind, int value);

}
