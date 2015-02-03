package com.example.wchoi.pcbuildplanner;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 2015wchoi on 1/15/2015.
 */
public class Part implements Serializable {

    String label;
    ArrayList<String> content;

    public Part(String label, ArrayList<String> content) {
        this.label = label;
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public String getProduct() {
        if(content != null) {
            return content.get(0);
        }
        return "Part not selected";
    }
    public String getPrice() {
        if(content != null) {
            return content.get(1);
        }
        return "0";
    }
}
