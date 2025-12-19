package edu.sfsu.thecocktaildb.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> model;

    public NotificationsViewModel() {
        model = new MutableLiveData<>();
        model.setValue("I only sleep with white women");
    }

    public LiveData<String> getText() {
        return model;
    }
}