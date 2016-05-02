package us.zxcv.rmorris4.zxcvnetworktool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ConfigureActivity extends AppCompatActivity {


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        String[] arr = new String[MainActivity.netObjList.size()];
        for (int i = 0; i < MainActivity.netObjList.size(); i++) {
            arr[i] = MainActivity.netObjList.get(i).name;
        }

        EditNetworkList adapter = new EditNetworkList(ConfigureActivity.this, MainActivity.netObjList, arr);
        //TestNetworkList(TestActivity.this, web, web2, imageId);
        list = (ListView) findViewById(R.id.lstNetwork);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ConfigureActivity.this, EditNetworkActivity.class);
                intent.putExtra("id", String.valueOf(position));
                ConfigureActivity.this.startActivity(intent);

                //Toast.makeText(ConfigureActivity.this, MainActivity.netObjList.get(position).name + " ( " + MainActivity.netObjList.get(position).name + ") is " + sresult, Toast.LENGTH_SHORT).show();

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
            Intent intent = new Intent(ConfigureActivity.this, TestActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Configure Networks") {
            Intent intent = new Intent(ConfigureActivity.this, ConfigureActivity.class);
            this.startActivity(intent);
        }else if (title == "Device Info") {
            Intent intent = new Intent(ConfigureActivity.this, DeviceActivity.class);
            this.startActivity(intent);
        } else if (title == "Settings") {
            Intent intent = new Intent(ConfigureActivity.this, SettingsActivity.class);
            this.startActivity(intent);
        } else if (title == "About") {
            Intent intent = new Intent(ConfigureActivity.this, AboutActivity.class);
            this.startActivity(intent);
        } else if (title == "Menu") {
            Intent intent = new Intent(ConfigureActivity.this, MainActivity.class);
            this.startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
