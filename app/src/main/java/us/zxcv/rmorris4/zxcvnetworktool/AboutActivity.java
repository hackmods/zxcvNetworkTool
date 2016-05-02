package us.zxcv.rmorris4.zxcvnetworktool;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final Button b1 = (Button) this.findViewById(R.id.btnAboutLinkdIn);
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ca.linkedin.com/in/RyanJamesMorris"));
                startActivity(browserIntent);
            }
        });

        final Button b2 = (Button) this.findViewById(R.id.btnAboutTwitter);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/hackmods"));
                startActivity(browserIntent);
            }
        });

        final Button b3 = (Button) this.findViewById(R.id.btnAboutWebsite);
        b3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://zxcv.us/"));
                startActivity(browserIntent);
            }
        });

        final Button b4 = (Button) this.findViewById(R.id.btnAboutEmail);
        b4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "rmorris4@ncstudents.niagaracollege.ca", null));
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL,"rmorris4@ncstudents.niagaracollege.ca");
            intent.putExtra(Intent.EXTRA_TEXT," Insert Email Here");
            Intent mailer = Intent.createChooser(intent, null);

            startActivity(mailer);
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
            Intent intent = new Intent(AboutActivity.this, TestActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Configure Networks") {
            Intent intent = new Intent(AboutActivity.this, ConfigureActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Device Info") {
            Intent intent = new Intent(AboutActivity.this,DeviceActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Settings") {
            Intent intent = new Intent(AboutActivity.this,SettingsActivity.class);
            this.startActivity(intent);
        }
        else if(title == "About") {
            Intent intent = new Intent(AboutActivity.this,AboutActivity.class);
            this.startActivity(intent);
        }
        else if(title == "Menu") {
            Intent intent = new Intent(AboutActivity.this,MainActivity.class);
            this.startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
}
}
