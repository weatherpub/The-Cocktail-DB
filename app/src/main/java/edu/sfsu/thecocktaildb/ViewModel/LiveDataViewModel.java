package edu.sfsu.thecocktaildb.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.sfsu.thecocktaildb.Model.CocktailModel;

public class LiveDataViewModel {

    // Instantiate an instance of itself.
    private static final LiveDataViewModel obj = new LiveDataViewModel();

    // Use this method to get an instance of this object.
    //private final MutableLiveData<ArrayList<CocktailModel>> model;
    private final ArrayList<CocktailModel> model;

    public static LiveDataViewModel getInstance() {
        return obj;
    }

    private MutableLiveData<ArrayList<CocktailModel>> current;
    // Anyone can get a list of models.
    public MutableLiveData<ArrayList<CocktailModel>> newMutableLiveData() {
        if(current == null) {
            current = new MutableLiveData<>();
        }
        return current;
    }

    // Constructor is Private...only this class 'FuelStationViewModel' can instantiate it.
    private LiveDataViewModel() {
        Log.v("fs", "LiveDataViewModel -> Constructor");
        model = new ArrayList<>();
        Log.v("fs", "LiveDataViewModel -> Instantiated! (After model = new ArrayList<>())");
    }

    public ArrayList<CocktailModel> getData() {
        // return new ArrayList<>();
        return model;
    }
}
/**
 * The partner who wants (or needs) sex more is in a subservient position to the partner who satisfies said urges.
 * The one who satisfies sexual urges will be in the driver's seat and will govern the relationship, in the same way that
 * the person who loves the least will always have more power over the one who loves the most.
 * As long as someone needs something of value, the supplier of that valuable something can charge pretty much whatever they want,
 * because they are calling the shots. Men need to practice self discipline, self control, and self restraint,
 * otherwise they will be at the mercy of women and will continue to be dominated by them.
 */