package com.codeberry.settingsinterfacemanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class SettingsLineItem implements Parcelable {

    public static final Creator<SettingsLineItem> CREATOR = new Creator<SettingsLineItem>() {
        @Override
        public SettingsLineItem createFromParcel(Parcel in) {
            return new SettingsLineItem(in);
        }

        @Override
        public SettingsLineItem[] newArray(int size) {
            return new SettingsLineItem[size];
        }
    };

    /**
     * Variable to hold Setting ID.
     */
    private int mSettingId;

    /**
     * Variable to hold Setting value.
     */
    private int mSettingValue;

    /**
     * Variable to hold setting name.
     */
    private String mSettingName;

    /**
     * @brief Parameterized constructor
     * @param settingId : int type
     * @param settingValue : int type
     */
    public SettingsLineItem(int settingId, int settingValue, String settingsName) {
        this.mSettingId = settingId;
        this.mSettingValue = settingValue;
        this.mSettingName = settingsName;
    }


    protected SettingsLineItem(Parcel in) {
        mSettingId = in.readInt();
        mSettingValue = in.readInt();
        mSettingName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mSettingId);
        dest.writeInt(mSettingValue);
        dest.writeString(mSettingName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @brief Getter method for Setting ID variable.
     * @return Setting ID : int type.
     */
    public int getSettingId() {
        return mSettingId;
    }

    /**
     * @brief Setter method for Setting ID variable.
     * @param settingId : int type.
     */
    public void setSettingId(int settingId) {
        mSettingId = settingId;
    }



    /**
     * @brief Getter method for Setting value variable.
     * @return Setting value : int type.
     */
    public int getSettingValue() {
        return mSettingValue;
    }

    /**
     * @brief Setter method for Setting value variable.
     * @param settingValue : int type.
     */
    public void setSettingValue(int settingValue) {
        this.mSettingValue = settingValue;
    }

    /**
     * @brief Getter method for Setting name.
     * @return String : Setting name.
     */
    public String getSettingName() {
        return mSettingName;
    }

    /**
     * @brief Setter method for Setting name.
     * @param settingName : Setting name.
     */
    public void setSettingName(String settingName) {
        this.mSettingName = settingName;
    }

    /**
     * @brief Method to retrieve data as string.
     * @return Setting data : String.
     */
    @Override
    public String toString() {
        StringBuffer settingListItem = new StringBuffer("SettingsListItem{");
        settingListItem.append("mSettingId=" + mSettingId);
        settingListItem.append(", mSettingValue=" + mSettingValue);
        settingListItem.append(", mSettingName=" + mSettingName);
        settingListItem.append("}");
        return settingListItem.toString();
    }

    /**
     * @brief Method to compare compare this SettingsListItem object with the passed object.
     * @param obj : Instance of SettingsListItem object.
     * @return boolean : Returns true if the passed object is not null and similar to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (this.getClass() == obj.getClass()) {
                SettingsLineItem settingItem = (SettingsLineItem) obj;
                return getSettingId() == settingItem.getSettingId();
            }
        }
        return false;
    }

    /**
     * @brief Method to return hashCode
     * @return Setting ID : int type.
     */
    @Override
    public int hashCode() {
        return mSettingId;
    }

}
