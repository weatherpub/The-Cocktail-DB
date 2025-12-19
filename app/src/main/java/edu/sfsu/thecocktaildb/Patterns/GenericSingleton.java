package edu.sfsu.thecocktaildb.Patterns;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import edu.sfsu.thecocktaildb.Model.ArtistModel;

public class GenericSingleton {
    private static final GenericSingleton obj = new GenericSingleton();

    private final ArrayList<ArtistModel> model;
    private final MutableLiveData<ArrayList<ArtistModel>> mutableLiveData;

    public static GenericSingleton getInstance() {
        return obj;
    }

    public GenericSingleton() {
        model = new ArrayList<>();
        mutableLiveData = new MutableLiveData<>();
    }

    public ArrayList<ArtistModel> getModel() {
        return model;
    }

    public MutableLiveData<ArrayList<ArtistModel>> getMutableLiveData() {
        Log.i("log", "MutableLiveData<ArrayList<ArtistModel>> has been initialized.");
        return mutableLiveData;
    }
}