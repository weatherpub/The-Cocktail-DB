package edu.sfsu.thecocktaildb.ViewModel;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import edu.sfsu.thecocktaildb.Model.CocktailModel;

public class SingletonViewModel {
    private static final SingletonViewModel obj = new SingletonViewModel();

    private final ArrayList<CocktailModel> model;

    public static SingletonViewModel getInstance() {
        return obj;
    }

    private SingletonViewModel() {
        model = new ArrayList<>();
    }

    public ArrayList<CocktailModel> getModel() {
        return model;
    }

    public MutableLiveData<CocktailModel> newMutableLiveData() {
        return new MutableLiveData<>();
    }
}