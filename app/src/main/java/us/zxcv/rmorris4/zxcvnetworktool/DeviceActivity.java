package us.zxcv.rmorris4.zxcvnetworktool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.net.*;
import android.net.wifi.WifiManager;
public class DeviceActivity extends AppCompatActivity {

    public String   dns1 ;
    public String   dns2;
    public String   gateway;
    public String   ipAddress;
    public String   leaseDuration;
    public String   netmask;
    public String   serverAddress;
    TextView info;
    DhcpInfo d;
    WifiManager wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        wifi= (WifiManager) getSystemService(Context.WIFI_SERVICE);
        d=wifi.getDhcpInfo();

        dns1="DNS 1: "+String.valueOf(d.dns1);
        dns2="DNS 2: "+String.valueOf(d.dns2);
        gateway="Default Gateway: "+String.valueOf(d.gateway);
        ipAddress="IP Address: "+String.valueOf(d.ipAddress);
        leaseDuration="Lease Time: "+String.valueOf(d.leaseDuration);
        netmask="Subnet Mask: "+String.valueOf(d.netmask);
        serverAddress="Server IP: "+String.valueOf(d.serverAddress);

        info= (TextView) findViewById(R.id.txtDeviceInfo);
        info.setText("Network Info\n"+dns1+"\n"+dns2+"\n"+gateway+"\n"+ipAddress+"\n"+leaseDuration+"\n"+netmask+"\n"+serverAddress);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add("Menu");
        menu.add("Test Network");
        menu.add("Configure Networks");
        menu.add("Device Info");
        menu.add("Settings");
        menu.add("About");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String title = item.getTitle().toString();

        if(title == "Test Network") {
            Intent intent = new Intent(DeviceActivity.this, TestActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Configure Networks") {
            Intent intent = new Intent(DeviceActivity.this, ConfigureActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Device Info") {
            Intent intent = new Intent(DeviceActivity.this,DeviceActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Settings") {
            Intent intent = new Intent(DeviceActivity.this,SettingsActivity.class);
            this.startActivity(intent);
        }
        else if(title == "About") {
            Intent intent = new Intent(DeviceActivity.this,AboutActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Menu") {
            Intent intent = new Intent(DeviceActivity.this,MainActivity.class);
            this.startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


    //custom methods
    public String intToIp(int i) {

        return ((i >> 24 ) & 0xFF ) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ( i & 0xFF) ;
    }


    //battery stuff
    private int mBatteryLevel;
    private IntentFilter mBatteryLevelFilter;

    BroadcastReceiver mBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mBatteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            TextView battery = (TextView) findViewById(R.id.txtDeviceBattery);
            battery.setText("Current Battery Level: " + mBatteryLevel);
        }
    };

    private void registerMyReceiver() {
        mBatteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryReceiver, mBatteryLevelFilter);
    }
}
