package com.example.wchoi.pcbuildplanner;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;



public class Home extends ListActivity {

    private List<Build> builds;

    private void refreshPostList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Builds");
        setProgressBarIndeterminateVisibility(true);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                setProgressBarIndeterminateVisibility(false);
                if(e == null) {

                    builds.clear();
                    for(ParseObject build : parseObjects) {
                        builds.add(new Build(build.getObjectId(), build.getString("title")));
                    }
                    ((ArrayAdapter<Build>)getListAdapter()).notifyDataSetChanged();
                }
                else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_home);

        /* ParseObject testObject = new ParseObject("Builds");
        testObject.put("foo", "bar");
        testObject.saveInBackground(); */

        builds = new ArrayList<Build>();
        ArrayAdapter<Build> adapter = new ArrayAdapter<Build>(this, R.layout.list_item_layout, builds);
        setListAdapter(adapter);

        refreshPostList();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Build build = builds.get(position);
        Intent intent = new Intent(this, EditBuild.class);
        intent.putExtra("build", build);
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
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("New Build");
                alert.setMessage("Enter build name:");

                final EditText input = new EditText(this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = String.valueOf(input.getText());
                        if(name.length() != 0) {
                            final ParseObject post = new ParseObject("Builds");
                            post.put("title", name);
                            post.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e != null) {
                                        Toast.makeText(getApplicationContext(), "Failed to Save to Parse", Toast.LENGTH_SHORT).show();
                                        Log.d(getClass().getSimpleName(), "User update error: " + e);
                                    }
                                }
                            });

                            refreshPostList();
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alert.show();

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
