package us.zxcv.rmorris4.zxcvnetworktool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends Activity {

    static ArrayList <NetObj> netObjList = new ArrayList <NetObj>();
    static ArrayList <Settings> settings = new ArrayList <Settings>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //load loopback as a static default
        try {
            FileInputStream file = MainActivity.this.openFileInput("NetworkObjects");
            ObjectInputStream in = new ObjectInputStream(file);
            Object obj = in.readObject();
            while(obj != null)
            {
                NetObj n = (NetObj) obj;
                netObjList.add(n);
                obj = in.readObject();
            }
            in.close();
            file.close();
        }catch (Exception ex) {
            final NetObj loopback = new NetObj("Loopback", "127.0,0.1", "80");
            netObjList.add(loopback);
        }

        try {
            FileInputStream file = MainActivity.this.openFileInput("Settings");
            ObjectInputStream in = new ObjectInputStream(file);
            Object obj = in.readObject();
            while(obj != null)
            {
                Settings n = (Settings) obj;
                settings.add(n);
                obj = in.readObject();
            }
            in.close();
            file.close();

        }catch (Exception ex) {

            Settings s = new Settings();
            settings.add(s);
        }


        Button btnTest = (Button) this.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnDevice = (Button) this.findViewById(R.id.btnResults);
        btnDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeviceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnSettings = (Button) this.findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnAbout = (Button) this.findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnEdit = (Button) this.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigureActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
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
        int id = item.getItemId();
        String title = item.getTitle().toString();

        if(title == "Test Network") {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Configure Networks") {
            Intent intent = new Intent(MainActivity.this, ConfigureActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Device Info") {
            Intent intent = new Intent(MainActivity.this,DeviceActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Settings") {
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            this.startActivity(intent);
        }
        else if(title == "About") {
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Menu") {
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
