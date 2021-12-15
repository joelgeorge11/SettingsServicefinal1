package com.codeberry.settingsService;

import static android.os.Build.ID;
import static com.codeberry.settingsService.Constants.*;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.net.IDN;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    SQLiteDatabase mSqLiteDatabase;


    public DbHelper(@Nullable Context context) {
        super(context, "vehiclesetup.db", null, 1);
    }

    /**
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_VALUE + " INTEGER " + ")");
    }

    /**
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS vehicle");
        onCreate(sqLiteDatabase);

    }

    private void openDataBase() {
        mSqLiteDatabase = getWritableDatabase();
    }

    private void closeDatabase() {
        mSqLiteDatabase.close();
    }

    public void initializeDefaultData() {
        openDataBase();
        String query = "select count(*) from " + TABLE_NAME;
        Cursor cursor = mSqLiteDatabase.rawQuery(query, null);
        if (null != cursor) {
            cursor.moveToFirst();
            String count = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
            if (null != count && Integer.parseInt(count) > INITIAL_VALUE_ZERO) {
                Log.w("suh", "first time insertion into db");
            } else {
                Log.w("suh", "finsert default values");
                insertDefaultValues();
            }
            cursor.close();
        } else {
            Log.w("suh", "finsert default values");
            insertDefaultValues();
        }
        closeDatabase();
    }

    private void insertDefaultValues() {
        Data vehicleModelData = new Data(VEHICLE_MODEl, INITIAL_VALUE_ZERO);
        Data touchScreenBeep = new Data(TOUCH_SCREEN_BEEP, INITIAL_VALUE_ZERO);
        Data fuelSaverDisplay = new Data(TOUCH_SCREEN_BEEP, INITIAL_VALUE_ZERO);
        Data themeModeManual = new Data(THEME_MODE_MANUAL, INITIAL_VALUE_ZERO);
        Data displayBrihtnessOn = new Data(DISPLAY_BRIGHTNESS_HEADLIGHT_ON, INITIAL_VALUE_THREE);
        Data displayBrihtnessOff = new Data(DISPLAY_BRIGHTNESS_HEADLIGHT_OFF, INITIAL_VALUE_THREE);
        insertData(vehicleModelData);
        insertData(touchScreenBeep);
        insertData(fuelSaverDisplay);
        insertData(displayBrihtnessOn);
        insertData(displayBrihtnessOff);
        insertData(themeModeManual);
    }

    /**
     * @param data
     */

    public void insertData(Data data) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, data.getID());
        values.put(COLUMN_VALUE, data.getVALUE());
        mSqLiteDatabase.insert(TABLE_NAME, null, values);
    }


    public boolean update(int settingsId, int value) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("UPDATE " + TABLE_NAME + " SET VALUE = " + "" + value + " " + "WHERE ID = " + "" + settingsId + "");
        sqLiteDatabase.close();
        return true;
    }

    public void getAndUpdateSettingsValue() {
        HashMap<Integer, Integer> map = SettingsPropertyValueMap.getSettingsPropertyValueMap();
        openDataBase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_VALUE
        };
        Cursor cursor = mSqLiteDatabase.query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        while(cursor.moveToNext()) {
            int settingsId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_ID));
            int settingsValue = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_VALUE));
            map.put(settingsId,settingsValue);
        }
        closeDatabase();
    }
}
