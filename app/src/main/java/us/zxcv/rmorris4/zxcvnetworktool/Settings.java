package us.zxcv.rmorris4.zxcvnetworktool;

import java.io.Serializable;

public class Settings implements Serializable{


    public int port = 80;
    public int timeout = 10;

    public boolean wifi = true;
    public boolean cell = true;
    public boolean periodic = true;

    public static final int splashTime = 5000;
}
