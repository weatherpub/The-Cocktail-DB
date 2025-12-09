package edu.sfsu.thecocktaildb.ui.home;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import edu.sfsu.thecocktaildb.Model.CocktailModel;

/*
public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Top News -> fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
*/
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  HomeViewModel
public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<CocktailModel>> liveData;

    public HomeViewModel() {
        liveData = new MutableLiveData<>();
        Log.v("log", "HomeViewModel is initialized");
    }

    public LiveData<ArrayList<CocktailModel>> getLiveData() {
        return liveData;
    }
}