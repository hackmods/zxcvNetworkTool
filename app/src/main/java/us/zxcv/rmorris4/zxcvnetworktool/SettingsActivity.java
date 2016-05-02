package us.zxcv.rmorris4.zxcvnetworktool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    ListView list;
    Settings setpref = new Settings();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        try {
            FileInputStream file = SettingsActivity.this.openFileInput("PrefSettings");
            ObjectInputStream in = new ObjectInputStream(file);
            Object obj = in.readObject();
            setpref = (Settings) obj;

            in.close();
            file.close();
        }catch (Exception ex) {
            //if in doubt load default
          // Toast.makeText(SettingsActivity.this, "Error Loading" + ex, Toast.LENGTH_LONG).show();
            Settings s = new Settings();
            setpref = s;
        }

        final EditText port = (EditText) SettingsActivity.this.findViewById(R.id.txtPort);
        int portnum =setpref.port;
        port.setText(String.valueOf(portnum));

        final EditText time = (EditText) SettingsActivity.this.findViewById(R.id.txtTime);
        int timev = setpref.timeout;
        time.setText(String.valueOf(timev));

        final Switch cell = (Switch) SettingsActivity.this.findViewById(R.id.cboCell);
        if(setpref.cell)
            cell.setEnabled(true);
        else
            cell.setEnabled(false);
        final Switch wifi = (Switch) SettingsActivity.this.findViewById(R.id.cboWfi);
        if(setpref.wifi)
            wifi.setEnabled(true);
        else
            wifi.setEnabled(false);
        final Switch scan = (Switch) SettingsActivity.this.findViewById(R.id.cboScan);
        if(setpref.cell)
            scan.setEnabled(true);
        else
            scan.setEnabled(false);

        final Button save = (Button) this.findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    //load changes
                    final EditText sport = (EditText) SettingsActivity.this.findViewById(R.id.txtPort);
                    setpref.port = Integer.valueOf(sport.toString());

                    final EditText stime = (EditText) SettingsActivity.this.findViewById(R.id.txtTime);
                    setpref.timeout = Integer.valueOf(stime.toString());

                    final Switch scell = (Switch) SettingsActivity.this.findViewById(R.id.cboCell);
                    if (scell.isChecked())
                        setpref.cell = true;
                    else
                        setpref.cell = false;

                    final Switch swifi = (Switch) SettingsActivity.this.findViewById(R.id.cboWfi);
                    if (swifi.isChecked())
                        setpref.wifi = true;
                    else
                        setpref.wifi = false;

                    final Switch sscan = (Switch) SettingsActivity.this.findViewById(R.id.cboScan);
                    if (sscan.isChecked())
                        setpref.periodic = true;
                    else
                        setpref.periodic = false;
                }
                catch (Exception e)
                {
                    Toast.makeText(SettingsActivity.this, "Weird saving info", Toast.LENGTH_LONG).show();
                }
                try {
                    FileOutputStream file = SettingsActivity.this.openFileOutput("PrefSettings", Context.MODE_PRIVATE);
                    ObjectOutputStream out = new ObjectOutputStream(file);
                   // for (int x = 0; x < MainActivity.settings.size(); x++)
                        out.writeObject(setpref);
                    out.close();
                    file.close();
                    Toast.makeText(SettingsActivity.this, "Data Saved", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(SettingsActivity.this, "Error Loading" + ex, Toast.LENGTH_LONG).show();
                }

            }
        });

        final Button reset = (Button) this.findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final EditText port = (EditText) SettingsActivity.this.findViewById(R.id.txtPort);
                int portnum = setpref.port;
                port.setText(String.valueOf(portnum));

                final EditText time = (EditText) SettingsActivity.this.findViewById(R.id.txtTime);
                int timev = setpref.timeout;
                time.setText(String.valueOf(timev));

                final Switch cell = (Switch) SettingsActivity.this.findViewById(R.id.cboCell);
                if(setpref.cell)
                    cell.setEnabled(true);
                else
                    cell.setEnabled(false);
                final Switch wifi = (Switch) SettingsActivity.this.findViewById(R.id.cboWfi);
                if(setpref.wifi)
                    wifi.setEnabled(true);
                else
                    wifi.setEnabled(false);
                final Switch scan = (Switch) SettingsActivity.this.findViewById(R.id.cboScan);
                if(setpref.cell)
                    scan.setEnabled(true);
                else
                    scan.setEnabled(false);

                Toast.makeText(SettingsActivity.this, "Settings Reloaded", Toast.LENGTH_SHORT).show();
            }
        });


        final Button pclear = (Button) this.findViewById(R.id.btnSettingsPrefClear);
        pclear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                  try {
                FileOutputStream file = SettingsActivity.this.openFileOutput("PrefSettings", Context.MODE_PRIVATE);
                ObjectOutputStream out = new ObjectOutputStream(file);
                Settings s = new Settings();
                setpref = s;
                out.writeObject(s);
                out.close();
                file.close();

                final EditText port = (EditText) SettingsActivity.this.findViewById(R.id.txtPort);
                int portnum = s.port;
                port.setText(String.valueOf(portnum));

                final EditText time = (EditText) SettingsActivity.this.findViewById(R.id.txtTime);
                int timev = s.timeout;
                time.setText(String.valueOf(timev));

                final Switch cell = (Switch) SettingsActivity.this.findViewById(R.id.cboCell);
                if (s.cell)
                    cell.setEnabled(true);
                else
                    cell.setEnabled(false);

                final Switch wifi = (Switch) SettingsActivity.this.findViewById(R.id.cboWfi);
                if (s.wifi)
                    wifi.setEnabled(true);
                else
                    wifi.setEnabled(false);

                final Switch scan = (Switch) SettingsActivity.this.findViewById(R.id.cboScan);
                if (s.cell)
                    scan.setEnabled(true);
                else
                    scan.setEnabled(false);

                Toast.makeText(SettingsActivity.this, "Data Reset", Toast.LENGTH_LONG).show();
                   } catch (Exception ex) {
                      Toast.makeText(SettingsActivity.this, "Error Resetting", Toast.LENGTH_LONG).show();
                }
            }

        });

        final Button clear = (Button) this.findViewById(R.id.BtnSettingsNetClear);
        clear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            ArrayList <NetObj> c = new ArrayList <NetObj>();
                MainActivity.netObjList = new ArrayList <NetObj>();
                final NetObj loop = new NetObj("Loopback", "127.0,0.1", "80");
                MainActivity.netObjList.add(loop);
                try {
                    FileOutputStream file = SettingsActivity.this.openFileOutput("NetworkObjects", Context.MODE_PRIVATE);
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    for (int x = 0; x < MainActivity.netObjList.size(); x++)
                        out.writeObject(MainActivity.netObjList.get(x));
                    out.close();
                    file.close();
                   Toast.makeText(SettingsActivity.this, "Network Reset", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(SettingsActivity.this, "Error Resetting", Toast.LENGTH_LONG).show();
                }

            }
        });
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
        int id = item.getItemId();
        String title = item.getTitle().toString();

        if(title == "Test Network") {
            Intent intent = new Intent(SettingsActivity.this, TestActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Configure Networks") {
            Intent intent = new Intent(SettingsActivity.this, ConfigureActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Device Info") {
            Intent intent = new Intent(SettingsActivity.this,DeviceActivity.class);
            this.startActivity(intent);
         }
        else if(title == "Settings") {
            Intent intent = new Intent(SettingsActivity.this,SettingsActivity.class);
            this.startActivity(intent);
        }
        else if(title == "About") {
            Intent intent = new Intent(SettingsActivity.this,AboutActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Menu") {
            Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
