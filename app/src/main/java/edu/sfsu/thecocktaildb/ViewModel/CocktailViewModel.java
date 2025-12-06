package edu.sfsu.thecocktaildb.ViewModel;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import edu.sfsu.thecocktaildb.Model.CocktailModel;

// The ViewModel that's part of the ui package use singleton's, not observables.

// This is a singleton example, this doesn't have to be used for MVVM.
// Notice: FuelStationViewModel does not extend the ViewModel
// you couldn't instantiate it anyway, because the constructor is private.
public class CocktailViewModel {

    // Instantiate an instance of itself.
    private static final CocktailViewModel obj = new CocktailViewModel();

    // Use this method to get an instance of this object.
    private final ArrayList<CocktailModel> model;

    public static CocktailViewModel getInstance() {
        return obj;
    }

    // Constructor is Private...only this class 'CocktailViewModel' can instantiate it.
    // Prevent subclassing by declaring the constructor as private.
    private CocktailViewModel() {
        Log.v("Cock", "CocktailViewModel-> Constructor");
        model = new ArrayList<>();
    }

    // Anyone can get a list of models.
    public ArrayList<CocktailModel> getData() {
        return model;
    }

    public MutableLiveData<ArrayList<CocktailModel>> get() {
        return new MutableLiveData<>();
    }
}

/*
From Android Documentation
Create LiveData objects

LiveData is a wrapper that can be used with any data, including objects that implement Collections, such as List.
A LiveData object is usually stored with in a viewModel object and is accessed via a getter method, as demonstrated in the following example:
*/

/**
 * The partner who wants (or needs) sex more is in a subservient position to the partner who satisfies said urges.
 * The one who satisfies sexual urges will be in the driver's seat and will govern the relationship, in the same way that
 * the person who loves the least will always have more power over the one who loves the most.
 * As long as someone needs something of value, the supplier of that valuable something can charge pretty much whatever they want,
 * because they are calling the shots. Men need to practice self discipline, self control, and self restraint,
 * otherwise they will be at the mercy of women and will continue to be dominated by them.
 */