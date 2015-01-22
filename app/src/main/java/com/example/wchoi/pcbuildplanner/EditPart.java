package com.example.wchoi.pcbuildplanner;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class EditPart extends ListActivity {

    TextView category;
    EditText editText;
    Button button;
    public List<String> products;
    public List<String> prices;
    public ArrayList<Part2> parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_edit_part);

        Intent intent = this.getIntent();
        category = (TextView) findViewById(R.id.textView);
        category.setText(intent.getStringExtra("category"));

        products = new ArrayList<String>();
        prices = new ArrayList<String>();
        parts = new ArrayList<Part2>();
        PartAdapter adapter = new PartAdapter(this, R.layout.list_item_layout_edit_part, parts);
        setListAdapter(adapter);

        editText = (EditText) findViewById(R.id.extractEditText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keywords = editText.getText().toString();
                new RetrieveFeedTask(EditPart.this).execute(new String[]{
                        "http://open.api.ebay.com/shopping?callname=FindPopularItems&responseencoding=XML&appid=TJHSST78e-e81b-4852-9bae-1f4018a870f&siteid=0&QueryKeywords="
                                + keywords
                                + ":&version=713"
                });
                Log.d("", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                setProgressBarIndeterminateVisibility(true);
            }
        });


    }

    class PartAdapter extends ArrayAdapter<Part2> {

        private ArrayList<Part2> parts;
        private PartViewHolder partViewHolder;

        private class PartViewHolder {
            TextView product;
            TextView price;
        }

        public PartAdapter(Context context, int resId, ArrayList<Part2> parts) {
            super(context, resId, parts);
            this.parts = parts;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item_layout_edit_part, null);
                partViewHolder = new PartViewHolder();
                partViewHolder.product = (TextView)v.findViewById(R.id.textView);
                partViewHolder.price = (TextView)v.findViewById(R.id.textView2);
                v.setTag(partViewHolder);
            } else partViewHolder = (PartViewHolder)v.getTag();

            Part2 part = parts.get(pos);

            if (part != null) {
                partViewHolder.product.setText(part.getProduct());
                partViewHolder.price.setText(part.getPrice());
            }

            return v;
        }
    }

    public void update() {
        ((ArrayAdapter<String>)getListAdapter()).notifyDataSetChanged();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        finish();
    }
}

class RetrieveFeedTask extends AsyncTask<String, Void, String> {

    private Exception exception;
    public List<String> titleArray = new ArrayList<String>();
    public List<String> priceArray = new ArrayList<String>();
    private final Context activityName;

    public RetrieveFeedTask(Context context) {
        activityName = context;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));

            NodeList start = doc.getElementsByTagName("Item");

            for(int iItem = 0; iItem < start.getLength(); iItem++) {
                Node itemArray = start.item(iItem);
                NodeList item = itemArray.getChildNodes();
                for (int i = 0; i < item.getLength(); i++) {
                    Node data = item.item(i);
                    if(data.getNodeName().equalsIgnoreCase("Title")) {
                        titleArray.add(data.getTextContent());
                    }
                    if(data.getNodeName().equalsIgnoreCase("ConvertedCurrentPrice")) {
                        priceArray.add(data.getTextContent());
                    }
                }
            }
        }
        catch (Exception e) {
            Log.d("", "XML Parsing Exception = " + e);
        }
        return "";
    }

    @Override
    protected void onPostExecute(String feed) {
        super.onPostExecute(feed);
        EditPart activity = (EditPart)activityName;
        Log.d("", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        //activity.products = titleArray;
        activity.products.clear();
        activity.products.addAll(titleArray);
        activity.prices.clear();
        activity.prices.addAll(priceArray);
        activity.parts.clear();
        for(int i = 0; i < activity.products.size(); i++) {
            String a = activity.products.get(i);
            String b = "$" + activity.prices.get(i);
            Part2 part = new Part2(a, b);
            activity.parts.add(part);
        }
        activity.update();
        activity.setProgressBarIndeterminateVisibility(false);
        Log.d("", "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
    }
}