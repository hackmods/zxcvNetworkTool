package us.zxcv.rmorris4.zxcvnetworktool;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class resultActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String[] arr = new String[MainActivity.netObjList.size()];
        for (int i = 0; i < MainActivity.netObjList.size(); i++)
        {
            arr[i] = MainActivity.netObjList.get(i).name;
        }

        TestNetworkList adapter = new TestNetworkList( resultActivity.this, MainActivity.netObjList, arr);
        //TestNetworkList(TestActivity.this, web, web2, imageId);
        list=(ListView)findViewById(R.id.lstResult);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(resultActivity.this, "You Clicked at " + MainActivity.netObjList.get(position).name, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add("Menu");
        menu.add("Test Network");
        menu.add("Results");
        menu.add("Settings");
        menu.add("About");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String title = item.getTitle().toString();

        if(title == "Test Network") {
            Intent intent = new Intent(resultActivity.this, TestActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Results") {
            Intent intent = new Intent(resultActivity.this,resultActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Settings") {
            Intent intent = new Intent(resultActivity.this,SettingsActivity.class);
            this.startActivity(intent);
        }
        else if(title == "About") {
            Intent intent = new Intent(resultActivity.this,AboutActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Menu") {
            Intent intent = new Intent(resultActivity.this,MainActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
