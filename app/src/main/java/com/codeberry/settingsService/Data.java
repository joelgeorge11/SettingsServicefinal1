package com.codeberry.settingsService;

public class Data {
    private  int ID;
    private  int VALUE;


    public Data(int ID, int VALUE) {
        this.ID = ID;
        this.VALUE = VALUE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getVALUE() {
        return VALUE;
    }

    public void setVALUE(int VALUE) {
        this.VALUE = VALUE;
    }
}
