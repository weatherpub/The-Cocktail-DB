package edu.sfsu.thecocktaildb.DataStructures.Heap;

import java.util.HashMap;

public class UrlHeap {
    private HashMap<String, String> url = new HashMap<>();
    private String value;

    public UrlHeap(String value) {
        this.value = value;
    }

    public void setValue(String key, String value) {
        url.put(key, value);
    }

    public String getValue(String item) {
        return url.get(item);
    }
}