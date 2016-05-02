package us.zxcv.rmorris4.zxcvnetworktool;

import android.app.Activity;
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

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class EditNetworkActivity extends Activity {
    //track which network object
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_network);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = Integer.parseInt(extras.getString("id"));
        }

        final TextView name = (TextView) this.findViewById(R.id.txtName);
        final TextView address = (TextView) this.findViewById(R.id.txtAddress);
        final TextView port = (TextView) this.findViewById(R.id.txtPort);

        name.setText(MainActivity.netObjList.get(id).name);
        address.setText(MainActivity.netObjList.get(id).ip);
       port.setText(MainActivity.netObjList.get(id).port);

        Button btnEdit = (Button) this.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.netObjList.get(id).name = name.getText().toString();
                MainActivity.netObjList.get(id).ip = address.getText().toString();
                MainActivity.netObjList.get(id).port = port.getText().toString();

                try {
                    FileOutputStream file = EditNetworkActivity.this.openFileOutput("NetworkObjects", Context.MODE_PRIVATE);
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    for (int x = 0; x < MainActivity.netObjList.size(); x++)
                        out.writeObject(MainActivity.netObjList.get(x));
                    out.close();
                    file.close();

                } catch (Exception ex) {
                    Toast.makeText(EditNetworkActivity.this, "Error saving changes", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(EditNetworkActivity.this, ConfigureActivity.class);
                EditNetworkActivity.this.startActivity(intent);
            }
        });

        Button btnCancel = (Button) this.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditNetworkActivity.this, ConfigureActivity.class);
                EditNetworkActivity.this.startActivity(intent);
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
            Intent intent = new Intent(EditNetworkActivity.this, TestActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Configure Networks") {
            Intent intent = new Intent(EditNetworkActivity.this, ConfigureActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Device Info") {
            Intent intent = new Intent(EditNetworkActivity.this,DeviceActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Settings") {
            Intent intent = new Intent(EditNetworkActivity.this,SettingsActivity.class);
            this.startActivity(intent);
        }
        else if(title == "About") {
            Intent intent = new Intent(EditNetworkActivity.this,AboutActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Menu") {
            Intent intent = new Intent(EditNetworkActivity.this,MainActivity.class);
            this.startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
