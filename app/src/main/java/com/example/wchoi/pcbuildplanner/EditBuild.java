package com.example.wchoi.pcbuildplanner;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class EditBuild extends ListActivity {

    private Build build;
    private ArrayList<Part> parts;
    private TextView buildName;

    private void refreshPostList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Builds");
        setProgressBarIndeterminateVisibility(true);

        query.getInBackground(build.getId(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_edit_build);

        Intent intent = this.getIntent();
        buildName = (TextView)findViewById(R.id.textView);

        parts = new ArrayList<Part>();
        BuildAdapter adapter = new BuildAdapter(this, R.layout.list_item_layout_edit_build, parts);
        setListAdapter(adapter);

        if(intent.getExtras() != null) {
            build = (Build)intent.getSerializableExtra("build");
            buildName.setText(build.getTitle());
            parts.add(new Part("CPU", build.getCpu()));
            parts.add(new Part("Motherboard", build.getMobo()));
            parts.add(new Part("Memory", build.getRam()));
            parts.add(new Part("Graphics", build.getGpu()));
            parts.add(new Part("Power", build.getPsu()));
            parts.add(new Part("Storage", build.getStorage()));
            parts.add(new Part("Case", build.getTower()));
            parts.add(new Part("Optical", build.getOdd()));
            parts.add(new Part("Cooler", build.getCpu_cooler()));
            parts.add(new Part("Others", build.getOthers()));
        }

        refreshPostList();
    }

    class BuildAdapter extends ArrayAdapter<Part> {

        private ArrayList<Part> parts;
        private PartViewHolder partViewHolder;

        private class PartViewHolder {
            TextView label;
            TextView product;
            TextView price;
        }

        public BuildAdapter(Context context, int resId, ArrayList<Part> parts) {
            super(context, resId, parts);
            this.parts = parts;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item_layout_edit_build, null);
                partViewHolder = new PartViewHolder();
                partViewHolder.label = (TextView)v.findViewById(R.id.textView);
                partViewHolder.product = (TextView)v.findViewById(R.id.textView2);
                partViewHolder.price = (TextView)v.findViewById(R.id.textView3);
                v.setTag(partViewHolder);
            } else partViewHolder = (PartViewHolder)v.getTag();

            Part part = parts.get(pos);

            if (part != null) {
                partViewHolder.label.setText(part.getLabel());
                partViewHolder.product.setText(part.getProduct());
                partViewHolder.price.setText("$" + part.getPrice());
            }
            return v;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, EditPart.class);
        intent.putExtra("category", parts.get(position).label);
        intent.putExtra("build", build);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_build, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.action_refresh: {
                refreshPostList();
                break;
            }
            case R.id.action_settings: {
                // Do something when user selects Settings from Action Bar overlay
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
