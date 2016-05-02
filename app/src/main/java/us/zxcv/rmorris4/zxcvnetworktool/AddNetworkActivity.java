package us.zxcv.rmorris4.zxcvnetworktool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class AddNetworkActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_network);

        final TextView name = (TextView) this.findViewById(R.id.txtName);
        final TextView address = (TextView) this.findViewById(R.id.txtAddress);
        final TextView port = (TextView) this.findViewById(R.id.txtPort);
        Button btnAdd = (Button) this.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetObj n = new NetObj(name.getText().toString(), address.getText().toString(), port.getText().toString());
                MainActivity.netObjList.add(n);

                try {
                    FileOutputStream file = AddNetworkActivity.this.openFileOutput("NetworkObjects", Context.MODE_PRIVATE);
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    for (int x = 0; x < MainActivity.netObjList.size(); x++)
                        out.writeObject(MainActivity.netObjList.get(x));
                    out.close();
                    file.close();

                } catch (Exception ex) {
                    Toast.makeText(AddNetworkActivity.this, "Error saving list", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(AddNetworkActivity.this, TestActivity.class);
                AddNetworkActivity.this.startActivity(intent);
            }
        });

        Button btnCancel = (Button) this.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNetworkActivity.this, TestActivity.class);
                AddNetworkActivity.this.startActivity(intent);
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

        if (title == "Test Network") {
            Intent intent = new Intent(AddNetworkActivity.this, TestActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Configure Networks") {
            Intent intent = new Intent(AddNetworkActivity.this, ConfigureActivity.class);
            this.startActivity(intent);
        }else if (title == "Device Info") {
            Intent intent = new Intent(AddNetworkActivity.this, DeviceActivity.class);
            this.startActivity(intent);
        } else if (title == "Settings") {
            Intent intent = new Intent(AddNetworkActivity.this, SettingsActivity.class);
            this.startActivity(intent);
        } else if (title == "About") {
            Intent intent = new Intent(AddNetworkActivity.this, AboutActivity.class);
            this.startActivity(intent);
        } else if (title == "Menu") {
            Intent intent = new Intent(AddNetworkActivity.this, MainActivity.class);
            this.startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
