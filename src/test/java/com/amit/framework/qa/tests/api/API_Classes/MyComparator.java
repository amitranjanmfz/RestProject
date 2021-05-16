package com.amit.framework.qa.tests.api.API_Classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

public class MyComparator  implements Comparator<JSONObject> {

    String comparingField;

    public MyComparator(String comparingField) {
        this.comparingField = comparingField;
    }

    public int compare(JSONObject o1, JSONObject o2)  {
        String v1 = null;
        String v3 = null;
        try {
            v1 = o1.get(comparingField).toString();
            v3 = o2.get(comparingField).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return v1.compareTo(v3);
    }
}
