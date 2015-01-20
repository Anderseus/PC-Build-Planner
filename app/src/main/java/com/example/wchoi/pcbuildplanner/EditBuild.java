package com.example.wchoi.pcbuildplanner;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class EditBuild extends ListActivity {

    private String buildId;
    private String buildTitle;
    private List<Part> parts;
    private TextView buildName;

    private void refreshPostList() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_edit_build);

        Intent intent = this.getIntent();
        buildName = (TextView)findViewById(R.id.textView);

        parts = new ArrayList<Part>();
        ArrayAdapter<Part> adapter = new ArrayAdapter<Part>(this, R.layout.list_item_layout, parts);
        setListAdapter(adapter);

        if(intent.getExtras() != null) {
            buildId = intent.getStringExtra("id");
            buildTitle = intent.getStringExtra("title");
            buildName.setText(buildTitle);
            parts.add((Part)intent.getSerializableExtra("cpu"));
            parts.add((Part)intent.getSerializableExtra("mobo"));
            parts.add((Part)intent.getSerializableExtra("ram"));
            parts.add((Part)intent.getSerializableExtra("gpu"));
            parts.add((Part)intent.getSerializableExtra("psu"));
            parts.add((Part)intent.getSerializableExtra("storage"));
            parts.add((Part)intent.getSerializableExtra("tower"));
            parts.add((Part)intent.getSerializableExtra("odd"));
            parts.add((Part)intent.getSerializableExtra("cpu_cooler"));
            parts.add((Part)intent.getSerializableExtra("others"));
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, EditPart.class);
        intent.putExtra("category", parts.get(position).label);
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
