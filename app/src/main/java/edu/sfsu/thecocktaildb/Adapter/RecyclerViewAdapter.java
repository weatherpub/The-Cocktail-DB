package edu.sfsu.thecocktaildb.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

    ArrayList<CocktailModel> model;
    /**
     * Tell the adapter what sort of data it should work with.
     */
    public RecyclerViewAdapter() {
        SingletonViewModel singletonViewModel = SingletonViewModel.getInstance();
        model = singletonViewModel.getData();
    }

    // Define ViewHolder as in inner class.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // public TextView idDrink;
        public TextView strDrink;

        public ViewHolder(@NonNull View view) {
            super(view); // super is used to invoke the parent class constructor.
            // idDrink = view.findViewById(R.id.text_IdDrink);
            strDrink = view.findViewById(R.id.text_strDrink);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    // We need to tell the adapter how many data items there are.
    @Override
    public int getItemCount() {
        Log.i("log", "model.size() -> " + model.size());
        return model.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {
        View itemView = holder.itemView;
        CocktailModel item = model.get(position);

        // holder.idDrink.setText(String.format("%s", item.getIdDrink()));
        holder.strDrink.setText(String.format("%s", item.getIdDrink()));

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