package edu.sfsu.thecocktaildb.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import edu.sfsu.thecocktaildb.R;
import edu.sfsu.thecocktaildb.Model.CocktailModel;
import edu.sfsu.thecocktaildb.ViewModel.SingletonViewModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    // MutableLiveData<CocktailModel> model;
    ArrayList<CocktailModel> model;

    /**
     * Tell the adapter what sort of data it should work with.
     */
    public RecyclerViewAdapter() {
        SingletonViewModel singletonViewModel = SingletonViewModel.getInstance();
        model = singletonViewModel.getModel();
        Log.i("log", "RecyclerViewAdapter() initialized");
    }
    /**
     * You cannot directly call a .size() method on a LiveData object itself because it's an
     * observable container, not a data structure like a list.
     */

    //---------------------------------------------------------------------------------- ViewHolder
    // Define ViewHolder as in inner class.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // private final CardView cardView;
        private final View view;
        public TextView idDrink;
        // public TextView strDrink;

        // public ViewHolder(@NonNull CardView view) {
        public ViewHolder(@NonNull View v) {
            super(v); // super is used to invoke the parent class constructor.
            view = v;
            // idDrink = view.findViewById(R.id.tv_IdDrink);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View cv = (View)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new ViewHolder(cv);
    }

    // We need to tell the adapter how many data items there are.
    @Override
    public int getItemCount() {
        // Log.i("log", "model.size() -> " + model.size());
        return model.size();
        // return 51;
    }

    @Override
    // public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    public void onBindViewHolder(@NonNull ViewHolder vh, @SuppressLint("RecyclerView") int position) {
        View itemView = vh.itemView;
        CocktailModel item = model.get(position);

        vh.idDrink.setText(String.format("%s", item.getIdDrink()));

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }
}