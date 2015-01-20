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

    @Override
    public String toString() {
        if(content == null) {
            return label;
        }
        else {
            return label + ": " + content.get(1);
        }
    }
}
