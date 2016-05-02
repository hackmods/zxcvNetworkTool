package us.zxcv.rmorris4.zxcvnetworktool;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Hackmods on 11/12/2015.
 */
public class TestNetworkList  extends ArrayAdapter<String> {
    //the lesson I used
    //http://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html
    private final Activity context;
    private final String[] web;

    public TestNetworkList(Activity context,
                           ArrayList<NetObj> info, String[] web) {
        super(context, R.layout.list_check, web);
        this.context = context;
        this.web = web;
       // this.imageId = "1";
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
       // View rowView= inflater.inflate(R.layout.list_check, null, true);
        View rowView= inflater.inflate(R.layout.list_network, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView txtAddress = (TextView) rowView.findViewById(R.id.address);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(MainActivity.netObjList.get(position).name);
        txtAddress.setText(MainActivity.netObjList.get(position).ip);

       imageView.setImageResource(R.drawable.circle_blue);
        return rowView;
    }
}