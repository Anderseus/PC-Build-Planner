package com.example.wchoi.pcbuildplanner;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;



public class Home extends ListActivity {

    private List<Build> builds;

    private void refreshPostList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_home);

        builds = new ArrayList<Build>();
        builds.add(new Build("0", "Build 1"));
        ArrayAdapter<Build> adapter = new ArrayAdapter<Build>(this, R.layout.list_item_layout, builds);
        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Build build = builds.get(position);
        Intent intent = new Intent(this, EditBuild.class);
        intent.putExtra("id", build.getId());
        intent.putExtra("title", build.getTitle());
        intent.putExtra("cpu", build.getCpu());
        intent.putExtra("mobo", build.getMobo());
        intent.putExtra("gpu", build.getGpu());
        intent.putExtra("psu", build.getPsu());
        intent.putExtra("storage", build.getStorage());
        intent.putExtra("tower", build.getTower());
        intent.putExtra("ram", build.getRam());
        intent.putExtra("odd", build.getOdd());
        intent.putExtra("cpu_cooler", build.getCpu_cooler());
        intent.putExtra("others", build.getOthers());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.action_new: {
                Intent intent = new Intent(this, EditBuild.class);
                startActivity(intent);
                break;
            }
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
