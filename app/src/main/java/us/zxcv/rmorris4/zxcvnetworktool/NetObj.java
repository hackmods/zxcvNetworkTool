package us.zxcv.rmorris4.zxcvnetworktool;


import java.io.Serializable;

public class NetObj implements Serializable {
    public String name = "Unknown";
    public String ip = "";
    public String port = "";

    public NetObj(String name, String ip, String port)
    {
        this.name = (name.length() > 0) ? name : this.name;
        this.ip = (ip.length() > 0) ? name : this.ip;
        this.port = (port.length() > 0) ? name : this.port;
    }
    public String toString()
    {
        return this.name + " " + this.ip;
    }
}
